package com.qcap.core.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcap.core.dao.LoginLogTestMapper;
import com.qcap.core.service.LoginLogTestSrv;
@Service
public class LoginLogTestSrvImpl implements LoginLogTestSrv {

    @Autowired
	 private LoginLogTestMapper loginLogTestMapper;

	@Override
	public List<Map<String, Object>> getLoginLogs(Map params) {
		// TODO Auto-generated method stub
		return loginLogTestMapper.getLoginLogs(params);
	}

	@Override
	public int delLoginLog(String[] idStr) {
	    int num=0;
		for(String id:idStr){
	    	num=loginLogTestMapper.delLoginLog(id);
		}
		return num;
	}

}
