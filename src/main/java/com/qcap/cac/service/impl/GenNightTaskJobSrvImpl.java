package com.qcap.cac.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.GenDayTimeTaskJobMapper;
import com.qcap.cac.dao.GenNightTaskJobMapper;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.service.GenTaskJobSrv;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.DateUtil;


public class GenNightTaskJobSrvImpl implements GenTaskJobSrv {

	@Resource
	private GenDayTimeTaskJobMapper dayTimeTaskMapper;
	
	@Resource
	private GenNightTaskJobMapper nightTaskJobMapper;

	private Logger log = AppUtils.getLogger("genNightTask", true);

	@Value("${SHIFT_NIGHT}")
	private String shift;

	@Override
	public void geneTask() {
		/**
		 * 1、查询班次，获取开始时间和截止时间 2、查询所有周期性计划 3、查询岗位 4、查询值班人员 5、生成任务
		 */

		log.info("----------------生成周期性夜班任务job开始执行------------------");
		log.info("----------------查询班次------------------");

		List<TbTask> taskList = new ArrayList<>();

		// 1、查询班次
		Map<String, Object> shiftMap = this.dayTimeTaskMapper.selectShift(shift);
		String startTime = Objects.toString(shiftMap.get("startTime"), "");
		String endTime = Objects.toString(shiftMap.get("endTime"), "");

		if ("".equals(startTime) || "".equals(endTime)) {
			log.info("------------------班次设置有误：开始时间或结束时间为空---------");
			return;
		}
		
		// 2、查询周期性计划
		// 2.1查询计划——组装参数
		Calendar calendarTask = Calendar.getInstance();

		// 月的表示0-11标识1月到12月
		int monthNo = 1 + calendarTask.get(Calendar.MONTH);

		// 天的表示1-31 标识1号到31号
		int day = calendarTask.get(Calendar.DAY_OF_MONTH);

		// 周的表示： 1到7 表示周日到周六
		int week = calendarTask.get(Calendar.DAY_OF_WEEK);
		
		/**
		 * 加一天后的周、日、月
		 * 注意：
		 * 后面生成任务的时候也会用到该日历实例
		 */
		calendarTask.add(Calendar.DAY_OF_MONTH, 1);
		int monthNoAdd = 1 + calendarTask.get(Calendar.MONTH);
		int dayAdd = calendarTask.get(Calendar.DAY_OF_MONTH);
		int weekAdd = calendarTask.get(Calendar.DAY_OF_WEEK);

		log.info("-----------------组装查询周期性计划的参数-------------------");

		Map<String, Object> planMapParam = new HashMap<>();
		planMapParam.put("startTime", startTime);
		planMapParam.put("endTime", endTime);
		planMapParam.put("week", week);
		planMapParam.put("day", day);
		planMapParam.put("monthNo", monthNo);
		planMapParam.put("weekAdd", weekAdd);
		planMapParam.put("dayAdd", dayAdd);
		planMapParam.put("monthNoAdd", monthNoAdd);

		// 2.2 查询周期性计划————查询计划
		log.info("----------------查询周期性计划的参数为：" + planMapParam +"----------------");

		List<Map<String, Object>> planList = this.nightTaskJobMapper.selectNightPlan(planMapParam);

		log.info("------------------周期性计划的查询结果：" + planList +"----------------");

		// 校验List集合
		if (CollectionUtils.isEmpty(planList)) {
			log.info("------------------周期性计划的查询结果为空----------------");
			return;
		}

		// 遍历计划
		for (Map<String, Object> map : planList) {

			String planId = Objects.toString(map.get("planId"), "");
			String areaCode = Objects.toString(map.get("areaCode"), "");
			String standardCode = Objects.toString(map.get("standardCode"), "");
			String planStartTime = Objects.toString(map.get("startTime"), "");
			String planEndTime = Objects.toString(map.get("endTime"), "");
			String planTimeType = Objects.toString(map.get("planTimeType"), "");

			// 参数校验
			if (StringUtils.isEmpty(areaCode)) {
				log.info("-----------Id为" + planId + ",的计划的区域编码为空----------------");
				return;
			}

			// 3、查询岗位
			Map<String, Object> positionMap = this.dayTimeTaskMapper.selectPositionInfoByAreaCode(areaCode);
			String positionCode = Objects.toString(positionMap.get("positionCode"), "");
			String positionName = Objects.toString(positionMap.get("positionName"), "");
			
			// 4、查询值班人员
			// 4.1处理日期
			Calendar c = Calendar.getInstance();
			int dayNum = c.get(Calendar.DAY_OF_MONTH);
			String queryDay = "day" + dayNum;
			String curMonthNo = DateUtil.dateToMonth(new Date());

			// 4.2封装查询条件
			Map<String, Object> param = new HashMap<>();
			param.put("shift", shift);
			param.put("month", curMonthNo);
			param.put("positionCode", positionCode);
			param.put(queryDay, queryDay);

			log.info("------------查询当班人员的参数为：" + param +"----------------");

			// 4.3查询值班人员————查询人员
			List<Map<String, Object>> employeeList = this.dayTimeTaskMapper.selectWorkingEmployee(param);

			log.info("------------查到的当班人员为：" + employeeList);

			if (CollectionUtils.isEmpty(employeeList)) {
				log.info("----------------未查询到当班人员----------------");
				return;
			}
			
			// 5.1 处理人员数据
			List<String> employeeCodeList = new ArrayList<>();
			List<String> employeeNameList = new ArrayList<>();
			List<String> employeeTelList = new ArrayList<>();

			for (Map<String, Object> m : employeeList) {
				employeeCodeList.add(Objects.toString(m.get("employeeCode")));
				employeeNameList.add(Objects.toString(m.get("employeeName")));
				employeeTelList.add(Objects.toString(m.get("employeeTel")));
			}

			String employeeCode = String.join(",", employeeCodeList);
			String employeeName = String.join(",", employeeNameList);
			String employeeTel = String.join(",", employeeTelList);

			// 5.2查询标准详细信息
			Map<String, Object> standardMap = this.dayTimeTaskMapper.selectStandardInfo(standardCode);
			if (MapUtils.isEmpty(standardMap)) {
				log.info("--------------------根据标准编号未查询到标准----------------");
				return;
			}

			String uploadPicFlag = Objects.toString(standardMap.get("uploadPicFlag"));
			String checkFlag = Objects.toString(standardMap.get("checkFlag"));
			String standardName = Objects.toString(standardMap.get("standardName"));
			
			String areaName=this.dayTimeTaskMapper.selectAreaName(areaCode);
			
			log.info("------------------------处理计划日期---------------");
			
			/**
			 * 处理计划日期
			 * 夜班：startTime：20:00:00  endTime：08:00:00
			 * 计划时间小于 早上8点，则生成任务时，时间需往后推迟一天（同计划查询的理念）
			 */
			Date now = new Date();
			String nowStr = DateUtil.dateToString(now);
			Date planStartDate = DateUtil.stringToDateTime(nowStr + " " + planStartTime);
			Date planEndDate = DateUtil.stringToDateTime(nowStr + " " + planEndTime);
			
			//5.3 处理计划时间
			//对于日期需要进行处理的计划时间，重新进行赋值
			if(endTime.compareTo(planStartTime)> 0) {
				String curDateAddStr = DateUtil.dateToString(calendarTask.getTime());
				//计划时间重新赋值
				planStartDate = DateUtil.stringToDateTime(curDateAddStr + " " + planStartTime);
				planEndDate = DateUtil.stringToDateTime(curDateAddStr + " " + planEndTime);
				
			}
		
			log.info("------------------------开始生成任务---------------");
			
			// 5.4 生成任务
			TbTask task = new TbTask();
			task.setTaskType(CommonConstant.TASK_TYPE_REGULAR);
			task.setPositionCode(positionCode);
			task.setPositionName(positionName);
			task.setAreaCode(areaCode);
			task.setAreaName(areaName);
			task.setShift(shift);
			// task.setSpec(spec);
			task.setEmployeeCode(employeeCode);
			task.setEmployeeName(employeeName);
			task.setEmployeeTel(employeeTel);
			// task.setCompleteTime(completeTime);
			task.setStartTime(planStartDate);
			task.setEndTime(planEndDate);
			task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
			task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
			// task.setTaskScore(taskScore);
			// task.setTaskAdvice(taskAdvice);
			task.setCreateDate(now);
			task.setCreateEmp("SYS-NIGHT定时任务生成");
			task.setVersion(0);

			task.setPlanId(planId);
			task.setStandardCode(standardCode);
			task.setStandardName(standardName);
			task.setCheckFlag(checkFlag);
			task.setUploadPicFlag(uploadPicFlag);
			//设置lineNo
			task.setLineNo(DateUtil.dateTimeToStringForLineNo(new Date()));
			
			//5.5设置提醒时间
			//如果是专项任务，则设置提醒时间
			//计划时间类型为周
			if(CommonConstant.PLAN_TIME_TYPE_WEEK.equals(planTimeType)) {
				log.info("---------------------------对专项任务设置提醒时间----------------");
				//重新设置任务类型
				task.setTaskType(CommonConstant.TASK_TYPE_SPECIAL);
				//查询推延天数
				String delayDaysStr=this.dayTimeTaskMapper.selectDelayDays(planTimeType);
				int delayDays=Integer.valueOf(delayDaysStr);
				
				Calendar cal=Calendar.getInstance();
				cal.setTime(planStartDate);
				cal.add(Calendar.DAY_OF_WEEK, delayDays);
				Date taskRemindTime = cal.getTime();
				task.setTaskRemindTime(taskRemindTime);
				log.info("------------------------专项任务提醒时间设置完毕----------------");
			}
			
			//计划时间类型为月
			if(CommonConstant.PLAN_TIME_TYPE_MONTH.equals(planTimeType)) {
				log.info("---------------------------对专项任务设置提醒时间----------------");
				//重新设置任务类型
				task.setTaskType(CommonConstant.TASK_TYPE_SPECIAL);
				//查询推延天数
				String delayDaysStr=this.dayTimeTaskMapper.selectDelayDays(planTimeType);
				int delayDays=Integer.valueOf(delayDaysStr);
				
				Calendar cal=Calendar.getInstance();
				cal.setTime(planStartDate);
				cal.add(Calendar.DAY_OF_MONTH, delayDays);
				Date taskRemindTime = cal.getTime();
				task.setTaskRemindTime(taskRemindTime);
				log.info("------------------------专项任务提醒时间设置完毕----------------");
			}
			
			//计划时间类型为年
			if(CommonConstant.PLAN_TIME_TYPE_MONTH.equals(planTimeType)) {
				log.info("---------------------------对专项任务设置提醒时间----------------");
				//重新设置任务类型
				task.setTaskType(CommonConstant.TASK_TYPE_SPECIAL);
				//查询推延天数
				String delayDaysStr=this.dayTimeTaskMapper.selectDelayDays(planTimeType);
				int delayDays=Integer.valueOf(delayDaysStr);
				
				Calendar cal=Calendar.getInstance();
				cal.setTime(planStartDate);
				cal.add(Calendar.DAY_OF_YEAR, delayDays);
				Date taskRemindTime = cal.getTime();
				task.setTaskRemindTime(taskRemindTime);
				log.info("------------------------专项任务提醒时间设置完毕----------------");
			}
			
			log.info("---------------------------当前计划已生成任务----------------");
			taskList.add(task);

		}
		
		//任务全部生成后，新增到数据库
		this.dayTimeTaskMapper.insertTaskBatch(taskList);
		log.info("---------------------------job执行完毕----------------");
	}

}
