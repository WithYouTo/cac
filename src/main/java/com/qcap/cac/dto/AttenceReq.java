package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AttenceReq {

	@NotBlank(message = "员工编号不能为空")
	@ApiModelProperty(value = "员工编号")
	private String employeeCode;

	@ApiModelProperty(value = "签到时间")
	private String attenceTime;

	@ApiModelProperty(value = "签到地点")
	private String attencePlace;

	@ApiModelProperty(value = "工作安排")
	private String workContent;

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public String getAttenceTime() {
		return attenceTime;
	}

	public void setAttenceTime(String attenceTime) {
		this.attenceTime = attenceTime;
	}

	public String getAttencePlace() {
		return attencePlace;
	}

	public void setAttencePlace(String attencePlace) {
		this.attencePlace = attencePlace;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	@Override
	public String toString() {
		return "AttenceReq [employeeCode=" + employeeCode + ", attenceTime=" + attenceTime + ", attencePlace="
				+ attencePlace + ", workContent=" + workContent + "]";
	}

}