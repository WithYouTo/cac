package com.qcap.cac.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.util.ArrayList;
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

    @Override
    public List<TbWarehouseStock> getGoodsConfigList(WarehouseEntrySearchParam warehouseEntrySearchParam) {

        if(StringUtils.isEmpty(warehouseEntrySearchParam.getStoreroomId())){
            return new ArrayList<>();
        }

        //组装参数
        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
                .eq("STOREROOM_ID", warehouseEntrySearchParam.getStoreroomId());

         if(StringUtils.isNotEmpty(warehouseEntrySearchParam.getGoodsNo())) {
             queryWrapper.like("GOODS_NO", "%" + warehouseEntrySearchParam.getGoodsNo() + "%");
         }

        if(StringUtils.isNotEmpty(warehouseEntrySearchParam.getGoodsName())) {
            queryWrapper.like("GOODS_NAME", "%" + warehouseEntrySearchParam.getGoodsName() + "%");
        }

        if(StringUtils.isNotEmpty(warehouseEntrySearchParam.getSupplierName())) {
            queryWrapper.like("SUPPLIER_NAME", "%" + warehouseEntrySearchParam.getSupplierName() + "%");
        }

        if(StringUtils.isNotEmpty(warehouseEntrySearchParam.getGoodsNo())) {
            queryWrapper.like("GOODS_NO", "%" + warehouseEntrySearchParam.getGoodsNo() + "%");
        }

        queryWrapper.groupBy("GOODS_NO").groupBy("SUPPLIER_NAME");
        List<TbWarehouseStock> list = this.warehouseStockMapper.selectList(queryWrapper);
        return list;
    }
}