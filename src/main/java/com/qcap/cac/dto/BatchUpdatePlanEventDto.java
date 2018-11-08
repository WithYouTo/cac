package com.qcap.cac.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel
public class BatchUpdatePlanEventDto {

	private List<PlanEventDto> planEventList;

	public List<PlanEventDto> getPlanEventList() {
		return planEventList;
	}

	public void setPlanEventList(List<PlanEventDto> planEventList) {
		this.planEventList = planEventList;
	}

}