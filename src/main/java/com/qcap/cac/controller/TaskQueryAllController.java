package com.qcap.cac.controller;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.TaskQueryAllSearchDto;
import com.qcap.cac.service.GenTaskJobSrv;
import com.qcap.cac.service.TaskQueryAllSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/taskQuery")
public class TaskQueryAllController {
	
	@Resource
	private TaskQueryAllSrv taskQueryAllSrvImpl;
	
	@Resource(name = "genDayTimeTaskJobSrvImpl")
	private GenTaskJobSrv genDayTimeTaskJobSrvImpl;
	
	@Resource(name = "genNightTaskJobSrvImpl")
	private GenTaskJobSrv genNightTaskJobSrvImpl;
	
	@ResponseBody
	@RequestMapping(value="/selectAllTask",method=RequestMethod.POST)
	public Object selectAllTask(@Valid TaskQueryAllSearchDto taskQueryAllSearchDto) {
		new PageFactory<>().defaultPage();
		List<Map<String,Object>>list=this.taskQueryAllSrvImpl.selectAllTask(taskQueryAllSearchDto);
		PageInfo<Map<String,Object>> pageInfo =new PageInfo<>(list);
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectStandard",method=RequestMethod.POST)
	public Object selectStandard() {
		List<Map<String,Object>>list=this.taskQueryAllSrvImpl.selectStandard();
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectPosition",method=RequestMethod.POST)
	public Object selectPosition(@RequestParam("shift") String shift) {
		List<Map<String,Object>>list=this.taskQueryAllSrvImpl.selectPosition(shift);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value="/selectStandardDetail",method=RequestMethod.POST)
	public Object selectStandardDetail(@RequestParam(value="standardCode",required=true) String standardCode) {
		Map<String,Object> map=this.taskQueryAllSrvImpl.selectStandardDetail(standardCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectEmployee",method=RequestMethod.POST)
	public Object selectEmployee(@RequestParam String programCode) {
		List<Map<String,Object>>list=this.taskQueryAllSrvImpl.selectEmployee(programCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}
	
	@ResponseBody
	@RequestMapping(value="/genDayTimeJob",method=RequestMethod.POST)
	public Object genDayTimeJob() {
		this.genDayTimeTaskJobSrvImpl.geneTask();
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC);
	}
	
	@ResponseBody
	@RequestMapping(value="/genNightJob",method=RequestMethod.POST)
	public Object genNightJob() {
		this.genNightTaskJobSrvImpl.geneTask();
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC);
	}
	
	
}
