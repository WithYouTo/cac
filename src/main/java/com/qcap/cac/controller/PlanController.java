package com.qcap.cac.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.QueryPlanListDto;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.PlanSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;

@RestController
@RequestMapping("/plan")
public class PlanController {

	@Resource
	private PlanSrv planSrv;

	@Resource
	private CommonSrv commonSrv;

	@RequestMapping(value = "/queryPlanListByPage", method = RequestMethod.POST)
	public PageResParams queryPlanListByPage(@Valid QueryPlanListDto queryPlanListDto) throws Exception {
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String, Object>> list = this.planSrv.queryPlanListByPage(queryPlanListDto);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		Page<Map<String, Object>> pageList = (Page<Map<String, Object>>) list;
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,
				pageInfo.getTotal(), pageList);
	}

}
