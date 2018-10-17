package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import org.apache.ibatis.annotations.Select;

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

    List<Map> getWarehouseStockList(WarehouseEntryDto warehouseEntryDto);

    List<Map> getPositionList(String warehouseStockId);


    Integer updateById(TbWarehouseStock warehouseStock);


    List<TbWarehouseStock> getGoodsConfigList(WarehouseEntryDto warehouseEntryDto);

    List<TbWarehouseStock> getLeastStockNumList();


}
