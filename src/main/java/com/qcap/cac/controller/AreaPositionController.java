package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AreaPositionDto;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.cac.service.AreaPositionSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 岗位管理
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/areaPosition")
public class AreaPositionController {


    @Autowired
    private AreaPositionSrv areaPositionSrv;

    @Autowired
    private FastFileStorageClient storageClient;


    /**
     * 查询区域树
     */
    @ResponseBody
    @RequestMapping(value = "/initTree", method = RequestMethod.POST)
    public ResParams initTree() {
        List<Map> list = areaPositionSrv.initTree();
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
    }


    /**
     * 查询岗位信息
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams getAreaPositionList(IPage<TbAreaPosition> page,AreaPositionDto areaPositionDto) {
        try {
            this.areaPositionSrv.getAreaPositionList(page,areaPositionDto);
            List<TbAreaPosition> ls = page.getRecords();
            for(TbAreaPosition item : ls){
                String positionTypeName = areaPositionSrv.selectPositionTypeName(item.getPositionType());
                item.setPositionTypeName(positionTypeName);
            }
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResParams updateAreaPosition(@Valid TbAreaPosition areaPosition) {
        try {
            this.areaPositionSrv.updateAreaPosition(areaPosition);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }

    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResParams insertAreaPosition(@Valid TbAreaPosition areaPosition) {
        try {
            this.areaPositionSrv.insertAreaPosition(areaPosition);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, null);
    }



    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResParams deleteById(@Valid  String positionId) {
        TbAreaPosition areaPosition = this.areaPositionSrv.getById(positionId);
        if(StringUtils.isNotEmpty(areaPosition.getPositionUrl())){
            storageClient.deleteFile(areaPosition.getPositionUrl());
        }
        this.areaPositionSrv.deleteAreaPosition(positionId);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC, null);
    }


}
