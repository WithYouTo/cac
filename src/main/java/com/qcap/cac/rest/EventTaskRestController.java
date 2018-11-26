/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: EventTaskRestController.java 
 * @Prject: cac
 * @Package: com.qcap.cac.rest 
 * @Description: TODO
 * @author: 张天培(2017004)   
 * @date: 2018年10月11日 上午9:16:00 
 * @version: V1.0   
 */
package com.qcap.cac.rest;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.EventTaskRestDto;
import com.qcap.cac.dto.QueryHistoryFlightInfoReq;
import com.qcap.cac.dto.QueryHistoryFlightInfoResp;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.EventTaskRestSrv;
import com.qcap.core.model.ResParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EventTaskRestController
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年10月11日 上午9:16:00
 */
@Api(tags = "APP航班事件接口")
@RestController
@RequestMapping(value = "/rest/eventTask", headers = "api_version=v1")
public class EventTaskRestController {

	@Resource
	private EventTaskRestSrv eventTaskSrv;

	@RequestMapping(value = "genEventTask", method = RequestMethod.POST)
	@ApiOperation(value = "航班数据录入及事件性任务生成", notes = "航班数据录入及事件性任务生成", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams geneEventTask(@Valid EventTaskRestDto eventTaskDto) {
		try{
			eventTaskSrv.geneEventTask(eventTaskDto);
		}catch (Exception e){
			throw new BaseException(CommonCodeConstant.SYS_EXCEPTION_CODE,CommonCodeConstant.SYS_EXCEPTION_MSG);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "新增航班数据成功");

	}

	@RequestMapping(value = "/queryHistoryFlightInfo", method = RequestMethod.POST)
	@ApiOperation(value = "历史航班查询", notes = "历史航班查询", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams queryHistoryFlightInfo(@Valid QueryHistoryFlightInfoReq req) throws Exception {
		List<QueryHistoryFlightInfoResp> ls = eventTaskSrv.queryHistoryFlightInfo(req);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

	@RequestMapping(value = "/selectFlightShiftInfo", method = RequestMethod.POST)
	@ApiOperation(value = "航班班次信息查询", notes = "航班班次信息查询", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public ResParams selectFlightShiftInfo(@Valid QueryHistoryFlightInfoReq req){
		List<Map<String ,String >> list = this.eventTaskSrv.selectFlightShiftInfo();
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE,CommonCodeConstant.SUCCESS_QUERY_DESC,list);
	}

}
