package com.qcap.cac.rest;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.*;
import com.qcap.cac.service.EquipRestSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ResParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "APP设备接口")
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
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
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
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
    }

    /**
     *
     * @Description: 获取设备状态（扫二维码）
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
    public ResParams getEquipStatus(String equipNo,String equipType){
        return this.equipRestSrv.getEquipStatus(equipNo,equipType);
    }


    /**
     *
     * @Description: 获取设备状态(点击按钮跳转，无需扫二维码)
     *
     *
     * @MethodName: getEquipStatus
     * @Parameters: [equipNo]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/25 18:42
     */
    @PostMapping("/getEquipStatusByEquipNo")
    @ApiOperation(value="获取设备状态",notes="获取设备状态",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams getEquipStatusByEquipNo(String equipNo){
        return this.equipRestSrv.getEquipStatusByEquipNo(equipNo);
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
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC, null);
    }


    /**
     *
     * @Description: 管理员对设备进行充电，使用，停泊和损坏处理时，修改设备状态并新增对应的数据记录
     *
     *
     * @MethodName: updateEquipStatusInManagerMode
     * @Parameters: [updateUsingEquipStatusReq]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/27 12:25
     */
    @PostMapping("/updateEquipStatusInManagerMode")
    @ApiOperation(value="管理员修改设备状态",notes="管理员修改设备状态",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams updateEquipStatusInManagerMode(UpdateUsingEquipStatusReq updateUsingEquipStatusReq){
        this.equipRestSrv.updateEquipStatusInManagerMode(updateUsingEquipStatusReq);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC, null);
    }

    /**
     *
     * @Description: 管理员对损坏中的设备进行报修，停用处理，修改设备状态并新增对应的数据记录
     *
     *
     * @MethodName: updateDamageEquipStatusInManagerMode
     * @Parameters: [updateUsingEquipStatusReq]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/27 12:40
     */
    @PostMapping("/updateDamageEquipStatusInManagerMode")
    @ApiOperation(value="管理员修改损坏中的设备状态",notes="管理员修改损坏中的设备状态",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams updateDamageEquipStatusInManagerMode(UpdateUsingEquipStatusReq updateUsingEquipStatusReq){
        this.equipRestSrv.updateDamageEquipStatusInManagerMode(updateUsingEquipStatusReq);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC, null);
    }
}