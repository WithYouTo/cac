package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipChargeSearchParam;
import com.qcap.cac.dto.EquipMaintInsertParam;
import com.qcap.cac.dto.EquipMaintSearchParam;
import com.qcap.cac.service.EquipChargeSrv;
import com.qcap.cac.service.EquipMaintSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipMaint")
public class EquipMaintController {

    @Autowired
    private EquipMaintSrv equipMaintSrv;


    @ResponseBody
    @RequestMapping(value = "/listEquipMaint", method = RequestMethod.POST)
    public Object listEquipMaint(@Valid EquipMaintSearchParam equipMaintSearchParam){
        new PageFactory<Map<String, Object>>().defaultPage();
        List<Map<String, Object>> list = this.equipMaintSrv.listEquipMaint(equipMaintSearchParam);

        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(),pageList);
    }


    @ResponseBody
    @RequestMapping(value = "/insertEquipMaint", method = RequestMethod.POST)
    public Object insertEquipMaint(@Valid EquipMaintInsertParam equipMaintInsertParam){
        this.equipMaintSrv.insertEquipMaint(equipMaintInsertParam);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, null);
    }

    @ResponseBody
    @RequestMapping(value = "/initMaintTypeSelect", method = RequestMethod.POST)
    public Object initEquipRepairStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.MAINT_TYPE);
    }
}
