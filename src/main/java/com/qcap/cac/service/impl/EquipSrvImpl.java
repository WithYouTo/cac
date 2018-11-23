package com.qcap.cac.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipPartsMapper;
import com.qcap.cac.dao.EquipPlanMapper;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;
import com.qcap.cac.entity.TbEquip;
import com.qcap.cac.entity.TbEquipParts;
import com.qcap.cac.entity.TbEquipPlan;
import com.qcap.cac.service.EquipSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.JpushTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.AppUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Transactional
public class EquipSrvImpl implements EquipSrv {

    @Resource
    private EquipMapper equipMapper;

    @Resource
    private EquipPartsMapper equipPartsMapper;

    @Resource
    private EquipPlanMapper equipPlanMapper;

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public Map<String,String> insertParts(@Valid PartsInsertDto partsInsertParam) {
        String employeeNo = AppUtils.getLoginUser().getAccount();
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

        EntityTools.setCreateEmpAndTime(parts);
        this.equipPartsMapper.insert(parts);
        return map;
    }

    @Override
    public void listPartsByEquipId(IPage<Map<String, Object>> page, String equipId) {
        List<Map<String, Object>> list = this.equipPartsMapper.listPartsByEquipId(page,equipId);
        page.setRecords(list);
    }

    @Override
    public void insertEquip(@Valid EquipInsertDto equipInsertDto){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //1、新建设备对象将入参转为设备对象
        TbEquip equip = new TbEquip();
        BeanUtils.copyProperties(equipInsertDto,equip);
        // 判断是否有设备Id,没有则生成UUID
        if(StringUtils.isEmpty(equip.getEquipId())){
            equip.setEquipId(UUIDUtils.getUUID());
        }

        try {
            //2、根据传入的设备类型生成设备编号
            String equipType = equip.getEquipType();
            String equipNo = getEquipNoByEquipType(equipInsertDto.getProgramCode(),equipType);
            //3、通过设备编码生成二维码，上传到文件服务器，并返回路径
            String url = getQrCodeUrlByEquipNo(equipNo);
            //4、通过维保周期和启用时间生成下次维保时间
            Date startUseTime = format.parse(equipInsertDto.getStartUseTime());
            Date buyTime = format.parse(equipInsertDto.getBuyTime());
            Date nextPlanTime = getNewPlanDate(startUseTime, equip.getMaintCycle());
            //5、通过下次维保时间和维保提醒提前时间生成下次维保提醒时间
            String noticeDateStr = format.format(getNoticeDate(nextPlanTime,equip.getAdvanceTime()));
            //6、重组equip对象
            equip.setNoticeDate(noticeDateStr);
            equip.setStartUseTime(startUseTime);
            equip.setBuyTime(buyTime);
            equip.setNextMaintTime(nextPlanTime);
            equip.setEquipCodeUrl(url);
            equip.setEquipNo(equipNo);
            equip.setEquipState(CommonConstant.EQUIP_STATUS_NORMAL);
            EntityTools.setCreateEmpAndTime(equip);
            //在设备表中新增一条equip数据
            this.equipMapper.insert(equip);
            // 8、根据设备信息生成设备维保计划
            insertMaintPlan(equip);
            // 9、更新配件信息，并生成维保计划
            updatePartsInfo(equip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEquip(@Valid EquipInsertDto equipInsertDto) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date nextPlanTime = null;

        TbEquip equip = new TbEquip();
        BeanUtils.copyProperties(equipInsertDto,equip);

        TbEquipPlan equipPlan = this.equipPlanMapper.selectEquipPlanByEquipId(equip.getEquipId());
        if(equipPlan != null){
            if(equipPlan.getLatestMaintTime() != null){
                nextPlanTime = getNewPlanDate(equipPlan.getLatestMaintTime(),equip.getMaintCycle());
            }else{
                nextPlanTime = getNewPlanDate(equipPlan.getStartUseTime(),equip.getMaintCycle());
            }
            String noticeDateStr = format.format(getNoticeDate(nextPlanTime,equip.getAdvanceTime()));
            equipPlan.setNoticeDate(noticeDateStr);
            EntityTools.setUpdateEmpAndTime(equipPlan);
            equipPlan.setNextMaintTime(nextPlanTime);
            this.equipPlanMapper.updateNextMaintTime(equipPlan);
        }else{
            TbEquip tempEquip = this.equipMapper.selectEquipByEquipId(equip.getEquipId());
            nextPlanTime = getNewPlanDate(tempEquip.getNextMaintTime(),equip.getMaintCycle());
        }

        String noticeDateStr = format.format(getNoticeDate(nextPlanTime,equip.getAdvanceTime()));
        //重组equip对象
        equip.setNextMaintTime(nextPlanTime);
        equip.setNoticeDate(noticeDateStr);
        EntityTools.setUpdateEmpAndTime(equip);
        this.equipMapper.updateEquip(equip);
    }

    @Override
    public void updatePartsAndMaintTime(@Valid PartsInsertDto partsInsertDto) {
        Date nextPlanTime = null;
        TbEquipParts parts = new TbEquipParts();
        BeanUtils.copyProperties(partsInsertDto,parts);

        EntityTools.setUpdateEmpAndTime(parts);
        EntityTools.setCreateEmpAndTime(parts);

        //根据配件Id，获取配件维保计划，判断维保计划中最近维保时间是否为空，若为空，取开始使用时间，通过维保周期获取配件下次维保时间
        TbEquipPlan partsPlan = this.equipPlanMapper.selectPartsPlanByPartsId(parts.getPartsId());
        if(partsPlan.getLatestMaintTime() != null){
            nextPlanTime = getNewPlanDate(partsPlan.getLatestMaintTime(),parts.getMaintCycle());
        }else{
            nextPlanTime = getNewPlanDate(partsPlan.getStartUseTime(),parts.getMaintCycle());
        }

        partsPlan.setMaintCycle(parts.getMaintCycle());

        EntityTools.setUpdateEmpAndTime(partsPlan);
        partsPlan.setNextMaintTime(nextPlanTime);
        parts.setNextMaintTime(nextPlanTime);
        partsPlan.setPartsModel(parts.getPartsModel());
        partsPlan.setPartsName(parts.getPartsName());
        //修改配件信息
        this.equipPartsMapper.updatePartsByPartsId(parts);
        //修改配件维保记录
        this.equipPlanMapper.updateNextMaintTime(partsPlan);
    }

    @Override
    public void insertPartsAndMaintTime(@Valid PartsInsertDto partsInsertDto) {
        TbEquipParts parts = new TbEquipParts();
        BeanUtils.copyProperties(partsInsertDto,parts);
        parts.setPartsId(UUIDUtils.getUUID());
        StringBuffer partsNo = new StringBuffer();
        TbEquip equip = this.equipMapper.selectEquipByEquipId(parts.getEquipId());
        //根据所属设备的开始使用时间获取下次维保时间
        Date nextPlanTime = getNewPlanDate(equip.getStartUseTime(),parts.getMaintCycle());
        parts.setNextMaintTime(nextPlanTime);
        //生成配件编号
        int num = this.equipPartsMapper.getMaxPartsNumByEquipId(equip.getEquipId());
        String str = String.format("%04d", num);
        partsNo.append(equip.getEquipNo()).append(str);
        parts.setPartsNo(partsNo.toString());
        parts.setStartUseTime(equip.getStartUseTime());
        parts.setEquipNo(equip.getEquipNo());
        
        EntityTools.setCreateEmpAndTime(parts);
        //新增配件信息
        this.equipPartsMapper.insert(parts);
        insertMaintPlan(parts);
    }

    @Override
    public void deleteEquipByEquipId(String equipId) {
        String[] ids = equipId.split(",");
        for (int i = 0; i < ids.length; i++) {
            this.equipMapper.deleteEquipByEquipId(ids[i]);
            this.equipPlanMapper.deletePlanByEquipId(ids[i]);
        }
    }

    @Override
    public void getEquipOperateRecordByEquipId(IPage<Map<String, Object>> page,String equipId) {
        List<Map<String, Object>> list = this.equipMapper.getEquipOperateRecordByEquipId(page,equipId);
        for(Map<String,Object> map :list){
            String equipState = map.get("status").toString();
            CommonConstant.EQUIP_OPERATE_STATUS.get(equipState);
            map.put("statusName", CommonConstant.EQUIP_OPERATE_STATUS.get(equipState));
        }
        page.setRecords(list);
    }

    @Override
    public void tempEquipNoticeJob() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String noticeDate = format.format(date);
        List<TbEquip> list = this.equipMapper.selectEquipByNoticeDate(noticeDate);
        for (TbEquip equip : list){
            String employeeNo = equip.getResponseNo();
            String equipNo = equip.getEquipNo();
            String maintDate = format.format(equip.getNextMaintTime());
            JpushTools.pushSingle(employeeNo,"编号为"+equipNo+"的设备将于"+maintDate+"进行整机维保！");
        }
    }

    @Override
    public void updateEquipFileUrls(@Valid EquipInsertDto equipInsertDto) {
        TbEquip equip = new TbEquip();
        BeanUtils.copyProperties(equipInsertDto,equip);
        EntityTools.setUpdateEmpAndTime(equip);
        this.equipMapper.updateEquip(equip);
    }

    @Override
    public void listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto) {
        String perfix=RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        List<Map<String, Object>> list = this.equipMapper.listEquip(page,equipSearchDto);
        for(Map<String,Object> map :list){
            String equipState = map.get("equipState").toString();
            String equipWorkState = map.get("equipWorkState").toString();

            CommonConstant.EQUIP_STATUS.get(equipState);
            map.put("statusName", CommonConstant.EQUIP_STATUS.get(equipState));
            map.put("workStatusName", CommonConstant.EQUIP_WORK_STATUS.get(equipWorkState));

            String imgUrl =  Objects.toString(map.get("imgUrl"));
            map.put("imgUrl",perfix+imgUrl);
            map.put("perfix",perfix);
        }
        page.setRecords(list);
    }

    @Override
    public Map<String, String> updateParts(@Valid PartsInsertDto partsInsertParam) {
        TbEquipParts parts = new TbEquipParts();
        Map<String,String> map = new HashMap<String,String>();

        BeanUtils.copyProperties(partsInsertParam,parts);
        map.put("equipId",partsInsertParam.getEquipId());

        EntityTools.setUpdateEmpAndTime(parts);
        this.equipPartsMapper.updatePartsByPartsId(parts);
        return map;
    }

    @Override
    public void deletePartsByPartsId(String partsId) {
        this.equipPartsMapper.deletePartsByPartsId(partsId);
        this.equipPlanMapper.deletePlanByPartsId(partsId);
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
    private String getEquipNoByEquipType(String program,String equipType) {
        int num = this.equipMapper.getMaxEquipNumByEquipType(equipType,program);
        String equipNo = String.format("%03d", num);
        StringBuffer str = new StringBuffer();
        str.append(program).append(equipType).append(equipNo);
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
        String dir = RedisTools.getCommonConfig("CAC_AREA_SAVE_PATH");
        String host = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        File directory = new File(dir);

        File file = QrCodeUtil.generate(equipNo, 120, 120, directory);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(),multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()),null);
        return  host+storePath.getFullPath();
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


    /**
     *
     * @Description: 获取下次维保提前提醒时间
     *
     *
     * @MethodName: getNoticeDate
     * @Parameters: [time, maintCycle] 
     * @ReturnType: java.util.Date
     *
     * @author huangxiang
     * @date 2018/11/5 10:30
     */
    private Date getNoticeDate(Date time, String advanceTime){
        Calendar c = Calendar.getInstance();
        //设置时间
        c.setTime(time);
        Integer days = Integer.parseInt(advanceTime);
        //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)
        c.add(Calendar.DATE, -days);
        //结果
        Date date = c.getTime();
        return date;
    }

    /**
     *
     * @Description: 更新配件信息，并生成配件维保计划
     *
     *
     * @MethodName: updatePartsInfo
     * @Parameters: [equip] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/17 9:20
     */
    private void updatePartsInfo(TbEquip equip) {
        //获取所属当前设备的所有配件信息
        List<TbEquipParts> partsList = this.equipPartsMapper.listPartsEntityByEquipId(equip.getEquipId());
        String equipNo = equip.getEquipNo();
        Date nextPlanTime = null;
        Integer count = CommonCodeConstant.SYS_START_PARTS_NO;
        for (TbEquipParts part: partsList){
            //设置设备下次维保时间
            nextPlanTime  = getNewPlanDate(equip.getStartUseTime(),part.getMaintCycle());
            part.setNextMaintTime(nextPlanTime);
            //通过设备编号设置配件编号
            StringBuffer partsNo = new StringBuffer();
            String str = String.format("%04d", count);

            partsNo.append(equipNo).append(str);
            part.setPartsNo(partsNo.toString());
            part.setStartUseTime(equip.getStartUseTime());
            part.setEquipNo(equipNo);
            part.setEquipType(equip.getEquipType());
            count++;
            this.equipPartsMapper.updatePartsNoAndNextMaintTimeByPartsId(part);
            insertMaintPlan(part);
        }
    }

    /**
     *
     * @Description: 生成设备维保记录
     *
     *
     * @MethodName: insertMaintPlan
     * @Parameters: [equip] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/17 9:49
     */
    private void insertMaintPlan(TbEquip equip) {
        TbEquipPlan equipPlan = new TbEquipPlan();
        BeanUtils.copyProperties(equip,equipPlan);
        equipPlan.setPlanId(UUIDUtils.getUUID());
        this.equipPlanMapper.insert(equipPlan);
    }

    /**
     *
     * @Description: 生成配件维保计划
     *
     *
     * @MethodName: insertMaintPlan 
     * @Parameters: [equipParts] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/17 9:49
     */
    private void insertMaintPlan(TbEquipParts equipParts) {
        TbEquipPlan equipPlan = new TbEquipPlan();
        BeanUtils.copyProperties(equipParts,equipPlan);
        equipPlan.setPlanId(UUIDUtils.getUUID());
        this.equipPlanMapper.insert(equipPlan);
    }

}
