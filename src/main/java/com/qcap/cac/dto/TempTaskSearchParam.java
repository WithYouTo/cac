package com.qcap.cac.dto;

import com.qcap.cac.constant.CommonConstant;

public class TempTaskSearchParam {

	private String employeeCode;
	private String taskStatus;
	private String shift;
	private String programCode;
	private static final String taskType=CommonConstant.TASK_TYPE_TEMP;

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
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
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

}
