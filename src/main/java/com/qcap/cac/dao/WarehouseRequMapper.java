package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseRequ;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 领用单表
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface WarehouseRequMapper extends BaseMapper<TbWarehouseRequ> {


    List<Map<String,String>> getRequGoodsList(WarehouseReqDto warehouseReqDto);


}
