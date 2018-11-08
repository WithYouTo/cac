package com.qcap.cac.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.BatchUpdatePlanEventDto;
import com.qcap.cac.dto.PlanEventDto;
import com.qcap.cac.dto.QueryPlanEventListDto;

public interface PlanEventSrv {

	void queryPlanEventListByPage(IPage<Map<String, String>> page, QueryPlanEventListDto queryPlanEventListDto);

	void addPlanEvent(PlanEventDto planEventDto) throws Exception;

	void editPlanEvent(PlanEventDto planEventDto) throws Exception;

	void deletePlanEvent(PlanEventDto planEventDto) throws Exception;

	void batchUpdatePlanEvent(BatchUpdatePlanEventDto batchUpdatePlanEventDto) throws Exception;

}
