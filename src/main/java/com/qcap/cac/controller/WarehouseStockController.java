package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseStock;
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
        if (StringUtils.isEmpty(warehouseEntrySearchParam.getStoreroomId())) {
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


    /**
     * 获取仓库配置信息
     */
    @ResponseBody
    @RequestMapping(value = "/goodsConfigList", method = RequestMethod.POST)
    public PageResParams goodsConfigList(WarehouseEntrySearchParam warehouseEntrySearchParam) {
        if (StringUtils.isEmpty(warehouseEntrySearchParam.getStoreroomId())) {
            //return PageResParams.newInstance()
        }

        //组装参数
        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
                .eq("STOREROOM_ID", warehouseEntrySearchParam.getStoreroomId()).groupBy("GOODS_NO");
        queryWrapper.or(wrapper->{
            wrapper.like( "GOODS_NO", "%" + warehouseEntrySearchParam.getGoodsNo() + "%")
                    .or().like("GOODS_NAME", "%" + warehouseEntrySearchParam.getGoodsName()  + "%")
                    .or().like( "SUPPLIER_NAME", "%" + warehouseEntrySearchParam.getSupplierName()  + "%");
            return wrapper;

        });
        List<TbWarehouseStock> list = warehouseStockService.list(queryWrapper);

        PageInfo pageInfo = new PageInfo(list);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 获取仓库配置信息
     */
    @ResponseBody
    @RequestMapping(value = "/adjustLimitStore", method = RequestMethod.POST)
    public Object updateGoodsConfigLimit(TbWarehouseStock warehouseStock) {

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "修改警戒线成功", null);
    }

}
