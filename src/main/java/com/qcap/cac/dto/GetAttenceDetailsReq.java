package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAttenceDetailsReq {

	@NotBlank(message = "员工编号不能为空")
	@ApiModelProperty(value = "员工编号")
	private String employeeCode;

	@NotBlank(message = "日期不能为空")
	@ApiModelProperty(value = "日期")
	private String date;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "GetAttenceDetailsReq [employeeCode=" + employeeCode + ", date=" + date + "]";
	}

}