package com.qcap.cac.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseEntryMapper;
import com.qcap.cac.dao.WarehouseStockLogMapper;
import com.qcap.cac.dao.WarehouseStockMapper;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.entity.TbWarehouseStockLog;
import com.qcap.cac.service.IWarehouseStockService;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private WarehouseStockLogMapper warehouseStockLogMapper;


    @Override
    public List<Map> getWarehouseStockList(WarehouseEntryDto warehouseEntryDto) {
        List<Map> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(warehouseEntryDto.getStoreroomId())){
            list = this.warehouseStockMapper.getWarehouseStockList(warehouseEntryDto);
        }
        return list;
    }

    @Override
    public List<Map> getPositionList(String warehouseStockId) {
        List<Map> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(warehouseStockId)){
            list = this.warehouseStockMapper.getPositionList(warehouseStockId);
        }
        return list;
    }

    @Override
    public List<TbWarehouseStock> getGoodsConfigList(WarehouseEntryDto warehouseEntryDto) {

        if(StringUtils.isEmpty(warehouseEntryDto.getStoreroomId())){
            return new ArrayList<>();
        }

        //组装参数
        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
                .eq("STOREROOM_ID", warehouseEntryDto.getStoreroomId());

         if(StringUtils.isNotEmpty(warehouseEntryDto.getGoodsNo())) {
             queryWrapper.like("GOODS_NO", "%" + warehouseEntryDto.getGoodsNo() + "%");
         }

        if(StringUtils.isNotEmpty(warehouseEntryDto.getGoodsName())) {
            queryWrapper.like("GOODS_NAME", "%" + warehouseEntryDto.getGoodsName() + "%");
        }

        if(StringUtils.isNotEmpty(warehouseEntryDto.getSupplierName())) {
            queryWrapper.like("SUPPLIER_NAME", "%" + warehouseEntryDto.getSupplierName() + "%");
        }

        if(StringUtils.isNotEmpty(warehouseEntryDto.getGoodsNo())) {
            queryWrapper.like("GOODS_NO", "%" + warehouseEntryDto.getGoodsNo() + "%");
        }

        queryWrapper.groupBy("GOODS_NO").groupBy("SUPPLIER_NAME");
        List<TbWarehouseStock> list = this.warehouseStockMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Integer updateGoodsNum(TbWarehouseStock warehouseStock) {
        if(null == warehouseStock){
            return 0;
        }
        //查询详情
        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
                .eq("WAREHOUSE_STOCK_ID", warehouseStock.getWarehouseStockId());
        TbWarehouseStock stock = this.warehouseStockMapper.selectOne(queryWrapper);
        if(null == stock){
            throw  new RuntimeException("根据ID没有查询到库存数据");
        }
        //更新库存数量
        warehouseStockMapper.updateById(warehouseStock);

        TbWarehouseStockLog log = new TbWarehouseStockLog();
        log.setWarehouseStockLogId(UUIDUtils.getUUID());
        BeanUtil.copyProperties(stock,log);
        String opera = stock.getGoodsName() + "由原【" + stock.getGoodsNum() + "】调整为【" + warehouseStock.getGoodsNum() + "】";
        log.setLogOperation(opera);
        warehouseStockLogMapper.insert(log);
        return 1;
    }
}