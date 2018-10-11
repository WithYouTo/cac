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

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.service.EventTaskSrv;
import com.qcap.cac.tools.CommonTools;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/** 
 * @ClassName: EventTaskRestController 
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年10月11日 上午9:16:00  
 */
@Api(description="事件性任务接口")
@RestController
@RequestMapping(value="/eventTask",headers="api_version=v1")
public class EventTaskRestController {
	
	@Resource
	private EventTaskSrv eventTaskSrvImpl;
	
	@ResponseBody
	@RequestMapping(value="genEventTask",method=RequestMethod.POST)
	@ApiOperation(value="航班数据录入及事件性任务生成",notes="航班数据录入及事件性任务生成",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object geneEventTask(
			@ApiParam(value="航班号",required=true)@RequestParam(value="flightName",required=true)String flightName,
			@ApiParam(value="事件类型",required=true)@RequestParam(value="eventType",required=true)String eventType,
			@ApiParam(value="航班类型",required=true)@RequestParam(value="flightType",required=true)String flightType,
			@ApiParam(value="登机口/廊桥",required=true)@RequestParam(value="areaName",required=true)String areaName,
			@ApiParam(value="区域编码",required=true)@RequestParam(value="areaCode",required=true)String areaCode,
			@ApiParam(value="保障等级",required=true)@RequestParam(value="guaranteeType",required=true)String guaranteeType,
			@ApiParam(value="计划起飞、到达时间",required=true)@RequestParam(value="planningTakeoffTime",required=true)String planningTakeoffTime,
			@ApiParam(value="预计起飞/到达时间",required=true)@RequestParam(value="estimatedTakeoffTime",required=true)String estimatedTakeoffTime,
			@ApiParam(value="机型",required=false)@RequestParam(value="aircraftType",required=false)String aircraftType,
			@ApiParam(value="app登陆人",required=true)@RequestParam(value="loginName",required=true)String loginName) {
			Map<String, String>param=new HashMap<>();
			param.put("flightName", flightName);
			param.put("eventType", eventType);
			param.put("flightType", flightType);
			param.put("areaName", areaName);
			param.put("areaCode", areaCode);
			param.put("guaranteeType", guaranteeType);
			param.put("planningTakeoffTime", planningTakeoffTime);
			param.put("estimatedTakeoffTime", estimatedTakeoffTime);
			param.put("aircraftType", aircraftType);
			param.put("loginName", loginName);
			Map map=this.eventTaskSrvImpl.geneEventTask(param);
			return CommonTools.setMessage(CommonCodeConstant.SUCCESS_CODE, "", map);
		
	}

}
