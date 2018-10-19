package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehousePositionMapper;
import com.qcap.cac.dao.WarehouseStorageMapper;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehousePosition;
import com.qcap.cac.entity.TbWarehouseStorage;
import com.qcap.cac.service.WarehousePositionService;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    public Integer insertPosition(TbWarehousePosition tbWarehousePosition) {
        tbWarehousePosition.setWarehousePositionId(UUIDUtils.getUUID());
        tbWarehousePosition.setCreateDate(new Date());
        tbWarehousePosition.setCreateEmp("SYS");
        return this.warehousePositionMapper.insert(tbWarehousePosition);
    }

    @Override
    public Integer updatePosition(TbWarehousePosition tbWarehousePosition) {
        tbWarehousePosition.setUpdateDate(new Date());
        tbWarehousePosition.setUpdateEmp("SYS");
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