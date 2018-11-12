package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryQrcodeListDto {

	// @NotBlank(message = "areaCode不能为空")
	@ApiModelProperty(value = "二维码类型")
	private String qrcodeType;

	public String getQrcodeType() {
		return qrcodeType;
	}

	public void setQrcodeType(String qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	@Override
	public String toString() {
		return "QueryQrcodeListDto [qrcodeType=" + qrcodeType + "]";
	}

}