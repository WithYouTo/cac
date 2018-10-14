package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipChargeSearchParam;
import com.qcap.cac.dto.EquipPlanSearchParam;
import com.qcap.cac.service.EquipPlanSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipPlan")
public class EquipPlanController {

    @Autowired
    private EquipPlanSrv equipPlanSrv;

    @ResponseBody
    @RequestMapping(value = "/listEquipPlan", method = RequestMethod.POST)
    public Object listEquipPlan(@Valid EquipPlanSearchParam equipPlanSearchParam){
        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> list = this.equipPlanSrv.listEquipPlan(equipPlanSearchParam);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;

        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), pageList);
    }

    @ResponseBody
    @RequestMapping(value = "/listPartsPlanByEquipId", method = RequestMethod.POST)
    public Object listPartsPlanByEquipId(String equipId){
        new PageFactory<Map<String, Object>>().defaultPage();
        List<Map<String, Object>> list = this.equipPlanSrv.listPartsPlanByEquipId(equipId);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;

        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), pageList);
    }
}
