package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseEntry;
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
public interface WarehouseEntryService extends IService<TbWarehouseEntry> {

    void getEntryList(IPage<Map<String, Object>> page, WarehouseEntryDto warehouseEntryDto);

    List<Map> getStoreRoomList();

    Integer importExcel(List<EntryPoiEntity> entryList);

    /**
     * 根据项目编号查询储藏室下拉框
     * @param programCode
     * @return
     */
    List<Map<String,String>> getStoreRoomListByProgramCode(String programCode);



}
