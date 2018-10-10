package com.qcap.cac.dto;

public class TempTaskSearchParam {
	
	private String employeeName;
	private String employeeTel;
	private String taskStatus;
	
	public String getEmployeeName() {
		return employeeName;
	}
	public String getEmployeeTel() {
		return employeeTel;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public void setEmployeeTel(String employeeTel) {
		this.employeeTel = employeeTel;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}
