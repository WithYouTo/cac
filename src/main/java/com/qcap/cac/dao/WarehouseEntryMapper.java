package com.qcap.cac.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.entity.WarehouseEntry;
import org.apache.ibatis.annotations.Param;

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
public interface WarehouseEntryMapper extends BaseMapper<WarehouseEntry> {

    List<Map<String, Object>> getWarehouseEntryList(IPage page, @Param("map") Map<String, Object> map);

}
