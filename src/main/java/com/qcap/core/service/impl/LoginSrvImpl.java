package com.qcap.core.service.impl;

import java.util.List;

import com.qcap.core.dao.LoginMapper;
import com.qcap.core.model.Menu;
import com.qcap.core.model.MenuTree;
import com.qcap.core.service.LoginSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.model.TbUser;

@Service
@Transactional
public class LoginSrvImpl implements LoginSrv {

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public TbUser getTbUser(String username) {
		TbUser manager = loginMapper.getTbUser(username);
		return manager;
	}

	@Override
	public List<MenuTree> getMenuList(String managerId) {
		return loginMapper.getMenuList(managerId);
	}

	@Override
	public List<Menu> getAuthList(String managerId) {
		return loginMapper.getAuthList(managerId);
	}

	@Override
	public List<MenuTree> getMenuListByRoleName(String name) {
		// TODO Auto-generated method stub
		return this.loginMapper.getMenuListByRoleName(name);
	}

}
