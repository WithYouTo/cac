package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlanDto {

	@ApiModelProperty(value = "主键")
	private String planId;

	@ApiModelProperty(value = "区域编码")
	private String areaCode;

	@ApiModelProperty(value = "区域名称")
	private String areaName;

	@ApiModelProperty(value = "房间类型")
	private String areaType;

	@ApiModelProperty(value = "标准编码")
	private String standardCode;

	@ApiModelProperty(value = "时间类型")
	private String planTimeType;

	@ApiModelProperty(value = "月")
	private String month;

	@ApiModelProperty(value = "日")
	private String day;

	@ApiModelProperty(value = "周")
	private String week;

	@ApiModelProperty(value = "开始时间")
	private String planStartTime;

	@ApiModelProperty(value = "结束时间")
	private String planEndTime;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}

	public String getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(String planEndTime) {
		this.planEndTime = planEndTime;
	}

	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", areaCode=" + areaCode + ", areaName=" + areaName + ", areaType="
				+ areaType + ", standardCode=" + standardCode + ", planTimeType=" + planTimeType + ", month=" + month
				+ ", day=" + day + ", week=" + week + ", planStartTime=" + planStartTime + ", planEndTime="
				+ planEndTime + "]";
	}

}