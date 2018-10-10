package com.qcap.cac.service.impl;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseEntryMapper;
import com.qcap.cac.dao.WarehouseStockMapper;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.TbWarehouseEntry;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.entity.WarehouseEntry;
import com.qcap.cac.poiEntity.EntryPoiEntity;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.dao.TbManagerMapper;
import com.qcap.core.entity.TbRole;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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
public class WarehouseEntryServiceImpl extends ServiceImpl<WarehouseEntryMapper, TbWarehouseEntry> implements IWarehouseEntryService {

    @Resource
    private WarehouseEntryMapper warehouseEntryMapper;

    @Resource
    private WarehouseStockMapper warehouseStockMapper;

    @Override
    public List<Map> getEntryList(WarehouseEntrySearchParam warehouseEntrySearchParam) {
        List<Map> list = warehouseEntryMapper.getWarehouseEntryList(warehouseEntrySearchParam);
        return list;
    }

    @Override
    public List<Map> getStoreRoomList() {
        return this.warehouseEntryMapper.getStoreRoomList();
    }

    @Override
    public Integer importExcel(List<EntryPoiEntity> entryList) {

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
            String entryUnit = item.getEntryUnit();
            if(StringUtils.isEmpty(entryUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的入库单位不能为空！");
            }

            String limitStore = item.getLimitStore();
            if(StringUtils.isEmpty(limitStore)){
                throw new RuntimeException("第" + (i + 1) + "行的最低警戒线不能为空！");
            }
            String limitUnit = item.getEntryUnit();
            if(StringUtils.isEmpty(limitUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的最低警戒线单位不能为空！");
            }

            String sumNum = item.getSumNum();
            if(StringUtils.isEmpty(sumNum)){
                throw new RuntimeException("第" + (i + 1) + "行的总数量不能为空！");
            }
            String minUnit = item.getMinUnit();
            if(StringUtils.isEmpty(minUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的最小单位不能为空！");
            }

            String buyNum = item.getBuyNum();
            if(StringUtils.isEmpty(buyNum)){
                throw new RuntimeException("第" + (i + 1) + "行的采购数量不能为空！");
            }
            String buyUnit = item.getMinUnit();
            if(StringUtils.isEmpty(buyUnit)){
                throw new RuntimeException("第" + (i + 1) + "行的采购单位不能为空！");
            }

            //入库单位
            TbWarehouseEntry entry = new TbWarehouseEntry();
            //库存表
            TbWarehouseStock stock = new TbWarehouseStock();
            BeanUtil.copyProperties(item,entry);
            BeanUtil.copyProperties(item,stock);

            //库存主键
            String  stockId = UUIDUtils.getUUID();
            String batchNo = UUIDUtils.getBatchNo();
            entry.setWarehouseEntryId(UUIDUtils.getUUID());
            entry.setStoreroom(storeRoom);
            String storeRoomId = this.warehouseEntryMapper.selecStoreRoomId(storeRoom);
            entry.setStoreroomId(storeRoomId);
            entry.setEntryBatchNo(batchNo);
            entry.setWarehouseStockId(stockId);
            entry.setEntryNum(Integer.parseInt(entryNum));
            entry.setEntryUnit(entryUnit);
            entry.setSumNum(sumNum);
            entry.setMinUnit(minUnit);
            entry.setEntryTime(DateUtil.dateTimeToString(new Date()));
            entry.setDeleteFlag("N");
            entry.setCreateEmp("SYS");
            entry.setCreateDate(new Date());
            this.warehouseEntryMapper.insert(entry);
            count++;

            //新增库存表
            stock.setWarehouseStockId(stockId);
            stock.setStoreroom(storeRoom);
            stock.setStoreroomId(stockId);
            stock.setGoodsType(buyType);
            stock.setGoodsNo(buyNo);
            stock.setGoodsName(goodsName);
            stock.setLimitStore(Integer.parseInt(limitStore));
            stock.setBuyType(buyType);
            stock.setBuyNo(buyNo);
            stock.setBuyNum(buyNum);
            stock.setEntryUnit(entryUnit);
            stock.setSupplierNo(supplierName);
            stock.setSupplierNo(supplierName);
            stock.setGoodsNum(sumNum);
            stock.setStockInstrution("EXCEL导入");
            stock.setDeleteFlag("N");
            stock.setCreateEmp("SYS");
            stock.setCreateDate(new Date());
            this.warehouseStockMapper.insert(stock);
        }
        return count;
    }

}