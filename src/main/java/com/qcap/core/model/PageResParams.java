package com.qcap.core.model;

import java.io.Serializable;

public class PageResParams implements Serializable {

	private static final long serialVersionUID = 5284600372269887394L;

	private Integer code;

	private String desc;

	private long count;

	private Object data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public static PageResParams newInstance(Integer code, String desc, long count, Object data) {
		PageResParams pageResParams = new PageResParams();
		pageResParams.setCode(code);
		pageResParams.setDesc(desc);
		pageResParams.setCount(count);
		pageResParams.setData(data);
		return pageResParams;
	}
}
