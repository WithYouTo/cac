package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.WarehouseRequMapper;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseRequ;
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
public class WarehouseRequServiceImpl extends ServiceImpl<WarehouseRequMapper, TbWarehouseRequ> implements IWarehouseRequService {

    @Resource
    private WarehouseRequMapper warehouseRequMapper;

    @Override
    public List<Map<String,String>> getRequList(WarehouseReqDto warehouseReqDto) {
        List<Map<String,String>> list = new ArrayList<>();

        if(StringUtils.isNotEmpty(warehouseReqDto.getStoreroomId())){
            list = this.warehouseRequMapper.getRequGoodsList(warehouseReqDto);
        }
        return list;
    }
}