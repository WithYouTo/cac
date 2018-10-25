package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateAllMessageReadReq {

	@NotBlank(message = "员工编号不能为空")
	@ApiModelProperty(value = "员工编号")
	private String employeeCode;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	@Override
	public String toString() {
		return "UpdateAllMessageReadReq [employeeCode=" + employeeCode + "]";
	}

}