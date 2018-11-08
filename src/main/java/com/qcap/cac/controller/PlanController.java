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
import com.qcap.cac.dto.BatchUpdatePlanDto;
import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.PlanSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;

@RestController
@RequestMapping("/plan")
public class PlanController {

	@Resource
	private PlanSrv planSrv;

	@Resource
	private CommonSrv commonSrv;

	@RequestMapping(value = "/queryPlanListByPage", method = RequestMethod.POST)
	public PageResParams queryPlanListByPage(IPage<Map<String, String>> page,
			@Valid QueryPlanListDto queryPlanListDto) {
		this.planSrv.queryPlanListByPage(page, queryPlanListDto);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,
				page.getTotal(), page.getRecords());
	}

	@RequestMapping(value = "/addPlan", method = RequestMethod.POST)
	public ResParams addPlan(@Valid PlanDto planDto) throws Exception {
		this.planSrv.addPlan(planDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC);
	}

	@RequestMapping(value = "/editPlan", method = RequestMethod.POST)
	public ResParams editPlan(@Valid PlanDto planDto) throws Exception {
		this.planSrv.editPlan(planDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/deletePlan", method = RequestMethod.POST)
	public ResParams deletePlan(@Valid PlanDto planDto) throws Exception {
		this.planSrv.deletePlan(planDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/batchUpdatePlan", method = RequestMethod.POST)
	public ResParams batchUpdatePlan(@RequestBody BatchUpdatePlanDto batchUpdatePlanDto) throws Exception {
		this.planSrv.batchUpdatePlan(batchUpdatePlanDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/selectPlanTimeTypeList", method = RequestMethod.POST)
	public ResParams selectPlanTimeTypeList() {
		Map<String, String> map = CommonConstant.PLAN_TIME_TYPE;
		List<Map<String, String>> ls = new ArrayList<>();
		for (String key : map.keySet()) {
			Map<String, String> mapTmp = new HashMap<>();
			mapTmp.put("id", key);
			mapTmp.put("text", map.get(key));
			ls.add(mapTmp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/selectStandardCodeList", method = RequestMethod.POST)
	public ResParams selectStandardCodeList() {
		List<Map<String, String>> list = this.commonSrv.selectStandardNameList();

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/selectArea", method = RequestMethod.POST)
	public Object selectAreaItem() {
		List<ZTreeNode> list = this.commonSrv.selectAreaItem();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询区域成功", list);
	}

	@RequestMapping(value = "/selectCheckFlagList", method = RequestMethod.POST)
	public ResParams selectCheckFlagList() {
		Map<String, String> map = CommonConstant.TASK_CHECK_FLAG;
		List<Map<String, String>> ls = new ArrayList<>();
		for (String key : map.keySet()) {
			Map<String, String> mapTmp = new HashMap<>();
			mapTmp.put("id", key);
			mapTmp.put("text", map.get(key));
			ls.add(mapTmp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/selectUploadPicFlagList", method = RequestMethod.POST)
	public ResParams selectUploadPicFlagList() {
		Map<String, String> map = CommonConstant.UPLOAD_PIC_FLAG;
		List<Map<String, String>> ls = new ArrayList<>();
		for (String key : map.keySet()) {
			Map<String, String> mapTmp = new HashMap<>();
			mapTmp.put("id", key);
			mapTmp.put("text", map.get(key));
			ls.add(mapTmp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/selectStartScanFlagList", method = RequestMethod.POST)
	public ResParams selectStartScanFlagList() {
		Map<String, String> map = CommonConstant.START_SCAN_FLAG;
		List<Map<String, String>> ls = new ArrayList<>();
		for (String key : map.keySet()) {
			Map<String, String> mapTmp = new HashMap<>();
			mapTmp.put("id", key);
			mapTmp.put("text", map.get(key));
			ls.add(mapTmp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/selectEndScanFlagList", method = RequestMethod.POST)
	public ResParams selectEndScanFlagList() {
		Map<String, String> map = CommonConstant.END_SCAN_FLAG;
		List<Map<String, String>> ls = new ArrayList<>();
		for (String key : map.keySet()) {
			Map<String, String> mapTmp = new HashMap<>();
			mapTmp.put("id", key);
			mapTmp.put("text", map.get(key));
			ls.add(mapTmp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

}
