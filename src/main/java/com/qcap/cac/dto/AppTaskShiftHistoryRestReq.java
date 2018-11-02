package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class AppTaskShiftHistoryRestReq {

	@NotBlank(message = "当前登录人不能为空")
	@ApiModelProperty(value = "当前登录人", required = true)
	private String loginName;

	@ApiModelProperty("分页码")
	private String lineNo;


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	
	

}
