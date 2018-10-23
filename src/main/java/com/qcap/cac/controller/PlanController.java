package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.PlanDto;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.PlanSrv;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;

@SuppressWarnings("deprecation")
@RestController
@RequestMapping("/plan")
public class PlanController {

	@Resource
	private PlanSrv planSrv;

	@Resource
	private CommonSrv commonSrv;

	@RequestMapping(value = "/queryPlanListByPage", method = RequestMethod.POST)
	public PageResParams queryPlanListByPage(@Valid QueryPlanListDto queryPlanListDto) {
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.planSrv.queryPlanListByPage(queryPlanListDto);
		for (Map<String, Object> map : list) {
			String planTimeType = ObjectUtils.toString(map.get("planTimeType"));
			String startTime = ObjectUtils.toString(map.get("startTime"));
			String endTime = ObjectUtils.toString(map.get("endTime"));
			String month = ObjectUtils.toString(map.get("month"));
			String day = ObjectUtils.toString(map.get("day"));
			String week = ObjectUtils.toString(map.get("week"));
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

		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,
				pageInfo.getTotal(), pageInfo.getList());
	}

	@RequestMapping(value = "/addPlan", method = RequestMethod.POST)
	public ResParams addPlan(HttpServletRequest request, @Valid PlanDto planDto) throws Exception {
		this.planSrv.addPlan(planDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC);
	}

	@RequestMapping(value = "/editPlan", method = RequestMethod.POST)
	public ResParams editPlan(HttpServletRequest request, @Valid PlanDto planDto) throws Exception {
		this.planSrv.editPlan(planDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@RequestMapping(value = "/deletePlan", method = RequestMethod.POST)
	public ResParams deletePlan(@Valid PlanDto planDto) throws Exception {
		this.planSrv.deletePlan(planDto);
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

}
