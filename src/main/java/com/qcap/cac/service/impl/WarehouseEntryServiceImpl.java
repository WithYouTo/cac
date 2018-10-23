package com.qcap.cac.service.impl;


import cn.hutool.core.bean.BeanUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseEntryMapper;
import com.qcap.cac.dao.WarehousePositionMapper;
import com.qcap.cac.dao.WarehouseStockMapper;
import com.qcap.cac.dao.WarehouseStorageMapper;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbWarehouseEntry;
import com.qcap.cac.entity.TbWarehousePosition;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.entity.TbWarehouseStorage;
import com.qcap.cac.poiEntity.EntryPoiEntity;
import com.qcap.cac.service.WarehouseEntryService;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
public class WarehouseEntryServiceImpl extends ServiceImpl<WarehouseEntryMapper, TbWarehouseEntry> implements WarehouseEntryService {

    @Resource
    private WarehouseEntryMapper warehouseEntryMapper;

    @Resource
    private WarehouseStockMapper warehouseStockMapper;

    @Resource
    private WarehousePositionMapper warehousePositionMapper;

    @Resource
    private WarehouseStorageMapper warehouseStorageMapper;

    @Override
    public List<Map> getEntryList(WarehouseEntryDto warehouseEntryDto) {
        List<Map> list = warehouseEntryMapper.getWarehouseEntryList(warehouseEntryDto);
        return list;
    }

    @Override
    public List<Map> getStoreRoomList() {
        return this.warehouseEntryMapper.getStoreRoomList();
    }

    @Override
    public Integer importExcel(List<EntryPoiEntity> entryList) {

        //库入库批次号
        String batchNo = UUIDUtils.getBatchNo();

        Integer count = 0;
        for(int i = 0 ; i < entryList.size(); i++){
            EntryPoiEntity item = entryList.get(i);
            //如果是空行，直接跳出导入
            if(null == item){
                return count;
            }
            //验证每个字段是否为空
            String buyNo = item.getBuyNo();
            if(StringUtils.isEmpty(buyNo)){
                throw new RuntimeException("第" + (i + 1) + "行的采购编号不能为空！");
            }
            String buyType = item.getBuyType();
            if(StringUtils.isEmpty(buyType)){
                throw new RuntimeException("第" + (i + 1) + "行的采购类别不能为空！");
            }
            String goodsType = item.getGoodsType();
            if(StringUtils.isEmpty(goodsType)){
                throw new RuntimeException("第" + (i + 1) + "行的物品类别不能为空！");
            }
            String goodsName = item.getGoodsName();
            if(StringUtils.isEmpty(goodsName)){
                throw new RuntimeException("第" + (i + 1) + "行的物品名称不能为空！");
            }
            String supplierName = item.getSupplierName();
            if(StringUtils.isEmpty(supplierName)){
                throw new RuntimeException("第" + (i + 1) + "行的供应商名称不能为空！");
            }
            String storeRoom = item.getStoreRoom();
            if(StringUtils.isEmpty(storeRoom)){
                throw new RuntimeException("第" + (i + 1) + "行的仓库名称不能为空！");
            }

            String entryNum = item.getEntryNum();
            if(StringUtils.isEmpty(entryNum)){
                throw new RuntimeException("第" + (i + 1) + "行的数量不能为空！");
            }
            if(!ToolUtil.isNumeric(entryNum)){
                throw new RuntimeException("第" + (i + 1) + "行的数量的格式不正确！");
            }

            String entryUnit = item.getEntryUnit();
            if(StringUtils.isEmpty(entryUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的入库单位不能为空！");
            }

            String limitStore = item.getLimitStore();
            if(StringUtils.isEmpty(limitStore)){
                throw new RuntimeException("第" + (i + 1) + "行的最低警戒线不能为空！");
            }
            if(!ToolUtil.isNumeric(limitStore)){
                throw new RuntimeException("第" + (i + 1) + "行的最低警戒线的格式不正确！");
            }
            String limitUnit = item.getLimitStoreUnit();
            if(StringUtils.isEmpty(limitUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的最低警戒线单位不能为空！");
            }

            String sumNum = item.getSumNum();
            if(StringUtils.isEmpty(sumNum)){
                throw new RuntimeException("第" + (i + 1) + "行的总数量不能为空！");
            }
            if(!ToolUtil.isNumeric(sumNum)){
                throw new RuntimeException("第" + (i + 1) + "行的总数量的格式不正确！");
            }
            String minUnit = item.getMinUnit();
            if(StringUtils.isEmpty(minUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的最小单位不能为空！");
            }

            String buyNum = item.getBuyNum();
            if(StringUtils.isEmpty(buyNum)){
                throw new RuntimeException("第" + (i + 1) + "行的采购数量不能为空！");
            }
            if(!ToolUtil.isNumeric(buyNum)){
                throw new RuntimeException("第" + (i + 1) + "行的采购数量的格式不正确！");
            }
            String buyUnit = item.getMinUnit();
            if(StringUtils.isEmpty(buyUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的采购单位不能为空！");
            }

            String storeroomId = this.warehouseEntryMapper.selecStoreRoomId(storeRoom);
            if(StringUtils.isEmpty(storeroomId)){
                throw new RuntimeException("第" + (i + 1) + "行储藏室在区域中不存在！");
            }

            //系统默认库位是否存在
            QueryWrapper<TbWarehousePosition> pWrapper = new QueryWrapper<>();
            pWrapper.eq("INSTRUCTION","SYSTEM-CONFIG-EXCEL")
                    .eq("STOREROOM_ID",storeroomId)
                    .groupBy("INSTRUCTION");
            TbWarehousePosition warehousePosition = this.warehousePositionMapper.selectOne(pWrapper);
            if(null == warehousePosition){
                throw new RuntimeException("系统默认库位不存在！");
            }


            //入库表
            TbWarehouseEntry entry = new TbWarehouseEntry();
            //库存表
            TbWarehouseStock stock = new TbWarehouseStock();
            BeanUtil.copyProperties(item,entry);
            BeanUtil.copyProperties(item,stock);
            //库存主键
            String  stockId = UUIDUtils.getUUID();
            //判断物品编码是否在库存表中存在
            QueryWrapper<TbWarehouseStock> wrapper = new QueryWrapper<>();
            wrapper.eq("GOODS_NO",buyNo)
                    //.eq("SUPPLIER_NAME",supplierName)
                    .eq("STOREROOM_ID",storeroomId);

             //存在，直接更新库存数量
            if( warehouseStockMapper.selectCount(wrapper) > 0){
                TbWarehouseStock warehouseStock = warehouseStockMapper.selectOne(wrapper);
                BeanUtil.copyProperties(item,warehouseStock);
                BigDecimal oldNum = new BigDecimal(warehouseStock.getGoodsNum());
                BigDecimal goodsNum = oldNum.add(new BigDecimal(sumNum));
                warehouseStock.setGoodsNum(ToolUtil.toInt(goodsNum));
                warehouseStockMapper.updateById(warehouseStock);
                //库存主键
                stockId = warehouseStock.getWarehouseStockId();
            }else{
                //新增库存表
                stock.setWarehouseStockId(stockId);
                stock.setStoreroom(storeRoom);
                stock.setStoreroomId(storeroomId);
                stock.setGoodsType(buyType);
                stock.setGoodsNo(buyNo);
                stock.setGoodsName(goodsName);
                stock.setLimitStore(Integer.parseInt(limitStore));
                stock.setBuyType(buyType);
                stock.setBuyNo(buyNo);
                stock.setBuyNum(ToolUtil.toInt(buyNum));
                stock.setEntryUnit(entryUnit);
                stock.setSupplierNo(supplierName);
                stock.setSupplierName(supplierName);
                stock.setGoodsNum(ToolUtil.toInt(sumNum));
                stock.setMinUnit(minUnit);
                stock.setStockInstrution("EXCEL导入");
                stock.setDeleteFlag("N");
                stock.setCreateEmp("SYS");
                stock.setCreateDate(new Date());
                this.warehouseStockMapper.insert(stock);
            }


            //库位-物品关联表
            String positionId = warehousePosition.getWarehousePositionId();
            //库位-物品关联是否已经存在
            QueryWrapper<TbWarehouseStorage> sWrapper = new QueryWrapper<>();
            sWrapper.eq("WAREHOUSE_POSITION_ID",positionId)
                    .eq("WAREHOUSE_STOCK_ID",stockId);

            if(warehouseStorageMapper.selectCount(sWrapper) == 0){
                TbWarehouseStorage storage = new TbWarehouseStorage();
                storage.setWarehouseStorageId(UUIDUtils.getUUID());
                storage.setWarehousePositionId(positionId);
                storage.setWarehouseStockId(stockId);
                storage.setCreateDate(new Date());
                storage.setCreateEmp("SYS");
                this.warehouseStorageMapper.insert(storage);
            }

            entry.setWarehouseEntryId(UUIDUtils.getUUID());
            entry.setStoreroom(storeRoom);
            entry.setStoreroomId(storeroomId);
            entry.setEntryBatchNo(batchNo);
            entry.setWarehouseStockId(stockId);
            entry.setEntryNum(ToolUtil.toInt((entryNum)));
            entry.setEntryUnit(entryUnit);
            entry.setSumNum(ToolUtil.toInt((sumNum)));
            entry.setMinUnit(minUnit);
            entry.setEntryTime(DateUtil.dateTimeToString(new Date()));
            entry.setDeleteFlag("N");
            entry.setCreateEmp("SYS");
            entry.setCreateDate(new Date());
            this.warehouseEntryMapper.insert(entry);
            count++;
        }
        return count;
    }




}