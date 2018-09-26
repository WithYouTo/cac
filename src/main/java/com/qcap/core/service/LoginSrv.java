package com.qcap.core.service;

import java.util.List;

import com.qcap.core.model.Menu;
import com.qcap.core.model.MenuTree;
import com.qcap.cac.model.TbUser;

public interface LoginSrv {
	TbUser getTbUser(String username);

	List<MenuTree> getMenuList(String managerId);

	List<Menu> getAuthList(String managerId);

	List<MenuTree> getMenuListByRoleName(String name);
}
