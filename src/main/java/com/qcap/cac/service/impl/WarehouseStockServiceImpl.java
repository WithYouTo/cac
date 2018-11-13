package com.qcap.cac.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseStockLogMapper;
import com.qcap.cac.dao.WarehouseStockMapper;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.entity.TbWarehouseStockLog;
import com.qcap.cac.poiEntity.PurchasePoiEntity;
import com.qcap.cac.service.WarehouseStockService;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class WarehouseStockServiceImpl extends ServiceImpl<WarehouseStockMapper, TbWarehouseStock> implements WarehouseStockService {

    @Resource
    private WarehouseStockMapper warehouseStockMapper;

    @Resource
    private WarehouseStockLogMapper warehouseStockLogMapper;


    @Override
    public void getWarehouseStockList(IPage<Map<String, Object>> page,WarehouseEntryDto warehouseEntryDto) {
        List<Map<String, Object>> list =  this.warehouseStockMapper.getWarehouseStockList(page,warehouseEntryDto);
        page.setRecords(list);
    }

    @Override
    public void getPositionList(IPage<Map<String, Object>> page,String warehouseStockId) {
        List<Map<String, Object>> list = this.warehouseStockMapper.getPositionList(page,warehouseStockId);
        page.setRecords(list);
    }

    @Override
    public IPage<TbWarehouseStock> getGoodsConfigList(IPage<TbWarehouseStock> page, WarehouseEntryDto warehouseEntryDto) {

        //组装参数
        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
                .eq("STOREROOM_ID", warehouseEntryDto.getStoreroomId());

         if(StringUtils.isNotEmpty(warehouseEntryDto.getGoodsNo())) {
             queryWrapper.like("GOODS_NO", warehouseEntryDto.getGoodsNo());
         }

        if(StringUtils.isNotEmpty(warehouseEntryDto.getGoodsName())) {
            queryWrapper.like("GOODS_NAME", warehouseEntryDto.getGoodsName());
        }

        if(StringUtils.isNotEmpty(warehouseEntryDto.getSupplierName())) {
            queryWrapper.like("SUPPLIER_NAME",  warehouseEntryDto.getSupplierName());
        }

        if(StringUtils.isNotEmpty(warehouseEntryDto.getGoodsNo())) {
            queryWrapper.like("GOODS_NO",  warehouseEntryDto.getGoodsNo());
        }

        queryWrapper.groupBy("GOODS_NO").groupBy("SUPPLIER_NAME");

        return this.warehouseStockMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void updateGoodsNum(TbWarehouseStock warehouseStock) {
        if(StringUtils.isEmpty(warehouseStock.getWarehouseStockId())){
            throw  new RuntimeException("调整库存主键为空");
        }
        //库存需要调整的数目
        Integer goodsNum = warehouseStock.getGoodsNum();
        //查询原有库存的数目
        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
                .eq("WAREHOUSE_STOCK_ID", warehouseStock.getWarehouseStockId());
        TbWarehouseStock stock = this.warehouseStockMapper.selectOne(queryWrapper);
        if(null == stock){
            throw  new RuntimeException("根据主键没有查询到库存数据");
        }
        //写入库存调整日志
        TbWarehouseStockLog log = new TbWarehouseStockLog();
        log.setWarehouseStockLogId(UUIDUtils.getUUID());
        BeanUtil.copyProperties(stock,log);
        String opera = stock.getGoodsName() + "由原【" + stock.getGoodsNum() + "】调整为【" + goodsNum + "】";
        log.setLogOperation(opera);
        EntityTools.setCreateEmpAndTime(log);
        warehouseStockLogMapper.insert(log);

        //更新库存数量
        stock.setGoodsNum(goodsNum);
        EntityTools.setUpdateEmpAndTime(stock);
        warehouseStockMapper.updateById(stock);
    }


    /**
     * 低于警戒线，生成请购单
     * date YYYY-MM-DD
     */
    public List<PurchasePoiEntity>  generatePurchaseOrder(String date){
        return this.warehouseStockMapper.getLeastStockNumList(date);

    }

    @Override
    public void checkBeforeExport() {
        //查询数据库中低于警戒线中的记录
        List<TbWarehouseStock> list = this.warehouseStockMapper.getLowLimitStockList(DateUtil.getDay());
        //遍历更新采购的起止日期
        for(TbWarehouseStock item : list) {
            if (StringUtils.isEmpty(item.getWarehouseStockId())) {
                throw new RuntimeException("请购单中物品信息主键为空");
            }
            TbWarehouseStock stock = this.warehouseStockMapper.selectById(item.getWarehouseStockId());
            if (null == stock) {
                throw new RuntimeException("请购单中根据主键没有查询到信息");
            }
            if (ToolUtil.isEmpty(item.getBuyDuration())) {
                throw new RuntimeException("物品名称为【" + item.getGoodsName() + "】没有设置采购周期");
            }
        }
    }
}