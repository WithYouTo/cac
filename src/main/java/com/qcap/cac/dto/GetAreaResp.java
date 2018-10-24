package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class GetAreaResp {

	private String areaId;
	private String areaCode;
	private String areaName;
	private String areaType;
	private String superAreaCode;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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

	public String getSuperAreaCode() {
		return superAreaCode;
	}

	public void setSuperAreaCode(String superAreaCode) {
		this.superAreaCode = superAreaCode;
	}

	@Override
	public String toString() {
		return "GetAreaResp [areaId=" + areaId + ", areaCode=" + areaCode + ", areaName=" + areaName + ", areaType="
				+ areaType + ", superAreaCode=" + superAreaCode + "]";
	}

}