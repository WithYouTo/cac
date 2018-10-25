package com.qcap.cac.dto;

public class AppLeaveReq {

	/**
	 *   主键ID
	 */
	private String leaveId;

	/**
	 *   序号
	 */
	private Integer lineNo;

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
	 *   请假状态(AUDITING待审批，PASS已通过，REFUSE拒绝)
	 */
	private String leaveStatus;


	/**
	 *   请假状态(AUDITING待审批，PASS已通过，REFUSE拒绝)
	 */
	private String leaveStatusName;

	/**
	 *   审批人工号
	 */
	private String auditPerson;

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

	/**
	 *   备注
	 */
	private String remark;


	/**
	 *   创建时间
	 */
	private String createTime;


	/**
	 *   审批时间
	 */
	private String auditTime;


	/**
	 *   驳回原因
	 */
	private String refuseReason;

	/**
	 *   驳回图片
	 */
	private String refuseUrl;

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public String getRefuseUrl() {
		return refuseUrl;
	}

	public void setRefuseUrl(String refuseUrl) {
		this.refuseUrl = refuseUrl;
	}

	public String getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(String leaveId) {
		this.leaveId = leaveId;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

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

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getLeaveStatusName() {
		return leaveStatusName;
	}

	public void setLeaveStatusName(String leaveStatusName) {
		this.leaveStatusName = leaveStatusName;
	}

	public String getAuditPerson() {
		return auditPerson;
	}

	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
