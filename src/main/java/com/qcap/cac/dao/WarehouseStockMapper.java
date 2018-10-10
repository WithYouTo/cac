package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseStock;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 库存管理表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface WarehouseStockMapper extends BaseMapper<TbWarehouseStock> {

    List<Map> getWarehouseStockList(WarehouseEntrySearchParam warehouseEntrySearchParam);

    List<Map> getPositionList(String warehouseStockId);
}
