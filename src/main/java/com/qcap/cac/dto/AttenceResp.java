package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AttenceResp {

	@NotBlank(message = "员工编号不能为空")
	@ApiModelProperty(value = "员工编号")
	private String employeeCode;

	@ApiModelProperty(value = "序号")
	private String lineNo;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public String toString() {
		return "GetMessageReq [employeeCode=" + employeeCode + ", lineNo=" + lineNo + "]";
	}

}