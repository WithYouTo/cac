package com.qcap.cac.controller;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.service.CommonSrv;
import com.qcap.core.entity.TbManager;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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


    @Resource
    private CommonSrv commonSrv;

    @Resource
    private FastDFSClientWrapper dfsClient;


    /**
     *
     * @Description: 获取设备状态下拉框
     *
     *
     * @MethodName: initEquipStatus
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 20:33
     */
    @ResponseBody
    @RequestMapping(value = "/initEquipStatus", method = RequestMethod.POST)
    public Object initEquipStatus(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_STATUS);
    }

    /**
     *
     * @Description: 获取设备工作状态下拉框
     *
     *
     * @MethodName: initEquipWorkState
     * @Parameters: [] 
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/11/2 14:39
     */
    @ResponseBody
    @RequestMapping(value = "/initEquipWorkState", method = RequestMethod.POST)
    public Object initEquipWorkState(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.EQUIP_WORK_STATUS);
    }

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
        List<Map<String,String>> equipTypeList = this.commonSrv.getEquipTypeList();
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, equipTypeList);
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


    /**
     *
     * @Description: 获取领用状态
     *
     *
     * @MethodName: initWarehouseRequStatusSelect
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/13 12:20
     */
    @ResponseBody
    @RequestMapping(value = "/initWarehouseRequStatusSelect", method = RequestMethod.POST)
    public Object initWarehouseRequStatusSelect(){
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, CommonConstant.WAREHOUSE_REQ_STATUS);
    }

    /**
     *
     * @Description: 初始化证件类型下拉框
     *
     *
     * @MethodName: initCardType
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/22 15:11
     */
    @ResponseBody
    @RequestMapping(value = "/initCardType", method = RequestMethod.POST)
    public Object initCardType(){
        List<Map<String,String>> list = this.commonSrv.getListByCode(CommonCodeConstant.INIT_CARD_TYPE_SELECT);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);

    }

    /**
     *
     * @Description: 初始化性别下拉框
     *
     *
     * @MethodName: initGender
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/22 15:12
     */
    @ResponseBody
    @RequestMapping(value = "/initGender", method = RequestMethod.POST)
    public Object initGender(){
        List<Map<String,String>> list = this.commonSrv.getListByCode(CommonCodeConstant.INIT_GENDER_SELECT);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
    }

    /**
     *
     * @Description: 初始化员工状态下拉框
     *
     *
     * @MethodName: initWorkStatus
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/11/7 13:42
     */
    @ResponseBody
    @RequestMapping(value = "/initWorkStatus", method = RequestMethod.POST)
    public Object initWorkStatus(){
        List<Map<String,String>> list = this.commonSrv.getListByCode(CommonCodeConstant.INIT_WORK_STATUS);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
    }

    /**
     *
     * @Description: 初始化婚姻状况下拉框
     *
     *
     * @MethodName: initMarriageSit
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/22 15:12
     */
    @ResponseBody
    @RequestMapping(value = "/initMarriageSit", method = RequestMethod.POST)
    public Object initMarriageSit(){
        List<Map<String,String>> list = this.commonSrv.getListByCode(CommonCodeConstant.INIT_MARRIAGE_SIT_SELECT);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);

    }

    @ResponseBody
    @RequestMapping(value = "/initProgramCode", method = RequestMethod.POST)
    public Object initProgramCodes(){
        List<String> projectCodes = AppUtils.getLoginUserProjectCodes();
        List<Map<String,String>> list = this.commonSrv.getProgramCodes(projectCodes);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
    }

    /**
     *
     * @Description: 上传文件
     *
     *
     * @MethodName: upload
     * @Parameters: [file]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/16 19:08
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(MultipartFile file) throws Exception {
        String path = dfsClient.uploadFile(file);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPLOAD_DESC, path);
    }
    
    /**
     *
     * @Description: 通过文件路径删除文件
     *
     *
     * @MethodName: delete
     * @Parameters: [] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/31 9:07
     */
    @RequestMapping(value = "/deleteFile",method = RequestMethod.POST)
    public ResParams delete(String url){
        dfsClient.deleteFile(url);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_FILE_DESC, "");
    }
}
