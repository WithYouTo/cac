package com.qcap.cac.dto;

public class MessageDto{


	private String messageId;

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
	 * 用户ID
	 */
	private String userId;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}


	public String getProgramCode() {
		return programCode;
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


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}