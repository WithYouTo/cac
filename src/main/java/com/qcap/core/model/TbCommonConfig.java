package com.qcap.core.model;

import java.io.Serializable;

public class TbCommonConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 主键
	 */
	private String id;
	
	/**
	 * 配置键
	 */	
	private String key;
	
	/**
	 * 配置值
	 */	
	private String value;
	
	/**
	 * 配置类型
	 */	
	private String type;
	
	/**
	 * 备注
	 */
	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	
}
