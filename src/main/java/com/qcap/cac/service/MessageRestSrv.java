package com.qcap.cac.service;

import java.util.List;

import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.GetMessageResp;
import com.qcap.cac.dto.UpdateMessageReadReq;

public interface MessageRestSrv {

	public List<GetMessageResp> getMessageForNewest(GetMessageReq req) throws Exception;

	public List<GetMessageResp> getMessage(GetMessageReq req) throws Exception;

	public void updateMessageForRead(UpdateMessageReadReq req);

}
