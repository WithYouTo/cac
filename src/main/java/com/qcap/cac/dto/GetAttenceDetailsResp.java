package com.qcap.cac.dto;

import java.util.Arrays;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GetAttenceDetailsResp {

	@ApiModelProperty(value = "签到时间")
	private String attenceTime;

	@ApiModelProperty(value = "签到地点")
	private String attencePlace;

	@ApiModelProperty(value = "签到地点")
	private String attencePlaceName;

	@ApiModelProperty(value = "工作安排")
	private String workContent;

	@ApiModelProperty(value = "签到照片")
	private String[] url;

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

	public String getAttencePlaceName() {
		return attencePlaceName;
	}

	public void setAttencePlaceName(String attencePlaceName) {
		this.attencePlaceName = attencePlaceName;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String[] getUrl() {
		return url;
	}

	public void setUrl(String[] url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "GetAttenceDetailsResp [attenceTime=" + attenceTime + ", attencePlace=" + attencePlace
				+ ", attencePlaceName=" + attencePlaceName + ", workContent=" + workContent + ", url="
				+ Arrays.toString(url) + "]";
	}

}