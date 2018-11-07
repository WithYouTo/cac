package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.PlanEventDto;
import com.qcap.cac.dto.QueryPlanEventListDto;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.PlanEventSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;

@SuppressWarnings("static-access")
@RestController
@RequestMapping("/planEvent")
public class PlanEventController {

	@Resource
	private PlanEventSrv planEventSrv;

	@Resource
	private CommonSrv commonSrv;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtProperties jwtProperties;

	@RequestMapping(value = "/queryPlanEventListByPage", method = RequestMethod.POST)
	public PageResParams queryPlanListByPage(IPage<Map<String, String>> page,
			@Valid QueryPlanEventListDto queryPlanEventListDto) {
		// new PageFactory<Map<String, Object>>().defaultPage();
		// List<Map<String, Object>> list =
		// this.planEventSrv.queryPlanEventListByPage(queryPlanEventListDto);
		// for (Map<String, Object> map : list) {
		// String eventType = Objects.toString(map.get("eventType"));
		// String guaranteeType = Objects.toString(map.get("guaranteeType"));
		// map.put("eventTypeName", CommonConstant.EVENT_TYPE.get(eventType));
		// map.put("guaranteeTypeName",
		// CommonConstant.GUARANTEE_TYPE.get(guaranteeType));
		// map.put("remark", map.get("remark1"));
		// }
		//
		// PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String,
		// Object>>(list);
		// return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE,
		// CommonCodeConstant.SUCCESS_QUERY_DESC,
		// pageInfo.getTotal(), pageInfo.getList());
		this.planEventSrv.queryPlanEventListByPage(page, queryPlanEventListDto);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,
				page.getTotal(), page.getRecords());
	}

	@RequestMapping(value = "/addPlanEvent", method = RequestMethod.POST)
	public ResParams addPlan(HttpServletRequest request, @Valid PlanEventDto planEventDto) throws Exception {

		String token = request.getHeader(jwtProperties.getTokenHeader());
		String userId = jwtTokenUtil.getUsernameFromToken(token);
		this.planEventSrv.addPlanEvent(planEventDto, userId);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC);
	}

	@RequestMapping(value = "/editPlanEvent", method = RequestMethod.POST)
	public ResParams editPlan(HttpServletRequest request, @Valid PlanEventDto planEventDto) throws Exception {

		String token = request.getHeader(jwtProperties.getTokenHeader());
		String userId = jwtTokenUtil.getUsernameFromToken(token);
		this.planEventSrv.editPlanEvent(planEventDto, userId);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/deletePlanEvent", method = RequestMethod.POST)
	public ResParams deletePlan(@Valid PlanEventDto planEventDto) throws Exception {
		this.planEventSrv.deletePlanEvent(planEventDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
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
