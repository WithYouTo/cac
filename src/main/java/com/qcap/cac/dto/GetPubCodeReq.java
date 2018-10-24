package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetPubCodeReq {

	@NotBlank(message = "配置编码不能为空")
	@ApiModelProperty(value = "配置编码")
	private String configCode;

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	@Override
	public String toString() {
		return "GetPubCodeReq [configCode=" + configCode + "]";
	}

}