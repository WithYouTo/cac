package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;

@ApiModel
public class GetMessageResp {

	private String messageId;

	private String title;

	private String content;

	private String time;

	private String status;

	private String statusName;

	private String lineNo;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Override
	public String toString() {
		return "GetMessageForNewestResp [messageId=" + messageId + ", title=" + title + ", content=" + content
				+ ", time=" + time + ", status=" + status + ", statusName=" + statusName + ", lineNo=" + lineNo + "]";
	}

}