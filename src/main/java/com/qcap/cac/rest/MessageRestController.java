package com.qcap.cac.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.GetMessageReq;
import com.qcap.cac.dto.GetMessageResp;
import com.qcap.cac.dto.UpdateMessageReadReq;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.core.model.ResParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(tags = "APP消息中心接口")
@RestController
@RequestMapping(value = "/rest/message", headers = "api_version=v1")
public class MessageRestController {

	@Resource
	private MessageRestSrv messageRestSrv;

	@RequestMapping(value = "/getMessageForNewest", method = RequestMethod.POST)
	@ApiOperation(value = "最新消息列表", notes = "最新消息列表", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams getMessageForNewest(@Valid GetMessageReq req) throws Exception {
		List<GetMessageResp> ls = messageRestSrv.getMessageForNewest(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/getMessage", method = RequestMethod.POST)
	@ApiOperation(value = "消息列表", notes = "消息列表", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams getMessage(@Valid GetMessageReq req) throws Exception {
		List<GetMessageResp> ls = messageRestSrv.getMessage(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/updateMessageForRead", method = RequestMethod.POST)
	@ApiOperation(value = "更新消息状态为已读", notes = "更新消息状态为已读", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams updateMessageForRead(@Valid UpdateMessageReadReq req) throws Exception {
		messageRestSrv.updateMessageForRead(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC);
	}

}
