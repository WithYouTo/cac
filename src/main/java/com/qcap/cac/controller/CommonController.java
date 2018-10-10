package com.qcap.cac.controller;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.service.CommonSrv;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {


    @Autowired
    private CommonSrv commonSrv;

    @ResponseBody
    @RequestMapping(value = "/initEquipChargeStatusSelect", method = RequestMethod.POST)
    public Object initEquipChargeStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_CHARGE_STATUS);
    }

    @ResponseBody
    @RequestMapping(value = "/initEquipUseStatusSelect", method = RequestMethod.POST)
    public Object initEquipUseStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_USE_STATUS);
    }

    @ResponseBody
    @RequestMapping(value = "/initEquipRepairStatusSelect", method = RequestMethod.POST)
    public Object initEquipRepairStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_REPAIR_STATUS);
    }

    @ResponseBody
    @RequestMapping(value = "/initEquipTypeSelect", method = RequestMethod.POST)
    public Object initEquipTypeSelect(){
        //todo 暂为假数据，从通用代码档读取
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_REPAIR_STATUS);
    }
}
