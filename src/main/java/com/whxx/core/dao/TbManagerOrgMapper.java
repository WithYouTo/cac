package com.whxx.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whxx.core.entity.TbManagerOrg;
import org.apache.ibatis.annotations.Param;
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
public interface TbManagerOrgMapper extends BaseMapper<TbManagerOrg>
{
    List<String> getManagerIdByOrgId(@Param("orgId") String orgId);
}
