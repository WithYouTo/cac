package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class IsMessageNoReadResp {

	private String isNoReadFlag;

	private String noReadCount;

	public String getIsNoReadFlag() {
		return isNoReadFlag;
	}

	public void setIsNoReadFlag(String isNoReadFlag) {
		this.isNoReadFlag = isNoReadFlag;
	}

	public String getNoReadCount() {
		return noReadCount;
	}

	public void setNoReadCount(String noReadCount) {
		this.noReadCount = noReadCount;
	}

	@Override
	public String toString() {
		return "IsMessageNoReadResp [isNoReadFlag=" + isNoReadFlag + ", noReadCount=" + noReadCount + "]";
	}

}