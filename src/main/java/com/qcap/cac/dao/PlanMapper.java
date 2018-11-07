package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.entity.TbPlan;

@Repository
public interface PlanMapper extends BaseMapper<TbPlan> {

	List<Map<String, String>> selectPlanByPage(IPage<Map<String, String>> page,
			@Param("param") QueryPlanListDto queryPlanListDto);

	TbPlan selectPlanById(String planId);

	int updatePlan(TbPlan plan);

	int deletePlanById(String planId);
}