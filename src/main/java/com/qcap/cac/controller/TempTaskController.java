package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.TempTaskDto;
import com.qcap.cac.dto.TempTaskSearchParam;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;

@RestController
@RequestMapping("/tempTask")
public class TempTaskController {
	
	@Resource
	private TempTaskSrv tempTaskSrv;
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public Object listTask(@Valid TempTaskSearchParam paramDto){
		List<Map> list=tempTaskSrv.listTask(paramDto);
		PageInfo pageInfo=new PageInfo<>(list);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}
	
	@ResponseBody
	@RequestMapping("/selectStandard")
	public Object selectStandardItem() {
		List<Map>list=this.tempTaskSrv.selectStandardItem();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询标准成功", list);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectArea",method=RequestMethod.POST)
	public Object selectAreaItem() {
		List<Map>list=this.tempTaskSrv.selectAreaItem();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询区域成功", list);
	}
	
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object insertTempTask(@Valid TempTaskDto taskDto) {
		Map<String,Object>map=this.tempTaskSrv.insertTempTask(taskDto);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
	}
	
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Object updateTempTask(@Valid TempTaskDto taskDto) {
		Map<String,Object>map=this.tempTaskSrv.updateTempTask(taskDto);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
	}
	
	@ResponseBody
	@RequestMapping(value="/selectTaskStatus",method=RequestMethod.POST)
	public Object selectTaskStatus() {
		List<Map>list=new ArrayList<>();
		Set<Entry<String,String>>entrys=CommonConstant.TASK_STATUS.entrySet();
		for(Entry<String,String>en:entrys) {
			Map map=new HashMap<>();
			map.put("key", en.getKey());
			map.put("value", en.getValue());
			if(!CommonConstant.TASK_STATUS_CANCLE.equals(en.getKey())) {
				//过滤掉已取消状态
				list.add(map);
			}
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteTask(@RequestParam String taskCode) {
		Map map=this.tempTaskSrv.deleteTempTask(taskCode);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
		
	}
	@ResponseBody
	@RequestMapping(value="/selectShift",method=RequestMethod.POST)
	public Object selectShiftType() {
		List<Map>list=new ArrayList<>();
		Map mapInit= new HashMap<String,String>();
		mapInit.put("key", "");
		mapInit.put("value", "全部");
		list.add(mapInit);
		Set<Entry<String,String>>entrys=CommonConstant.SHIFT.entrySet();
		for(Entry<String,String>en:entrys) {
			Map map=new HashMap<>();
			map.put("key", en.getKey());
			map.put("value", en.getValue());
			list.add(map);
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", null);
		
	}
	
}
