package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseEntryMapper;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.WarehouseEntry;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.core.dao.TbManagerMapper;
import com.qcap.core.entity.TbRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
@Service
public class WarehouseEntryServiceImpl extends ServiceImpl<WarehouseEntryMapper, WarehouseEntry> implements IWarehouseEntryService {

    @Resource
    private WarehouseEntryMapper warehouseEntryMapper;

    @Override
    public List<Map> getEntryList(WarehouseEntrySearchParam warehouseEntrySearchParam) {
        List<Map> list = warehouseEntryMapper.getWarehouseEntryList(warehouseEntrySearchParam);
        return list;
    }

    @Override
    public List<Map> getStoreRoomList() {
        return this.warehouseEntryMapper.getStoreRoomList();
    }
}