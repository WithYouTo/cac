package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbAreaPosition;

import java.util.Map;


/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface AreaPositionMapper extends BaseMapper<TbAreaPosition> {


    String selectPositionTypeName(String positionType);

    /**
     *
     * 查询岗位是否排班
     * @author 曾欣
     * @date 2018/11/12 18:11
     * @param
     * @param paramMap
     * @return java.lang.Integer
     */

    Integer checkTaskByPosition(Map<String,String> paramMap);

    /**
     * 未完成状态的任务无法删除
     *
     * @author 曾欣
     * @date 2018/11/12 18:11
     * @param
     * @param positionCode
     * @return java.lang.Integer
     */

    Integer checkUndoStatusByPosition(String positionCode);


}
