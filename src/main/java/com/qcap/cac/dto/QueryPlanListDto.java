package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryPlanListDto {

	// @NotBlank(message = "areaCode不能为空")
	@ApiModelProperty(value = "区域")
	private String areaCode;

	// @NotBlank(message = "planTimeType不能为空")
	@ApiModelProperty(value = "清洁标准")
	private String standardCode;

	@ApiModelProperty(value = "时间类型")
	private String planTimeType;

	@ApiModelProperty(value = "清洁时间")
	private String time;

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

	public String getPlanTimeType() {
		return planTimeType;
	}

	public void setPlanTimeType(String planTimeType) {
		this.planTimeType = planTimeType;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "QueryPlanListDto [areaCode=" + areaCode + ", standardCode=" + standardCode + ", planTimeType="
				+ planTimeType + ", time=" + time + "]";
	}

}