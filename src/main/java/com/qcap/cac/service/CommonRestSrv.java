package com.qcap.cac.service;

import java.util.List;

import com.qcap.cac.dto.GetAreaReq;
import com.qcap.cac.dto.GetAreaResp;
import com.qcap.cac.dto.GetPubCodeReq;
import com.qcap.cac.dto.GetPubCodeResp;

public interface CommonRestSrv {

	public List<GetAreaResp> getArea(GetAreaReq req);

	public List<GetPubCodeResp> getPubCode(GetPubCodeReq req);

}
