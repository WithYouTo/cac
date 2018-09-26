package com.qcap.cac.common.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.qcap.cac.common.dao.CommonMapper;
import org.springframework.stereotype.Service;

import com.qcap.cac.common.dao.TbSysFileMapper;
import com.qcap.cac.common.service.CommonSrv;
import com.qcap.cac.model.TbMsg;
import com.qcap.cac.service.WebUserSrv;
@Service
public class CommonSrvImpl implements CommonSrv {
	
	@Resource
	private CommonMapper commonMapper;
	
	@Resource
	private TbSysFileMapper sysFileMapper;
	
	@Resource
	private WebUserSrv webUserSrv;
	
	@Override
	public List<Map<String, Object>> selectBuildingByCode(String managementId) {
		// TODO Auto-generated method stub
		return commonMapper.selectBuildingByCode(managementId);
	}

	@Override
	public void insertMsg(TbMsg msg) {
		// TODO Auto-generated method stub
		commonMapper.insertMsg(msg);
	}

	@Override
	public Map<String, Object> selectLocation(String name, String id) {
		// TODO Auto-generated method stub
		return commonMapper.selectLocation(name, id);
	}

	@Override
	public List<Map<String, Object>> selectBuildingOrAreaByUserId(Map<String,Object>map) {
		// TODO Auto-generated method stub
		return commonMapper.selectBuildingOrAreaByUserId(map);
	}
	
}
