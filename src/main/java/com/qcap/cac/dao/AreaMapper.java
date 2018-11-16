package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.entity.TbArea;
import com.qcap.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
public interface AreaMapper extends BaseMapper<TbArea> {

    List<Map<String, Object>> selectAreaList(IPage<Map<String, Object>> page,@Param("paramMap") Map paramMap);

    List<ZTreeNode> initTree(@Param("paramMap") Map paramMap);

    @Select("select IFNULL(MAX(SUBSTRING(AREA_CODE, - 3)),-1) from tb_area t WHERE `LEVEL` = #{level} ")
    Integer selectMaxNum(Integer level);

    @Select("select (IFNULL(MAX(SEQ_NO),0) + 1) from tb_area t WHERE `SUPER_AREA_CODE` = #{superAreaCode} ")
    Integer selectMaxSeqNo(String superAreaCode);

    @Select("select PROGRAM_CODE from tb_area t WHERE `AREA_CODE` = #{areaCode} limit 1 ")
    String selectProgramCodeByAreaCode(String areaCode);

    /**
     *
     * 删除区域之前，判断是否有计划已经包含
     * @author 曾欣
     * @date 2018/11/12 15:51
     * @param
     * @param areaCode
     * @return java.lang.Integer
     */

    Integer checkPlanExistAreaCode(String areaCode);

    /**
     *
     * 查询父级的最大区域编码
     * @author 曾欣
     * @date 2018/11/13 19:42
     * @param
     * @return java.lang.Integer
     */

    @Select("select (IFNULL(MAX(AREA_CODE),-1) + 1) from tb_area t WHERE `LEVEL` = -1")
    Integer selectParentLevelMaxNum();

    @Select("select (IFNULL(MAX(SEQ_NO),0) + 1) from tb_area t WHERE `LEVEL` = -1 ")
    Integer selectParentLevelMaxSeqNo();

    /**
     *
     * 删除区域之前，判断是否有岗位已经包含
     * @author 曾欣
     * @date 2018/11/12 15:51
     * @param
     * @param areaCode
     * @return java.lang.Integer
     */

    Integer checkPositionExistAreaCode(String areaCode);

    /**
     *
     * 删除区域之前，判断是否有子区域
     * @author 曾欣
     * @date 2018/11/13 17:00
     * @param
     * @param areaCode
     * @return java.lang.Integer
     */

    Integer checkSubAreaByAreaCode(String areaCode);



}
