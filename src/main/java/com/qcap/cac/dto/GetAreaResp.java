package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAreaResp {

	@NotBlank(message = "岗位编号不能为空")
	@ApiModelProperty(value = "岗位编号")
	private String positionCode;

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	@Override
	public String toString() {
		return "GetAreaReq [positionCode=" + positionCode + "]";
	}

}