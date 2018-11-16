package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PlanEventDto {

	@ApiModelProperty(value = "主键")
	private String planEventId;

	@ApiModelProperty(value = "项目名称")
	private String programCode;

	@ApiModelProperty(value = "区域编码")
	private String areaCode;

	@ApiModelProperty(value = "区域名称")
	private String areaName;

	@ApiModelProperty(value = "房间类型")
	private String areaType;

	@ApiModelProperty(value = "标准编码")
	private String standardCode;

	@ApiModelProperty(value = "事件类型")
	private String eventType;

	@ApiModelProperty(value = "保障等级")
	private String guaranteeType;

	@ApiModelProperty(value = "备注")
	private String remark;

	@ApiModelProperty(value = "是否检查")
	private String checkFlag;

	@ApiModelProperty(value = "是否上传图片")
	private String uploadPicFlag;

	@ApiModelProperty(value = "任务开始是否扫码")
	private String startScanFlag;

	@ApiModelProperty(value = "任务结束是否扫码")
	private String endScanFlag;

	public String getPlanEventId() {
		return planEventId;
	}

	public void setPlanEventId(String planEventId) {
		this.planEventId = planEventId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getUploadPicFlag() {
		return uploadPicFlag;
	}

	public void setUploadPicFlag(String uploadPicFlag) {
		this.uploadPicFlag = uploadPicFlag;
	}

	public String getStartScanFlag() {
		return startScanFlag;
	}

	public void setStartScanFlag(String startScanFlag) {
		this.startScanFlag = startScanFlag;
	}

	public String getEndScanFlag() {
		return endScanFlag;
	}

	public void setEndScanFlag(String endScanFlag) {
		this.endScanFlag = endScanFlag;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	@Override
	public String toString() {
		return "PlanEventDto{" +
				"planEventId='" + planEventId + '\'' +
				", programCode='" + programCode + '\'' +
				", areaCode='" + areaCode + '\'' +
				", areaName='" + areaName + '\'' +
				", areaType='" + areaType + '\'' +
				", standardCode='" + standardCode + '\'' +
				", eventType='" + eventType + '\'' +
				", guaranteeType='" + guaranteeType + '\'' +
				", remark='" + remark + '\'' +
				", checkFlag='" + checkFlag + '\'' +
				", uploadPicFlag='" + uploadPicFlag + '\'' +
				", startScanFlag='" + startScanFlag + '\'' +
				", endScanFlag='" + endScanFlag + '\'' +
				'}';
	}
}