package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseReqDetailMapper;
import com.qcap.cac.dto.WarehouseReqSearchParam;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.IWarehouseReqDetailService;
import com.qcap.cac.service.IWarehouseRequService;
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
public class WarehouseReqDetailServiceImpl extends ServiceImpl<WarehouseReqDetailMapper, TbWarehouseReqdetail> implements IWarehouseReqDetailService {

    @Resource
    private WarehouseReqDetailMapper warehouseReqDetailMapper;


    @Override
    public List<Map> getRequestedList(WarehouseReqSearchParam warehouseReqSearchParam) {
        if(StringUtils.isEmpty(warehouseReqSearchParam.getStoreroomId())){
            return new ArrayList<>();
        }
//
//        //组装参数
//        QueryWrapper<TbWarehouseStock> queryWrapper = new QueryWrapper<TbWarehouseStock>()
//                .eq("STOREROOM_ID", warehouseReqSearchParam.getStoreroomId());
//
//        if(StringUtils.isNotEmpty(warehouseReqSearchParam.getRequLocName())) {
//            queryWrapper.like("REQU_LOC_NAME", "%" + warehouseReqSearchParam.getRequLocName() + "%");
//        }
//
//        if(StringUtils.isNotEmpty(warehouseReqSearchParam.getGoodsNo())) {
//            queryWrapper.like("GOODS_NO", "%" + warehouseReqSearchParam.getGoodsNo() + "%");
//        }
//
//        if(StringUtils.isNotEmpty(warehouseReqSearchParam.getGoodsName())) {
//            queryWrapper.like("GOODS_NAME", "%" + warehouseReqSearchParam.getGoodsName() + "%");
//        }
//
//        if(StringUtils.isNotEmpty(warehouseReqSearchParam.getRequName())) {
//            queryWrapper.like("REQU_NAME", "%" + warehouseEntrySearchParam.getGoodsNo() + "%");
//        }
//
//        queryWrapper.groupBy("GOODS_NO").groupBy("SUPPLIER_NAME");
        List<Map> list = this.warehouseReqDetailMapper.getRequestedGoodsList(warehouseReqSearchParam);
        return list;
    }
}