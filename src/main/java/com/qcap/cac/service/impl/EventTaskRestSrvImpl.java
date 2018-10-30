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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EventTaskRestMapper;
import com.qcap.cac.dao.TempTaskMapper;
import com.qcap.cac.dto.EventTaskRestDto;
import com.qcap.cac.dto.QueryHistoryFlightInfoReq;
import com.qcap.cac.dto.QueryHistoryFlightInfoResp;
import com.qcap.cac.entity.TbFlightInfo;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.EventTaskRestSrv;
import com.qcap.cac.tools.JpushTools;
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
public class EventTaskRestSrvImpl implements EventTaskRestSrv {

	@Resource
	private EventTaskRestMapper eventTaskRestMapper;

	@Resource
	private TempTaskMapper tempTaskMapper;

	@Resource
	private JdbcTemplate jdbcTemplate;

	@Override
	public void geneEventTask(EventTaskRestDto eventTaskDto) {
		Date planningTakeoffDateTime = DateUtil.stringToDateTime(eventTaskDto.getPlanningTakeoffTime());
		Date estimatedTakeoffDateTime = DateUtil.stringToDateTime(eventTaskDto.getEstimatedTakeoffTime());

		// 新增航班数据到数据库
		TbFlightInfo flightInfo = new TbFlightInfo();
		BeanUtils.copyProperties(eventTaskDto, flightInfo);
		flightInfo.setFlightInfoId(UUIDUtils.getUUID());
		flightInfo.setDepartureGate(eventTaskDto.getAreaCode());
		flightInfo.setPlanningTakeoffTime(planningTakeoffDateTime);
		flightInfo.setEstimatedTakeoffTime(estimatedTakeoffDateTime);
		flightInfo.setCreateEmp(eventTaskDto.getEmployeeCode());
		flightInfo.setCreateDate(new Date());
		flightInfo.setVersion(0);
		eventTaskRestMapper.insertFlightInfo(flightInfo);

		// 查询事件基础设定,设置任务开始、结束时间
		String eventBasicType = "";
		if (CommonConstant.EVENT_TYPE_ARRIVE.equals(eventTaskDto.getEventType())) {
			if (CommonConstant.GUARANTEE_TYPE_NORMAL.equals(eventTaskDto.getGuaranteeType())) {
				eventBasicType = CommonConstant.EVENT_TYPE_ARRIVE + "_" + CommonConstant.GUARANTEE_TYPE_NORMAL;
			}
			if (CommonConstant.GUARANTEE_TYPE_IMPORTANT.equals(eventTaskDto.getGuaranteeType())) {
				eventBasicType = CommonConstant.EVENT_TYPE_ARRIVE + "_" + CommonConstant.GUARANTEE_TYPE_IMPORTANT;
			}
		} else {
			eventBasicType = CommonConstant.EVENT_TYPE_LEAVE;
		}
		String advanceTimeCountStr = eventTaskRestMapper.selectAdvanceTime(eventBasicType);
		if (StringUtils.isEmpty(advanceTimeCountStr)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "未查询到事件基础设定");
		}

		// 任务开始时间和结束时间的设置
		int advanceTimeCount = Integer.valueOf(advanceTimeCountStr);
		Date taskStartTime = null;
		Date taskEndTime = null;
		if (advanceTimeCount > 0) {
			taskEndTime = planningTakeoffDateTime;
			Calendar cal = Calendar.getInstance();
			cal.setTime(planningTakeoffDateTime);
			cal.add(Calendar.MINUTE, advanceTimeCount * (-1));
			taskStartTime = cal.getTime();
		} else {
			taskStartTime = planningTakeoffDateTime;
			Calendar cal = Calendar.getInstance();
			cal.setTime(planningTakeoffDateTime);
			cal.add(Calendar.MINUTE, advanceTimeCount * (-1));
			taskEndTime = cal.getTime();
		}

		// 查询事件性计划
		List<Map<String, String>> eventPlanList = eventTaskRestMapper.selectEventPlan(eventTaskDto);
		if (CollectionUtils.isEmpty(eventPlanList)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "根据区域、到/离类型和保障等级未查询到事件性计划");
		}

		// 查询岗位
		Map<String, Object> positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(eventTaskDto.getAreaCode());
		if (MapUtils.isEmpty(positionMap)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "该区域未设置岗位");
		}
		String positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
		String positionName = ToolUtil.toStr(positionMap.get("positionName"));

		// 查询班次
		// String planningTakeoffTime = eventTaskDto.getPlanningTakeoffTime();
		// String queryTime = planningTakeoffTime.substring(11);
		String queryTime = DateUtil.dateTimeToString(taskStartTime).substring(11);
		Map<String, String> shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		if (MapUtils.isEmpty(shiftMap)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "根据计划时间未查询到班次，请先设置班次");
		}
		String shift = shiftMap.get("shift");

		/**
		 * 查询当班人员
		 */
		// 处理日期
		// Date planningTakeOffDate =
		// DateUtil.stringToDate(planningTakeoffTime);
		Calendar calendar = Calendar.getInstance();
		// calendar.setTime(planningTakeOffDate);
		calendar.setTime(taskStartTime);
		int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		// String queryDay = "day" + dayNum;
		String queryDay = "DAY_" + dayNum;
		// String month=DateUtil.dateToMonth(planningTakeOffDate);
		String month = DateUtil.dateToMonth(taskStartTime);

		// 封装查询条件
		Map<String, Object> param = new HashMap<>();
		param.put("shift", shift);
		param.put("month", month);
		param.put("positionCode", positionCode);
		param.put(queryDay, queryDay);

		// 查询当班人员
		// List<Map<String, Object>> list =
		// this.tempTaskMapper.selectWorkingEmployee(param);
		List<Map<String, Object>> list = this.selectWorkingEmployee(shift, month, positionCode, queryDay);
		if (CollectionUtils.isEmpty(list)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "根据计划时间未查询到当班人员，请先设置当班人员");
		}

		List<String> employeeCodeList = new ArrayList<>();
		List<String> employeeNameList = new ArrayList<>();
		List<String> employeeTelList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			employeeCodeList.add(ToolUtil.toStr(m.get("employeeCode")));
			employeeNameList.add(ToolUtil.toStr(m.get("employeeName")));
			employeeTelList.add(ToolUtil.toStr(m.get("employeeTel")));

			/**
			 * 推送消息到该值班人员
			 */
		}
		String employeeCode = String.join(",", employeeCodeList);
		String employeeName = String.join(",", employeeNameList);
		String employeeTel = String.join(",", employeeTelList);

		Date now = new Date();
		TbTask task = new TbTask();
		task.setTaskType(CommonConstant.TASK_TYPE_EVENT);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setAreaCode(eventTaskDto.getAreaCode());
		task.setAreaName(eventTaskDto.getAreaName());
		task.setShift(shift);
		// task.setSpec(spec);
		task.setEmployeeCode(employeeCode);
		task.setEmployeeName(employeeName);
		task.setEmployeeTel(employeeTel);
		// task.setCompleteTime(completeTime);
		// task.setStartTime(planningTakeoffDateTime);
		task.setStartTime(taskStartTime);
		task.setEndTime(taskEndTime);
		task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
		task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
		// task.setTaskScore(taskScore);
		// task.setTaskAdvice(taskAdvice);
		task.setCreateDate(now);
		task.setCreateEmp(eventTaskDto.getEmployeeCode());
		task.setVersion(0);

		List<TbTask> taskList = new ArrayList<>();
		for (Map<String, String> m : eventPlanList) {

			task.setTaskId(UUIDUtils.getUUID());
			task.setTaskCode(CommonConstant.TASK_PREFIX_E + DateUtil.dateTimeToStringForLineNo(now));
			String standardCode = m.get("standardCode");

			// 查询标准详细信息
			List<Map<String, Object>> standardList = this.tempTaskMapper.selectStandardItem(standardCode);
			if (CollectionUtils.isEmpty(standardList)) {
				throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "该标准不存在");
			}

			String uploadPicFlag = ToolUtil.toStr(standardList.get(0).get("uploadPicFlag"));
			String checkFlag = ToolUtil.toStr(standardList.get(0).get("checkFlag"));
			String standardName = ToolUtil.toStr(standardList.get(0).get("standardName"));

			task.setPlanId(m.get("planEventId"));
			task.setStandardCode(standardCode);
			task.setStandardName(standardName);
			task.setCheckFlag(checkFlag);
			task.setUploadPicFlag(uploadPicFlag);
			task.setLineNo(DateUtil.dateTimeToStringForLineNo(new Date()));
			taskList.add(task);
		}

		JpushTools.pushArray(employeeCodeList, "您有临时任务生成，请注意查阅");

		this.tempTaskMapper.insertTaskBatch(taskList);
	}

	@Override
	public List<QueryHistoryFlightInfoResp> queryHistoryFlightInfo(QueryHistoryFlightInfoReq req) {
		return eventTaskRestMapper.selectFlightInfo(req);
	}

	public List<Map<String, Object>> selectWorkingEmployee(String shift, String month, String positionCode,
			String queryDay) {
		String sql = "SELECT EMPLOYEE_CODE employeeCode,EMPLOYEE_NAME employeeName,EMPLOYEE_TEL employeeTel "
				+ "FROM tb_task_arrangement WHERE SHIFT = '" + shift + "' AND MONTH = '" + month
				+ "' AND POSITION_CODE = '" + positionCode + "' AND DELETE_FLAG = 'NORMAL' AND " + queryDay + "='√'";
		return jdbcTemplate.queryForList(sql);
	}

}
