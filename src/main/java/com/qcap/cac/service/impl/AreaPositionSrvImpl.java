package com.qcap.cac.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dao.AreaPositionMapper;
import com.qcap.cac.dto.AreaPositionDto;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.cac.service.AreaPositionSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        return areaMapper.initTree();
    }

    @Override
    public List<TbAreaPosition> getAreaPositionList(AreaPositionDto areaPositionDto) {
       QueryWrapper<TbAreaPosition> wrapper = new QueryWrapper<>();

       if(StringUtils.isNotEmpty(areaPositionDto.getPositionName())){
           wrapper.like("POSITION_NAME","%" + areaPositionDto.getPositionName() + "%");
       }

        if(StringUtils.isNotEmpty(areaPositionDto.getAreaName())){
            wrapper.like("AREA_NAME","%" + areaPositionDto.getAreaName() + "%");
        }

        return this.areaPositionMapper.selectList(wrapper);
    }

    @Override
    public Integer insertAreaPosition(TbAreaPosition areaPosition) throws Exception{

        String positionCode = UUIDUtils.getPositionCode();
        //根据岗位编码生成二维码
        String positionUrl = getQrCodeUrlByPositionCode(positionCode);
        areaPosition.setPositionCode(positionCode);
        areaPosition.setPositionUrl(positionUrl);
        areaPosition.setPositionId(UUIDUtils.getUUID());
        areaPosition.setCreateDate(new Date());
        areaPosition.setCreateEmp("SYS");
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
    public Integer updateAreaPosition(TbAreaPosition areaPosition) {
        return this.areaPositionMapper.updateById(areaPosition);
    }

    @Override
    public Integer deleteAreaPosition(String positionId) {
        return this.areaPositionMapper.deleteById(positionId);
    }
}
