package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetPubCodeResp {

	@ApiModelProperty(value = "编码")
	private String value;

	@ApiModelProperty(value = "说明")
	private String text;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "GetPubCodeResp [value=" + value + ", text=" + text + "]";
	}

}