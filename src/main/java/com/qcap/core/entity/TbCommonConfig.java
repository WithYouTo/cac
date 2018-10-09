package com.qcap.core.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public class TbCommonConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 配置键
	 */
	private String keyName;
	/**
	 * 配置值
	 */
	private String keyValue;
	/**
	 * 配置类型
	 */
	private String type;
	/**
	 * 备注
	 */
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TbCommonConfig{" + ", id=" + id + ", keyName=" + keyName + ", keyValue=" + keyValue + ", type=" + type
				+ ", remark=" + remark + "}";
	}
}
