package com.qcap.cac.controller;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.service.CommonSrv;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 下拉框等公用方法控制器
 *
 *
 * @MethodName:
 * @Parameters:
 * @ReturnType:
 *
 * @author huangxiang
 * @date 2018/10/13 12:17
 */
@RestController
@RequestMapping("/common")
public class CommonController {


    @Autowired
    private CommonSrv commonSrv;

    /**
     *
     * @Description: 获取设备充电状态下拉框
     *
     *
     * @MethodName: initEquipChargeStatusSelect
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/13 12:20
     */
    @ResponseBody
    @RequestMapping(value = "/initEquipChargeStatusSelect", method = RequestMethod.POST)
    public Object initEquipChargeStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_CHARGE_STATUS);
    }


    /**
     *
     * @Description: 获取设备使用状态下拉框
     *
     *
     * @MethodName: initEquipUseStatusSelect
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/13 12:20
     */
    @ResponseBody
    @RequestMapping(value = "/initEquipUseStatusSelect", method = RequestMethod.POST)
    public Object initEquipUseStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_USE_STATUS);
    }

    /**
     *
     * @Description: 获取设备维修状态下拉框
     *
     *
     * @MethodName: initEquipRepairStatusSelect
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/13 12:20
     */
    @ResponseBody
    @RequestMapping(value = "/initEquipRepairStatusSelect", method = RequestMethod.POST)
    public Object initEquipRepairStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_REPAIR_STATUS);
    }

    /**
     *
     * @Description: 获取设备类型下拉框
     *
     *
     * @MethodName: initEquipTypeSelect
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/13 12:21
     */
    @ResponseBody
    @RequestMapping(value = "/initEquipTypeSelect", method = RequestMethod.POST)
    public Object initEquipTypeSelect(){
        //todo 获取设备类型下拉框
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_REPAIR_STATUS);
    }


   /**
    *
    * @Description: 根据设备类型获取设备名称下拉框,返回map为设备编号和设备名称（设备编号）
    *
    *
    * @MethodName: initEquipNameSelect
    * @Parameters: [equipType]
    * @ReturnType: java.lang.Object
    *
    * @author huangxiang
    * @date 2018/10/13 12:22
    */
    @ResponseBody
    @RequestMapping(value = "/initEquipNameSelect", method = RequestMethod.POST)
    public Object initEquipNameSelect(String equipType){
        List<Map<String,String>> list = this.commonSrv.getEquipNameByEquipType(equipType);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
    }


    /**
     *
     * @Description:根据设备编号获取配件名称下拉框
     *
     *
     * @MethodName: initPartsNameSelect
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/13 12:26
     */
    @ResponseBody
    @RequestMapping(value = "/initPartsNameSelect", method = RequestMethod.POST)
    public Object initPartsNameSelect(String equipNo){
        List<Map<String,String>> list = this.commonSrv.getPartsNameByEquipNo(equipNo);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
    }
}
