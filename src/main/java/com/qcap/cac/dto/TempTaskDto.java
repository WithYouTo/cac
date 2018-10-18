package com.qcap.cac.dto;

public class TempTaskDto {
	
	private String taskCode;
	
	private String areaCode;
	
	private String areaName;
	
	private String standardCode;
	
	private String standardName;
	
	private String startTime;
	
	private String endTime;
	
	private String spec;
	
	private String fileUrl;
	
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getTaskCode() {
		return taskCode;
	}
	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public String getStandardCode() {
		return standardCode;
	}
	public String getStandardName() {
		return standardName;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getSpec() {
		return spec;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
}
