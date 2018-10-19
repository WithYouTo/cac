package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehousePosition;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 * 库位管理表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface WarehousePositionMapper extends BaseMapper<TbWarehousePosition> {


    TbArea getStoreRoomInfo(@Param("storeroomId") String storeroomId);

    Integer updateById(TbWarehousePosition warehousePosition);


}
