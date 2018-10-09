package com.qcap.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbManagerOrg;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Repository
public interface TbManagerOrgMapper extends BaseMapper<TbManagerOrg> {
	List<String> getManagerIdByOrgId(@Param("orgId") String orgId);
}
