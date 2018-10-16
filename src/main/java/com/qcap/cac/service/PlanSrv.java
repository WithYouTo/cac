package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.QueryPlanListDto;

public interface PlanSrv {

	public List<Map<String, Object>> queryPlanListByPage(QueryPlanListDto queryPlanListDto);

}
