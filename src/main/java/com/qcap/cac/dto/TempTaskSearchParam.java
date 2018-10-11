package com.qcap.cac.dto;

import com.qcap.cac.constant.CommonConstant;

public class TempTaskSearchParam {
	
	private String employeeName;
	private String employeeTel;
	private String taskStatus;
	private String shift;
	private static final String taskType=CommonConstant.TASK_TYPE_TEMP;

	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
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
