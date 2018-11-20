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
	
	@ApiModelProperty(value = "说明")
	private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "QueryQrcodeListDto [qrcodeType=" + qrcodeType + ", programCode=" + programCode + ", remark=" + remark
				+ "]";
	}

}