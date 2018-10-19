package com.qcap.cac.dto;

public class ProgramSearchDto {
	
	private String programName;
	
	private String areaCode;
	
	private String startTime;
	
	private String endTime;
	
	private String effectDate;
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProgramName() {
		return programName;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}
	
}
