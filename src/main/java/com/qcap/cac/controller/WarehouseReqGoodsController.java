package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.service.WarehouseReqDetailService;
import com.qcap.cac.service.WarehouseRequService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.AppUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 领用申请和领用查询
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
    public PageResParams getRequestedGoodsList(IPage<Map<String, Object>> page,@Valid  WarehouseReqDto warehouseReqDto) {
        try {
            this.warehouseReqDetailService.getRequestedList(page,warehouseReqDto);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 领用申请（查询主表）
     */
    @ResponseBody
    @RequestMapping(value = "/requlist", method = RequestMethod.POST)
    public PageResParams getReqList(IPage<Map<String,String>> page, @Valid  WarehouseReqDto warehouseReqDto) {
        try {
            this.warehouseRequService.getRequList(page,warehouseReqDto);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 领用申请（根据领用单查询明细）
     */
    @ResponseBody
    @RequestMapping(value = "/reqDetailList", method = RequestMethod.POST)
    public PageResParams getReqDetailList(IPage<Map<String, Object>> page,String warehouseRequId) {
        try {
            this.warehouseReqDetailService.getReqDetailList(page,warehouseRequId);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 新增领用单
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addRequ(@Valid  TbWarehouseRequ warehouseRequ) {
        //获取当前登录人
        String employeeCode = AppUtils.getLoginUserAccount();
        if(StringUtils.isEmpty(employeeCode)){
            return ResParams.newInstance(CoreConstant.FAIL_CODE,CoreConstant.EXCEL_ILLEGAL_USER_MSG,null);
        }

        try {
           this.warehouseRequService.insertWarehouseRequ(warehouseRequ,employeeCode);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.ADD_SUCCESS,null);
    }


    /**
     * 修改领用单
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateReq(@Valid TbWarehouseRequ warehouseRequ) {
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
    public Object commitRequ(@Valid TbWarehouseRequ warehouseRequ) {
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
     * 物品列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsList", method = RequestMethod.POST)
    public Object goodsList(@Valid  String storeroomId) {
        //返回结果
        Map<String,Object> map =new HashMap<>();
        //选择储藏室的物品
        if(StringUtils.isEmpty(storeroomId)){
            return ResParams.newInstance(CoreConstant.FAIL_CODE,"请先选择储藏室",null);
        }
        try {
            List<Map<String,String>> list = this.warehouseReqDetailService.GoodsNoAppList(storeroomId);
            map.put("goodsList",list);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CommonCodeConstant.SUCCESS_QUERY_DESC,map);
    }


    /**
     * 新增领用明细
     */
    @ResponseBody
    @RequestMapping(value = "/addDetail", method = RequestMethod.POST)
    public Object addReqDetail(@Valid  TbWarehouseReqdetail warehouseReqdetail) {
        //返回结果
        Map<String,String> map =new HashMap<>();
        try {
            String warehouseRequId = this.warehouseReqDetailService.insertReqDetail(warehouseReqdetail);
            map.put("warehouseRequId",warehouseRequId);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.ADD_SUCCESS,map);
    }


    /**
     * 修改领用明细
     */
    @ResponseBody
    @RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
    public Object updateReqDetail(TbWarehouseReqdetail warehouseReqdetail) {
        try {
            this.warehouseReqDetailService.updateById(warehouseReqdetail);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.EDIT_SUCCESS,null);
    }

    /**
     * 删除领用明细
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetail", method = RequestMethod.POST)
    public Object deleteReqDetail(String warehouseReqdetailId) {
        try {
            this.warehouseReqDetailService.removeById(warehouseReqdetailId);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(),null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.DELETE_SUCCESS,null);
    }



}
