package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.service.IWarehouseReqDetailService;
import com.qcap.cac.service.IWarehouseRequService;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
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
    public PageResParams getAreaList(String pAreaCode) {

        new PageFactory<Map>().defaultPage();

        List<Map> list = areaSrv.getAreaList(pAreaCode);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping(value = "/areaInfo", method = RequestMethod.POST)
    public ResParams areaInfo(@Valid  String areaId) {
        TbArea area = this.areaSrv.getById(areaId);
        //Map<String,Object> map = new HashMap();
        //map.put("area",area);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, area);
    }

    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResParams insertArea(@Valid  TbArea area) {
        area.setAreaId(UUIDUtils.getUUID());
        area.setAreaCode(UUIDUtils.getAreaCode("1"));
        area.setCreateEmp("SYS");
        this.areaSrv.save(area);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, area);
    }



    /**
     * 新增
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResParams updateArea(@Valid  TbArea area) {
        this.areaSrv.updateById(area);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, area);
    }


}
