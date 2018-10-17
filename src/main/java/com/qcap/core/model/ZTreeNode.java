package com.qcap.core.model;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;

/**
 *
 * jquery ztree 插件的节点
 *
 * @author fengshuonan
 * @date 2017年2月17日 下午8:25:14
 */
public class ZTreeNode {
	/**
	 * 节点id
	 */
	private String id;

	/**
	 * 父节点id
	 */
	private String pid;
	/**
	 * 节点名称
	 */
	private String name;
	/**
	 * 是否打开节点
	 */
	private Boolean open;

	private String rootName = "top";
	/**
	 * 是否被选中
	 */
	private Boolean checked;

	private List<ZTreeNode> children;

	private String desc1;

	private String desc2;

	private String desc3;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = BooleanUtils.toBoolean(open);
	}

	public Boolean getIsOpen() {
		return open;
	}

	public void setIsOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = BooleanUtils.toBoolean(checked);
	}

	public List<ZTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<ZTreeNode> children) {
		this.children = children;
	}

	public String getRootName() {
		return rootName;
	}

	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	public String getDesc1() {
		return desc1;
	}

	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}

	public String getDesc2() {
		return desc2;
	}

	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}

	public String getDesc3() {
		return desc3;
	}

	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}

	public static ZTreeNode createParent() {
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked("true");
		zTreeNode.setId("0");
		zTreeNode.setName("顶级");
		zTreeNode.setOpen("true");
		zTreeNode.setPid("top");
		zTreeNode.setChildren(null);
		return zTreeNode;
	}
}
