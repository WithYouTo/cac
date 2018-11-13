package com.qcap.cac.dto;

public class TaskArrangeSearchDto {
	
	private String shift;
	
	private String month;

	private String positionCode;

	private String employeeCode;

	private String programCode;

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getShift() {
		return shift;
	}

	public String getMonth() {
		return month;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
