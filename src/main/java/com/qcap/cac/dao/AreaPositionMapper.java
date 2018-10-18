package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbAreaPosition;
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
public interface AreaPositionMapper extends BaseMapper<TbAreaPosition> {

    List<Map> selectAreaList(@Param("areaCode") String areaCode);

    List<Map> initTree();


}
