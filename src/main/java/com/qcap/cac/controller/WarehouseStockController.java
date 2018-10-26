package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.WarehouseStockService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

/**
 * 库存查询
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseStock")
public class WarehouseStockController {


    @Autowired
    private WarehouseStockService warehouseStockService;


    /**
     * 获取库存列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(IPage<Map<String, Object>> page, @Valid  WarehouseEntryDto warehouseEntryDto) {
        try {
            warehouseStockService.getWarehouseStockList(page,warehouseEntryDto);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 获取物品所在库位的明细
     */
    @ResponseBody
    @RequestMapping(value = "/positionList", method = RequestMethod.POST)
    public PageResParams getGoodsPositionList(IPage<Map<String, Object>> page,String warehouseStockId) {
        try {
            this.warehouseStockService.getPositionList(page,warehouseStockId);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,  page.getTotal(), page.getRecords());
    }


    /**
     * 调整库存
     */
    @ResponseBody
    @RequestMapping(value = "/adjustGoodsNum", method = RequestMethod.POST)
    public Object updateGoodsNum(@Valid TbWarehouseStock warehouseStock) {
        try {
            this.warehouseStockService.updateGoodsNum(warehouseStock);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.SYS_EXCEPTION_MSG, null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC, null);
    }


}
