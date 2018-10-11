package com.qcap.cac.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.TempTaskMapper;
import com.qcap.cac.dto.TempTaskDto;
import com.qcap.cac.dto.TempTaskSearchParam;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
@Service
@Transactional
public class TempTaskSrvImpl implements TempTaskSrv {
	
	@Resource
	private TempTaskMapper  tempTaskMapper;
	
	@Override
	public List<Map> listTask(TempTaskSearchParam paramDto) {
		// TODO Auto-generated method stub
		return tempTaskMapper.listTask(paramDto);
	}

	@Override
	public Map deleteTempTask(String taskCode) {
		// TODO Auto-generated method stub
		Map<String, Object>map=new HashMap<>();
		String taskStatus=this.tempTaskMapper.selectTaskStatus(taskCode);
		if(CommonConstant.TASK_STATUS_WAIT.equals(taskStatus)) {
			this.tempTaskMapper.deleteTempTask(taskCode);
			
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_SUCCESS_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务已成功取消");
			 return map;
		}
		if(CommonConstant.TASK_STATUS_WORKING.equals(taskStatus)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务正在进行中，不允许取消");
			 return map;
		}
		if(CommonConstant.TASK_STATUS_FINISH.equals(taskStatus)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务已完成，不允许取消");
			 return map;
		}
		if(CommonConstant.TASK_STATUS_CANCLE.equals(taskStatus)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务已取消");
			 return map;
		}
		return null;
	}

	@Override
	public List<Map> selectStandardItem() {
		// TODO Auto-generated method stub
		String standardCode=null;
		return this.tempTaskMapper.selectStandardItem(standardCode);
	}

	@Override
	public List<Map> selectAreaItem() {
		// TODO Auto-generated method stub
		return this.tempTaskMapper.selectAreaItem();
	}

	@Override
	public Map<String,Object> insertTempTask(TempTaskDto taskDto) {
		Map<String,Object>map=new HashMap<>();
		// TODO Auto-generated method stub
		 String areaCode=taskDto.getAreaCode();
		 String areaName=taskDto.getAreaName();
		 String standardCode=taskDto.getStandardCode();
		 String standardName=taskDto.getStandardName();
		 String startTime=taskDto.getStartTime();
		 String endTime=taskDto.getEndTime();
		 String spec=taskDto.getSpec(); 
		 String positionCode; 
		 String positionName; 
		 //查询标准详细信息
		 List<Map>standardList=this.tempTaskMapper.selectStandardItem(standardCode);
		 if(standardList ==null || standardList.isEmpty() ) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该标准不存在");
			 return map;
		 }
		 String uploadPicFlag=ToolUtil.toStr(standardList.get(0).get("uploadPicFlag")) ;
		 String checkFlag=ToolUtil.toStr(standardList.get(0).get("checkFlag"));
		 //查询岗位
		 Map positionMap=this.tempTaskMapper.selectPositionInfoByAreaCode(areaCode);
		 if(positionMap !=null && !positionMap.isEmpty()) {
			 positionCode=ToolUtil.toStr(positionMap.get("positionCode"));
			 positionName=ToolUtil.toStr(positionMap.get("positionName"));
		 }else {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该区域未设置岗位");
			 return map;
		 }
		 
		//查询班次
		 String queryTime=startTime.substring(11);
		 Map<String, String>shiftMap=this.tempTaskMapper.selectShiftByTime(queryTime);
		 if(shiftMap == null || shiftMap.isEmpty()) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "根据计划时间未查询到班次，请先设置班次");
			return map;
		 }
		 String shift=shiftMap.get("shift");
		 
		Date now=new Date();
		TbTask task=new TbTask();
		task.setTaskId(UUIDUtils.getUUID());
		task.setTaskCode(CommonConstant.TASK_PREFIX_T+DateUtil.dateTimeToStringForLineNo(now));
//		task.setPlanId(planId);
		task.setTaskType(CommonConstant.TASK_TYPE_TEMP);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setAreaCode(areaCode);
		task.setAreaName(areaName);
		task.setStandardCode(standardCode);
		task.setStandardName(standardName);
		task.setShift(shift);
		task.setSpec(spec);
//		task.setCompleteTime(completeTime);
		task.setStartTime(DateUtil.stringToDateTime(startTime));
		task.setEndTime(DateUtil.stringToDateTime(endTime));
		task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
		task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
//		task.setTaskScore(taskScore);
		task.setCheckFlag(checkFlag);
		task.setUploadPicFlag(uploadPicFlag);
//		task.setTaskAdvice(taskAdvice);
		task.setCreateDate(now);
		/**
		 * 登录没做，无法获取登录人
		 * TODO
		 */
		task.setCreateEmp("SYS_TODO");
		task.setVersion(0);
		 //处理日期
		 Date startDate=DateUtil.stringToDate(startTime);
		 Calendar calendar=Calendar.getInstance();
		 calendar.setTime(startDate);
		 int dayNum=calendar.get(Calendar.DAY_OF_MONTH);
		 String queryDay="day"+dayNum;
		 String month=DateUtil.dateToMonth(startDate);
		 //封装查询条件
		 Map<String, Object>param=new HashMap<>();
		 param.put("shift", shift);
		 param.put("month", month);
		 param.put("positionCode", positionCode);
		 param.put(queryDay, queryDay);
		 //查询当班人员
		List<Map>list= this.tempTaskMapper.selectWorkingEmployee(param);
		if(ToolUtil.isEmpty(list)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "未查询到当班人员");
			 return map;
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
		task.setEmployeeCode(employeeCode);
		task.setEmployeeName(employeeName);
		task.setEmployeeTel(employeeTel);
		this.tempTaskMapper.insertTempTask(task);
		
		 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_SUCCESS_FLAG);
		 map.put(CommonConstant.BACK_MESSAGE, "新增临时任务成功");
		 return map;
	}

	@Override
	public Map<String,Object> updateTempTask(TempTaskDto taskDto) {
		Map<String,Object>map=new HashMap<>();
		// TODO Auto-generated method stub
		String taskCode=taskDto.getTaskCode();
		 String areaCode=taskDto.getAreaCode();
		 String areaName=taskDto.getAreaName();
		 String standardCode=taskDto.getStandardCode();
		 String standardName=taskDto.getStandardName();
		 String startTime=taskDto.getStartTime();
		 String endTime=taskDto.getEndTime();
		 String spec=taskDto.getSpec(); 
		 String positionCode; 
		 String positionName; 
		 /**
		  * 查询任务状态，只有未开始的任务才能修改
		  */
		 String taskStatus=this.tempTaskMapper.selectTaskStatus(taskCode);
		 if(CommonConstant.TASK_STATUS_WORKING.equals(taskStatus)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务正在执行中，不允许修改");
			 return map;
		 }else if(CommonConstant.TASK_STATUS_FINISH.equals(taskStatus)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务已完成，不允许修改");
			 return map;
		 }else if(CommonConstant.TASK_STATUS_CANCLE.equals(taskStatus)) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该任务已取消，不允许修改");
			 
			 return map;
		 }
		 
		 //查询标准详细信息
		 List<Map>standardList=this.tempTaskMapper.selectStandardItem(standardCode);
		 if(standardList ==null || standardList.isEmpty() ) {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该标准不存在");
			 return map;
		 }
		 String uploadPicFlag=ToolUtil.toStr(standardList.get(0).get("uploadPicFlag")) ;
		 String checkFlag=ToolUtil.toStr(standardList.get(0).get("checkFlag"));
		 //查询岗位
		 Map positionMap=this.tempTaskMapper.selectPositionInfoByAreaCode(areaCode);
		 if(positionMap !=null && !positionMap.isEmpty()) {
			 positionCode=ToolUtil.toStr(positionMap.get("positionCode"));
			 positionName=ToolUtil.toStr(positionMap.get("positionName"));
		 }else {
			 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			 map.put(CommonConstant.BACK_MESSAGE, "该区域未设置岗位");
			 return map;
		 }
		 
		//查询班次
		 String queryTime=startTime.substring(11);
		 Map<String, String>shiftMap=this.tempTaskMapper.selectShiftByTime(queryTime);
		 if(shiftMap == null || shiftMap.isEmpty()) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "根据计划时间未查询到班次，请先设置班次");
			return map;
		 }
		 String shift=shiftMap.get("shift");
		 
		Date now=new Date();
		TbTask task=new TbTask();
		task.setTaskCode(taskCode);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setAreaCode(areaCode);
		task.setAreaName(areaName);
		task.setStandardCode(standardCode);
		task.setStandardName(standardName);
		task.setShift(shift);
		task.setSpec(spec);
		task.setStartTime(DateUtil.stringToDateTime(startTime));
		task.setEndTime(DateUtil.stringToDateTime(endTime));
		task.setCheckFlag(checkFlag);
		task.setUploadPicFlag(uploadPicFlag);
//		task.setTaskAdvice(taskAdvice);
		task.setUpdateDate(now);
		/**
		 * 登录没做，无法获取登录人
		 * TODO
		 */
		task.setUpdateEmp("SYS_TODO");
		 //处理日期
		 Date startDate=DateUtil.stringToDate(startTime);
		 Calendar calendar=Calendar.getInstance();
		 calendar.setTime(startDate);
		 int dayNum=calendar.get(Calendar.DAY_OF_MONTH);
		 String queryDay="day"+dayNum;
		 String month=DateUtil.dateToMonth(startDate);
		 //封装查询条件
		 Map<String, Object>param=new HashMap<>();
		 param.put("shift", shift);
		 param.put("month", month);
		 param.put("positionCode", positionCode);
		 param.put(queryDay, queryDay);
		 //查询当班人员
		List<Map>list= this.tempTaskMapper.selectWorkingEmployee(param);
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
		task.setEmployeeCode(employeeCode);
		task.setEmployeeName(employeeName);
		task.setEmployeeTel(employeeTel);
		this.tempTaskMapper.updateTempTask(task);
		
		 map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_SUCCESS_FLAG);
		 map.put(CommonConstant.BACK_MESSAGE, "修改临时任务成功");
		 return map;
	}

}
