package com.qcap.cac.dto;

public class AppLeaveApplyReq {


	/**
	 *   用户主键
	 */
	private String personId;

	/**
	 *   用户工号
	 */
	private String workNo;

	/**
	 *   请假类型（获取通用代码档）
	 */
	private String leaveType;

	/**
	 *   请假类型（获取通用代码档）
	 */
	private String leaveTypeName;

	/**
	 *   请假人姓名
	 */
	private String personName;

	/**
	 *   请假人电话
	 */
	private String personMobile;

	/**
	 *   请假原因
	 */
	private String leaveReason;


	/**
	 *   请假开始时间
	 */
	private String leaveStartTime;

	/**
	 *   请假截止时间
	 */
	private String leaveEndTime;

	/**
	 *   请假图片
	 */
	private String leaveUrl;

	/**
	 *   请假时长
	 */
	private String leaveTotalTime;

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonMobile() {
		return personMobile;
	}

	public void setPersonMobile(String personMobile) {
		this.personMobile = personMobile;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getLeaveStartTime() {
		return leaveStartTime;
	}

	public void setLeaveStartTime(String leaveStartTime) {
		this.leaveStartTime = leaveStartTime;
	}

	public String getLeaveEndTime() {
		return leaveEndTime;
	}

	public void setLeaveEndTime(String leaveEndTime) {
		this.leaveEndTime = leaveEndTime;
	}

	public String getLeaveUrl() {
		return leaveUrl;
	}

	public void setLeaveUrl(String leaveUrl) {
		this.leaveUrl = leaveUrl;
	}

	public String getLeaveTotalTime() {
		return leaveTotalTime;
	}

	public void setLeaveTotalTime(String leaveTotalTime) {
		this.leaveTotalTime = leaveTotalTime;
	}
}
