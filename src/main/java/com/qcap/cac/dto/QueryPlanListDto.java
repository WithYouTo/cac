package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryPlanListDto {

	// @NotBlank(message = "areaCode不能为空")
	@ApiModelProperty(value = "区域")
	private String areaCode;

	// @NotBlank(message = "planTimeType不能为空")
	@ApiModelProperty(value = "活动内容")
	private String standardCode;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	@Override
	public String toString() {
		return "QueryPlanListDto [areaCode=" + areaCode + ", standardCode=" + standardCode + "]";
	}

}