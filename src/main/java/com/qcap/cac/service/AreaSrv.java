package com.qcap.cac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.entity.TbArea;

import java.util.List;
import java.util.Map;

public interface AreaSrv extends IService<TbArea> {

    /**
     * 加载区域树
     * @return
     */
    List<Map> initTree();


    /**
     * 根据areaCode查询管辖区域
     * @param areaCode
     * @return
     */
    List<Map> getAreaList(String areaCode);


    /**
     * 新增区域
     * @param area
     * @return
     */
    TbArea insertArea(TbArea area);


    /**
     * 修改区域
     * @param area
     * @return
     */
    TbArea updateArea(TbArea area);


}
