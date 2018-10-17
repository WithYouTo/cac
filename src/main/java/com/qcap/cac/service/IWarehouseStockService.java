package com.qcap.cac.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface IWarehouseStockService extends IService<TbWarehouseStock> {

    List<Map> getWarehouseStockList(WarehouseEntryDto warehouseEntryDto);

    List<Map> getPositionList(String warehouseStockId);

    List<TbWarehouseStock> getGoodsConfigList(WarehouseEntryDto warehouseEntryDto);

    Integer updateGoodsNum(TbWarehouseStock warehouseStock);

    void generatePurchaseOrder(String date);

}
