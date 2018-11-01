package com.qcap.cac.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.CommonRestMapper;
import com.qcap.cac.dto.GetAreaReq;
import com.qcap.cac.dto.GetAreaResp;
import com.qcap.cac.dto.GetPubCodeReq;
import com.qcap.cac.dto.GetPubCodeResp;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.cac.exception.BaseException;
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
		String eventType = req.getEventType();
		if (StringUtils.isNotBlank(positionCode)) {
			try {
				TbAreaPosition areaPosition = commonRestMapper.getPositionByPositionCode(positionCode);
				List<String> areaCodeList = Arrays.asList(areaPosition.getAreaCode().split(","));
				return commonRestMapper.getAreaListByAreaCodeList(areaCodeList);
			} catch (Exception e) {
				throw new BaseException(CommonCodeConstant.ERROR_CODE_40403,
						CommonCodeConstant.ERROR_CODE_40403_MSG + "【" + positionCode + "】");
			}
		}
		if (StringUtils.isNotBlank(eventType)) {
			req.setAreaType(CommonConstant.AREA_TYPE.get(eventType));

		}
		return commonRestMapper.getAreaList(req);
	}

	@Override
	public List<GetPubCodeResp> getPubCode(GetPubCodeReq req) {
		return commonRestMapper.getPubCodeList(req);
	}

}
