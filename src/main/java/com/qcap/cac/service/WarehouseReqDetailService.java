package com.qcap.cac.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseReqdetail;

import javax.validation.Valid;
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


    List<Map<String, Object>> getRequestedList(WarehouseReqDto warehouseReqDto);

    List<Map<String,Object>>  getReqDetailList(String warehouseRequId);

    /**
     * 返回warehouseRequId
     * @param warehouseReqdetail
     * @return
     */
    String insertReqDetail(TbWarehouseReqdetail warehouseReqdetail);

}
