package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.WarehouseEntry;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface IWarehouseEntryService extends IService<WarehouseEntry> {

    List<Map> getEntryList(WarehouseEntrySearchParam warehouseEntrySearchParam);

    List<Map> getStoreRoomList();

}
