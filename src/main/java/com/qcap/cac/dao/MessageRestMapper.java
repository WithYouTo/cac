package com.qcap.cac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.IsMessageNoReadReq;
import com.qcap.cac.entity.TbMessage;

@Repository
public interface MessageRestMapper extends BaseMapper<TbMessage> {

	List<TbMessage> getMessageForNewest(GetMessageReq req);

	List<TbMessage> getMessage(GetMessageReq req);

	int updateMessageForRead(TbMessage message);

	int updateMessageALLForRead(TbMessage message);

	int isMesisMessageNoRead(IsMessageNoReadReq req);
}