package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.PlanEventDto;
import com.qcap.cac.dto.QueryPlanEventListDto;

public interface PlanEventSrv {

	List<Map<String, Object>> queryPlanEventListByPage(QueryPlanEventListDto queryPlanEventListDto);

	void addPlanEvent(PlanEventDto planEventDto, String userId) throws Exception;

	void editPlanEvent(PlanEventDto planEventDto, String userId) throws Exception;

	void deletePlanEvent(PlanEventDto planEventDto) throws Exception;

}
