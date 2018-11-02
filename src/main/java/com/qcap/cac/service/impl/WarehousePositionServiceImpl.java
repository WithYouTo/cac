package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehousePositionMapper;
import com.qcap.cac.dao.WarehouseStorageMapper;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehousePosition;
import com.qcap.cac.entity.TbWarehouseStorage;
import com.qcap.cac.service.WarehousePositionService;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
@Service
public class WarehousePositionServiceImpl extends ServiceImpl<WarehousePositionMapper, TbWarehousePosition> implements WarehousePositionService {

    @Resource
    private WarehousePositionMapper warehousePositionMapper;

    @Resource
    private WarehouseStorageMapper warehouseStorageMapper;


    @Override
    public IPage<TbWarehousePosition> getPositionList(IPage<TbWarehousePosition> page, @Valid WarehouseEntryDto warehouseEntryDto) {
        return warehousePositionMapper.selectPage(page,new QueryWrapper<TbWarehousePosition>()
                .eq("storeroom_Id", warehouseEntryDto.getStoreroomId())
                .eq("delete_flag","N"));
    }


    @Override
    public Integer insertPosition(TbWarehousePosition tbWarehousePosition) {
        if(null == tbWarehousePosition){
            throw new RuntimeException("库位参数为空");
        }
        if(selectExistPosition(tbWarehousePosition) >  0){
            throw new RuntimeException("库位已经存在");
        }

        tbWarehousePosition.setWarehousePositionId(UUIDUtils.getUUID());
        tbWarehousePosition.setDeleteFlag("N");
        EntityTools.setCreateEmpAndTime(tbWarehousePosition);
        return this.warehousePositionMapper.insert(tbWarehousePosition);
    }

    @Override
    public Integer updatePosition(TbWarehousePosition tbWarehousePosition) {
        if(null == tbWarehousePosition){
            throw new RuntimeException("库位参数为空");
        }
        if(selectExistPosition(tbWarehousePosition) >=  1){
            throw new RuntimeException("库位已经存在");
        }

        tbWarehousePosition.setDeleteFlag("N");
        EntityTools.setUpdateEmpAndTime(tbWarehousePosition);
        return this.warehousePositionMapper.updateById(tbWarehousePosition);
    }

    @Override
    public Integer deletePosition(TbWarehousePosition tbWarehousePosition) {
        if(null == tbWarehousePosition.getWarehousePositionId()){
            throw new RuntimeException("库位参数主键为空");
        }
        if(selectGoodsExistPosition(tbWarehousePosition.getWarehousePositionId()) > 0){
            throw new RuntimeException("库位存在物品，不能删除");
        }

        tbWarehousePosition.setDeleteFlag("Y");
        EntityTools.setUpdateEmpAndTime(tbWarehousePosition);
        return this.warehousePositionMapper.updateById(tbWarehousePosition);
    }

    @Override
    public Integer selectExistPosition(TbWarehousePosition tbWarehousePosition) {
        Integer count = 0;
        /**
         * 组装查询条件
         */
        QueryWrapper<TbWarehousePosition> wrapper = new QueryWrapper<>();
        if(null != tbWarehousePosition) {
            wrapper.and(condition -> condition.eq("storeRoom", tbWarehousePosition.getStoreroom())
                    .eq("BUILDING_NAME", tbWarehousePosition.getBuildingName())
                    .eq("FLOOR_NAME", tbWarehousePosition.getFloorName())
                    .eq("ROOM_NAME", tbWarehousePosition.getRoomName())
                    .eq("DELETE_FLAG", "N")
                    .eq("RANGE_SHELF", tbWarehousePosition.getRangeShelf()));
            count = this.warehousePositionMapper.selectCount(wrapper);
        }
        return count;
    }

    @Override
    public Integer selectGoodsExistPosition(String warehousePositionId) {
        Integer count = 0;
        /**
         * 组装查询条件
         */
        QueryWrapper<TbWarehouseStorage> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(warehousePositionId)) {
            wrapper.and(condition -> condition.eq("WAREHOUSE_POSITION_ID", warehousePositionId));
            count = this.warehouseStorageMapper.selectCount(wrapper);
        }
        return count;
    }

    @Override
    public TbArea selectAreaById(String storeRoomId) {
        return this.warehousePositionMapper.getStoreRoomInfo(storeRoomId);
    }
}