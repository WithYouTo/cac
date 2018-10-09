package com.qcap.core.entity;

import java.io.Serializable;

/**
 * <p>
 * 角色和菜单关联表
 * </p>
 *
 * @author PH
 * @since 2018-07-31
 */
public class TbRoleMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 菜单code
	 */
	private String menuCode;
	/**
	 * 角色num
	 */
	private Integer roleNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public Integer getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(Integer roleNum) {
		this.roleNum = roleNum;
	}

	@Override
	public String toString() {
		return "TbRoleMenu{" + ", id=" + id + ", menuCode=" + menuCode + ", roleNum=" + roleNum + "}";
	}
}
