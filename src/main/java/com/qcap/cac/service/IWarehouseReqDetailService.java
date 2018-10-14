package com.qcap.cac.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.WarehouseReqSearchParam;
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
public interface IWarehouseReqDetailService extends IService<TbWarehouseReqdetail> {


    List<Map> getRequestedList(WarehouseReqSearchParam warehouseReqSearchParam);

}
