package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.PlanMapper;
import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.entity.TbPlan;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.PlanSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;

@Service
@Transactional
public class PlanSrvImpl implements PlanSrv {

	@Resource
	private PlanMapper planMapper;

	@Override
	public void queryPlanListByPage(IPage<Map<String, String>> page, QueryPlanListDto queryPlanListDto) {
		List<Map<String, String>> ls = planMapper.selectPlanByPage(page, queryPlanListDto);
		if (CollectionUtils.isNotEmpty(ls)) {
			for (Map<String, String> map : ls) {
				String planTimeType = map.get("planTimeType");
				String startTime = map.get("startTime");
				String endTime = map.get("endTime");
				String month = map.get("month");
				String day = map.get("day");
				String week = map.get("week");
				map.put("planTimeTypeName", CommonConstant.PLAN_TIME_TYPE.get(planTimeType));
				map.put("time", startTime + "-" + endTime);
				if (StringUtils.isNotBlank(month)) {
					map.put("monthName", month + "月");
				}
				if (StringUtils.isNotBlank(day)) {
					if ("ALL".equals(day)) {
						map.put("dayName", "全部");
					} else {
						map.put("dayName", day + "号");
					}
				}
				if (StringUtils.isNotBlank(week)) {
					map.put("weekName", "周" + ToolUtil.toChinese(week));
				}
			}
		}

		page.setRecords(ls);
	}

	@Override
	public void addPlan(PlanDto planDto) throws Exception {
		String[] planStartTimeArr = planDto.getPlanStartTime().split(",");
		String[] planEndTimeArr = planDto.getPlanEndTime().split(",");
		if (planStartTimeArr != null && planEndTimeArr != null && planStartTimeArr.length == planEndTimeArr.length) {
			for (int i = 0; i < planStartTimeArr.length; i++) {
				String planStartTime = planStartTimeArr[i];
				String planEndTime = planEndTimeArr[i];

				TbPlan plan = new TbPlan();
				BeanUtils.copyProperties(plan, planDto);
				plan.setPlanId(UUIDUtils.getUUID());
				plan.setStartTime(planStartTime);
				plan.setEndTime(planEndTime);

				if (CommonConstant.PLAN_TIME_TYPE_DAY.equals(plan.getPlanTimeType())) {
					plan.setMonth(null);
					plan.setDay(CommonConstant.DAY_ALL);
					plan.setWeek(null);
				} else if (CommonConstant.PLAN_TIME_TYPE_MONTH.equals(plan.getPlanTimeType())) {
					plan.setMonth(null);
					plan.setWeek(null);
				} else if (CommonConstant.PLAN_TIME_TYPE_YEAR.equals(plan.getPlanTimeType())) {
					plan.setWeek(null);
				} else if (CommonConstant.PLAN_TIME_TYPE_WEEK.equals(plan.getPlanTimeType())) {
					plan.setMonth(null);
					plan.setDay(null);
				}

				plan = EntityTools.setCreateEmpAndTime(plan);
				planMapper.insert(plan);
			}
		}
	}

	@Override
	public void editPlan(PlanDto planDto) throws Exception {
		TbPlan plan = planMapper.selectPlanById(planDto.getPlanId());
		if (plan != null) {
			BeanUtils.copyProperties(plan, planDto);
			// plan.setPlanId(UUIDUtils.getUUID());
			plan.setStartTime(planDto.getPlanStartTime().replace(",", ""));
			plan.setEndTime(planDto.getPlanEndTime().replace(",", ""));

			if (CommonConstant.PLAN_TIME_TYPE_DAY.equals(plan.getPlanTimeType())) {
				plan.setMonth(null);
				plan.setDay(CommonConstant.DAY_ALL);
				plan.setWeek(null);
			} else if (CommonConstant.PLAN_TIME_TYPE_MONTH.equals(plan.getPlanTimeType())) {
				plan.setMonth(null);
				plan.setWeek(null);
			} else if (CommonConstant.PLAN_TIME_TYPE_YEAR.equals(plan.getPlanTimeType())) {
				plan.setWeek(null);
			} else if (CommonConstant.PLAN_TIME_TYPE_WEEK.equals(plan.getPlanTimeType())) {
				plan.setMonth(null);
				plan.setDay(null);
			}

			plan = EntityTools.setUpdateEmpAndTime(plan);
			planMapper.updatePlan(plan);
		} else {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, CommonCodeConstant.ERROR_CODE_40401_MSG);
		}
	}

	@Override
	public void deletePlan(PlanDto planDto) {
		planMapper.deletePlanById(planDto.getPlanId());
	}
}
