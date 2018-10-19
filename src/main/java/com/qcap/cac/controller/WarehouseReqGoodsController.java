package com.qcap.cac.controller;


import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.service.WarehouseReqDetailService;
import com.qcap.cac.service.WarehouseRequService;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出库查询
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseReqGoods")
public class WarehouseReqGoodsController {


    @Autowired
    private WarehouseReqDetailService warehouseReqDetailService;

    @Autowired
    private WarehouseRequService warehouseRequService;

    /**
     * 领用查询（已出库）
     */
    @ResponseBody
    @RequestMapping(value = "/requestedList", method = RequestMethod.POST)
    public PageResParams getRequestedGoodsList(WarehouseReqDto warehouseReqDto) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> list =  this.warehouseReqDetailService.getRequestedList(warehouseReqDto);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
    }


    /**
     * 领用申请（查询主表）
     */
    @ResponseBody
    @RequestMapping(value = "/requlist", method = RequestMethod.POST)
    public PageResParams getReqList(WarehouseReqDto warehouseReqDto) {

        new PageFactory<Map<String, Object>>().defaultPage();


        List<Map<String,String>> list =  this.warehouseRequService.getRequList(warehouseReqDto);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", pageInfo.getTotal(), list);
    }


    /**
     * 领用申请（根据领用单查询明细）
     */
    @ResponseBody
    @RequestMapping(value = "/reqDetailList", method = RequestMethod.POST)
    public PageResParams getReqDetailList(String warehouseRequId) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String,Object>> list =  this.warehouseReqDetailService.getReqDetailList(warehouseRequId);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", pageInfo.getTotal(), list);
    }


    /**
     * 新增领用单
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addRequ(TbWarehouseRequ warehouseRequ) {
       if(null == warehouseRequ){
           ResParams.newInstance(CoreConstant.FAIL_CODE,"领用单参数为空",null);
       }

       warehouseRequ.setRequBatchNo(UUIDUtils.getBatchNo());
       warehouseRequ.setRequStatus("INIT");
       warehouseRequ.setCreateEmp("SYS");
       this.warehouseRequService.save(warehouseRequ);
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.ADD_SUCCESS,null);
    }


    /**
     * 修改领用单
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateReq(TbWarehouseRequ warehouseRequ) {
        try {
            this.warehouseRequService.updateById(warehouseRequ);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.EDIT_SUCCESS,null);
    }

    /**
     * 提交领用单
     */
    @ResponseBody
    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public Object commitRequ(TbWarehouseRequ warehouseRequ) {
        try {
            this.warehouseRequService.commitRequ(warehouseRequ);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.EDIT_SUCCESS,null);
    }

    /**
     * 删除领用单
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteReq(String warehouseRequId) {
        try {
            this.warehouseRequService.delete(warehouseRequId);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.DELETE_SUCCESS,null);
    }


    /**
     * 新增领用明细
     */
    @ResponseBody
    @RequestMapping(value = "/addDetail", method = RequestMethod.POST)
    public Object addReqDetail(TbWarehouseReqdetail warehouseReqdetail) {
        if(null == warehouseReqdetail){
            ResParams.newInstance(CoreConstant.FAIL_CODE,"领用明细参数为空",null);
        }

        Map<String,String> map = new HashMap<>();
        String warehouseRequId = this.warehouseReqDetailService.insertReqDetail(warehouseReqdetail);
        map.put("warehouseRequId",warehouseRequId);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.ADD_SUCCESS,map);
    }


    /**
     * 修改领用明细
     */
    @ResponseBody
    @RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
    public Object updateReqDetail(TbWarehouseReqdetail warehouseReqdetail) {

        this.warehouseReqDetailService.updateById(warehouseReqdetail);

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.EDIT_SUCCESS,null);
    }

    /**
     * 删除领用明细
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetail", method = RequestMethod.POST)
    public Object deleteReqDetail(String warehouseReqdetailId) {

        this.warehouseReqDetailService.removeById(warehouseReqdetailId);

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.DELETE_SUCCESS,null);
    }




}
