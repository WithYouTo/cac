package com.qcap.cac.controller;


import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.poiEntity.PurchasePoiEntity;
import com.qcap.cac.service.WarehouseStockService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.poi.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 仓库配置管理
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseGoodsConfig")
public class WarehouseGoodsConfigController {


    @Autowired
    private WarehouseStockService warehouseStockService;

    /**
     * 获取仓库配置信息
     */
    @ResponseBody
    @RequestMapping(value = "/goodsConfigList", method = RequestMethod.POST)
    public PageResParams goodsConfigList(WarehouseEntryDto warehouseEntryDto) {

        new PageFactory<TbWarehouseStock>().defaultPage();

        List<TbWarehouseStock> list =  this.warehouseStockService.getGoodsConfigList(warehouseEntryDto);

        Page pageList = (Page) list;
        PageInfo pageInfo = new PageInfo(list);

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), pageList);
    }


    /**
     * 调整警戒线
     */
    @ResponseBody
    @RequestMapping(value = "/adjustLimitStore", method = RequestMethod.POST)
    public Object updateGoodsConfigLimit(TbWarehouseStock warehouseStock) {
        if(null == warehouseStock){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "修改警戒线参数为空", null);
        }
        this.warehouseStockService.updateById(warehouseStock);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "修改警戒线成功", null);
    }


    /**
     * 生成请购单
     */
    // @ResponseBody
    @RequestMapping(value = "/generatePurchaseOrder")
    public void generatePurchaseOrder(HttpServletResponse response) {
        String date = DateUtil.format(new Date(),"yyyy-MM-dd");
        List<PurchasePoiEntity> list = this.warehouseStockService.generatePurchaseOrder(date);
        //导出
        PoiUtils.exportExcel(list,"请购单" + date,"请购单",PurchasePoiEntity.class,"请购单.xls",response);
    }


}
