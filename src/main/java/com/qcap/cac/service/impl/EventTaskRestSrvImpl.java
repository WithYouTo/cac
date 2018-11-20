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
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

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
	
    @Resource
    private MessageRestSrv messageRestSrvImpl;

	@Override
	public void geneEventTask(EventTaskRestDto eventTaskDto) {
		Date planningTakeoffDateTime = DateUtil.stringToDateTime(eventTaskDto.getPlanningTakeoffTime());
		Date estimatedTakeoffDateTime = DateUtil.stringToDateTime(eventTaskDto.getEstimatedTakeoffTime());
		Date taskStartTime = null;
		Date taskEndTime = null;
		String positionCode = "";
		String positionName = "";
		String employeeCode = "";
		String employeeName = "";
		String employeeTel = "";

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

		// 查询事件基础设定(提前时间)
		int advanceHours = this.getAdvanceHours(eventTaskDto);

		// 任务开始时间和结束时间的设置
		Map<String, Date> taskTime = this.getTaskTime(advanceHours, planningTakeoffDateTime);
		taskStartTime = taskTime.get("start");
		taskEndTime = taskTime.get("end");

		// 获取岗位
		Map<String, Object> positionMap = this.getPosition(eventTaskDto);
		positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
		positionName = ToolUtil.toStr(positionMap.get("positionName"));

		// 获取班次
		String shift = this.getShift(taskStartTime);

		// 获取当班人员
		List<Map<String, Object>> list = this.getWorkingEmployee(shift, positionCode, taskStartTime);
		List<String> employeeCodeList = new ArrayList<>();
		List<String> employeeNameList = new ArrayList<>();
		List<String> employeeTelList = new ArrayList<>();
		for (Map<String, Object> employeeMap : list) {
			employeeCodeList.add(ToolUtil.toStr(employeeMap.get("employeeCode")));
			employeeNameList.add(ToolUtil.toStr(employeeMap.get("employeeName")));
			employeeTelList.add(ToolUtil.toStr(employeeMap.get("employeeTel")));
		}
		employeeCode = String.join(",", employeeCodeList);
		employeeName = String.join(",", employeeNameList);
		employeeTel = String.join(",", employeeTelList);

		// 获取事件性计划
		List<Map<String, String>> eventPlanList = this.getEventPlan(eventTaskDto);

		List<TbTask> taskList = new ArrayList<>();
		Set<String> programSet = new HashSet<>();
		for (Map<String, String> eventPlanMap : eventPlanList) {
			Date now = new Date();
			TbTask task = new TbTask();
			String programCode = eventPlanMap.get("programCode");
			programSet.add(programCode);
			task.setTaskId(UUIDUtils.getUUID());
			task.setPlanId(eventPlanMap.get("planEventId"));
			task.setTaskType(CommonConstant.TASK_TYPE_EVENT);
			task.setPositionCode(positionCode);
			task.setPositionName(positionName);
			task.setAreaCode(eventTaskDto.getAreaCode());
			task.setAreaName(eventTaskDto.getAreaName());
			task.setShift(shift);
			task.setProgramCode(eventPlanMap.get("programCode"));
			task.setEmployeeCode(employeeCode);
			task.setEmployeeName(employeeName);
			task.setEmployeeTel(employeeTel);
			task.setStartTime(taskStartTime);
			task.setEndTime(taskEndTime);
			task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
			task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
			task.setCreateDate(now);
			task.setCreateEmp(eventTaskDto.getEmployeeCode());
			task.setVersion(0);
			task.setTaskCode(CommonConstant.TASK_PREFIX_E + DateUtil.dateTimeToStringForLineNo(now));
			
			String uploadPicFlag = ToolUtil.toStr(eventPlanMap.get("uploadPicFlag"));
			String checkFlag = ToolUtil.toStr(eventPlanMap.get("checkFlag"));
			String startScanFlag = ToolUtil.toStr(eventPlanMap.get("startScanFlag"));
			String endScanFlag = ToolUtil.toStr(eventPlanMap.get("endScanFlag"));
			task.setCheckFlag(checkFlag);
			task.setUploadPicFlag(uploadPicFlag);
			task.setStartScanFlag(startScanFlag);
			task.setEndScanFlag(endScanFlag);
			// 查询标准详细信息
			Map<String, Object> standardMap = this.getStandard(eventPlanMap.get("standardCode"));
			String standardName = ToolUtil.toStr(standardMap.get("standardName"));
			task.setStandardCode(eventPlanMap.get("standardCode"));
			task.setStandardName(standardName);
			taskList.add(task);
		}
		tempTaskMapper.insertTaskBatch(taskList);

		// 根据工号推送任务通知
//		JpushTools.pushArray(employeeCodeList, "您有临时任务生成，请注意查阅");
		for(String programCode: programSet) {
			messageRestSrvImpl.JpushMessage(employeeCodeList, programCode, "您有临时任务生成，请注意查阅", "临时任务");
		}
	}

	@Override
	public List<QueryHistoryFlightInfoResp> queryHistoryFlightInfo(QueryHistoryFlightInfoReq req) {
		return eventTaskRestMapper.selectFlightInfo(req);
	}

	@Override
	public List<Map<String, String>> selectFlightShiftInfo() {
		List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
		programCodes.removeAll(Collections.singleton(""));
		String programCode = String.join(",",programCodes);
		return this.eventTaskRestMapper.selectFlightShiftInfo(programCode);
	}

	/**
	 * 
	 * @Title: getAdvanceHours @Description: 获取提前时间（单位：小时） @param: @param
	 * eventTaskDto @param: @return @return: int @throws
	 */
	public int getAdvanceHours(EventTaskRestDto eventTaskDto) {
		String eventBasicType = "";
		String advanceTimeCountStr = "";
		if (CommonConstant.EVENT_TYPE_ARRIVE.equals(eventTaskDto.getEventType())
				&& CommonConstant.GUARANTEE_TYPE_NORMAL.equals(eventTaskDto.getGuaranteeType())) {
			eventBasicType = CommonConstant.EVENT_TYPE_ARRIVE + "_" + CommonConstant.GUARANTEE_TYPE_NORMAL;
		} else if (CommonConstant.EVENT_TYPE_ARRIVE.equals(eventTaskDto.getEventType())
				&& CommonConstant.GUARANTEE_TYPE_IMPORTANT.equals(eventTaskDto.getGuaranteeType())) {
			eventBasicType = CommonConstant.EVENT_TYPE_ARRIVE + "_" + CommonConstant.GUARANTEE_TYPE_IMPORTANT;
		} else {
			eventBasicType = CommonConstant.EVENT_TYPE_LEAVE;
		}
		advanceTimeCountStr = eventTaskRestMapper.selectAdvanceTime(eventBasicType);
		if (StringUtils.isEmpty(advanceTimeCountStr)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "未查询到事件基础设定");
		}
		return Integer.valueOf(advanceTimeCountStr);
	}

	/**
	 * 
	 * @Title: getTaskTime @Description: 获取任务开始、结束时间 @param: @param
	 * advanceHours @param: @param planTime @param: @return @return:
	 * Map<String,Date> @throws
	 */
	public Map<String, Date> getTaskTime(int advanceHours, Date planTime) {
		Map<String, Date> taskTime = new HashMap<>();
		Date taskStartTime = null;
		Date taskEndTime = null;
		if (advanceHours > 0) {
			taskEndTime = planTime;
			Calendar cal = Calendar.getInstance();
			cal.setTime(planTime);
			cal.add(Calendar.MINUTE, advanceHours * (-1));
			taskStartTime = cal.getTime();
		} else {
			taskStartTime = planTime;
			Calendar cal = Calendar.getInstance();
			cal.setTime(planTime);
			cal.add(Calendar.MINUTE, advanceHours * (-1));
			taskEndTime = cal.getTime();
		}
		taskTime.put("start", taskStartTime);
		taskTime.put("end", taskEndTime);
		return taskTime;
	}

	/**
	 * 
	 * @Title: getEventPlan @Description: 获取事件性计划 @param: @param
	 * eventTaskDto @param: @return @return: List<Map<String,String>> @throws
	 */
	public List<Map<String, String>> getEventPlan(EventTaskRestDto eventTaskDto) {
		// 查询事件性计划
		List<Map<String, String>> eventPlanList = eventTaskRestMapper.selectEventPlan(eventTaskDto);
		if (CollectionUtils.isEmpty(eventPlanList)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "根据区域、到/离类型和保障等级未查询到事件性计划");
		}
		return eventPlanList;
	}

	/**
	 * 
	 * @Title: getPosition @Description: 获取岗位 @param: @param
	 * eventTaskDto @param: @return @return: Map<String,Object> @throws
	 */
	public Map<String, Object> getPosition(EventTaskRestDto eventTaskDto) {
		Map<String, Object> positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(eventTaskDto.getAreaCode());
		if (MapUtils.isEmpty(positionMap)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "该区域未设置岗位");
		}
		return positionMap;
	}

	/**
	 * 
	 * @Title: getShift @Description: 获取班次 @param: @param
	 * dateTime @param: @return @return: String @throws
	 */
	public String getShift(Date dateTime) {
		String queryTime = DateUtil.dateTimeToString(dateTime).substring(11);
		Map<String, String> shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		if (MapUtils.isEmpty(shiftMap)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "根据计划时间未查询到班次，请先设置班次");
		}
		return shiftMap.get("shift");
	}

	/**
	 * 
	 * @Title: getWorkingEmployee @Description: 获取当班人员 @param: @param
	 * shift @param: @param positionCode @param: @param
	 * dateTime @param: @return @return: List<Map<String,Object>> @throws
	 */
	public List<Map<String, Object>> getWorkingEmployee(String shift, String positionCode, Date dateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		String queryDay = "DAY_" + dayNum;
		String month = DateUtil.dateToMonth(dateTime);

		String sql = "SELECT EMPLOYEE_CODE employeeCode,EMPLOYEE_NAME employeeName,EMPLOYEE_TEL employeeTel "
				+ "FROM tb_task_arrangement WHERE SHIFT = '" + shift + "' AND MONTH = '" + month
				+ "' AND POSITION_CODE = '" + positionCode + "' AND DELETE_FLAG = 'NORMAL' AND " + queryDay + "='√'";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

		if (CollectionUtils.isEmpty(list)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "根据计划时间未查询到当班人员，请先设置当班人员");
		}
		return list;
	}

	/**
	 * 
	 * @Title: getStandard @Description: 获取清洁标准 @param: @param
	 * standardCode @param: @return @return: Map<String,Object> @throws
	 */
	public Map<String, Object> getStandard(String standardCode) {
		List<Map<String, Object>> standardList = this.tempTaskMapper.selectStandardItem(standardCode);
		if (CollectionUtils.isEmpty(standardList)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "该标准不存在");
		}
		return standardList.get(0);
	}

}
