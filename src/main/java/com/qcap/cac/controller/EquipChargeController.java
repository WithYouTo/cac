package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.EquipChargeSearchParam;
import com.qcap.cac.service.EquipChargeSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipCharge")
public class EquipChargeController{

    @Autowired
    private EquipChargeSrv equipChargeSrv;


    @ResponseBody
    @RequestMapping(value = "/listEquipCharge", method = RequestMethod.POST)
    public Object listEquipCharge(EquipChargeSearchParam equipChargeSearchParam){
        List<Map> list = this.equipChargeSrv.listEquipCharge(equipChargeSearchParam);
        PageInfo pageInfo = new PageInfo(list);
//        Page pageList = (Page) list;
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/getChargeTotalTimeByEquipId", method = RequestMethod.POST)
    public Object getChargeTotalTimeByEquipId(String equipId){
        String totalTime = this.equipChargeSrv.getChargeTotalTimeByEquipId(equipId);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", totalTime);
    }
}
