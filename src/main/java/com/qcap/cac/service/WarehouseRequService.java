package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseRequ;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface WarehouseRequService extends IService<TbWarehouseRequ> {


    void getRequList(IPage<Map<String,String>> page, @Valid WarehouseReqDto warehouseReqDto);

    void commitRequ(TbWarehouseRequ warehouseRequ);

    void delete(String warehouseRequId);

    void insertWarehouseRequ(TbWarehouseRequ warehouseRequ);

    void updateWarehouseRequ(TbWarehouseRequ warehouseRequ);

}
