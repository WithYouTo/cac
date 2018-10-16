package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class EventTaskRestDto {
	
	@NotBlank(message="航班号不能为空")
	@ApiModelProperty(value="航班号")
	private String flightName;
	
	@NotBlank(message="事件类型不能为空")
	@ApiModelProperty(value="事件类型")
	private String eventType;
	
	@NotBlank(message="航班类型不能为空")
	@ApiModelProperty(value="航班类型")
	private String flightType;
	
	@NotBlank(message="登机口/廊桥不能为空")
	@ApiModelProperty(value="登机口/廊桥")
	private String areaName;
	
	@NotBlank(message="区域编码不能为空")
	@ApiModelProperty(value="区域编码")
	private String areaCode;
	
	@NotBlank(message="保障等级不能为空")
	@ApiModelProperty(value="保障等级")
	private String guaranteeType;
	
	@NotBlank(message="计划起飞、到达时间不能为空")
	@ApiModelProperty(value="计划起飞、到达时间")
	private String planningTakeoffTime;
	
	@NotBlank(message="预计起飞/到达时间不能为空")
	@ApiModelProperty(value="预计起飞/到达时间")
	private String estimatedTakeoffTime;
	
	@ApiModelProperty(value="机型")
	private String aircraftType;
	
	@NotBlank(message="app登陆人不能为空")
	@ApiModelProperty(value="app登陆人")
	private String loginName;

	public String getFlightName() {
		return flightName;
	}

	public String getEventType() {
		return eventType;
	}

	public String getFlightType() {
		return flightType;
	}

	public String getAreaName() {
		return areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public String getPlanningTakeoffTime() {
		return planningTakeoffTime;
	}

	public String getEstimatedTakeoffTime() {
		return estimatedTakeoffTime;
	}

	public String getAircraftType() {
		return aircraftType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setFlightType(String flightType) {
		this.flightType = flightType;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public void setPlanningTakeoffTime(String planningTakeoffTime) {
		this.planningTakeoffTime = planningTakeoffTime;
	}

	public void setEstimatedTakeoffTime(String estimatedTakeoffTime) {
		this.estimatedTakeoffTime = estimatedTakeoffTime;
	}

	public void setAircraftType(String aircraftType) {
		this.aircraftType = aircraftType;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return "EventTaskRestDto [flightName=" + flightName + ", eventType=" + eventType + ", flightType=" + flightType
				+ ", areaName=" + areaName + ", areaCode=" + areaCode + ", guaranteeType=" + guaranteeType
				+ ", planningTakeoffTime=" + planningTakeoffTime + ", estimatedTakeoffTime=" + estimatedTakeoffTime
				+ ", aircraftType=" + aircraftType + ", loginName=" + loginName + "]";
	}
	
	
	
}
