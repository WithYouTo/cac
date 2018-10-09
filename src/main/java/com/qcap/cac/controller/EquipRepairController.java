package com.qcap.cac.controller;

import com.qcap.cac.dto.EquipRepairSearchParam;
import com.qcap.cac.service.EquipChargeSrv;
import com.qcap.cac.service.EquipRepairSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipRepair")
public class EquipRepairController{

    @Autowired
    private EquipRepairSrv equipRepairSrv;

    @ResponseBody
    @RequestMapping(value = "/listEquipRepair", method = RequestMethod.POST)
    public Object listEquipRepair(EquipRepairSearchParam equipRepairSearchParam){
        return null;
    }
}
