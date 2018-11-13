package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.AreaDto;
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
    void getAreaList(IPage<Map<String, Object>> page, String areaCode);


    /**
     * 新增区域
     * @param areaDto
     * @return
     */
    TbArea insertArea(AreaDto areaDto);


    /**
     * 修改区域
     * @param area
     * @return
     */
    TbArea updateArea(TbArea area);

    /**
     *
     * 删除区域
     * @author 曾欣
     * @date 2018/11/12 15:36
     * @param
     * @param areaId
     * @return void
     */
    public void deleteArea(String areaId);


}
