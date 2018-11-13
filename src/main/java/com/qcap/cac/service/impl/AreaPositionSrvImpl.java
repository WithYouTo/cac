package com.qcap.cac.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dao.AreaPositionMapper;
import com.qcap.cac.dto.AreaPositionDto;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.cac.service.AreaPositionSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.AppUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Service
@Transactional
public class AreaPositionSrvImpl extends ServiceImpl<AreaPositionMapper, TbAreaPosition> implements AreaPositionSrv {

    @Resource
    private  AreaPositionMapper areaPositionMapper;

    @Resource
    private  AreaMapper areaMapper;

    @Autowired
    private FastFileStorageClient storageClient;


    @Override
    public List<Map> initTree() {
        Map<String,Object> paraMap = new HashMap();
        List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
        paraMap.put("programCodes",programCodes);
        return areaMapper.initTree(paraMap);
    }

    @Override
    public IPage<TbAreaPosition> getAreaPositionList(IPage<TbAreaPosition> page, @Valid AreaPositionDto areaPositionDto) {
       QueryWrapper<TbAreaPosition> wrapper = new QueryWrapper<>();

       if(StringUtils.isNotEmpty(areaPositionDto.getPositionName())){
           wrapper.like("POSITION_NAME","%" + areaPositionDto.getPositionName() + "%");
       }

        if(StringUtils.isNotEmpty(areaPositionDto.getAreaName())){
            wrapper.like("AREA_NAME","%" + areaPositionDto.getAreaName() + "%");
        }

        return this.areaPositionMapper.selectPage(page,wrapper);
//        for(TbAreaPosition item : list){
//            String positionTypeName = areaPositionMapper.selectPositionTypeName(item.getPositionType());
//            item.setPositionTypeName(positionTypeName);
//        }
//
//        return list;
    }

    @Override
    public Integer insertAreaPosition(TbAreaPosition areaPosition) throws Exception{

        String positionCode = UUIDUtils.getPositionCode();

        QueryWrapper<TbAreaPosition> wrapper = new QueryWrapper<>();
        //岗位编码是否重复
        wrapper.eq("POSITION_CODE", areaPosition.getPositionCode());
        if(areaPositionMapper.selectCount(wrapper) > 0){
            throw new  RuntimeException("岗位编码已经存在");
        }

        wrapper = new QueryWrapper<>();
        //岗位名称是否存在
        wrapper.eq("POSITION_NAME", areaPosition.getPositionName());
        //wrapper.eq("AREA_CODE", areaPosition.getAreaCode());
        if(areaPositionMapper.selectCount(wrapper) > 0){
            throw new  RuntimeException("岗位已经存在");
        }

        //根据岗位编码生成二维码
        String positionUrl = getQrCodeUrlByPositionCode(positionCode);
        areaPosition.setPositionCode(positionCode);
        areaPosition.setPositionUrl(positionUrl);
        areaPosition.setPositionId(UUIDUtils.getUUID());

        //项目编码
        List<String> programCodes = AppUtils.getLoginUserProjectCodes();
        programCodes.removeAll(Collections.singleton(""));
        areaPosition.setProgramCode(StringUtils.join(programCodes,","));
//        areaPosition.setCreateDate(new Date());
//        areaPosition.setCreateEmp("SYS");
        EntityTools.setCreateEmpAndTime(areaPosition);
        return this.areaPositionMapper.insert(areaPosition);
    }


    /**
     *
     * @Description: 生成二维码并上传，返回路径
     *
     *
     * @MethodName: getQrCodeUrlByPositionCode
     * @Parameters: [positionCode]
     * @ReturnType: java.lang.String
     *
     * @author huangxiang
     * @date 2018/10/16 20:46
     */
    private String getQrCodeUrlByPositionCode(String positionCode) throws Exception{
        //设定图片暂存路径
        String savePath = RedisTools.getCommonConfig("CAC_AREA_SAVE_PATH");
        String filePathPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        if(StringUtils.isEmpty(savePath)){
            throw  new RuntimeException("系统没有配置二维码的暂存图片地址");
        }

        if(StringUtils.isEmpty(filePathPrefix)){
            throw  new RuntimeException("系统没有配置文件的访问前缀地址");
        }

        File directory = new File(savePath);
        File file = QrCodeUtil.generate(positionCode, 300, 300, directory);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(),multipartFile.getSize(), FilenameUtils.getExtension(multipartFile.getOriginalFilename()),null);
        String path = filePathPrefix + storePath.getFullPath();
        return  path;
    }

    @Override
    public Integer updateAreaPosition(TbAreaPosition areaPosition) throws Exception{

        String positionId = areaPosition.getPositionId();
        if(StringUtils.isEmpty(positionId)){
            throw  new RuntimeException("岗位修改主键为空");
        }

        //原始岗位记录
        TbAreaPosition area = this.areaPositionMapper.selectById(positionId);
        if(null == area){
            throw  new RuntimeException("根据主键没有查询到岗位信息");
        }

        if(StringUtils.isEmpty(area.getPositionCode())){
            throw  new RuntimeException("修改岗位时，岗位编码为空");
        }

        if(StringUtils.isEmpty(area.getPositionUrl())){
            //根据岗位编码生成二维码
            String positionUrl = getQrCodeUrlByPositionCode(area.getPositionCode());
            areaPosition.setPositionUrl(positionUrl);
        }

        if(StringUtils.isEmpty(area.getPositionCode())){
            //项目编码
            List<String> programCodes = AppUtils.getLoginUserProjectCodes();
            programCodes.removeAll(Collections.singleton(""));
            areaPosition.setProgramCode(StringUtils.join(programCodes,","));
        }

        return this.areaPositionMapper.updateById(areaPosition);
    }

    @Override
    public Integer deleteAreaPosition(String positionId) {
        if(StringUtils.isEmpty(positionId)){
           throw new RuntimeException("未选择记录");
        }
        TbAreaPosition position = this.areaPositionMapper.selectById(positionId);
        if(null == position){
            throw new RuntimeException("根据主键没有查询到记录");
        }
        //当月是否有排班
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("month", DateUtil.format(new Date(),"yyyyMM"));
        paramMap.put("positionCode",position.getPositionCode());
        if(areaPositionMapper.checkTaskByPosition(paramMap) > 0){
            throw new RuntimeException("该岗位当月有排班，无法删除");
        }
        //该岗位上有任务未完成
        if(areaPositionMapper.checkUndoStatusByPosition(position.getPositionCode()) > 0){
            throw new RuntimeException("该岗位上有任务未完成，无法删除");
        }

        return this.areaPositionMapper.deleteById(positionId);
    }

    @Override
    public String selectPositionTypeName(String positionType) {
        return this.areaPositionMapper.selectPositionTypeName(positionType);
    }
}
