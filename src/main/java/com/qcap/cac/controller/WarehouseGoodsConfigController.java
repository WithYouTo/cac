package com.qcap.cac.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.poiEntity.PurchasePoiEntity;
import com.qcap.cac.service.WarehouseStockService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.poi.PoiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    public PageResParams goodsConfigList(IPage<TbWarehouseStock> page, @Valid WarehouseEntryDto warehouseEntryDto) {
        try {
            this.warehouseStockService.getGoodsConfigList(page,warehouseEntryDto);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }


    /**
     * 调整警戒线
     */
    @ResponseBody
    @RequestMapping(value = "/adjustLimitStore", method = RequestMethod.POST)
    public Object updateGoodsConfigLimit(@Valid  TbWarehouseStock warehouseStock) {
        try {
            this.warehouseStockService.updateById(warehouseStock);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }


    /**
     * 生成请购单
     */
    @RequestMapping(value = "/generatePurchaseOrder")
    public void generatePurchaseOrder(HttpServletResponse response) {
        //生成请购单的操作日期
        String date = DateUtil.format(new Date(),"yyyy-MM-dd");
        try {
            List<PurchasePoiEntity> list = this.warehouseStockService.generatePurchaseOrder(date);
            //导出
            PoiUtils.exportExcel(list,"请购单" + date,"请购单",PurchasePoiEntity.class,"请购单.xls",response);
        } catch (Exception e) {
            ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
    }


}
