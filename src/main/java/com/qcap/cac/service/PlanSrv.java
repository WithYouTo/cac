package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;

public interface PlanSrv {

	List<Map<String, Object>> queryPlanListByPage(QueryPlanListDto queryPlanListDto);

	void addPlan(PlanDto planDto) throws Exception;

	void editPlan(PlanDto planDto) throws Exception;

	void deletePlan(PlanDto planDto) throws Exception;

}
