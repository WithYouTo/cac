package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AreaDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.service.AreaSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 区域管理
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/area")
public class AreaController {


    @Autowired
    private AreaSrv areaSrv;


    /**
     * 查询区域树
     */
    @ResponseBody
    @RequestMapping(value = "/initTree", method = RequestMethod.POST)
    public ResParams initTree() {
        List<Map> list = areaSrv.initTree();
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
    }


    /**
     * 选择节点查询管辖区域
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams getAreaList(IPage<Map<String, Object>> page, String pAreaCode) {
        try {
            this.areaSrv.getAreaList(page,pAreaCode);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 查询区域信息
     */
    @ResponseBody
    @RequestMapping(value = "/areaInfo", method = RequestMethod.POST)
    public ResParams areaInfo(@Valid  String areaId) {
        TbArea area = this.areaSrv.getById(areaId);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, area);
    }

    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResParams insertArea(@Valid AreaDto areaDto) {
        TbArea area = new TbArea();
        try {
            area = this.areaSrv.insertArea(areaDto);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, area);
    }



    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResParams updateArea(@Valid  TbArea area) {
        try {
            area =  this.areaSrv.updateArea(area);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, area);
    }


}
