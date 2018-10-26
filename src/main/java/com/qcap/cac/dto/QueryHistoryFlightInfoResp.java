package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryHistoryFlightInfoResp {

	@ApiModelProperty(value = "员工编号")
	private String flightName;

	@ApiModelProperty(value = "签到时间")
	private String eventType;

	@ApiModelProperty(value = "签到地点")
	private String eventTypeName;

	@ApiModelProperty(value = "签到照片")
	private String flightType;

	@ApiModelProperty(value = "工作安排")
	private String flightTypName;

	@ApiModelProperty(value = "签到照片")
	private String areaCode;

	@ApiModelProperty(value = "工作安排")
	private String areaName;

	@ApiModelProperty(value = "签到照片")
	private String guaranteeType;

	@ApiModelProperty(value = "工作安排")
	private String planningTakeoffTime;

	@ApiModelProperty(value = "签到照片")
	private String estimatedTakeoffTime;

	@ApiModelProperty(value = "工作安排")
	private String aircraftType;

	@ApiModelProperty(value = "序号")
	private String lineNo;

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTypeName() {
		return eventTypeName;
	}

	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}

	public String getFlightType() {
		return flightType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public String getFlightTypName() {
		return flightTypName;
	}

	public void setFlightTypName(String flightTypName) {
		this.flightTypName = flightTypName;
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

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getPlanningTakeoffTime() {
		return planningTakeoffTime;
	}

	public void setPlanningTakeoffTime(String planningTakeoffTime) {
		this.planningTakeoffTime = planningTakeoffTime;
	}

	public String getEstimatedTakeoffTime() {
		return estimatedTakeoffTime;
	}

	public void setEstimatedTakeoffTime(String estimatedTakeoffTime) {
		this.estimatedTakeoffTime = estimatedTakeoffTime;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public String toString() {
		return "QueryHistoryFlightInfoResp [flightName=" + flightName + ", eventType=" + eventType + ", eventTypeName="
				+ eventTypeName + ", flightType=" + flightType + ", flightTypName=" + flightTypName + ", areaCode="
				+ areaCode + ", areaName=" + areaName + ", guaranteeType=" + guaranteeType + ", planningTakeoffTime="
				+ planningTakeoffTime + ", estimatedTakeoffTime=" + estimatedTakeoffTime + ", aircraftType="
				+ aircraftType + ", lineNo=" + lineNo + "]";
	}

}