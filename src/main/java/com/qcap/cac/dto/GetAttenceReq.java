package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAttenceReq {

	@NotBlank(message = "员工编号不能为空")
	@ApiModelProperty(value = "员工编号")
	private String employeeCode;

	@NotBlank(message = "排班年月不能为空")
	@ApiModelProperty(value = "排班年月")
	private String month;

	private String shift;

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "GetAttenceReq [employeeCode=" + employeeCode + ", month=" + month + "]";
	}

}