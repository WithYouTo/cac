package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehousePosition;

import javax.validation.Valid;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface WarehousePositionService extends IService<TbWarehousePosition> {

    /**
     * 获取库位列表
     * @param page
     * @param warehouseEntryDto
     * @return
     */
    IPage<TbWarehousePosition> getPositionList(IPage<TbWarehousePosition> page, @Valid WarehouseEntryDto warehouseEntryDto);


    /**
     * 新增库位
     * @param tbWarehousePosition
     * @return
     */
    Integer insertPosition(TbWarehousePosition tbWarehousePosition);

    /**
     * 修改库位
     * @param tbWarehousePosition
     * @return
     */
    Integer updatePosition(TbWarehousePosition tbWarehousePosition);

    /**
     * 删除库位
     * @param tbWarehousePosition
     * @return
     */
    public Integer deletePosition(TbWarehousePosition tbWarehousePosition);


    /**
     * 查询库位是否已经存在
     * @param tbWarehousePosition
     * @return
     */
    Integer selectExistPosition(TbWarehousePosition tbWarehousePosition);

    /**
     * 查询库位是否已经存放物品
     * @param warehousePositionId
     * @return
     */
    Integer selectGoodsExistPosition(String warehousePositionId);


    TbArea selectAreaById(String storeRoomId);

}
