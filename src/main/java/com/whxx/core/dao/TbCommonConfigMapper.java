package com.whxx.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxx.core.entity.TbCommonConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Repository
public interface TbCommonConfigMapper extends BaseMapper<TbCommonConfig>
{
    List<String> selectDistinctType();

}
