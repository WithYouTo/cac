package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import org.apache.ibatis.annotations.Param;

import javax.validation.Valid;
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


    List<Map<String, Object>> getRequestedGoodsList(IPage<Map<String, Object>> page,@Param("obj") @Valid WarehouseReqDto warehouseReqDto);

    List<Map<String,Object>>  getReqDetailList(IPage<Map<String, Object>> page,@Param("warehouseRequId") @Valid String warehouseRequId);

    Integer updateReqDetailStatus(TbWarehouseRequ warehouseRequ);

    Integer deleteReqDetailStatus(TbWarehouseRequ warehouseRequ);

    /**
     * 查询物品编码下拉框
     * @param storeroomId
     * @return
     */
    List<Map<String,String>> GoodsNoAppList(String storeroomId);

}
