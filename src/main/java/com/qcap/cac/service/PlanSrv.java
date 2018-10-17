package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;

public interface PlanSrv {

	public List<Map<String, Object>> queryPlanListByPage(QueryPlanListDto queryPlanListDto);

	public void addPlan(PlanDto planDto, String userId) throws Exception;

	public void editPlan(PlanDto planDto, String userId) throws Exception;

	public void deletePlan(PlanDto planDto) throws Exception;

}
