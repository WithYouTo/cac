package com.qcap.cac.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseEntry;
import com.qcap.cac.entity.WarehouseEntry;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cindy
 * @since 2018-10-09
 */
public interface WarehouseEntryMapper extends BaseMapper<TbWarehouseEntry> {

    List<Map> getWarehouseEntryList(WarehouseEntrySearchParam warehouseEntrySearchParam);

    List<Map> getStoreRoomList();

    @Select("select AREA_ID from tb_area WHERE AREA_NAME = #{storeRoom}")
    String selecStoreRoomId(@Param("storeRoom") String storeRoom);

}
