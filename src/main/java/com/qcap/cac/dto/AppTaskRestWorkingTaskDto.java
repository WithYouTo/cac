package com.qcap.cac.dto;

public class AppTaskRestWorkingTaskDto {
	
	private String taskCode;
	
	private String employeeCode;
	
	private String startWorkTime;

	public String getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	
}
