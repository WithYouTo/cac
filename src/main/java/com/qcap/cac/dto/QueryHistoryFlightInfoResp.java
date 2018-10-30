package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryHistoryFlightInfoResp {

	@ApiModelProperty(value = "航班名称")
	private String flightName;

	@ApiModelProperty(value = "事件类型")
	private String eventType;

	@ApiModelProperty(value = "事件类型")
	private String eventTypeName;

	@ApiModelProperty(value = "航班类型")
	private String flightType;

	@ApiModelProperty(value = "航班类型")
	private String flightTypName;

	@ApiModelProperty(value = "区域编码")
	private String areaCode;

	@ApiModelProperty(value = "区域名称")
	private String areaName;

	@ApiModelProperty(value = "保障等级")
	private String guaranteeType;

	@ApiModelProperty(value = "保障等级")
	private String guaranteeTypeName;

	@ApiModelProperty(value = "计划起飞/到达时间")
	private String planningTakeoffTime;

	@ApiModelProperty(value = "预计起飞/到达时间")
	private String estimatedTakeoffTime;

	@ApiModelProperty(value = "机型")
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

	public String getGuaranteeTypeName() {
		return guaranteeTypeName;
	}

	public void setGuaranteeTypeName(String guaranteeTypeName) {
		this.guaranteeTypeName = guaranteeTypeName;
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
				+ areaCode + ", areaName=" + areaName + ", guaranteeType=" + guaranteeType + ", guaranteeTypeName="
				+ guaranteeTypeName + ", planningTakeoffTime=" + planningTakeoffTime + ", estimatedTakeoffTime="
				+ estimatedTakeoffTime + ", aircraftType=" + aircraftType + ", lineNo=" + lineNo + "]";
	}

}