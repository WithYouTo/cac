package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
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
import java.util.List;

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
    private AreaMapper areaMapper;

    @Resource
    private WarehousePositionMapper warehousePositionMapper;

    @Resource
    private WarehouseStorageMapper warehouseStorageMapper;


    @Override
    public IPage<TbWarehousePosition> getPositionList(IPage<TbWarehousePosition> page, @Valid WarehouseEntryDto warehouseEntryDto) {
        QueryWrapper<TbWarehousePosition> wrapper = new QueryWrapper<>();
        wrapper.eq("PROGRAM_CODE", warehouseEntryDto.getProgramCode())
                .eq("DELETE_FLAG","N");
        if(StringUtils.isNotEmpty(warehouseEntryDto.getStoreroomId())){
            wrapper.eq("STOREROOM_ID", warehouseEntryDto.getStoreroomId());
        }
        wrapper.orderByAsc("CREATE_DATE");
        return warehousePositionMapper.selectPage(page,wrapper);
    }


    @Override
    public Integer insertPosition(TbWarehousePosition tbWarehousePosition) {
        if(StringUtils.isEmpty(tbWarehousePosition.getStoreroomId())){
            throw new RuntimeException("请选择库位");
        }

        TbArea area = areaMapper.selectById(tbWarehousePosition.getStoreroomId());
        if(null == area){
            throw new RuntimeException("根据选择储藏室没有查询到信息");
        }

        tbWarehousePosition.setProgramCode(area.getProgramCode());
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
        //防止新增库位时，未填写programCode
        TbArea area = areaMapper.selectById(tbWarehousePosition.getStoreroomId());
        if(null == area){
            throw new RuntimeException("根据选择储藏室没有查询到信息");
        }
        tbWarehousePosition.setProgramCode(area.getProgramCode());

        //是否已经存在
        QueryWrapper<TbWarehousePosition> wrapper = new QueryWrapper<>();
        wrapper.and(condition -> condition
                .eq("PROGRAM_CODE", tbWarehousePosition.getProgramCode())
                .eq("BUILDING_NAME", tbWarehousePosition.getBuildingName())
                .eq("FLOOR_NAME", tbWarehousePosition.getFloorName())
                .eq("ROOM_NAME", tbWarehousePosition.getRoomName())
                .eq("DELETE_FLAG", "N")
                .eq("RANGE_SHELF", tbWarehousePosition.getRangeShelf()));

        List<TbWarehousePosition> list = warehousePositionMapper.selectList(wrapper);
        if(list.size() == 0 || list.size() == 1 && tbWarehousePosition.getWarehousePositionId().equals(list.get(0).getWarehousePositionId())){
            tbWarehousePosition.setDeleteFlag("N");
            EntityTools.setUpdateEmpAndTime(tbWarehousePosition);
            return this.warehousePositionMapper.updateById(tbWarehousePosition);
        }else{
            throw new RuntimeException("库位已经存在");
        }
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
            wrapper.and(condition -> condition
                    .eq("PROGRAM_CODE", tbWarehousePosition.getProgramCode())
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