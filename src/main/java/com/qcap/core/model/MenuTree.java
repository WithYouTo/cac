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

	public String getParentCode() {
		return pcode;
	}

	public void setParentCode(String pcode) {
		this.pcode = pcode;
	}

}
