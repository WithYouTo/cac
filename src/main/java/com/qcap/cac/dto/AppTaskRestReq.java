package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AppTaskRestReq {
	
	@NotBlank(message = "员工工号不能为空")
	@ApiModelProperty(value = "员工工号")
	private String employeeCode;
	
	@NotBlank(message = "任务状态不能为空")
	@ApiModelProperty(value = "任务状态")
	private String taskStatus;
	
	@ApiModelProperty(value = "分页号：联合排序用")
	private String lineNo;
	
	@ApiModelProperty(value = "任务开始时间：联合排序用")
	private String startTime;
	
	public String getLineNo() {
		return lineNo;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
