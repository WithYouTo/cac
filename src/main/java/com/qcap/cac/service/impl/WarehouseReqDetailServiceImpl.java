package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseReqDetailMapper;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.service.WarehouseReqDetailService;
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
public class WarehouseReqDetailServiceImpl extends ServiceImpl<WarehouseReqDetailMapper, TbWarehouseReqdetail> implements WarehouseReqDetailService {

    @Resource
    private WarehouseReqDetailMapper warehouseReqDetailMapper;


    @Override
    public List<Map<String, Object>> getRequestedList(WarehouseReqDto warehouseReqDto) {
        if(StringUtils.isEmpty(warehouseReqDto.getStoreroomId())){
            return new ArrayList<>();
        }
        List<Map<String, Object>> list = this.warehouseReqDetailMapper.getRequestedGoodsList(warehouseReqDto);
        return list;
    }

    @Override
    public List<Map<String, Object>> getReqDetailList(String warehouseRequId) {
        if(StringUtils.isEmpty(warehouseRequId)){
            return new ArrayList<>();
        }
        List<Map<String, Object>> list = this.warehouseReqDetailMapper.getReqDetailList(warehouseRequId);
        return list;
    }

    @Override
    public String insertReqDetail(TbWarehouseReqdetail warehouseReqdetail) {

        //判断是否是首次新增领用明细
        String warehouseRequId = warehouseReqdetail.getWarehouseRequId();
        if(StringUtils.isEmpty(warehouseRequId)){
            warehouseRequId = UUIDUtils.getUUID();
            warehouseReqdetail.setWarehouseRequId(warehouseRequId);
        }

        //判断物品名称
        if(StringUtils.isEmpty(warehouseReqdetail.getGoodsName())){
            throw new RuntimeException("新增明细物品名称为空");
        }

        //判断物品数量
        if(null == warehouseReqdetail.getApplyNum()){
            throw new RuntimeException("新增明细物品数量为空");
        }

        //判断领用明细是否已经存在
        QueryWrapper<TbWarehouseReqdetail> wrapper = new QueryWrapper<>();
        wrapper.eq("WAREHOUSE_REQU_ID",warehouseReqdetail.getWarehouseRequId());
        wrapper.eq("GOODS_NAME",warehouseReqdetail.getGoodsName());
        if(warehouseReqDetailMapper.selectCount(wrapper) > 0){
           //更新数量
            TbWarehouseReqdetail old = warehouseReqDetailMapper.selectOne(wrapper);
            if(old == null){
                throw new RuntimeException("根据主键没有查询到领用明细信息");
            }
            Integer newNum = old.getApplyNum() + warehouseReqdetail.getApplyNum();
            old.setApplyNum(newNum);
            this.warehouseReqDetailMapper.updateById(old);
        }else{
            warehouseReqdetail.setWarehouseReqdetailId(UUIDUtils.getUUID());
            warehouseReqdetail.setRequStatus("INIT");
            warehouseReqdetail.setCreateEmp("SYS");
            this.warehouseReqDetailMapper.insert(warehouseReqdetail);
        }
        return warehouseRequId;
    }

    @Override
    public List<Map<String, String>> GoodsNoAppList(String storeroomId) {
        return this.warehouseReqDetailMapper.GoodsNoAppList(storeroomId);
    }
}