package com.qcap.cac.dto;

public class AppTaskUpdateReq {

	private String completeTime;

	private String taskStatus;

	private String checkStatus;

	private String taskScore;

	private String taskAdvice;
	
	private String taskFinishDesc;
	
	private String disqualifiedReason;
	
	private String taskCode;

	private String feedbackImgUrl;

	private String checkImgUrl;
	
	private String positionCode;

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getFeedbackImgUrl() {
		return feedbackImgUrl;
	}

	public void setFeedbackImgUrl(String feedbackImgUrl) {
		this.feedbackImgUrl = feedbackImgUrl;
	}

	public String getCheckImgUrl() {
		return checkImgUrl;
	}

	public void setCheckImgUrl(String checkImgUrl) {
		this.checkImgUrl = checkImgUrl;
	}

	public String getCompleteTime() {
		return completeTime;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public String getTaskScore() {
		return taskScore;
	}

	public String getTaskAdvice() {
		return taskAdvice;
	}

	public String getTaskFinishDesc() {
		return taskFinishDesc;
	}

	public String getDisqualifiedReason() {
		return disqualifiedReason;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public void setTaskScore(String taskScore) {
		this.taskScore = taskScore;
	}

	public void setTaskAdvice(String taskAdvice) {
		this.taskAdvice = taskAdvice;
	}

	public void setTaskFinishDesc(String taskFinishDesc) {
		this.taskFinishDesc = taskFinishDesc;
	}

	public void setDisqualifiedReason(String disqualifiedReason) {
		this.disqualifiedReason = disqualifiedReason;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}
	
	
}
