package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;
import com.qcap.cac.service.EquipSrv;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equip")
public class EquipController {

    @Resource
    private EquipSrv equipSrv;


    @ResponseBody
    @RequestMapping(value = "/listEquip", method = RequestMethod.POST)
    public Object listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto){
        //todo 获取设备列表
        this.equipSrv.listEquip(page, equipSearchDto);
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());

    }

    @ResponseBody
    @RequestMapping(value = "/listPartsByEquipId", method = RequestMethod.POST)
    public Object listPartsByEquipId(IPage<Map<String, Object>> page, String equipId){
        //todo 根据设备Id获取所属配件列表
        this.equipSrv.listPartsByEquipId(page, equipId);
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }

    @ResponseBody
    @RequestMapping(value = "/insertEquip", method = RequestMethod.POST)
    public Object insertEquip(@Valid EquipInsertDto equipInsertDto){
        //todo 新增设备
        Map<String,String> result = this.equipSrv.insertEquip(equipInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, result);

    }

    @ResponseBody
    @RequestMapping(value = "/insertParts", method = RequestMethod.POST)
    public Object insertParts(@Valid PartsInsertDto equipInsertParam){
        // 新增配件
        Map<String,String> result = this.equipSrv.insertParts(equipInsertParam);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, result);
    }
}
