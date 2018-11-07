package com.qcap.cac.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.PlanEventMapper;
import com.qcap.cac.dto.PlanEventDto;
import com.qcap.cac.dto.QueryPlanEventListDto;
import com.qcap.cac.entity.TbPlanEvent;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.PlanEventSrv;
import com.qcap.cac.tools.UUIDUtils;

@Service
@Transactional
public class PlanEventSrvImpl implements PlanEventSrv {

	@Resource
	private PlanEventMapper planEventMapper;

	@Override
	public void queryPlanEventListByPage(IPage<Map<String, String>> page, QueryPlanEventListDto queryPlanEventListDto) {
		List<Map<String, String>> ls = planEventMapper.selectPlanEventByPage(page, queryPlanEventListDto);
		if (CollectionUtils.isNotEmpty(ls)) {
			for (Map<String, String> map : ls) {
				String eventType = map.get("eventType");
				String guaranteeType = map.get("guaranteeType");
				map.put("eventTypeName", CommonConstant.EVENT_TYPE.get(eventType));
				map.put("guaranteeTypeName", CommonConstant.GUARANTEE_TYPE.get(guaranteeType));
				map.put("remark", map.get("remark1"));
			}
		}
		page.setRecords(ls);
	}

	@Override
	public void addPlanEvent(PlanEventDto planEventDto, String userId) throws Exception {
		TbPlanEvent planEvent = new TbPlanEvent();
		BeanUtils.copyProperties(planEvent, planEventDto);
		planEvent.setPlanEventId(UUIDUtils.getUUID());
		planEvent.setRemark1(planEventDto.getRemark());
		planEvent.setCreateEmp(userId);
		planEventMapper.insert(planEvent);
	}

	@Override
	public void editPlanEvent(PlanEventDto planEventDto, String userId) throws Exception {
		TbPlanEvent planEvent = planEventMapper.selectPlanEventById(planEventDto.getPlanEventId());
		if (planEvent != null) {
			BeanUtils.copyProperties(planEvent, planEventDto);
			planEvent.setRemark1(planEventDto.getRemark());
			planEvent.setUpdateEmp(userId);
			planEvent.setUpdateDate(new Date());
			planEventMapper.updatePlanEvent(planEvent);
		} else {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, CommonCodeConstant.ERROR_CODE_40401_MSG);
		}
	}

	@Override
	public void deletePlanEvent(PlanEventDto planEventDto) {
		planEventMapper.deletePlanEventById(planEventDto.getPlanEventId());
	}
}
