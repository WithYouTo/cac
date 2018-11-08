package com.qcap.cac.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.BatchUpdatePlanDto;
import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;

public interface PlanSrv {

	void queryPlanListByPage(IPage<Map<String, String>> page, QueryPlanListDto queryPlanListDto);

	void addPlan(PlanDto planDto) throws Exception;

	void editPlan(PlanDto planDto) throws Exception;

	void deletePlan(PlanDto planDto) throws Exception;

	void batchUpdatePlan(BatchUpdatePlanDto batchUpdatePlanDto) throws Exception;

}
