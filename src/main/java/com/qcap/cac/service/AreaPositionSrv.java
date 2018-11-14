package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.AreaPositionDto;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.core.model.ZTreeNode;

import javax.validation.Valid;
import java.util.List;

public interface AreaPositionSrv extends IService<TbAreaPosition> {

    /**
     * 加载区域树
     * @return
     */
    List<ZTreeNode> initTree();


    /**
     * 根据areaPositionDto查询岗位信息
     * @param areaPositionDto
     * @return
     */
    IPage<TbAreaPosition> getAreaPositionList(IPage<TbAreaPosition> page, @Valid AreaPositionDto areaPositionDto);


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
    Integer updateAreaPosition(TbAreaPosition areaPosition) throws Exception;


    /**
     * 删除岗位
     * @param areaPositionId
     * @return
     */
    Integer deleteAreaPosition(String areaPositionId);

    /**
     * 根据岗位类型查询岗位类型名称
     * @param positionType
     * @return
     */

    String selectPositionTypeName(String positionType);


}
