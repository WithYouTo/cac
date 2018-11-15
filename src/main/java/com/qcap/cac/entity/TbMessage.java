package com.qcap.cac.entity;

import java.io.Serializable;
import java.util.Date;

public class TbMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8507264608492365291L;

	/**
	 * 主键
	 */
	private String MessageId;

	/**
	 * 项目编号
	 */
	private String programCode;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 消息内容
	 */
	private String content;

	/**
	 * 已读标识
	 */
	private String readFlag;

	/**
	 * 用户ID
	 */
	private String userId;

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProgramCode() {
		return programCode;
	}

	public String getType() {
		return type;
	}

	/**
	 * 用户ID
	 */
	private String type;

	/**
	 * 用户ID
	 */
	private int lineNo;

	/**
	 * 备注1
	 */
	private String remark1;

	/**
	 * 备注2
	 */
	private String remark2;

	/**
	 * 备注3
	 */
	private String remark3;

	/**
	 * 新增人
	 */
	private String createEmp;

	/**
	 * 新增时间
	 */
	private Date createDate;

	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 修改人
	 */
	private String updateEmp;

	/**
	 * 版本
	 */
	private Integer version;

	public String getMessageId() {
		return MessageId;
	}

	public void setMessageId(String messageId) {
		MessageId = messageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLineNo() {
		return lineNo;
	}

	public void setLineNo(int lineNo) {
		this.lineNo = lineNo;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateEmp() {
		return updateEmp;
	}

	public void setUpdateEmp(String updateEmp) {
		this.updateEmp = updateEmp;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "TbMessage [MessageId=" + MessageId + ", title=" + title + ", content=" + content + ", readFlag="
				+ readFlag + ", userId=" + userId + ", lineNo=" + lineNo + ", remark1=" + remark1 + ", remark2="
				+ remark2 + ", remark3=" + remark3 + ", createEmp=" + createEmp + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", updateEmp=" + updateEmp + ", version=" + version + "]";
	}

}