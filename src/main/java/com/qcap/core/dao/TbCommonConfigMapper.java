package com.qcap.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbCommonConfig;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Repository
public interface TbCommonConfigMapper extends BaseMapper<TbCommonConfig> {
	List<String> selectDistinctType();

}
