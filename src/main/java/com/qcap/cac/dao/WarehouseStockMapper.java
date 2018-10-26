package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.poiEntity.PurchasePoiEntity;
import org.apache.ibatis.annotations.Param;

import javax.validation.Valid;
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

    List<Map<String, Object>> getWarehouseStockList(IPage<Map<String, Object>> page, @Param("obj") @Valid WarehouseEntryDto warehouseEntryDto);

    List<Map<String, Object>> getPositionList(IPage<Map<String, Object>> page, @Param("warehouseStockId") String warehouseStockId);

    Integer updateById(TbWarehouseStock warehouseStock);

    List<PurchasePoiEntity> getLeastStockNumList();

}
