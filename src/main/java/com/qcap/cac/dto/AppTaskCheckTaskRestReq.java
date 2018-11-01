package com.qcap.cac.dto;

public class AppTaskCheckTaskRestReq {
	
	private String taskCode;
	
	private String positionCode;
	
	private String positionType;

	public String getTaskCode() {
		return taskCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	
	

}
