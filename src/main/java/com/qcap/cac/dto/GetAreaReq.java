package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAreaReq {

	@ApiModelProperty(value = "岗位编号")
	private String positionCode;

	@ApiModelProperty(value = "房间类型")
	private String areaType;

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

	@Override
	public String toString() {
		return "GetAreaReq [positionCode=" + positionCode + ", areaType=" + areaType + "]";
	}

}