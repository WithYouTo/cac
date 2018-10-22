package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;
import com.qcap.cac.service.EquipSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equip")
public class EquipController {

    @Resource
    private EquipSrv equipSrv;


    /**
     *
     * @Description: 获取设备列表
     *
     *
     * @MethodName: listEquip
     * @Parameters: [page, equipSearchDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:38
     */
    @ResponseBody
    @RequestMapping(value = "/listEquip", method = RequestMethod.POST)
    public Object listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto){
        this.equipSrv.listEquip(page, equipSearchDto);
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());

    }

    /**
     *
     * @Description: 根据设备Id获取所属配件列表
     *
     *
     * @MethodName: listPartsByEquipId
     * @Parameters: [page, equipId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:39
     */
    @ResponseBody
    @RequestMapping(value = "/listPartsByEquipId", method = RequestMethod.POST)
    public Object listPartsByEquipId(IPage<Map<String, Object>> page, String equipId){
        this.equipSrv.listPartsByEquipId(page, equipId);
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }

    /**
     *
     * @Description: 新增设备
     *
     *
     * @MethodName: insertEquip
     * @Parameters: [equipInsertDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:39
     */
    @ResponseBody
    @RequestMapping(value = "/insertEquip", method = RequestMethod.POST)
    public Object insertEquip(HttpServletRequest request, @Valid EquipInsertDto equipInsertDto){
//        String userName = RedisTools.getUserName(request);
//        this.equipSrv.insertEquip(equipInsertDto,userName);
        this.equipSrv.insertEquip(equipInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, "");

    }

    /**
     *
     * @Description: 新增配件
     *
     *
     * @MethodName: insertParts
     * @Parameters: [partsInsertParam]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:39
     */
    @ResponseBody
    @RequestMapping(value = "/insertParts", method = RequestMethod.POST)
    public Object insertParts(@Valid PartsInsertDto partsInsertParam){
        // 新增配件
        Map<String,String> result = this.equipSrv.insertParts(partsInsertParam);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, result);
    }

    /**
     *
     * @Description: 修改设备页面新增配件信息
     *
     *
     * @MethodName: insertPartsInEditPage
     * @Parameters: [partsInsertDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 15:23
     */
    @ResponseBody
    @RequestMapping(value = "/insertPartsInEditPage", method = RequestMethod.POST)
    public Object insertPartsInEditPage(@Valid PartsInsertDto partsInsertDto){
        // 修改设备
        this.equipSrv.insertPartsAndMaintTime(partsInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }

    /**
     *
     * @Description: 修改配件
     *
     *
     * @MethodName: updateParts
     * @Parameters: [partsInsertParam]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:39
     */
    @ResponseBody
    @RequestMapping(value = "/updateParts", method = RequestMethod.POST)
    public Object updateParts(@Valid PartsInsertDto partsInsertDto){
        // 修改配件
        this.equipSrv.updateParts(partsInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }


    /**
     *
     * @Description: 修改设备
     *
     *
     * @MethodName: updateEquip
     * @Parameters: [partsInsertParam]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:41
     */
    @ResponseBody
    @RequestMapping(value = "/updateEquip", method = RequestMethod.POST)
    public Object updateEquip(@Valid EquipInsertDto equipInsertDto){
        // 修改设备
        this.equipSrv.updateEquip(equipInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }



    /**
     *
     * @Description: 修改设备页面修改配件信息
     *
     *
     * @MethodName: updatePartsInEditPage
     * @Parameters: [partsInsertDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 15:18
     */
    @ResponseBody
    @RequestMapping(value = "/updatePartsInEditPage", method = RequestMethod.POST)
    public Object updatePartsInEditPage(@Valid PartsInsertDto partsInsertDto){
        // 修改设备
        this.equipSrv.updatePartsAndMaintTime(partsInsertDto);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }

    /**
     *
     * @Description: 通过配件Id删除配件
     *
     *
     * @MethodName: deletePartsByPartsId
     * @Parameters: [partsId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:39
     */
    @ResponseBody
    @RequestMapping(value = "/deletePartsByPartsId", method = RequestMethod.POST)
    public Object deletePartsByPartsId(String partsId){
        this.equipSrv.deletePartsByPartsId(partsId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC, null);
    }

    /**
     *
     * @Description: 通过设备Id删除设备
     *
     *
     * @MethodName: deletePartsByEquipId
     * @Parameters: [equipId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 13:40
     */
    @ResponseBody
    @RequestMapping(value = "/deletePartsByEquipId", method = RequestMethod.POST)
    public Object deletePartsByEquipId(String equipId){
        this.equipSrv.deletePartsByEquipId(equipId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC, null);
    }

    /**
     *
     * @Description: 根据设备ID逻辑删除设备信息
     *
     *
     * @MethodName: deleteEquipByEquipId
     * @Parameters: [equipId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/17 19:47
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEquipByEquipId", method = RequestMethod.POST)
    public Object deleteEquipByEquipId(String equipId){
        this.equipSrv.deleteEquipByEquipId(equipId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC, null);
    }
}
