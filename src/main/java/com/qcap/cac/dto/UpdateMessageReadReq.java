package com.qcap.cac.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UpdateMessageReadReq {

	@NotBlank(message = "消息ID不能为空")
	@ApiModelProperty(value = "员工编号")
	private String messageId;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Override
	public String toString() {
		return "UpdateMessageReadReq [messageId=" + messageId + "]";
	}

}