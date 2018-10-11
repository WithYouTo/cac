package com.qcap.cac.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseEntry;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.poiEntity.EntryPoiEntity;

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

    List<Map> getWarehouseStockList(WarehouseEntrySearchParam warehouseEntrySearchParam);

    List<Map> getPositionList(String warehouseStockId);


    List<Map> getGoodsConfigList(WarehouseEntrySearchParam warehouseEntrySearchParam);

}
