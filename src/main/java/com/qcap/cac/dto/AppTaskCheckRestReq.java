package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AppTaskCheckRestReq {
	
	@NotBlank(message = "员工工号不能为空")
	@ApiModelProperty(value = "员工工号")
	private String employeeCode;
	
	@NotBlank(message = "任务状态不能为空")
	@ApiModelProperty(value = "任务状态")
	private String taskStatus;
	
	@ApiModelProperty(value = "检查状态")
	private String checkStatus;
	
	@ApiModelProperty(value = "区域编码")
	private String areaCode;
	
	@ApiModelProperty(value = "分页号")
	private String lineNo;
	
	public String getAreaCode() {
		return areaCode;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

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
