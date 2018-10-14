/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: EventTaskSrvImpl.java 
 * @Prject: cac
 * @Package: com.qcap.cac.service 
 * @Description: TODO
 * @author: 张天培(2017004)   
 * @date: 2018年10月11日 上午10:13:56 
 * @version: V1.0   
 */
package com.qcap.cac.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EventTaskMapper;
import com.qcap.cac.dao.TempTaskMapper;
import com.qcap.cac.dto.EventTaskRestDto;
import com.qcap.cac.entity.TbFlightInfo;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.EventTaskSrv;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;

/** 
 * @ClassName: EventTaskSrvImpl 
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年10月11日 上午10:13:56  
 */
@Service
@Transactional
public class EventTaskSrvImpl implements EventTaskSrv {
	
	@Resource
	private EventTaskMapper eventTaskMapper;
	
	@Resource
	private TempTaskMapper tempTaskMapper;
	
	//事件性任务的任务时长
	@Value("${EVENT_TASK_MIN_INTERVAL}")
	private int eventTaskMinInterval;

	@Override
	public void geneEventTask(EventTaskRestDto eventTaskDto) {
		Date planningTakeoffDateTime = DateUtil.stringToDateTime(eventTaskDto.getPlanningTakeoffTime());
		Date estimatedTakeoffDateTime =DateUtil.stringToDateTime(eventTaskDto.getEstimatedTakeoffTime());
		
		//新增航班数据到数据库
		TbFlightInfo flightInfo = new TbFlightInfo();
		BeanUtils.copyProperties(eventTaskDto, flightInfo);
		flightInfo.setFlightInfoId(UUIDUtils.getUUID());
		flightInfo.setPlanningTakeoffTime(planningTakeoffDateTime);
		flightInfo.setEstimatedTakeoffTime(estimatedTakeoffDateTime);
		flightInfo.setCreateDate(new Date());
		flightInfo.setVersion(0);
		this.eventTaskMapper.insertFlightInfo(flightInfo);
		
		//查询事件性计划
		List<Map<String,String>> eventPlanList = this.eventTaskMapper.selectEventPlan(eventTaskDto);
		if(CollectionUtils.isEmpty(eventPlanList)) {
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "根据区域、到/离类型和保障等级未查询到事件性计划");
		}
		
		
		//查询岗位
		 Map positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(eventTaskDto.getAreaCode());
		 if(MapUtils.isEmpty(positionMap)) {
			 throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "该区域未设置岗位");
		 }
		 String positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
		 String positionName = ToolUtil.toStr(positionMap.get("positionName"));
		
		 //查询班次
		 String planningTakeoffTime = eventTaskDto.getPlanningTakeoffTime();
		 String queryTime = planningTakeoffTime.substring(11);
		 Map<String, String>shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		 if(MapUtils.isEmpty(shiftMap)) {
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "根据计划时间未查询到班次，请先设置班次");
		 }
		 String shift = shiftMap.get("shift");
		 
		 /**
		  * 查询当班人员
		  */
		 //处理日期
		 Date planningTakeOffDate = DateUtil.stringToDate(planningTakeoffTime);
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(planningTakeOffDate);
		 int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		 String queryDay = "day"+dayNum;
		 String month=DateUtil.dateToMonth(planningTakeOffDate);
		 
		 //封装查询条件
		 Map<String, Object>param=new HashMap<>();
		 param.put("shift", shift);
		 param.put("month", month);
		 param.put("positionCode", positionCode);
		 param.put(queryDay, queryDay);
		 
		 //查询当班人员
		List<Map<String,Object>>list= this.tempTaskMapper.selectWorkingEmployee(param);
		if(CollectionUtils.isEmpty(list)) {
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "根据计划时间未查询到班次，请先设置班次");
		}
		
		List<String> employeeCodeList=new ArrayList<>();
		List<String> employeeNameList=new ArrayList<>();
		List<String> employeeTelList=new ArrayList<>();
		for(Map m:list) {
			employeeCodeList.add(ToolUtil.toStr(m.get("employeeCode")));
			employeeNameList.add(ToolUtil.toStr(m.get("employeeName")));
			employeeTelList.add(ToolUtil.toStr(m.get("employeeTel")));
			
			/**
			 * 推送消息到该值班人员
			 */
		}
		String employeeCode= String.join(",", employeeCodeList);
		String employeeName= String.join(",", employeeNameList);
		String employeeTel= String.join(",", employeeTelList);
		
		//获取通用配置表中的计划时间间隔
		calendar.setTime(planningTakeoffDateTime);
		calendar.add(Calendar.MINUTE, eventTaskMinInterval);
		Date eventTaskEndTime=calendar.getTime();
		
		Date now=new Date();
		TbTask task=new TbTask();
		task.setTaskType(CommonConstant.TASK_TYPE_EVENT);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setAreaCode(eventTaskDto.getAreaCode());
		task.setAreaName(eventTaskDto.getAreaName());
		task.setShift(shift);
//		task.setSpec(spec);
		task.setEmployeeCode(employeeCode);
		task.setEmployeeName(employeeName);
		task.setEmployeeTel(employeeTel);
//		task.setCompleteTime(completeTime);
		task.setStartTime(planningTakeoffDateTime);
		task.setEndTime(eventTaskEndTime);
		task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
		task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
//		task.setTaskScore(taskScore);
//		task.setTaskAdvice(taskAdvice);
		task.setCreateDate(now);
		/**
		 * 登录没做，无法获取登录人
		 * TODO
		 */
		task.setCreateEmp(eventTaskDto.getLoginName());
		task.setVersion(0);
		
		List<TbTask>taskList=new ArrayList<>();
		for(Map<String, String>m:eventPlanList) {
			
			task.setTaskId(UUIDUtils.getUUID());
			task.setTaskCode(CommonConstant.TASK_PREFIX_E+DateUtil.dateTimeToStringForLineNo(now));
			String standardCode=m.get("standardCode");
			
			 //查询标准详细信息
			 List<Map<String,Object>>standardList=this.tempTaskMapper.selectStandardItem(standardCode);
			 if(CollectionUtils.isEmpty(standardList)) {
				 throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "该标准不存在");
			 }
			 
			String uploadPicFlag=ToolUtil.toStr(standardList.get(0).get("uploadPicFlag")) ;
			String checkFlag=ToolUtil.toStr(standardList.get(0).get("checkFlag"));
			String standardName=ToolUtil.toStr(standardList.get(0).get("standardName"));
			
			task.setPlanId(m.get("planEventId"));
			task.setStandardCode(standardCode);
			task.setStandardName(standardName);
			task.setCheckFlag(checkFlag);
			task.setUploadPicFlag(uploadPicFlag);
			taskList.add(task);
		}
		
		this.tempTaskMapper.insertTaskBatch(taskList);
	}

}
