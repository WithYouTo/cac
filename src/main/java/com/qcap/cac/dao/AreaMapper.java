package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbArea;
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

    List<Map> selectAreaList(@Param("areaCode") String areaCode);

    List<Map> initTree();

    @Select("select IFNULL(MAX(SUBSTRING(AREA_CODE, - 3)),99) + 1 from tb_area t WHERE `LEVEL` = #{level} ")
    Integer selectMaxNum(Integer level);


}
