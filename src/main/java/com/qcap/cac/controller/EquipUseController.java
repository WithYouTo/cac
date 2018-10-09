package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.EquipUseSearchParam;
import com.qcap.cac.service.EquipUseSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipUse")
public class EquipUseController{

    @Autowired
    private EquipUseSrv equipUseSrv;

    @ResponseBody
    @RequestMapping(value = "/listEquipUse", method = RequestMethod.POST)
    public Object listEquipCharge(EquipUseSearchParam equipUseSearchParam){
        List<Map> list = this.equipUseSrv.listEquipUse(equipUseSearchParam);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }

    @ResponseBody
    @RequestMapping(value = "/getUseTotalTimeByEquipId", method = RequestMethod.POST)
    public Object getChargeTotalTimeByEquipId(String equipId){
        String totalTime = this.equipUseSrv.getUseTotalTimeByEquipId(equipId);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", totalTime);
    }
}
