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
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
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

	private static final String QUERY_TASK_TIME_CHECK = "CHECK";

	@Resource
	private AppTaskRestMapper appTaskRestMapper;

	@Resource
	private TempTaskSrv tempTaskSrvImpl;

	@Override
	public Map<String, Object> queryTaskItem(String employeeCode) {
		
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_ITEM);
		List<Map<String, String>> list =this.appTaskRestMapper.queryTaskItem(param);
		
		Map<String, Object> result = new HashMap<>();
		for(Map<String, String> map:list) {
			result.put(map.get("taskItem"), map.get("taskNum"));
		}
		
		//处理未查询到任务时的数据
		if(!result.containsKey("waitingItem")) {
			result.put("waitingItem", 0);
		}
		
		if(!result.containsKey("workingItem")) {
			result.put("workingItem", 0);
		}
		
		if(!result.containsKey("finishItem")) {
			result.put("finishItem", 0);
		}
		
		if(!result.containsKey("tempItem")) {
			result.put("tempItem", 0);
		}
		
		if(!result.containsKey("disqualifiedItem")) {
			result.put("disqualifiedItem", 0);
		}
		
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> queryHistoryTask (AppTaskRestReq appTaskRestDto){
		
		String employeeCode = ToolUtil.toStr(appTaskRestDto.getEmployeeCode());
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
		String standardCode = ToolUtil.toStr(map.get("standardCode"));
		
		if(!StringUtils.isEmpty(standardCode)) {
			List<Map<String, Object>> marterialList = this.appTaskRestMapper.selectStandardDetailList(standardCode);
			map.put("marterialList", marterialList);
		}
		
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		
		//处理完成任务时上传图片的url
		String feedBackImgUrl = ToolUtil.toStr(map.get("feedBackImgUrl"));
		dealWithPicUrl(map,"feedBackFile", addressPrefix, feedBackImgUrl);
		
		//处理检查文件url
		String checkImgUrl = ToolUtil.toStr(map.get("checkImgUrl"));
		dealWithPicUrl(map,"checkFile", addressPrefix, checkImgUrl);
		
		//处理检查文件url
		String taskImgUrl = ToolUtil.toStr(map.get("taskImgUrl"));
		dealWithPicUrl(map,"taskImgFile", addressPrefix, taskImgUrl);

		return map;
	}

	/** 
	 * @Title: dealWithPicUrl 
	 * @Description: TODO
	 * @param map
	 * @param addressPrefix
	 * @param checkImgUrl
	 * @return: void
	 */
	private void dealWithPicUrl(Map<String, Object> map,String fileUrlName, String addressPrefix, String checkImgUrl) {
		if(!StringUtils.isEmpty(checkImgUrl)) {
			if(checkImgUrl.contains(",")) {
				String [] checkFileArr = checkImgUrl.split(",");
				for(String str: checkFileArr) {
					str = addressPrefix + str;
				}
				map.put(fileUrlName, checkFileArr);
			}else {
				String checkFile = addressPrefix + checkImgUrl;
				String [] fileArray = new String[1];
				fileArray[0]=checkFile;
				map.put(fileUrlName, fileArray);
			}
		}
	}

    @Override
	public List<Map<String, Object>> queryFinishAndCheckTask (AppTaskCheckRestReq appTaskRestCheckReq){
		String employeeCode = ToolUtil.toStr(appTaskRestCheckReq.getEmployeeCode());
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_FINISH);
		param.put("taskSatus", appTaskRestCheckReq.getTaskStatus());
		String startTime = Objects.toString(param.get("startTime"),"");
		if(!StringUtils.isEmpty(startTime)){
			//在当班时间内，则查询已完成任务
			return this.appTaskRestMapper.selectTaskIntro(param);
		}else{
			//不在当班时间内，则不进行查询
			return null;
		}
	}

    @Override
    public  Map<String, Object> selectStandardDetailInfo (String standardDetailId){
    	Map<String, Object> map = this.appTaskRestMapper.selectStandardDetailInfo(standardDetailId);
    	String imgUrl = ToolUtil.toStr(map.get("imgUrl"));
    	
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		dealWithPicUrl(map, "imgUrl", addressPrefix, imgUrl);
    	
    	String standardStep = ToolUtil.toStr(map.get("standardStep"));
    	
    	if(!StringUtils.isEmpty(standardStep)) {
    		 String WINDOWS_REGEX = "\n";
        	 String LINUX_REGEX = "\r\n";
        	 String [] standardStepArr = null;
        	if(standardStep.indexOf(WINDOWS_REGEX) != -1) {
        		standardStepArr = standardStep.split(WINDOWS_REGEX);
        	}
        	
        	if(standardStep.indexOf(LINUX_REGEX)  != -1) {
        		standardStepArr = standardStep.split(LINUX_REGEX);
        	}
        	
        	map.put("standardStep", standardStepArr);
    	}
    	
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
	 * 从此处开始是检查人员的接口
	 */
	@Override
	public Map<String, Object> queryCheckTaskItem (String employeeCode){
		Map<String, Object> map = new HashMap<>();
		//查询管理人员是否当班
		List<Map<String,Object>> list = this.tempTaskSrvImpl.selectCurrountWorkingEmployee(employeeCode);
		//当班
		if(CollectionUtils.isNotEmpty(list)){
			//查询管理人员的管理区域
			String positionCode = Objects.toString(list.get(0).get("positionCode"),"");
			String areaCode = this.appTaskRestMapper.selectAreaByPositionCode(positionCode);
			if(StringUtils.isEmpty(areaCode)){
				throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"根据岗位编号未查询所管辖区域编号");
			}
			//查询管理人员上班时间
			Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_CHECK);
			String startTime =Objects.toString(param.get("startTime"),"");
			if(!StringUtils.isEmpty(startTime)){
				param.put("areaCode",areaCode);
				List<Map<String,String>> taskItemList = this.appTaskRestMapper.queryCheckTaskItem(param);
				if(CollectionUtils.isNotEmpty(taskItemList)){
					for(Map<String,String> m:taskItemList){
						map.put(m.get("taskItem"),m.get("taskNum"));
					}
				}
			}
		}

		if(!map.containsKey("toCheckItem")){
			map.put("toCheckItem",0);
		}

		if(!map.containsKey("checkedItem")){
			map.put("checkedItem",0);
		}

		return map;
	};

	@Override
	public List<Map<String, Object>> queryCheckTask(AppTaskCheckRestReq appTaskCheckRestReq){
		List<Map<String,Object>> taskItemList =null;
		//查询管理人员是否当班
		List<Map<String,Object>> list = this.tempTaskSrvImpl.selectCurrountWorkingEmployee(appTaskCheckRestReq.getEmployeeCode());
		//当班
		if(CollectionUtils.isNotEmpty(list)){
			//查询管理人员的管理区域
			String positionCode = Objects.toString(list.get(0).get("positionCode"),"");
			String areaCode = this.appTaskRestMapper.selectAreaByPositionCode(positionCode);
			if(StringUtils.isEmpty(areaCode)){
				throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"根据岗位编号未查询所管辖区域编号");
			}

			//查询管理人员上班时间
			Map<String, Object> param = getTaskQueryTime(appTaskCheckRestReq.getEmployeeCode(),QUERY_TASK_TIME_CHECK);
			String startTime =Objects.toString(param.get("startTime"),"");
			if(!StringUtils.isEmpty(startTime)){
				param.put("areaCode",areaCode);
				/**
				 * 管理人员查询检查任务时，不根据employeeCode查询，而是根据其所管理的岗位对应的区域查询
				 * 去掉查询条件中的employeeCode；
				 */
				param.remove("employeeCode");
				taskItemList = this.appTaskRestMapper.selectTaskIntro(param);
			}
		}
		return taskItemList;
	};

	/**
	 * 查询任务、或统计任务时，
	 * 对于白班和夜班的任务时间进行处理
	 * @Title: getTaskQueryTime 
	 * @Description: TODO
	 * @param employeeCode
	 * @throws BaseException
	 * @return: Map<String, Object>
	 */
	private Map<String, Object> getTaskQueryTime(String employeeCode,String queryType) {
		
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
		String startTime = ToolUtil.toStr(shiftMap.get("startTime"));
		String endTime = ToolUtil.toStr(shiftMap.get("endTime"));
		
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
		case QUERY_TASK_TIME_CHECK:
			//检查任务的开始、结束检查时间与查询完成任务的时间相同
			map =  queryFinishTaskTime(shift, startTime, endTime, curDate, curTime, nextDate);
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
	private Map<String, Object> queryTaskItemTime(String shift, String startTime, String endTime, String curDate,
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
	
	private Map<String, Object> queryTaskHistoryTime(String endTime, String curDate,String curTime, String lastDate) {
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
	
	private Map<String, Object> queryFinishTaskTime(String shift, String startTime, String endTime, String curDate,
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
