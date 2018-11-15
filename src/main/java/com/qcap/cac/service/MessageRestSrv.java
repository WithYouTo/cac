package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.GetMessageResp;
import com.qcap.cac.dto.IsMessageNoReadReq;
import com.qcap.cac.dto.IsMessageNoReadResp;
import com.qcap.cac.dto.MessageDto;
import com.qcap.cac.dto.UpdateAllMessageReadReq;
import com.qcap.cac.dto.UpdateMessageReadReq;

public interface MessageRestSrv {

	List<GetMessageResp> getMessageForNewest(GetMessageReq req) throws Exception;

	List<GetMessageResp> getMessage(GetMessageReq req) throws Exception;

	void updateMessageForRead(UpdateMessageReadReq req) throws Exception;

	void updateMessageALLForRead(UpdateAllMessageReadReq req) throws Exception;

	IsMessageNoReadResp isMessageNoRead(IsMessageNoReadReq req) throws Exception;

	/** 
	 *
	 * @Title: listMessage 
	 * @param page
	 * @param messageDto
	 * @return: void
	 */
	List<Map<String , Object>> listMessage(@Valid MessageDto messageDto);

	/** 
	 *
	 * @Title: insertMessage 
	 * @param messageDto
	 * @return: void
	 */
	Object insertMessage(@Valid MessageDto messageDto,String str);

	/** 
	 *
	 * @Title: updateMessage 
	 * @param messageDto
	 * @return: void
	 */
	Object updateMessage(@Valid MessageDto messageDto);

	/** 
	 *
	 * @Title: deleteMessage 
	 * @param messageId
	 * @return: void
	 */
	Object deleteMessage(String messageId);

    void JpushMessage(Object userNo, String programCode, String msg, String title);
}
