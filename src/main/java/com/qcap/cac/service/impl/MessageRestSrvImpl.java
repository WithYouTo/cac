package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.MessageRestMapper;
import com.qcap.cac.dto.*;
import com.qcap.cac.entity.TbMessage;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.JpushTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ResParams;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageRestSrvImpl implements MessageRestSrv {

	@Resource
	private MessageRestMapper MessageRestMapper;

	@Override
	public List<GetMessageResp> getMessageForNewest(GetMessageReq req) throws Exception {
		List<GetMessageResp> lsRecord = new ArrayList<>();
		List<TbMessage> ls = MessageRestMapper.getMessageForNewest(req);
		if (CollectionUtils.isNotEmpty(ls)) {
			for (TbMessage msg : ls) {
				GetMessageResp resp = new GetMessageResp();
				BeanUtils.copyProperties(resp, msg);
				resp.setStatus(msg.getReadFlag());
				resp.setStatusName(CommonConstant.READ_FLAG.get(msg.getReadFlag()));
				resp.setTime(CommonConstant.sdf_YMDHMS.format(msg.getCreateDate()));
				lsRecord.add(resp);
			}
		}
		return lsRecord;
	}

	@Override
	public List<GetMessageResp> getMessage(@Valid GetMessageReq req) throws Exception {
		List<GetMessageResp> lsRecord = new ArrayList<>();
		List<TbMessage> ls = MessageRestMapper.getMessage(req);
		if (CollectionUtils.isNotEmpty(ls)) {
			for (TbMessage msg : ls) {
				GetMessageResp resp = new GetMessageResp();
				BeanUtils.copyProperties(resp, msg);
				resp.setStatus(msg.getReadFlag());
				resp.setStatusName(CommonConstant.READ_FLAG.get(msg.getReadFlag()));
				resp.setTime(CommonConstant.sdf_YMDHMS.format(msg.getCreateDate()));
				lsRecord.add(resp);
			}
		}
		return lsRecord;
	}

	@Override
	public void updateMessageForRead(UpdateMessageReadReq req) throws Exception {
		TbMessage message = new TbMessage();
		message.setMessageId(req.getMessageId());
		message.setReadFlag(CommonConstant.READ_FLAG_1);
		message = EntityTools.setUpdateEmpAndTime(message);

		MessageRestMapper.updateMessageForRead(message);
	}

	@Override
	public void updateMessageALLForRead(UpdateAllMessageReadReq req) throws Exception {
		TbMessage message = new TbMessage();
		message.setUserId(req.getEmployeeCode());
		message.setReadFlag(CommonConstant.READ_FLAG_1);
		message = EntityTools.setUpdateEmpAndTime(message);
		MessageRestMapper.updateMessageALLForRead(message);
	}

	@Override
	public IsMessageNoReadResp isMessageNoRead(IsMessageNoReadReq req) throws Exception {
		int count = MessageRestMapper.isMesisMessageNoRead(req);
		IsMessageNoReadResp resp = new IsMessageNoReadResp();
		if (count > 0) {
			resp.setIsNoReadFlag("1");
		} else {
			resp.setIsNoReadFlag("0");
		}
		resp.setNoReadCount(String.valueOf(count));
		return resp;
	}

	/** 通知公告列表 
	 * @Title: listMessage
	 * @param messageDto
	 */
	@Override
	public List<Map<String , Object>> listMessage(@Valid MessageDto messageDto) {
		
		return this.MessageRestMapper.ListMessageForProgram(messageDto);
	}

	/** 新增通知 
	 * @Title: insertMessage
	 * @param messageDto
	 * @return
	 */
	@Override
	public Object insertMessage(@Valid MessageDto messageDto,String str) {
		String type="";
		if("PC".equals(str)){
			type="2";
		}else {
			type="1";
		}
		TbMessage message = new TbMessage();
		message.setMessageId(UUIDUtils.getUUID());
		message.setContent(messageDto.getContent());
		EntityTools.setCreateEmpAndTime(message);
		
		message.setProgramCode(messageDto.getProgramCode());
		message.setReadFlag("1");
		message.setTitle(messageDto.getTitle());
		message.setType(type);
		message.setUserId(messageDto.getUserId());
		
		this.MessageRestMapper.insertMessage(message);
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "新增清洁标准成功", null);
	}

	/** 修改通知 
	 * @Title: updateMessage
	 * @param messageDto
	 * @return
	 * @see com.qcap.cac.service.MessageRestSrv#updateMessage(com.qcap.cac.dto.MessageDto)
	 */
	@Override
	public Object updateMessage(@Valid MessageDto messageDto) {
		TbMessage message = new TbMessage();
		message.setMessageId(messageDto.getMessageId());
		message.setContent(messageDto.getContent());
		message.setProgramCode(messageDto.getProgramCode());
		message.setTitle(messageDto.getTitle());
		EntityTools.setCreateEmpAndTime(message);
		
		this.MessageRestMapper.updateMessage(message);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改成功", null);
	}

	/** 删除通知
	 * @Title: deleteMessage
	 * @param messageId
	 * @return
	 * @see com.qcap.cac.service.MessageRestSrv#deleteMessage(java.lang.String)
	 */
	@Override
	public Object deleteMessage(String messageId) {
		this.MessageRestMapper.deleteMessage(messageId);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "删除成功", null);
	}

	/**
	 * 消息推送时增加到消息中心
	 * @param userNo  工号(多个人员时使用，隔开进行拼接)
	 * @param programCode 项目code
	 * @param msg 推送内容
	 * @param title 推送标题类型
	 * @return
	 */
	@Override
	public void JpushMessage(Object userNo,String programCode, String msg , String title){

		if(userNo instanceof String){
			String userId = (String) userNo;
			TbMessage message = new TbMessage();
			message.setMessageId(UUIDUtils.getUUID());
			message.setContent(msg);
			EntityTools.setCreateEmpAndTime(message);

			message.setProgramCode(programCode);
			message.setReadFlag("1");
			message.setTitle(title);
			message.setType("1");
			message.setUserId(userId);
			this.MessageRestMapper.insertMessage(message);

			JpushTools.pushSingle(userId,msg);

		}

		if(userNo instanceof List){
			List<String> list = (List<String>) userNo;
			JpushTools.pushArray(list,msg);

			for (String userId :list){
				TbMessage message = new TbMessage();
				message.setMessageId(UUIDUtils.getUUID());
				message.setContent(msg);
				EntityTools.setCreateEmpAndTime(message);

				message.setProgramCode(programCode);
				message.setReadFlag("1");
				message.setTitle(title);
				message.setType("1");
				message.setUserId(userId);
				this.MessageRestMapper.insertMessage(message);
			}
		}
	}


}
