package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipMaintInsertDto;
import com.qcap.cac.dto.EquipMaintSearchDto;
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
    public Object listEquipMaint(IPage<Map<String, Object>> page, @Valid EquipMaintSearchDto equipMaintSearchDto){
        this.equipMaintSrv.listEquipMaint(page,equipMaintSearchDto);
        List<Map<String, Object>> list = page.getRecords();
        for(Map<String,Object> map:list){
            String status = map.get("equipType").toString();
            CommonConstant.MAINT_TYPE.get(status);
            map.put("equipTypeName", CommonConstant.MAINT_TYPE.get(status));
        }
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(),list);
    }


    @ResponseBody
    @RequestMapping(value = "/insertEquipMaint", method = RequestMethod.POST)
    public Object insertEquipMaint(@Valid EquipMaintInsertDto equipMaintInsertDto){
        this.equipMaintSrv.insertEquipMaint(equipMaintInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, null);

    }

    @ResponseBody
    @RequestMapping(value = "/initMaintTypeSelect", method = RequestMethod.POST)
    public Object initEquipRepairStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.MAINT_TYPE);
    }
}
