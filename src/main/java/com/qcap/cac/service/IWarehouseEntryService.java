package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseEntry;
import com.qcap.cac.entity.WarehouseEntry;
import com.qcap.cac.poiEntity.EntryPoiEntity;

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
public interface IWarehouseEntryService extends IService<TbWarehouseEntry> {

    List<Map> getEntryList(WarehouseEntrySearchParam warehouseEntrySearchParam);

    List<Map> getStoreRoomList();

    Integer importExcel(List<EntryPoiEntity> entryList);



}
