package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QueryHistoryFlightInfoReq {

	@ApiModelProperty(value = "序号")
	private String lineNo;

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public String toString() {
		return "QueryHistoryFlightInfoReq [lineNo=" + lineNo + "]";
	}

}