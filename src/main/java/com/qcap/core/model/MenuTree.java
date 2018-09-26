package com.qcap.core.model;

import java.util.List;

public class MenuTree {

	private String title;

	private String name;

	private String icon;

	private String jump;

	private String code;

	private String pcode;

	private List<MenuTree> list;

	// 是否是菜单 1：菜单 0：按钮
	private int isMenu;

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getJump() {
		return jump;
	}

	public void setJump(String jump) {
		this.jump = jump;
	}

	public List<MenuTree> getList() {
		return list;
	}

	public void setList(List<MenuTree> list) {
		this.list = list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return code == null ? false : this.code.equals(((MenuTree) obj).getCode());
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

}
