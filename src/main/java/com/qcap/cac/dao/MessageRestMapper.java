package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.IsMessageNoReadReq;
import com.qcap.cac.dto.MessageDto;
import com.qcap.cac.entity.TbMessage;

@Repository
public interface MessageRestMapper extends BaseMapper<TbMessage> {

	List<TbMessage> getMessageForNewest(GetMessageReq req);

	List<TbMessage> getMessage(GetMessageReq req);

	int updateMessageForRead(TbMessage message);

	int updateMessageALLForRead(TbMessage message);

	int isMesisMessageNoRead(IsMessageNoReadReq req);

	/** 
	 *
	 * @Title: ListMessageForProgram 
	 * @param messageDto
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> ListMessageForProgram(@Valid MessageDto messageDto);

	/** 
	 *
	 * @Title: updateMessage 
	 * @param messageDto
	 * @return: void
	 */
	void updateMessage(TbMessage message);

	/** 
	 *
	 * @Title: deleteMessage 
	 * @param messageId
	 * @return: void
	 */
	void deleteMessage(String messageId);

	/** 
	 *
	 * @Title: insertMessage 
	 * @param message
	 * @return: void
	 */
	void insertMessage(TbMessage message);
}