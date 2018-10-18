package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryPlanEventListDto {

	@ApiModelProperty(value = "区域")
	private String areaCode;

	@ApiModelProperty(value = "活动内容")
	private String standardCode;

	@ApiModelProperty(value = "区域")
	private String eventType;

	@ApiModelProperty(value = "活动内容")
	private String guaranteeType;

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

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	@Override
	public String toString() {
		return "QueryPlanEventListDto [areaCode=" + areaCode + ", standardCode=" + standardCode + ", eventType="
				+ eventType + ", guaranteeType=" + guaranteeType + "]";
	}

}