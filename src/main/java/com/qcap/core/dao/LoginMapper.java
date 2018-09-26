package com.qcap.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qcap.core.model.Menu;
import com.qcap.core.model.MenuTree;
import com.qcap.cac.model.TbUser;

@Repository
public interface LoginMapper {
	TbUser getTbUser(@Param("account") String username);

	List<MenuTree> getMenuList(@Param("id") String managerId);

	List<Menu> getAuthList(@Param("id") String managerId);

	List<MenuTree> getMenuListByRoleName(String name);
}
