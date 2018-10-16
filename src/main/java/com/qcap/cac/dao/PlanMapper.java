package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.entity.TbPlan;

@Repository
public interface PlanMapper extends BaseMapper<TbPlan> {

	List<Map<String, Object>> selectPlanByPage(QueryPlanListDto queryPlanListDto);
}