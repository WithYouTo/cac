package com.qcap.cac.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.GetAreaReq;
import com.qcap.cac.dto.GetAreaResp;
import com.qcap.cac.service.CommonRestSrv;
import com.qcap.core.model.ResParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "APP公共接口")
@RestController
@RequestMapping(value = "/rest/common", headers = "api_version=v1")
public class CommonRestController {

	@Resource
	private CommonRestSrv commonRestSrv;

	@RequestMapping(value = "/getArea", method = RequestMethod.POST)
	@ApiOperation(value = "获取区域下拉框", notes = "获取区域下拉框", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams getArea(@Valid GetAreaReq req) {
		List<GetAreaResp> ls = commonRestSrv.getArea(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}
}
