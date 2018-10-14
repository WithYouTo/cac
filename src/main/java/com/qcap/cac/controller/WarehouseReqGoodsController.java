package com.qcap.cac.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseReqSearchParam;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.IWarehouseReqDetailService;
import com.qcap.cac.service.IWarehouseRequService;
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

    /**
     * 获取领用详情
     */
    @ResponseBody
    @RequestMapping(value = "/requestedList", method = RequestMethod.POST)
    public PageResParams getRequestedGoodsList(WarehouseReqSearchParam warehouseReqSearchParam) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map> list =  this.warehouseReqDetailService.getRequestedList(warehouseReqSearchParam);

        //Page pageList = (Page) list;
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }




}
