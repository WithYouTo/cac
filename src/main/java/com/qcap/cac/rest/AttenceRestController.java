package com.qcap.cac.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AttenceReq;
import com.qcap.cac.dto.GetAttenceDetailsReq;
import com.qcap.cac.dto.GetAttenceDetailsResp;
import com.qcap.cac.dto.GetAttenceReq;
import com.qcap.cac.dto.GetAttenceResp;
import com.qcap.cac.service.AttenceRestSrv;
import com.qcap.core.model.ResParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "APP排班接口")
@RestController
@RequestMapping(value = "/rest/attence", headers = "api_version=v1")
public class AttenceRestController {

	@Resource
	private AttenceRestSrv attenceRestSrv;

	@RequestMapping(value = "/attence", method = RequestMethod.POST)
	@ApiOperation(value = "签到", notes = "签到", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams attence(@Valid AttenceReq req, List<MultipartFile> fileList) throws Exception {
		attenceRestSrv.attence(req, fileList);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC);
	}

	@RequestMapping(value = "/getAttenceList", method = RequestMethod.POST)
	@ApiOperation(value = "获取签到列表", notes = "获取签到列表", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams getAttenceList(@Valid GetAttenceReq req) throws Exception {
		List<GetAttenceResp> ls = attenceRestSrv.getAttenceList(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/getAttenceDetails", method = RequestMethod.POST)
	@ApiOperation(value = "获取签到详情", notes = "获取签到详情", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams getAttenceDetails(@Valid GetAttenceDetailsReq req) throws Exception {
		List<GetAttenceDetailsResp> ls = attenceRestSrv.getAttenceDetails(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

}
