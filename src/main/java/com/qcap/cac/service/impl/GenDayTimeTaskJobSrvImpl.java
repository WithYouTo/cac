package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.GenDayTimeTaskJobMapper;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("genDayTimeTaskJobSrvImpl")
@Transactional
public class GenDayTimeTaskJobSrvImpl implements com.qcap.cac.service.GenTaskJobSrv {

	@Resource
	private GenDayTimeTaskJobMapper genDayTimeTaskMapper;

	private Logger log = AppUtils.getLogger("genDayTimeTask", true);

//	@Value("${SHIFT_DAYTIME}")
	private String shift = "DAYTIME";

	@Override
	public void geneTask() {
		/**
		 * 1、查询班次，获取开始时间和截止时间 2、查询所有周期性计划 3、查询岗位 4、查询值班人员 5、生成任务
		 */

		log.info("----------------生成周期性白班任务job开始执行------------------");
		log.info("----------------查询班次------------------");

		List<TbTask> taskList = new ArrayList<>();
		Date jobRunDate = new Date();

		// 1、查询班次
		Map<String, Object> shiftMap = this.genDayTimeTaskMapper.selectShift(shift);
		String startTime = ToolUtil.toStr(shiftMap.get("startTime"));
		String endTime = ToolUtil.toStr(shiftMap.get("endTime"));

		if ("".equals(startTime) || "".equals(endTime)) {
			log.info("------------------班次设置有误：开始时间或结束时间为空---------");
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"班次设置有误：开始时间或结束时间为空");
		}

		// 2、查询周期性计划
		// 2.1查询计划——组装参数
		Calendar calendar = Calendar.getInstance();

		// 月的表示0-11标识1月到12月
		int month = 1 + calendar.get(Calendar.MONTH);

		// 天的表示1-31 标识1号到31号
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// 周的表示： 1到7 表示周日到周六
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		log.info("-----------------组装查询周期性计划的参数-------------------");

		Map<String, Object> planMapParam = new HashMap<>();
		planMapParam.put("startTime", startTime);
		planMapParam.put("endTime", endTime);
		planMapParam.put("week", week);
		planMapParam.put("day", day);
		planMapParam.put("month", month);
		planMapParam.put("curDate", new Date());

		// 2.2 查询周期性计划————查询计划curDate
		log.info("----------------查询周期性计划的参数为：" + planMapParam +"----------------");

		List<Map<String, Object>> planList = this.genDayTimeTaskMapper.selectPlan(planMapParam);

		log.info("------------------周期性计划的查询结果：" + planList +"----------------");

		// 校验List集合
		if (CollectionUtils.isEmpty(planList)) {
			log.info("------------------周期性计划的查询结果为空----------------");
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"周期性计划的查询结果为空");
		}

		List<String> unGeneratePlanList = new ArrayList<>();
		List<String> planIdList = new ArrayList<>();
		// 遍历计划
		for (Map<String, Object> map : planList) {

			String planId = ToolUtil.toStr(map.get("planId"));
			String areaCode = ToolUtil.toStr(map.get("areaCode"));
			String standardCode = ToolUtil.toStr(map.get("standardCode"));
			String planStartTime = ToolUtil.toStr(map.get("startTime"));
			String planEndTime = ToolUtil.toStr(map.get("endTime"));
			String planTimeType = ToolUtil.toStr(map.get("planTimeType"));
			String uploadPicFlag = ToolUtil.toStr(map.get("uploadPicFlag"));
			String checkFlag = ToolUtil.toStr(map.get("checkFlag"));

			String startScanFlag = ToolUtil.toStr(map.get("startScanFlag"));
			String endScanFlag = ToolUtil.toStr(map.get("endScanFlag"));

			// 参数校验
			if (StringUtils.isEmpty(areaCode)) {
				log.info("-----------Id为" + planId + ",的计划的区域编码为空----------------");
				unGeneratePlanList.add("Id为" + planId + "，开始时间为："+planStartTime+"结束时间为："+planEndTime+"的计划的区域编码为空");
				continue;
			}

			// 3、查询岗位
            List<Map<String, Object>> positionList = this.genDayTimeTaskMapper.selectPositionInfoByAreaCode(areaCode,shift);
            if(CollectionUtils.isEmpty(positionList)){
                unGeneratePlanList.add("Id为" + planId + "，区域为："+areaCode+"的计划未查询到岗位");
                continue;
            }

            if(positionList.size() >1 ){
                throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"根据区域编码查询到"+positionList.size()+"个岗位");
            }

            String positionCode = ToolUtil.toStr(positionList.get(0).get("positionCode"));
            String positionName = ToolUtil.toStr(positionList.get(0).get("positionName"));

			// 处理日期
			Date curDate = calendar.getTime();
			int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
			String queryDay = "day" + dayNum;
			String monthNo = DateUtil.dateToMonth(curDate);

			// 封装查询条件
			Map<String, Object> param = new HashMap<>();
			param.put("shift", shift);
			param.put("month", monthNo);
			param.put("positionCode", positionCode);
			param.put(queryDay, queryDay);

			log.info("------------查询当班人员的参数为：" + param +"----------------");

			// 4、查询值班人员
			List<Map<String, Object>> employeeList = this.genDayTimeTaskMapper.selectWorkingEmployee(param);

			log.info("------------查到的当班人员为：" + employeeList +"------------------");

			if (CollectionUtils.isEmpty(employeeList)) {
				log.info("-----------------未查询到当班人员----------------");
				unGeneratePlanList.add("Id为" + planId + "，开始时间为："+planStartTime+"结束时间为："+planEndTime+"的计划未查询到当班人员");
				continue;
			}

			List<String> employeeCodeList = new ArrayList<>();
			List<String> employeeNameList = new ArrayList<>();
			List<String> employeeTelList = new ArrayList<>();

			for (Map<String, Object> m : employeeList) {
				employeeCodeList.add(ToolUtil.toStr(m.get("employeeCode")));
				employeeNameList.add(ToolUtil.toStr(m.get("employeeName")));
				employeeTelList.add(ToolUtil.toStr(m.get("employeeTel")));
			}

			String employeeCode = String.join(",", employeeCodeList);
			String employeeName = String.join(",", employeeNameList);
			String employeeTel = String.join(",", employeeTelList);

			// 查询标准详细信息
			Map<String, Object> standardMap = this.genDayTimeTaskMapper.selectStandardInfo(standardCode);
			if (MapUtils.isEmpty(standardMap)) {
				log.info("--------------------根据标准编号未查询到标准----------------");
				unGeneratePlanList.add("Id为" + planId + "，开始时间为："+planStartTime+"结束时间为："+planEndTime+"的计划未查询到标准");
				continue;
			}

			String standardName = ToolUtil.toStr(standardMap.get("standardName"));
			
			String areaName=this.genDayTimeTaskMapper.selectAreaName(areaCode);
			
			log.info("------------------------处理计划日期---------------");
			//处理计划日期
			Date now = new Date();
			String curDateStr = DateUtil.dateToString(now);
			Date planStartDate = DateUtil.stringToDateTime(curDateStr + " " + planStartTime);
			Date planEndDate = DateUtil.stringToDateTime(curDateStr + " " + planEndTime);
			
			log.info("------------------------开始生成任务---------------");

			// 5、生成任务
			TbTask task = new TbTask();
			task.setTaskId(UUIDUtils.getUUID());
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
			task.setCreateDate(jobRunDate);
			task.setCreateEmp("SYS-DAYTIME定时任务生成");
			task.setVersion(0);

			task.setPlanId(planId);
			task.setStandardCode(standardCode);
			task.setStandardName(standardName);
			task.setCheckFlag(checkFlag);
			task.setUploadPicFlag(uploadPicFlag);
			task.setEndScanFlag(endScanFlag);
			task.setStartScanFlag(startScanFlag);
			//设置任务编码
			String taskCodePrefix = "";
			if(CommonConstant.PLAN_TIME_TYPE_DAY.equals(planTimeType)){
				taskCodePrefix = CommonConstant.TASK_PREFIX_R;
			}else {
				taskCodePrefix = CommonConstant.TASK_PREFIX_S;
			}
			String taskCode = taskCodePrefix + DateUtil.dateTimeToStringForLineNo(new Date());
			task.setTaskCode(taskCode);
			
			//设置专项任务的计划时间，以便app端推迟专项任务
			if(!CommonConstant.PLAN_TIME_TYPE_DAY.equals(planTimeType)) {
				task.setTaskPlanTime(planStartDate);
			}
			
			//如果是专项任务，则设置提醒时间
			//计划时间类型为周
			if(CommonConstant.PLAN_TIME_TYPE_WEEK.equals(planTimeType)) {
				log.info("---------------------------对专项任务设置提醒时间----------------");
				//重新设置任务类型
				task.setTaskType(CommonConstant.TASK_TYPE_SPECIAL);
				//查询推延天数
				String delayDaysStr=this.genDayTimeTaskMapper.selectDelayDays(planTimeType);
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
				String delayDaysStr=this.genDayTimeTaskMapper.selectDelayDays(planTimeType);
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
				String delayDaysStr=this.genDayTimeTaskMapper.selectDelayDays(planTimeType);
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
			planIdList.add(planId);
		}

		//跟新计划时间
		Map<String,Object> param = new HashMap<>();
		param.put("curDate",jobRunDate);
		param.put("planId",String.join(",",planIdList));
		if(CollectionUtils.isNotEmpty(planIdList)){
			this.genDayTimeTaskMapper.updateJobRunTime(param);
		}

		//任务全部生成后，新增到数据库
		if(CollectionUtils.isNotEmpty(taskList)){
			this.genDayTimeTaskMapper.insertTaskBatch(taskList);
		}
		log.info("---------------------------job执行完毕----------------");
		System.out.println(unGeneratePlanList.toString());
	}

}
