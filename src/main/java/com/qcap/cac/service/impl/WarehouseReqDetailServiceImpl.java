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
        List<Map> list = this.warehouseReqDetailMapper.getRequestedGoodsList(warehouseReqSearchParam);
        return list;
    }
}