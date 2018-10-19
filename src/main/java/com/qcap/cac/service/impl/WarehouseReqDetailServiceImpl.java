package com.qcap.cac.service.impl;


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

        String warehouseRequId = warehouseReqdetail.getWarehouseRequId();
        if(StringUtils.isEmpty(warehouseRequId)){
            warehouseRequId = UUIDUtils.getUUID();
            warehouseReqdetail.setWarehouseRequId(warehouseRequId);
        }

        warehouseReqdetail.setWarehouseReqdetailId(UUIDUtils.getUUID());
        warehouseReqdetail.setRequStatus("INIT");
        warehouseReqdetail.setCreateEmp("SYS");
        this.warehouseReqDetailMapper.insert(warehouseReqdetail);
        return warehouseRequId;
    }
}