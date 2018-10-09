package com.qcap.core.dao;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbLoginLog;

/**
 * <p>
 * 登录记录 Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Repository
public interface TbLoginLogMapper extends BaseMapper<TbLoginLog> {
}
