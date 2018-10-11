package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipChargeSearchParam;
import com.qcap.cac.dto.EquipMaintSearchParam;
import com.qcap.cac.service.EquipChargeSrv;
import com.qcap.cac.service.EquipMiantSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipMaint")
public class EquipMaintController {

    @Autowired
    private EquipMiantSrv equipMiantSrv;


    @ResponseBody
    @RequestMapping(value = "/listEquipCharge", method = RequestMethod.POST)
    public Object listEquipCharge(EquipMaintSearchParam equipMaintSearchParam){
        new PageFactory<Map<String, Object>>().defaultPage();
        List<Map<String, Object>> list = this.equipMiantSrv.listEquipMaint(equipMaintSearchParam);

        PageInfo pageInfo = new PageInfo(list);
        for(Map<String, Object> map:list){
            String status = map.get("status").toString();
            CommonConstant.EQUIP_CHARGE_STATUS.get(status);
            map.put("statusName", CommonConstant.EQUIP_CHARGE_STATUS.get(status));
        }
        Page pageList = (Page) list;
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(),pageList);
    }
}
