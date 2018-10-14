package com.qcap.cac.controller;

import com.qcap.cac.dto.EquipInsertParam;
import com.qcap.cac.dto.EquipSearchParam;
import com.qcap.cac.dto.PartsInsertParam;
import com.qcap.cac.service.EquipSrv;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/equip")
public class EquipController {

    @Resource
    private EquipSrv equipSrv;

    @ResponseBody
    @RequestMapping(value = "/listEquip", method = RequestMethod.POST)
    public Object listEquip(@Valid EquipSearchParam equipSearchParam){
        //todo 获取设备列表
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/listPartsByEquipId", method = RequestMethod.POST)
    public Object listPartsByEquipId(String equipId){
        //todo 根据设备Id获取所属配件列表
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/insertEquip", method = RequestMethod.POST)
    public Object insertEquip(@Valid EquipInsertParam equipInsertParam){
        //todo 新增设备
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/insertParts", method = RequestMethod.POST)
    public Object insertEquip(@Valid PartsInsertParam equipInsertParam){
        //todo 新增配件
        return null;
    }
}
