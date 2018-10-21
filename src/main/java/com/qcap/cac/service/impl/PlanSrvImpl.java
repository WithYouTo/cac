package com.qcap.cac.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.PlanMapper;
import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.entity.TbPlan;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.PlanSrv;
import com.qcap.cac.tools.UUIDUtils;

@Service
@Transactional
public class PlanSrvImpl implements PlanSrv {

	@Resource
	private PlanMapper planMapper;

	@Override
	public List<Map<String, Object>> queryPlanListByPage(QueryPlanListDto queryPlanListDto) {
		return planMapper.selectPlanByPage(queryPlanListDto);
	}

	@Override
	public void addPlan(PlanDto planDto, String userId) throws Exception {
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

				plan.setCreateEmp(userId);
				planMapper.insert(plan);
			}
		}
	}

	@Override
	public void editPlan(PlanDto planDto, String userId) throws Exception {
		TbPlan plan = planMapper.selectPlanById(planDto.getPlanId());
		if (plan != null) {
			BeanUtils.copyProperties(plan, planDto);
			// plan.setPlanId(UUIDUtils.getUUID());
			plan.setStartTime(planDto.getPlanStartTime());
			plan.setEndTime(planDto.getPlanEndTime());

			plan.setUpdateEmp(userId);
			plan.setUpdateDate(new Date());
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
