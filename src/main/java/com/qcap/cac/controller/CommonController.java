package com.qcap.cac.controller;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.service.CommonSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
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
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", CommonConstant.EQUIP_CHARGE_STATUS);
    }
}
