package com.qcap.cac.entity;

import java.util.Date;

public class TbAttence {
	/**
	 * 主键
	 */
	private String attenceId;

	/**
	 * 部门名称
	 */
	private String orgName;


	/**
	 * 项目编码
	 */
	private String programCode;

	/**
	 * 用户ID
	 */
	private String personId;

	/**
	 * 工号
	 */
	private String workNo;

	/**
	 * 名称
	 */
	private String personName;

	/**
	 * 电话
	 */
	private String personMobile;

	/**
	 * 签到地点
	 */
	private String attencePlace;


	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public String getAttencePlace() {
		return attencePlace;
	}

	public void setAttencePlace(String attencePlace) {
		this.attencePlace = attencePlace;
	}

	/**
	 * 签到时间
	 */
	private Date attenceTime;

	/**
	 * 签到照片
	 */
	private String workContent;

	/**
	 * 签到照片
	 */
	private String filesUrl;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 新增人
	 */
	private String createEmp;

	/**
	 * 新增时间
	 */
	private Date createDate;

	/**
	 * 修改人
	 */
	private String updateEmp;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 版本
	 */
	private Integer version;

	public String getAttenceId() {
		return attenceId;
	}

	public void setAttenceId(String attenceId) {
		this.attenceId = attenceId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public Date getAttenceTime() {
		return attenceTime;
	}

	public void setAttenceTime(Date attenceTime) {
		this.attenceTime = attenceTime;
	}

	public String getWorkContent() {
		return workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	public String getFilesUrl() {
		return filesUrl;
	}

	public void setFilesUrl(String filesUrl) {
		this.filesUrl = filesUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateEmp() {
		return createEmp;
	}

	public void setCreateEmp(String createEmp) {
		this.createEmp = createEmp;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateEmp() {
		return updateEmp;
	}

	public void setUpdateEmp(String updateEmp) {
		this.updateEmp = updateEmp;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "TbAttence [attenceId=" + attenceId + ", orgName=" + orgName + ", personId=" + personId + ", workNo="
				+ workNo + ", personName=" + personName + ", personMobile=" + personMobile + ", attencePlace="
				+ attencePlace + ", attenceTime=" + attenceTime + ", workContent=" + workContent + ", filesUrl="
				+ filesUrl + ", remark=" + remark + ", createEmp=" + createEmp + ", createDate=" + createDate
				+ ", updateEmp=" + updateEmp + ", updateDate=" + updateDate + ", version=" + version + "]";
	}

}