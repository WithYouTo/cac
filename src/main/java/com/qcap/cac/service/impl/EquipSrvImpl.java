package com.qcap.cac.service.impl;

import cn.hutool.Hutool;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipPartsMapper;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;
import com.qcap.cac.entity.TbEquip;
import com.qcap.cac.entity.TbEquipParts;
import com.qcap.cac.service.EquipSrv;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.io.FileDescriptor.out;

@Service
@Transactional
public class EquipSrvImpl implements EquipSrv {

    @Resource
    private EquipMapper equipMapper;

    @Resource
    private EquipPartsMapper equipPartsMapper;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public Map<String,String> insertParts(@Valid PartsInsertDto partsInsertParam) {
        String partsId = UUIDUtils.getUUID();
        TbEquipParts parts = new TbEquipParts();
        Map<String,String> map = new HashMap<String,String>();

        BeanUtils.copyProperties(partsInsertParam,parts);


        //判断equipId是否为空，若为空重新生成
        if(("").equals(partsInsertParam.getEquipId())){
            String equipId = UUIDUtils.getUUID();
            parts.setEquipId(equipId);
            map.put("equipId",equipId);
        }

        map.put("equipId",parts.getEquipId());
        parts.setPartsId(partsId);
        parts.setCreateDate(new Date());
        parts.setUpdateDate(new Date());
        parts.setCreateEmp("sys");
        parts.setUpdateEmp("sys");

        this.equipPartsMapper.insert(parts);
        //todo 生成配件维保计划

        return map;
    }

    @Override
    public void listPartsByEquipId(IPage<Map<String, Object>> page, String equipId) {
        List<Map<String, Object>> list = this.equipPartsMapper.listPartsByEquipId(page,equipId);
        page.setRecords(list);
    }

    @Override
    public void insertEquip(@Valid EquipInsertDto equipInsertDto) throws Exception {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //1、新建设备对象将入参转为设备对象
        TbEquip equip = new TbEquip();
        BeanUtils.copyProperties(equipInsertDto,equip);
        //2、根据传入的设备类型生成设备编号
        String equipType = equip.getEquipType();
        String equipNo = getEquipNoByEquipType(equipType);
        //3、通过设备编码生成二维码，上传到文件服务器，并返回路径
        String url = getQrCodeUrlByEquipNo(equipNo);
        //4、通过维保周期和启用时间生成下次维保时间
        Date startUseTime = format.parse(equipInsertDto.getStartUseTime());
        Date buyTime = format.parse(equipInsertDto.getBuyTime());
        Date nextPlanTime = getNewPlanDate(startUseTime,equip.getMaintCycle());

        //todo 5、更新配件维保时间计划
        //todo 6、根据设备信息生成设备维保计划

        equip.setStartUseTime(startUseTime);
        equip.setBuyTime(buyTime);
        equip.setNextMaintTime(nextPlanTime);
        equip.setEquipCodeUrl(url);
        equip.setEquipNo(equipNo);
        equip.setEquipState(CommonConstant.EQUIP_STATUS_NORMAL);
        equip.setCreateDate(new Date());
        equip.setUpdateDate(new Date());
        equip.setCreateEmp("sys");
        equip.setUpdateEmp("sys");
        this.equipMapper.insert(equip);
    }



    @Override
    public void listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto) {
        List<Map<String, Object>> list = this.equipMapper.listEquip(page,equipSearchDto);
        for(Map<String,Object> map :list){
            String equipState = map.get("equipState").toString();
            CommonConstant.EQUIP_STATUS.get(equipState);
            map.put("statusName", CommonConstant.EQUIP_STATUS.get(equipState));
        }
        page.setRecords(list);
    }

    @Override
    public Map<String, String> updateParts(@Valid PartsInsertDto partsInsertParam) {
        TbEquipParts parts = new TbEquipParts();
        Map<String,String> map = new HashMap<String,String>();

        BeanUtils.copyProperties(partsInsertParam,parts);
        map.put("equipId",partsInsertParam.getEquipId());
        parts.setCreateDate(new Date());
        parts.setUpdateDate(new Date());
        parts.setCreateEmp("sys");
        parts.setUpdateEmp("sys");

        this.equipPartsMapper.updatePartsByPartsId(parts);
        return map;
    }

    @Override
    public void deletePartsByPartsId(String partsId) {
        this.equipPartsMapper.deletePartsByPartsId(partsId);
    }

    @Override
    public void deletePartsByEquipId(String equipId) {
        this.equipPartsMapper.deletePartsByEquipId(equipId);
    }

    /**
     *
     * @Description: 通过设备类型生成设备编号
     *
     *
     * @MethodName: getEquipNoByEquipType
     * @Parameters: [equipType]
     * @ReturnType: java.lang.String
     *
     * @author huangxiang
     * @date 2018/10/16 19:35
     */
    private String getEquipNoByEquipType(String equipType) {
        int num = this.equipMapper.getMaxEquipNumByEquipType(equipType);
        String equipNo = String.format("%03d", num);

        StringBuffer str = new StringBuffer();
        str.append(CommonCodeConstant.SYS_EQUIP_TYPE_PREFIX).append(equipType).append(equipNo);

        return str.toString();
    }

    /**
     *
     * @Description: 生成二维码并上传，返回路径
     *
     *
     * @MethodName: getQrCodeUrlByEquipNo
     * @Parameters: [equipNo]
     * @ReturnType: java.lang.String
     *
     * @author huangxiang
     * @date 2018/10/16 20:46
     */
    private String getQrCodeUrlByEquipNo(String equipNo) throws Exception{
        File directory = new File("D:\\qrCode.jpg");//设定为当前文件夹
//        String courseFile = directory.getCanonicalPath() ;
//        File f = new File(courseFile);

//        ApplicationHome home = new ApplicationHome(EquipSrvImpl.class);
//		File jarFile = home.getSource();

        File file = QrCodeUtil.generate(equipNo, 300, 300, directory);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(),multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()),null);
        return  storePath.getFullPath();
    }

    /**
     *
     * @Description: 获取下次维保时间
     *
     *
     * @MethodName: getNewPlanDate
     * @Parameters: [time, maintCycle]
     * @ReturnType: java.util.Date
     *
     * @author huangxiang
     * @date 2018/10/13 10:42
     */
    private Date getNewPlanDate(Date time, String maintCycle){
        Calendar c = Calendar.getInstance();
        //设置时间
        c.setTime(time);
        //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        c.add(Calendar.HOUR, Integer.parseInt(maintCycle));
        //结果
        Date date = c.getTime();
        return date;
    }
}
