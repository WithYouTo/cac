package com.qcap.cac.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.CommonRestMapper;
import com.qcap.cac.dto.GetAreaReq;
import com.qcap.cac.dto.GetAreaResp;
import com.qcap.cac.service.CommonRestSrv;

@Service
@Transactional
public class CommonRestSrvImpl implements CommonRestSrv {

	@Resource
	private CommonRestMapper commonRestMapper;

	@Override
	public List<GetAreaResp> getArea(GetAreaReq req) {
		String positionCode = req.getPositionCode();
		String areaType = req.getAreaType();
		if (StringUtils.isNotBlank(positionCode)) {
			// commonRestMapper
		} else if (StringUtils.isNotBlank(areaType)) {
			// commonRestMapper
		} else {
			// commonRestMapper
		}

		return null;
	}

}
