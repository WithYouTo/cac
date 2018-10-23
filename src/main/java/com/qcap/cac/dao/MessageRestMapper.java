package com.qcap.cac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.entity.TbMessage;

@Repository
public interface MessageRestMapper extends BaseMapper<TbMessage> {

	public List<TbMessage> getMessageForNewest(GetMessageReq req);

	public List<TbMessage> getMessage(GetMessageReq req);

	public int updateMessageForRead(TbMessage message);
}