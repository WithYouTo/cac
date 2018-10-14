package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseReqSearchParam;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.IWarehouseReqDetailService;
import com.qcap.cac.service.IWarehouseRequService;
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
    private IWarehouseReqDetailService warehouseReqDetailService;

    @Autowired
    private IWarehouseRequService warehouseRequService;

    /**
     * 领用查询
     */
    @ResponseBody
    @RequestMapping(value = "/requestedList", method = RequestMethod.POST)
    public PageResParams getRequestedGoodsList(WarehouseReqSearchParam warehouseReqSearchParam) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map> list =  this.warehouseReqDetailService.getRequestedList(warehouseReqSearchParam);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 查询主表领用申请
     */
    @ResponseBody
    @RequestMapping(value = "/requlist", method = RequestMethod.POST)
    public PageResParams getReqList(WarehouseReqSearchParam warehouseReqSearchParam) {

        new PageFactory<Map<String, Object>>().defaultPage();

        QueryWrapper<TbWarehouseRequ> wrapper = new QueryWrapper<>();
        List<TbWarehouseRequ> list =  this.warehouseRequService.list(wrapper);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 查询领用申请明细
     */
    @ResponseBody
    @RequestMapping(value = "/reqDetailList", method = RequestMethod.POST)
    public PageResParams getReqDetailList(WarehouseReqSearchParam warehouseReqSearchParam) {

        new PageFactory<Map<String, Object>>().defaultPage();

        QueryWrapper<TbWarehouseReqdetail> wrapper = new QueryWrapper<>();
        List<TbWarehouseReqdetail> list =  this.warehouseReqDetailService.list(wrapper);
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
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

       warehouseRequ.setWarehouseRequId(UUIDUtils.getUUID());
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

       this.warehouseRequService.updateById(warehouseRequ);

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.EDIT_SUCCESS,null);
    }

    /**
     * 删除领用单
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteReq(String warehouseRequId) {

        this.warehouseRequService.removeById(warehouseRequId);

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

        this.warehouseReqDetailService.save(warehouseReqdetail);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.ADD_SUCCESS,null);
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
