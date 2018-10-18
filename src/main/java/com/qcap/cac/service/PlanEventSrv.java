package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.PlanEventDto;
import com.qcap.cac.dto.QueryPlanEventListDto;

public interface PlanEventSrv {

	public List<Map<String, Object>> queryPlanEventListByPage(QueryPlanEventListDto queryPlanEventListDto);

	public void addPlanEvent(PlanEventDto planEventDto, String userId) throws Exception;

	public void editPlanEvent(PlanEventDto planEventDto, String userId) throws Exception;

	public void deletePlanEvent(PlanEventDto planEventDto) throws Exception;

}
