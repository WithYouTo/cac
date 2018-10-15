package com.qcap.cac.controller;


import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.IWarehouseStockService;
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
 * 入库管理控制器
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseStock")
public class WarehouseStockController {


    @Autowired
    private IWarehouseStockService warehouseStockService;


    /**
     * 获取库存列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(WarehouseEntryDto warehouseEntryDto) {
        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map> list = warehouseStockService.getWarehouseStockList(warehouseEntryDto);

        PageInfo pageInfo = new PageInfo(list);
        //Page pageList = (Page) list;

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 获取物品所在库位的明细
     */
    @ResponseBody
    @RequestMapping(value = "/positionList", method = RequestMethod.POST)
    public PageResParams getGoodsPositionList(String warehouseStockId) {
        new PageFactory<Map<String, Object>>().defaultPage();


        List<Map> list =  this.warehouseStockService.getPositionList(warehouseStockId);

        PageInfo pageInfo = new PageInfo(list);
        //Page pageList = (Page) list;

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 调整库存
     */
    @ResponseBody
    @RequestMapping(value = "/adjustGoodsNum", method = RequestMethod.POST)
    public Object updateGoodsNum(TbWarehouseStock warehouseStock) {
        if(warehouseStock == null){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "库存调整参数为空", null);
        }
        this.warehouseStockService.updateGoodsNum(warehouseStock);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "库存调整成功", null);
    }


}
