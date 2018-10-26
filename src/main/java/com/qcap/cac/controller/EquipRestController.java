package com.qcap.cac.controller;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.*;
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
     * @Description: 获取可用设备类型列表
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
    @ApiOperation(value="获取可用设备类型列表",notes="获取可用设备类型列表",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams listUseEquip(EquipListReq equipListReq){
        List<EquipListResp> list = this.equipRestSrv.getEquipList(equipListReq);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }

    /**
     *
     * @Description: 获取可用设备列表
     *
     *
     * @MethodName: listUnrevertEquip
     * @Parameters: [employeeCode]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/25 18:42
     */
    @PostMapping("/listUnrevertEquip")
    @ApiOperation(value="获取待归还设备列表",notes="获取待归还设备列表",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams listUnrevertEquip(String employeeCode){
        List<ListUnrevertEquipResp> list = this.equipRestSrv.getUnrevertEquipList(employeeCode);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }

    /**
     *
     * @Description: 获取设备状态
     *
     *
     * @MethodName: getEquipStatus
     * @Parameters: [equipNo]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/25 18:42
     */
    @PostMapping("/getEquipStatus")
    @ApiOperation(value="获取设备状态",notes="获取设备状态",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams getEquipStatus(String equipNo){
        GetEquipStatusResp esr = this.equipRestSrv.getEquipStatus(equipNo);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", esr);
    }

    /**
     *
     * @Description: 使用设备时修改设备状态
     *
     *
     * @MethodName: updateStopEquipStatus
     * @Parameters: [updateStopEquipStatusReq]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/25 18:42
     */
    @PostMapping("/updateStopEquipStatus")
    @ApiOperation(value="使用设备时修改设备状态",notes="使用设备时修改设备状态",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams updateStopEquipStatus(UpdateStopEquipStatusReq updateStopEquipStatusReq){
        return this.equipRestSrv.updateStopEquipStatus(updateStopEquipStatusReq);
    }

    /**
     *
     * @Description: 归还设备时修改设备状态
     *
     *
     * @MethodName: updateUsingEquipStatus
     * @Parameters: [updateUsingEquipStatusReq]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/25 18:42
     */
    @PostMapping("/updateUsingEquipStatus")
    @ApiOperation(value="归还设备时修改设备状态",notes="归还设备时修改设备状态",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams updateUsingEquipStatus(UpdateUsingEquipStatusReq updateUsingEquipStatusReq){
        this.equipRestSrv.updateUsingEquipStatus(updateUsingEquipStatusReq);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", null);
    }
}
