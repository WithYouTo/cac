package com.qcap.cac.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseRequ;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface IWarehouseRequService extends IService<TbWarehouseRequ> {


    List<Map<String,String>> getRequList(WarehouseReqDto warehouseReqDto);

    Integer commitRequ(TbWarehouseRequ warehouseRequ);

    Integer delete(String warehouseRequId);

}
