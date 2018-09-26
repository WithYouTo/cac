package com.qcap.core.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.core.dao.ManagerMapper;
import com.qcap.core.dao.RoleMapper;
import com.qcap.core.model.RoleMenu;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.RoleSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.core.model.Role;

@Service
@Transactional
public class RoleSrvImpl implements RoleSrv {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ManagerMapper managerMapper;

	@Override
	public void insertRole(Role role) {
		int num = this.roleMapper.getNewRoleNum();
		role.setNum(num);
		roleMapper.insertRole(role);
	}

	@Override
	public Role selectById(String roleId) {
		return roleMapper.getById(roleId);
	}

	@Override
	public String getPname(Integer pnum) {
		return roleMapper.getPname(pnum);
	}

	@Override
	public List<ZTreeNode> roleTreeList() {
		return roleMapper.roleTreeList();
	}

	@Override
	public List<Map<String, Object>> selectRoles(String roleName, String companyCode) {
		List<Map<String, Object>> roles = roleMapper.selectRoles(roleName, companyCode);
		for (Map role : roles) {
			if (role.containsKey("pnum")) {
				Integer pnum = Integer.valueOf(role.get("pnum").toString());
				String pname = roleMapper.getPname(pnum);
				role.put("pName", pname);
			}
		}
		return roles;
	}

	@Override
	public String checkNum(Integer num) {
		return roleMapper.checkNum(num);
	}

	@Override
	public void updateById(Role role) {
		roleMapper.updateById(role);
	}

	@Override
	public void delRoleById(String roleId) {
		Role role = roleMapper.getById(roleId);

		// 删除角色
		roleMapper.deleteById(roleId);

		// 删除该角色所有的权限
		roleMapper.deleteRolesByrolenum(role.getNum());
	}

	@Override
	public void setAuthority(String roleId, String ids) {
		Role role = this.roleMapper.getById(roleId);
		Integer rolenum = role.getNum();
		// 删除该角色所有的权限
		this.roleMapper.deleteRolesByrolenum(rolenum);

		// 添加新的权限
		for (String id : ids.split(",")) {
			RoleMenu roleMenu = new RoleMenu();
			roleMenu.setRolenum(rolenum);
			roleMenu.setMenuid(id);
			this.roleMapper.insertRoleMenu(roleMenu);
		}
	}

	@Override
	public List<ZTreeNode> roleTreeListByRoleId(String userId) {
		List<String> roleids = this.managerMapper.getRoleIdById(userId);
		// String roleid = theUser.getRoleid();
		if (roleids.isEmpty()) {
			List<ZTreeNode> roleTreeList = this.roleMapper.roleTreeList();
			return roleTreeList;
		} else {

			int size = roleids.size();
			String[] strArray = (String[]) roleids.toArray(new String[size]);
			List<ZTreeNode> roleTreeListByUserId = this.roleMapper.roleTreeListByRoleId(strArray);
			return roleTreeListByUserId;
		}
	}

	@Override
	public Role selectByRoleNum(Integer rolenum) {
		return roleMapper.getByRoleNum(rolenum);
	}

	@Override
	public Role selectByRoleId(String id) {
		return roleMapper.getByRoleId(id);

	}

	@Override
	public String selectExistNum(Map params) {
		// TODO Auto-generated method stub
		return this.roleMapper.selectExistNum(params);
	}

}
