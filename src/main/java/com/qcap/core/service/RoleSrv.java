package com.qcap.core.service;

import java.util.List;
import java.util.Map;

import com.qcap.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;

import com.qcap.core.model.Role;

public interface RoleSrv {
	void insertRole(Role role);

	Role selectById(String roleId);

	String getPname(Integer pnum);

	List<ZTreeNode> roleTreeList();

	List<Map<String, Object>> selectRoles(@Param("roleName") String roleName, @Param("companyCode") String companyCode);

	String checkNum(Integer num);

	void updateById(Role role);

	void delRoleById(String roleId);

	void setAuthority(String roleId, String ids);

	List<ZTreeNode> roleTreeListByRoleId(String userId);

	Role selectByRoleNum(Integer rolenum);

	Role selectByRoleId(String id);

	String selectExistNum(Map params);
}
