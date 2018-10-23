package com.qcap.cac.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.MessageRestMapper;
import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.GetMessageResp;
import com.qcap.cac.dto.UpdateMessageReadReq;
import com.qcap.cac.entity.TbMessage;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.tools.EntityTools;

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
				resp.setStatusName(CommonConstant.READ_FLAG.get(msg.getReadFlag()));
				resp.setTime(CommonConstant.sdf_YMDHMS.format(msg.getCreateDate()));
				lsRecord.add(resp);
			}
		}
		return lsRecord;
	}

	@Override
	public void updateMessageForRead(UpdateMessageReadReq req) {
		TbMessage message = new TbMessage();
		message.setMessageId(req.getMessageId());
		message.setReadFlag(CommonConstant.READ_FLAG_1);
		message = EntityTools.setUpdateEmpAndTime(message);

		MessageRestMapper.updateMessageForRead(message);
	}

}
