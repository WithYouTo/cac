package com.qcap.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbRoleMenu;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author PH
 * @since 2018-07-31
 */
@Repository
public interface TbRoleMenuMapper extends BaseMapper<TbRoleMenu> {
	List<TbRoleMenu> getRoleMenuByRoleId(@Param("roleId") String roleId);

	void updateRoleMenuByMenuCode(@Param("oldCode") String oldCode, @Param("newCode") String newCode);
}
