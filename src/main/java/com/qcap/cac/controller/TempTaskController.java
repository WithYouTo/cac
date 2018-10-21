package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.TempTaskDto;
import com.qcap.cac.dto.TempTaskSearchParam;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.utils.DateUtil;

@RestController
@RequestMapping("/tempTask")
public class TempTaskController {

	@Resource
	private TempTaskSrv tempTaskSrv;

	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Object listTask(@Valid TempTaskSearchParam paramDto) {
		new PageFactory<>().defaultPage();
		List<Map<String,Object>> list = tempTaskSrv.listTask(paramDto);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}

	
	@RequestMapping("/selectStandard")
	public Object selectStandardItem() {
		List<Map<String,Object>> list = this.tempTaskSrv.selectStandardItem();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询标准成功", list);
	}

	
	@RequestMapping(value = "/selectArea", method = RequestMethod.POST)
	public Object selectAreaItem() {
		List<ZTreeNode> list = this.tempTaskSrv.selectAreaItem();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询区域成功", list);
	}

	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object insertTempTask(@Valid TempTaskDto taskDto) {
		Map<String, Object> map = this.tempTaskSrv.insertTempTask(taskDto);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object updateTempTask(@Valid TempTaskDto taskDto) {
		Map<String, Object> map = this.tempTaskSrv.updateTempTask(taskDto);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
	}

	
	@RequestMapping(value = "/selectTaskStatus", method = RequestMethod.POST)
	public Object selectTaskStatus() {
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", CommonConstant.TASK_STATUS);
	}

	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object deleteTask(@RequestParam String taskCode) {
		return this.tempTaskSrv.deleteTempTask(taskCode);

	}

	
	@RequestMapping(value = "/selectShift", method = RequestMethod.POST)
	public Object selectShiftType() {
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", CommonConstant.SHIFT);

	}
	
	
	@RequestMapping(value = "/selectAllEmployee", method = RequestMethod.POST)
	public Object selectAllEmployee() {
		List<Map<String, Object>> list = this.tempTaskSrv.selectAllEmployee(DateUtil.dateToMonth(new Date()));
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,list);
	}
	
	@RequestMapping(value = "/selectDefaultEmployee", method = RequestMethod.POST)
	public Object selectDefaultEmployee (@RequestParam ("startTime") String startTime ,@RequestParam ("areaCode") String areaCode) {
		Map<String, Object> map = this.tempTaskSrv.selectDefaultEmployee(startTime, areaCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
	}

}
