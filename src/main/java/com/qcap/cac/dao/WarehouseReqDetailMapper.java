package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.dto.WarehouseReqSearchParam;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseStock;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 领用明细Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface WarehouseReqDetailMapper extends BaseMapper<TbWarehouseReqdetail> {


    List<Map> getRequestedGoodsList(WarehouseReqSearchParam warehouseReqSearchParam);

}
