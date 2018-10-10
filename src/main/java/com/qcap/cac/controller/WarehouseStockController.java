package com.qcap.cac.controller;


import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.poiEntity.EntryPoiEntity;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.cac.service.IWarehouseStockService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.poi.PoiUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 入库管理控制器
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseStock")
public class WarehouseStockController {


    @Autowired
    private IWarehouseStockService warehouseStockService;


    /**
     * 获取库存列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(WarehouseEntrySearchParam warehouseEntrySearchParam) {
        if (StringUtils.isEmpty(warehouseEntrySearchParam.getStoreRoomId())) {
            //return PageResParams.newInstance()
        }
        List<Map> list = warehouseStockService.getWarehouseStockList(warehouseEntrySearchParam);
        PageInfo pageInfo = new PageInfo(list);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 获取物品所在库位的明细
     */
    @ResponseBody
    @RequestMapping(value = "/positionList", method = RequestMethod.POST)
    public Object getGoodsPositionList(String warehouseStockId) {
       List<Map> list =  this.warehouseStockService.getPositionList(warehouseStockId);
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", list);
    }

}
