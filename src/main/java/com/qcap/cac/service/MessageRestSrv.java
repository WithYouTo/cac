package com.qcap.cac.service;

import java.util.List;

import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.GetMessageResp;
import com.qcap.cac.dto.IsMessageNoReadReq;
import com.qcap.cac.dto.IsMessageNoReadResp;
import com.qcap.cac.dto.UpdateAllMessageReadReq;
import com.qcap.cac.dto.UpdateMessageReadReq;

public interface MessageRestSrv {

	List<GetMessageResp> getMessageForNewest(GetMessageReq req) throws Exception;

	List<GetMessageResp> getMessage(GetMessageReq req) throws Exception;

	void updateMessageForRead(UpdateMessageReadReq req) throws Exception;

	void updateMessageALLForRead(UpdateAllMessageReadReq req) throws Exception;

	IsMessageNoReadResp isMessageNoRead(IsMessageNoReadReq req) throws Exception;

}
