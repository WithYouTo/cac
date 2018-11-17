package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.BatchUpdatePlanEventDto;
import com.qcap.cac.dto.PlanEventDto;
import com.qcap.cac.dto.QueryPlanEventListDto;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.PlanEventSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;

@RestController
@RequestMapping("/planEvent")
public class PlanEventController {

	@Resource
	private PlanEventSrv planEventSrv;

	@Resource
	private CommonSrv commonSrv;

	@RequestMapping(value = "/queryPlanEventListByPage", method = RequestMethod.POST)
	public PageResParams queryPlanListByPage(IPage<Map<String, String>> page,
			@Valid QueryPlanEventListDto queryPlanEventListDto) {
		this.planEventSrv.queryPlanEventListByPage(page, queryPlanEventListDto);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,
				page.getTotal(), page.getRecords());
	}

	@RequestMapping(value = "/addPlanEvent", method = RequestMethod.POST)
	public ResParams addPlan(@Valid PlanEventDto planEventDto) throws Exception {
		this.planEventSrv.addPlanEvent(planEventDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC);
	}

	@RequestMapping(value = "/editPlanEvent", method = RequestMethod.POST)
	public ResParams editPlan(@Valid PlanEventDto planEventDto) throws Exception {
		this.planEventSrv.editPlanEvent(planEventDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/batchUpdatePlanEvent", method = RequestMethod.POST)
	public ResParams batchUpdatePlanEvent(@RequestBody BatchUpdatePlanEventDto batchUpdatePlanEventDto)
			throws Exception {
		this.planEventSrv.batchUpdatePlanEvent(batchUpdatePlanEventDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/deletePlanEvent", method = RequestMethod.POST)
	public ResParams deletePlan(@Valid PlanEventDto planEventDto) throws Exception {
		this.planEventSrv.deletePlanEvent(planEventDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC);
	}

	@RequestMapping(value = "/selectStandardCodeList", method = RequestMethod.POST)
	public ResParams selectStandardCodeList() {
		List<Map<String, String>> list = this.commonSrv.selectStandardNameList();

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@RequestMapping(value = "/selectEventTypeList", method = RequestMethod.POST)
	public ResParams selectEventTypeList() {
		List<Map<String, String>> ls = new ArrayList<>();
		Map<String, String> map = CommonConstant.EVENT_TYPE;
		for (String key : map.keySet()) {
			Map<String, String> mapTemp = new HashMap<>();
			mapTemp.put("id", key);
			mapTemp.put("text", map.get(key));
			ls.add(mapTemp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/selectGuaranteeTypeList", method = RequestMethod.POST)
	public ResParams selectGuaranteeTypeList() {
		List<Map<String, String>> ls = new ArrayList<>();
		Map<String, String> map = CommonConstant.GUARANTEE_TYPE;
		for (String key : map.keySet()) {
			Map<String, String> mapTemp = new HashMap<>();
			mapTemp.put("id", key);
			mapTemp.put("text", map.get(key));
			ls.add(mapTemp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@ResponseBody
	@RequestMapping(value = "/selectArea", method = RequestMethod.POST)
	public Object selectAreaItem() {
		List<ZTreeNode> list = this.commonSrv.selectAreaItem();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询区域成功", list);
	}

}
