package com.qcap.cac.controller;


import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.service.WarehouseReqDetailService;
import com.qcap.cac.service.WarehouseRequService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

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

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
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

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
    }


    /**
     * 新增领用单
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addRequ(@Valid  TbWarehouseRequ warehouseRequ, HttpServletRequest request) {
       if(null == warehouseRequ){
           ResParams.newInstance(CoreConstant.FAIL_CODE,"领用单参数为空",null);
       }

        String token = request.getHeader(jwtProperties.getTokenHeader());
        String userId = jwtTokenUtil.getUsernameFromToken(token);

        try {
           this.warehouseRequService.insertWarehouseRequ(warehouseRequ,userId);
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
     * 物品列表
     */
    @ResponseBody
    @RequestMapping(value = "/goodsList", method = RequestMethod.POST)
    public Object goodsList(@Valid  String storeroomId) {
        if(StringUtils.isEmpty(storeroomId)){
            ResParams.newInstance(CoreConstant.FAIL_CODE,"查询物品列表参数为空",null);
        }

        //返回主键
        Map<String,Object> map =new HashMap<>();
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
        if(null == warehouseReqdetail){
            ResParams.newInstance(CoreConstant.FAIL_CODE,"领用明细参数为空",null);
        }

        //返回主键
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
