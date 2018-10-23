package com.qcap.cac.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AppTaskRestCheckReq;
import com.qcap.cac.dto.AppTaskRestReq;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.AppTaskRestSrv;
import com.qcap.core.model.ResParams;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="app端任务模块接口")
@Controller
@RequestMapping("/rest/appTask")
public class AppTaskRestController {
	
	@Resource
	public AppTaskRestSrv appTaskRestSrv;
	
	@ResponseBody
	@RequestMapping(value = "/queryTaskItem", method = RequestMethod.POST)
	@ApiOperation(value="清洁人员查询各状态任务数量",notes="清洁人员查询各状态任务数量",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object queryTaskItem (@ApiParam(value = "员工工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode) {
		Map<String, Object> map = this.appTaskRestSrv.queryTaskItem(employeeCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
	};
	
	@ResponseBody
	@RequestMapping(value = "/queryHistoryTask", method = RequestMethod.POST)
	@ApiOperation(value="清洁人员查询任务历史记录",notes="清洁人员查询任务历史记录",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object queryHistoryTask(@Valid AppTaskRestReq appTaskRestDto) {
		List<Map<String, Object>> list = null;
		try {
			list = this.appTaskRestSrv.queryHistoryTask(appTaskRestDto);
		} catch (Exception e) {
			throw new BaseException(CommonCodeConstant.SYS_EXCEPTION_CODE,CommonCodeConstant.SYS_EXCEPTION_MSG);
		} 
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryUnfinishTask", method = RequestMethod.POST)
	@ApiOperation(value="清洁人员查询待处理任务列表、进行中任务列表",notes="清洁人员查询待处理任务列表、进行中任务列表",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object queryUnfinishTask(@Valid AppTaskRestReq appTaskRestDto) {
		List<Map<String, Object>> list = this.appTaskRestSrv.queryUnfinishTask(appTaskRestDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryTaskDetail", method = RequestMethod.POST)
	@ApiOperation(value="清洁人员查询任务详情",notes="清洁人员查询任务详情",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object queryTaskDetail(@ApiParam(value = "任务编号", required = true) @RequestParam("taskCode") String taskCode ) {
		Map<String, Object> map =this.appTaskRestSrv.queryTaskDetail(taskCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
	}
	
	@ResponseBody
	@RequestMapping(value = "/queryFinishAndCheckTask", method = RequestMethod.POST)
	@ApiOperation(value="已完成任务、检查任务、已检查任务列表",notes="已完成任务、检查任务、已检查任务列表",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object queryFinishAndCheckTask(@Valid AppTaskRestCheckReq appTaskRestCheckReq) {
		List<Map<String, Object>> list = this.appTaskRestSrv.queryFinishAndCheckTask(appTaskRestCheckReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
	}

}
