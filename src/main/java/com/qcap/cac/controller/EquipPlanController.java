package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.EquipPlanSearchDto;
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
    public Object listEquipPlan(IPage<Map<String, Object>> page,@Valid EquipPlanSearchDto equipPlanSearchDto){
        this.equipPlanSrv.listEquipPlan(page,equipPlanSearchDto);
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }

    @ResponseBody
    @RequestMapping(value = "/listPartsPlanByEquipId", method = RequestMethod.POST)
    public Object listPartsPlanByEquipId(IPage<Map<String, Object>> page,String equipId){
        this.equipPlanSrv.listPartsPlanByEquipId(page,equipId);
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }
}
