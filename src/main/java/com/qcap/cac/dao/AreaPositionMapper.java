package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbAreaPosition;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     *
     * 判斷區域編碼是否已經存在崗位中
     * @author 曾欣
     * @date 2018/11/15 15:31
     * @param
     * @param areaCode
     * @return java.lang.Integer
     */

    Integer checkExistPositionByAreaCodes(@Param("areaCode") String areaCode,@Param("shift") String shift);


    /**
     *
     * 查询项目编码最后三位的最大值
     * @author 曾欣
     * @date 2018/11/15 15:31
     * @param
     * @return java.lang.Integer
     */

    @Select("select IFNULL(MAX(SUBSTRING(POSITION_CODE, -3) + 1),'001') from tb_area_position")
    String selectMaxSuffixNum();


}
