package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.CommonMapper;
import com.qcap.cac.service.CommonSrv;

@Service
@Transactional
public class CommonSrvImpl implements CommonSrv {

	@Resource
	private CommonMapper commonMapper;

	@Override
	public List<Map<String, String>> getEquipNameByEquipType(String equipType) {
		return this.commonMapper.getEquipNameByEquipType(equipType);
	}

	@Override
	public List<Map<String, String>> getPartsNameByEquipNo(String equipNo) {
		return this.commonMapper.getPartsNameByEquipNo(equipNo);
	}

	@Override
	public String getAreaNameByAreaCode(String areaCode) {
		List<Map<String, Object>> ls = commonMapper.getAreaNameByAreaCode(areaCode);
		if (CollectionUtils.isNotEmpty(ls)) {
			return Objects.toString(ls.get(0).get("areaName"));
		}
		return null;
	}

	@Override
	public String getStandardNameByStandardCode(String standardCode) {
		List<Map<String, Object>> ls = commonMapper.getStandardNameByStandardCode(standardCode);
		if (CollectionUtils.isNotEmpty(ls)) {
			return Objects.toString(ls.get(0).get("standardName"));
		}
		return null;
	}

    @Override
    public List<Map<String, String>> getEquipTypeList() {
        return this.commonMapper.getEquipTypeList();
    }
}
