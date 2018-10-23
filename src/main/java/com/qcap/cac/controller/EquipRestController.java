package com.qcap.cac.controller;

import com.qcap.cac.dto.EquipListResp;
import com.qcap.cac.service.EquipRestSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ResParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/rest/equip",headers = "api_version=v1")
public class EquipRestController {

    @Resource
    private EquipRestSrv equipRestSrv;

    /**
     *
     * @Description: 获取所有可用设备
     *
     *
     * @MethodName: getEquipList
     * @Parameters: [] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/23 15:48
     */
    @PostMapping("/getEquipList")
    public ResParams getEquipList(String employeeNo){
        List<EquipListResp> list = this.equipRestSrv.getEquipList(employeeNo);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }
}
