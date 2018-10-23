package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AppTaskRestCheckReq {
	
	@NotBlank(message = "员工工号不能为空")
	@ApiModelProperty(value = "员工工号")
	private String employeeCode;
	
	@NotBlank(message = "任务状态不能为空")
	@ApiModelProperty(value = "任务状态")
	private String taskStatus;
	
	@ApiModelProperty(value = "检查状态")
	private String checkStatus;
	
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	
	
	
}
