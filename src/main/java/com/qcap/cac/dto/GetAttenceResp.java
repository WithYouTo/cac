package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAttenceResp {

	@ApiModelProperty(value = "日期")
	private String day;

	@ApiModelProperty(value = "班次")
	private String shift;

	@ApiModelProperty(value = "签到状态")
	private String attenceStatus;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getAttenceStatus() {
		return attenceStatus;
	}

	public void setAttenceStatus(String attenceStatus) {
		this.attenceStatus = attenceStatus;
	}

	@Override
	public String toString() {
		return "GetAttenceResp [day=" + day + ", shift=" + shift + ", attenceStatus=" + attenceStatus + "]";
	}

}