package com.qcap.cac.dto;

public class AreaDto {

	private String areaName;
	private String areaType;
	private String superAreaCode;
	private String buildingPurpose;
	private String level;


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

	public String getBuildingPurpose() {
		return buildingPurpose;
	}

	public void setBuildingPurpose(String buildingPurpose) {
		this.buildingPurpose = buildingPurpose;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
