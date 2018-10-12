package com.qcap.cac.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.TaskQueryAllSearchDto;
import com.qcap.cac.service.TaskQueryAllSrv;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;

@Controller
@RequestMapping("/taskQuery")
public class TaskQueryAllController {
	
	@Resource
	private TaskQueryAllSrv taskQueryAllSrvImpl;
	
	@ResponseBody
	@RequestMapping(value="/selectAllTask",method=RequestMethod.POST)
	public Object selectAllTask(@Valid TaskQueryAllSearchDto taskQueryAllSearchDto) {
		List<Map>list=this.taskQueryAllSrvImpl.selectAllTask(taskQueryAllSearchDto);
		PageInfo pageInfo =new PageInfo<>(list);
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectStandard",method=RequestMethod.POST)
	public Object selectStandard() {
		List<Map>list=this.taskQueryAllSrvImpl.selectStandard();
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectPosition",method=RequestMethod.POST)
	public Object selectPosition() {
		List<Map>list=this.taskQueryAllSrvImpl.selectPosition();
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}
	
//	@ResponseBody
//	@RequestMapping(value="/selectEmployee",method=RequestMethod.POST)
//	public Object selectEmployee() {
//		List<Map>list=this.taskQueryAllSrvImpl.selectEmployee();
//		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
//	}
}
