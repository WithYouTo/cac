package com.qcap.cac.service;

import java.util.List;

import com.qcap.cac.dto.GetAreaReq;
import com.qcap.cac.dto.GetAreaResp;

public interface CommonRestSrv {

	List<GetAreaResp> getArea(GetAreaReq req);

}
