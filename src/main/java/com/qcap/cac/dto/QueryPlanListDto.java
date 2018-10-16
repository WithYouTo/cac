package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryPlanListDto {

	// @NotBlank(message = "areaCode不能为空")
	@ApiModelProperty(value = "区域")
	private String areaCode;

	// @NotBlank(message = "planTimeType不能为空")
	@ApiModelProperty(value = "时间类型")
	private String planTimeType;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPlanTimeType() {
		return planTimeType;
	}

	public void setPlanTimeType(String planTimeType) {
		this.planTimeType = planTimeType;
	}

	@Override
	public String toString() {
		return "QueryPlanListDto [areaCode=" + areaCode + ", planTimeType=" + planTimeType + "]";
	}

}