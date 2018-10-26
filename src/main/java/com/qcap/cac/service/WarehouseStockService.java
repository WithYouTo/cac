package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.poiEntity.PurchasePoiEntity;

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
public interface WarehouseStockService extends IService<TbWarehouseStock> {

    void getWarehouseStockList(IPage<Map<String, Object>> page, WarehouseEntryDto warehouseEntryDto);

    void getPositionList(IPage<Map<String, Object>> page,String warehouseStockId);

    IPage<TbWarehouseStock> getGoodsConfigList(IPage<TbWarehouseStock> page, WarehouseEntryDto warehouseEntryDto);

    void updateGoodsNum(TbWarehouseStock warehouseStock);

    List<PurchasePoiEntity>  generatePurchaseOrder(String date);

}
