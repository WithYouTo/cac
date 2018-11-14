package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.QueryPlanEventListDto;
import com.qcap.cac.entity.TbPlanEvent;

@Repository
public interface PlanEventMapper extends BaseMapper<TbPlanEvent> {

	List<Map<String, String>> selectPlanEventByPage(IPage<Map<String, String>> page,
			@Param("param") QueryPlanEventListDto queryPlanEventListDto);

	TbPlanEvent selectPlanEventById(String planEventId);

	int updatePlanEvent(TbPlanEvent planEvent);

	int deletePlanEventById(String planEventId);

	void batchUpdatePlanEvent(List<TbPlanEvent> planEventList);

    TbPlanEvent getPlanEventByPlanEvent(TbPlanEvent planEvent);
}