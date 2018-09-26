package com.qcap.cac.common.service.impl;

import java.util.Map;

import com.qcap.cac.common.dao.SmiMapper;
import com.qcap.cac.common.service.SmiSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.model.Tbsmi01;

@Service
@Transactional
public class SmiSrvImpl implements SmiSrv {

	@Autowired
    SmiMapper smiMapper;
	
	@Override
	public void insert(Tbsmi01 smi01) {
		// TODO Auto-generated method stub
		smiMapper.insertSmi(smi01);
	}

	@Override
	public Map selectCode(Map param) {
		// TODO Auto-generated method stub
		return this.smiMapper.selectCode(param);
	}

}
