package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.WarehouseReqDetailMapper;
import com.qcap.cac.dao.WarehouseRequMapper;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.service.IWarehouseRequService;
import com.qcap.core.common.CoreConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
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
public class WarehouseRequServiceImpl extends ServiceImpl<WarehouseRequMapper, TbWarehouseRequ> implements IWarehouseRequService {

    @Resource
    private WarehouseRequMapper warehouseRequMapper;

    @Resource
    private WarehouseReqDetailMapper warehouseReqDetailMapper;

    @Override
    public List<Map<String,String>> getRequList(WarehouseReqDto warehouseReqDto) {
        List<Map<String,String>> list = new ArrayList<>();

        if(StringUtils.isNotEmpty(warehouseReqDto.getStoreroomId())){
            list = this.warehouseRequMapper.getRequGoodsList(warehouseReqDto);
        }
        return list;
    }

    @Override
    public Integer commitRequ(TbWarehouseRequ warehouseRequ) {
        Integer count = 0;//更新记录数

        String warehouseRequId = warehouseRequ.getWarehouseRequId();
        if(StringUtils.isEmpty(warehouseRequId)){
            throw  new RuntimeException("请先选择一条记录");
        }

        if (!CommonConstant.WAREHOUSE_REQ_STATUS_INIT.equals(warehouseRequ.getRequStatus())){
            throw  new RuntimeException("选择的记录不是已申请，无法进行修改操作");
        }
        warehouseRequ.setRequStatus(CommonConstant.WAREHOUSE_REQ_STATUS_COMMIT);
        //更新主表
        this.warehouseRequMapper.updateById(warehouseRequ);
        //更新明细表
        count = this.warehouseReqDetailMapper.updateReqDetailStatus(warehouseRequ);
        return count;
    }

    @Override
    public Integer delete(String warehouseRequId) {
        Integer count = 0;//更新记录数

        if(StringUtils.isEmpty(warehouseRequId)){
            throw  new RuntimeException("请先选择一条记录");
        }

        TbWarehouseRequ warehouseRequ = this.warehouseRequMapper.selectById(warehouseRequId);

        if(null == warehouseRequ){
            throw  new RuntimeException("根据选择记录没有查询到信息");
        }

        if (!CommonConstant.WAREHOUSE_REQ_STATUS_INIT.equals(warehouseRequ.getRequStatus())){
            throw  new RuntimeException("选择的记录不是已申请，无法进行删除操作");
        }
        //删除主表
        this.warehouseRequMapper.deleteById(warehouseRequId);
        //删除明细表
        count = this.warehouseReqDetailMapper.deleteReqDetailStatus(warehouseRequ);
        return count;
    }
}