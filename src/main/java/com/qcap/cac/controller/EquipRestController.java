package com.qcap.cac.controller;

import com.qcap.cac.dto.EquipListResp;
import com.qcap.cac.service.EquipRestSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ResParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
    @PostMapping("/listUseEquip")
    @ApiOperation(value="获取可用设备列表",notes="获取可用设备列表",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams listUseEquip(String employeeNo){
        List<EquipListResp> list = this.equipRestSrv.getEquipList(employeeNo);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }


    @PostMapping("/listUnrevertEquip")
    @ApiOperation(value="获取可用设备列表",notes="获取可用设备列表",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams listUnrevertEquip(){
        return null;
    }

}
