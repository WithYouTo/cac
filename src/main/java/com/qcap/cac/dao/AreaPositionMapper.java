package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbAreaPosition;


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


}
