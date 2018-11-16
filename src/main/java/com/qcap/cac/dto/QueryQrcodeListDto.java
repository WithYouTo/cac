package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryQrcodeListDto {

	// @NotBlank(message = "areaCode不能为空")
	@ApiModelProperty(value = "二维码类型")
	private String qrcodeType;

	@ApiModelProperty(value = "项目编码")
	private String programCode;

	public String getQrcodeType() {
		return qrcodeType;
	}

	public void setQrcodeType(String qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	@Override
	public String toString() {
		return "QueryQrcodeListDto{" +
				"qrcodeType='" + qrcodeType + '\'' +
				", programCode='" + programCode + '\'' +
				'}';
	}
}