package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.PlanMapper;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.service.PlanSrv;

@Service
@Transactional
public class PlanSrvImpl implements PlanSrv {

	@Resource
	private PlanMapper planMapper;

	@Override
	public List<Map<String, Object>> queryPlanListByPage(QueryPlanListDto queryPlanListDto) {
		return planMapper.selectPlanByPage(queryPlanListDto);
	}

}
