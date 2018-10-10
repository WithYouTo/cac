package com.qcap.cac.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseEntryMapper;
import com.qcap.cac.dao.WarehouseStockMapper;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseEntry;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.poiEntity.EntryPoiEntity;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.cac.service.IWarehouseStockService;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
@Service
public class WarehouseStockServiceImpl extends ServiceImpl<WarehouseStockMapper, TbWarehouseStock> implements IWarehouseStockService {

    @Resource
    private WarehouseEntryMapper warehouseEntryMapper;

    @Resource
    private WarehouseStockMapper warehouseStockMapper;


    @Override
    public List<Map> getWarehouseStockList(WarehouseEntrySearchParam warehouseEntrySearchParam) {
        return this.warehouseStockMapper.getWarehouseStockList(warehouseEntrySearchParam);
    }

    @Override
    public List<Map> getPositionList(String warehouseStockId) {
        return this.warehouseStockMapper.getPositionList(warehouseStockId);
    }
}