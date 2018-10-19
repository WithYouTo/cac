package com.qcap.cac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.AreaPositionDto;
import com.qcap.cac.entity.TbAreaPosition;

import java.util.List;
import java.util.Map;

public interface AreaPositionSrv extends IService<TbAreaPosition> {

    /**
     * 加载区域树
     * @return
     */
    List<Map> initTree();


    /**
     * 根据areaPositionDto查询岗位信息
     * @param areaPositionDto
     * @return
     */
    List<TbAreaPosition> getAreaPositionList(AreaPositionDto areaPositionDto);


    /**
     * 新增岗位
     * @param areaPosition
     * @return
     */
    Integer insertAreaPosition(TbAreaPosition areaPosition) throws Exception;

    /**
     * 修改岗位
     * @param areaPosition
     * @return
     */
    Integer updateAreaPosition(TbAreaPosition areaPosition);


    /**
     * 删除岗位
     * @param areaPositionId
     * @return
     */
    Integer deleteAreaPosition(String areaPositionId);


}
