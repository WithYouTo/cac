package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAreaReq {

	@ApiModelProperty(value = "岗位编号")
	private String positionCode;

	@ApiModelProperty(value = "房间类型")
	private String areaType;

	@ApiModelProperty(value = "事件类型")
	private String eventType;

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "GetAreaReq [positionCode=" + positionCode + ", areaType=" + areaType + ", eventType=" + eventType + "]";
	}

}