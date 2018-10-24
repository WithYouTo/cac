package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.AppTaskRestMapper;
import com.qcap.cac.dto.AppTaskCheckRestReq;
import com.qcap.cac.dto.AppTaskFinishReq;
import com.qcap.cac.dto.AppTaskRestReq;
import com.qcap.cac.dto.AppTaskUpdateReq;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.AppTaskRestSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
@Transactional
public class AppTaskRestSrvImpl implements AppTaskRestSrv {
	
	private static final String QUERY_TASK_TIME_ITEM = "ITEM";
	
	private static final String QUERY_TASK_TIME_HISTORY = "HISTORY";
	
	private static final String QUERY_TASK_TIME_FINISH = "FINISH";

	@Resource
	private AppTaskRestMapper appTaskRestMapper;

	@Override
	public Map<String, Object> queryTaskItem(String employeeCode) {
		
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_ITEM);
		List<Map<String, String>> list =this.appTaskRestMapper.queryTaskItem(param);
		
		Map<String, Object> result = new HashMap<>();
		for(Map<String, String> map:list) {
			result.put(map.get("taskItem"), map.get("taskNum"));
		}
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> queryHistoryTask (AppTaskRestReq appTaskRestDto){
		
		String employeeCode = Objects.toString(appTaskRestDto.getEmployeeCode());
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_HISTORY);
		param.put("taskSatus", appTaskRestDto.getTaskStatus());
		return this.appTaskRestMapper.selectTaskIntro(param);
		
	}
	
	@Override
	public List<Map<String, Object>> queryUnfinishTask(AppTaskRestReq appTaskRestDto){
		Map<String, Object> param = new HashMap<>();
		param.put("employeeCode", appTaskRestDto.getEmployeeCode());
		param.put("taskSatus", appTaskRestDto.getTaskStatus());
		return this.appTaskRestMapper.selectTaskIntro(param);
	}

    @Override
	public Map<String, Object> queryTaskDetail (String taskCode){
		
		Map<String, Object> map = this.appTaskRestMapper.queryTaskDetail(taskCode);
		String standardCode = Objects.toString(map.get("standardCode"));
		
		if(!StringUtils.isEmpty(standardCode)) {
			List<Map<String, Object>> marterialList = this.appTaskRestMapper.selectStandardDetailList(standardCode);
			map.put("marterialList", marterialList);
		}
		
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		//处理完成任务时上传图片的url
		String feedBackImgUrl = Objects.toString(map.get("feedBackImgUrl"));
		if(!StringUtils.isEmpty(feedBackImgUrl)) {
			if(feedBackImgUrl.contains(",")) {
				String [] fileArr = feedBackImgUrl.split(",");
				for(String str: fileArr) {
					str = addressPrefix + str;
				}
				map.put("feedBackFile", String.join(",", fileArr));
			}else {
				String feedBackFile = addressPrefix + feedBackImgUrl;
				map.put("feedBackFile", feedBackFile);
			}
			}
		
		//处理检查文件url
		String checkImgUrl = Objects.toString(map.get("checkImgUrl"));
		if(!StringUtils.isEmpty(checkImgUrl)) {
			if(checkImgUrl.contains(",")) {
				String [] checkFileArr = checkImgUrl.split(",");
				for(String str: checkFileArr) {
					str = addressPrefix + str;
				}
				map.put("checkFile", String.join(",", checkFileArr));
			}else {
				String checkFile = addressPrefix + checkImgUrl;
				map.put("checkFile", String.join(",", checkFile));
			}
		}

		return map;
	}

    @Override
	public List<Map<String, Object>> queryFinishAndCheckTask (AppTaskCheckRestReq appTaskRestCheckReq){
		String employeeCode = Objects.toString(appTaskRestCheckReq.getEmployeeCode());
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_FINISH);
		param.put("taskSatus", appTaskRestCheckReq.getTaskStatus());
		param.put("checkSatus", appTaskRestCheckReq.getCheckStatus());
		return this.appTaskRestMapper.selectTaskIntro(param);
	}

    @Override
    public  Map<String, Object> selectStandardDetailInfo (String standardDetailId){
        return this.appTaskRestMapper.selectStandardDetailInfo(standardDetailId);
    }

    @Override
    public void workingTask(String taskCode) {
        AppTaskUpdateReq appTaskUpdateReq = new AppTaskUpdateReq();
        appTaskUpdateReq.setTaskCode(taskCode);
        appTaskUpdateReq.setTaskStatus(CommonConstant.TASK_STATUS_WORKING);
        this.appTaskRestMapper.updateTask(appTaskUpdateReq);
    }

    @Override
    public void finishTask(AppTaskFinishReq appTaskFinishReq) throws InvocationTargetException, IllegalAccessException {
        AppTaskUpdateReq appTaskUpdateReq = new AppTaskUpdateReq();
        BeanUtils.copyProperties(appTaskUpdateReq , appTaskFinishReq);
		appTaskUpdateReq.setTaskStatus(CommonConstant.TASK_STATUS_FINISH);
        this.appTaskRestMapper.updateTask(appTaskUpdateReq);
    }

    /**
	 * 查询任务、或统计任务时，
	 * 对于白班和夜班的任务时间进行处理
	 * @Title: getTaskQueryTime 
	 * @Description: TODO
	 * @param employeeCode
	 * @throws BaseException
	 * @return: Map<String, Object>
	 */
	protected Map<String, Object> getTaskQueryTime(String employeeCode,String queryType) {
		
		Date now = new Date();
		String monthNo = DateUtil.dateToMonth(now);
		if (StringUtils.isEmpty(employeeCode)) {
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "工号为空");
		}

		// 查询班次类型
		Map<String, Object> param = new HashMap<>();
		param.put("employeeCode", employeeCode);
		param.put("monthNo", monthNo);
		String shift = this.appTaskRestMapper.selectshiftType(param);

		if (StringUtils.isEmpty(shift)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "未查询到该员工的班次");
		}

		// 查询班次设定的工作时间
		Map<String, Object> shiftMap = this.appTaskRestMapper.selectShiftTime(shift);
		if (MapUtils.isEmpty(shiftMap)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40401, "未查询到班次设置的起止时间");
		}
		String startTime = Objects.toString(shiftMap.get("startTime"));
		String endTime = Objects.toString(shiftMap.get("endTime"));
		
		/**当天日期**/
		String curDateTime = DateUtil.dateTimeToString(now);
		// yyyy-MM-dd
		String curDate = curDateTime.substring(0, 10);
		// HH:mm:ss
		String curTime = curDateTime.substring(11);
		
		/**后一天日期**/
		Calendar calendarNext = Calendar.getInstance();
		calendarNext.setTime(now);
		calendarNext.add(Calendar.DAY_OF_MONTH, 1);
		// yyyy-MM-dd
		String nextDate = DateUtil.dateToString(calendarNext.getTime());
		
		/**前一天日期**/
		Calendar calendarLast = Calendar.getInstance();
		calendarLast.setTime(now);
		calendarLast.add(Calendar.DAY_OF_MONTH, -1);
		// yyyy-MM-dd
		String lastDate = DateUtil.dateToString(calendarLast.getTime());
		
		/**获取时间**/
		Map<String, Object> map = null;
		switch(queryType) {
		case QUERY_TASK_TIME_ITEM:
			map = queryTaskItemTime(shift, startTime, endTime, curDate, curTime, nextDate, lastDate);
			break;
		case QUERY_TASK_TIME_HISTORY:
			map = queryTaskHistoryTime(endTime, curDate, curTime, lastDate);
			break;
		case QUERY_TASK_TIME_FINISH:
			map = queryFinishTaskTime(shift, startTime, endTime, curDate, curTime, nextDate);
			break;
		}
		
		//返回参数
		map.put("type", queryType);
		map.put("employeeCode", employeeCode);
		return map;
	}

	/** 
	 * 查询任务数量：
	 * @Title: queryTaskItemTime 
	 * @Description: TODO
	 * @param shift
	 * @param startTime
	 * @param endTime
	 * @param curDate
	 * @param curTime
	 * @param nextDate
	 * @param lastDate
	 * @return
	 * @return: Map<String,Object>
	 */
	protected Map<String, Object> queryTaskItemTime(String shift, String startTime, String endTime, String curDate,
			String curTime, String nextDate, String lastDate) {
		// 组装查询条件
		String newStartTime = "";
		String newEndTime = "";
		Map<String, Object> map = new HashMap<>();
		if (CommonConstant.SHIFT_DAYTIME.equals(shift)) {
			/**
			 * 白班：
			 * 如果当前时间（时分秒）小于startTime(08:00:00)
			 * 则查询上一个班的任务，反之则查询当班任务
			 */
			if(curTime.compareTo(startTime) < 0) {
				newStartTime = lastDate +" " + startTime;
				newEndTime = lastDate +" " + endTime;
			}else {
				newStartTime = curDate +" " + startTime;
				newEndTime = curDate +" " + endTime;
			}
		}
		
		if(CommonConstant.SHIFT_NIGHT.equals(shift)) {
			/**
			 * 夜班：
			 * 如果当前时间（时分秒）小于startTime(20:00:00)
			 * 则查询上一个班的任务，反之则查询当班任务
			 */
			if(curTime.compareTo(startTime) < 0) {
				newStartTime = lastDate +" " + startTime;
				//往后推一天
				newEndTime = curDate +" " + endTime;
			}else {
				newStartTime = curDate +" " + startTime;
				//往后推一天
				newEndTime = nextDate +" " + endTime;
			}
		}
		
		map.put("startTime", newStartTime);
		map.put("endTime", newEndTime);
		return map;
	}
	
	protected Map<String, Object> queryTaskHistoryTime(String endTime, String curDate,String curTime, String lastDate) {
		// 组装查询条件
		String newEndTime = "";
		Map<String, Object> map = new HashMap<>();
		/**
		 * 白班：curTime <  endTime(20:00:00)，查询上一班的任务，否则查询当天及以前的任务
		 * 夜班：curTime <  endTime(08:00:00)，查询上一班的任务，否则查询当天及以前的任务
		 */
		if(curTime.compareTo(endTime) < 0) {
			newEndTime = lastDate +" " + endTime;
		}else {
			newEndTime = curDate +" " + endTime;
		}
		
		map.put("endTime", newEndTime);
		return map;
		
	}
	
	protected Map<String, Object> queryFinishTaskTime(String shift, String startTime, String endTime, String curDate,
			String curTime, String nextDate) {
		// 组装查询条件
		String newStartTime = "";
		String newEndTime = "";
		Map<String, Object> map = new HashMap<>();
		if (CommonConstant.SHIFT_DAYTIME.equals(shift)) {
			/**
			 * 白班：
			 * 如果当前时间（时分秒）在当班时间内[startTime(08:00:00),endTime("20:00:00"))内，
			 * 则查询当班已完成的任务，反之则不查询任务
			 */
			if(curTime.compareTo(startTime) >= 0 && curTime.compareTo(endTime) < 0 ) {
				newStartTime = curDate +" " + startTime;
				newEndTime = curDate +" " + endTime;
			}
		}
		
		if(CommonConstant.SHIFT_NIGHT.equals(shift)) {
			/**
			 * 夜班：
			 * 如果当前时间（时分秒）在当班时间内[startTime(20:00:00),endTime("08:00:00"))内，
			 * 则查询当班已完成的任务，反之则不查询任务
			 */
			if(curTime.compareTo(startTime) >= 0 || curTime.compareTo(endTime) < 0 ) {
				newStartTime = curDate +" " + startTime;
				//往后推一天
				newEndTime = nextDate +" " + endTime;
			}
		}
		
		map.put("startTime", newStartTime);
		map.put("endTime", newEndTime);
		
		return map;
	}

}
