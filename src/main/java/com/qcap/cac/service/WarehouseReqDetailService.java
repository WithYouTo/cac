package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;

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
public interface WarehouseReqDetailService extends IService<TbWarehouseReqdetail> {

    /**
     * 查询领用明细
     * @param page
     * @param warehouseReqDto
     */
    void getRequestedList(IPage<Map<String, Object>> page,WarehouseReqDto warehouseReqDto);


    /**
     * 查询领用申请
     * @param page
     * @param warehouseRequId
     */
    void getReqDetailList(IPage<Map<String, Object>> page,String warehouseRequId);


    /**
     * 返回warehouseRequId
     * @param warehouseReqdetail
     * @return
     */
    String insertReqDetail(TbWarehouseReqdetail warehouseReqdetail);


    /**
     * 查询物品编码下拉框
     * @param storeroomId
     * @return
     */
    List<Map<String,String>> GoodsNoAppList(String storeroomId);

}
