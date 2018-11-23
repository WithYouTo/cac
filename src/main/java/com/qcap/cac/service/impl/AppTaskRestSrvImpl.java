package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.AppTaskRestMapper;
import com.qcap.cac.dao.TempTaskMapper;
import com.qcap.cac.dto.*;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.entity.TbTaskArrangeShift;
import com.qcap.cac.entity.TbTaskDelay;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.AppTaskRestSrv;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.*;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.DateUtil;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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

	@Resource
	private TempTaskMapper tempTaskMapper;

	//获取FASTDFS客户端bean
    @Resource
	FastDFSClientWrapper fastDFSClientWrapper ;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Resource
    private MessageRestSrv messageRestSrvImpl;

//    @Resource
//    private TaskArrangeSrv taskArrangeSrvImpl;

	@Override
	public Map<String, Object> queryTaskItem(String employeeCode) {
		
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_ITEM);
		param.put("curTime", new Date());
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
		param.put("taskStatus", appTaskRestDto.getTaskStatus());
		param.put("startTimePage", appTaskRestDto.getStartTime());
		param.put("lineNo", appTaskRestDto.getLineNo());
		//历史记录分页标识
		param.put("pageType", "queryHistory");
		return this.appTaskRestMapper.selectTaskIntro(param);
		
	}
	
	@Override
	public List<Map<String, Object>> queryUnfinishTask(AppTaskRestReq appTaskRestDto){
		//根据查询时间分白班和夜班进行统计
		String employeeCode = appTaskRestDto.getEmployeeCode();
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_ITEM);
		param.put("employeeCode", employeeCode);
		param.put("taskStatus", appTaskRestDto.getTaskStatus());
		param.put("startTimePage", appTaskRestDto.getStartTime());
		param.put("lineNo", appTaskRestDto.getLineNo());
		param.put("curTime", new Date());
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
    
   @Override
   public Object  delaySpecialTask(String taskCode) {
	   String taskTimeType = this.appTaskRestMapper.selectSpecialTaskTimeType(taskCode);
	   
	 //更改专项任务时间
	  Map<String, String> map = this.appTaskRestMapper.selectTaskTime(taskCode);
	  String startTime =map.get("startTime");
	  String endTime =map.get("endTime");
	  String taskPlanTime =map.get("taskPlanTime");
	  
	  String newTaskPlanTIme = getNewTaskPlanTime(taskTimeType, taskPlanTime);
	   
	  if(startTime.compareTo(newTaskPlanTIme) < 0) {
		   //新增专项任务延迟记录
		   TbTaskDelay taskDelay = new TbTaskDelay();
		   taskDelay.setTaskDelayId(UUIDUtils.getUUID());
		   taskDelay.setTaskCode(taskCode);
		   taskDelay.setDelayDays(""+CommonConstant.SPECIAL_TASK_DELAY_DAYS_1);
		   taskDelay.setOperationDate(new Date());
		   EntityTools.setCreateEmpAndTime(taskDelay);
		   this.appTaskRestMapper.insertTaskDelay(taskDelay);
		   
		  //处理开始时间
		  String newStartTime =  dealWithSpecialTaskTime(startTime);
		  //处理结束时间
		  String newEndTime =  dealWithSpecialTaskTime(endTime);
		  
		  Map<String, Object> param = new HashMap<>();
		  param.put("startTime", newStartTime);
		  param.put("endTime", newEndTime);
		  param.put("taskCode", taskCode);
		  //推迟任务时间
		  this.appTaskRestMapper.updateSpecialTask(param);
		  return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "将该专项任务进行延迟一天清洁");
	  }else {
		  return ResParams.newInstance(CommonCodeConstant.ERROR_CODE_40402, "该任务已无法延迟，请开始清洁");
		  
	  }
	   
   }

/** 
 * @Title: getNewTaskPlanTime 
 * @Description: TODO
 * @param taskTimeType
 * @param taskPlanTime
 * @return: String
 */
private String getNewTaskPlanTime(String taskTimeType, String taskPlanTime) {
	Calendar calendar = Calendar.getInstance();
	  calendar.setTime(DateUtil.stringToDateTime(taskPlanTime));
	  if(CommonConstant.PLAN_TIME_TYPE_WEEK.equals(taskTimeType)) {
		  calendar.add(Calendar.DAY_OF_MONTH, CommonConstant.SPECIAL_TASK_DELAY_DAYS_7);
	  }
	  
	  if(CommonConstant.PLAN_TIME_TYPE_MONTH.equals(taskTimeType)) {
		  calendar.add(Calendar.MONTH, 1);
	  }
	  
	  if(CommonConstant.PLAN_TIME_TYPE_YEAR.equals(taskTimeType)) {
		  calendar.add(Calendar.YEAR, 1);
	  }
	  
	  return DateUtil.dateTimeToString(calendar.getTime());
}

/** 
 * @Title: dealWithSpecialTaskTime 
 * @Description: TODO
 * @param startTime
 * @return: String
 */
private String dealWithSpecialTaskTime(String startTime) {
	Calendar calendar = Calendar.getInstance();
	  calendar.setTime(DateUtil.stringToDateTime(startTime));
	  calendar.add(Calendar.DAY_OF_MONTH, CommonConstant.SPECIAL_TASK_DELAY_DAYS_1);
	  return DateUtil.dateTimeToString(calendar.getTime());
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
				for(int i = 0; i< checkFileArr.length; i++) {
					checkFileArr[i] = addressPrefix + checkFileArr[i];
				}
				map.put(fileUrlName, checkFileArr);
			}else {
				String checkFile = addressPrefix + checkImgUrl;
				String [] fileArray = new String[1];
				fileArray[0]=checkFile;
				map.put(fileUrlName, fileArray);
			}
		}else {
			map.put(fileUrlName, new ArrayList<>());
		}
	}

    @Override
	public List<Map<String, Object>> queryFinishAndCheckTask (AppTaskRestReq appTaskRestReq){
		String employeeCode = ToolUtil.toStr(appTaskRestReq.getEmployeeCode());
		//根据查询时间分白班和夜班进行统计
		Map<String, Object> param = getTaskQueryTime(employeeCode,QUERY_TASK_TIME_ITEM);
		param.put("taskStatus", appTaskRestReq.getTaskStatus());
		param.put("startTimePage", appTaskRestReq.getStartTime());
		param.put("lineNo", appTaskRestReq.getLineNo());
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
    	
		//处理图片和视频
		if(!StringUtils.isEmpty(imgUrl)) {
			if(imgUrl.contains(",")) {
				String [] checkFileArr = imgUrl.split(",");
				map.put("imgUrl", checkFileArr);
			}else {
				String [] fileArray = new String[1];
				fileArray[0]=imgUrl;
				if(imgUrl.endsWith("mp4") || imgUrl.endsWith("avi")){
					map.put("fileUrl", imgUrl);
					map.remove("imgUrl");
				}else{
					map.put("imgUrl", fileArray);
				}
			}
		}else {
			map.put("imgUrl", new ArrayList<>());
		}
    	
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

        	if(standardStep.indexOf(WINDOWS_REGEX) == -1 && standardStep.indexOf(LINUX_REGEX)  == -1){
				standardStepArr=new String[]{standardStep};
			}

        	map.put("standardStep", standardStepArr);
    	}

        return this.appTaskRestMapper.selectStandardDetailInfo(standardDetailId);
    }

    @Override
    public void workingTask(AppTaskRestWorkingTaskDto appTaskRestWorkingTaskDto) {
        AppTaskUpdateReq appTaskUpdateReq = new AppTaskUpdateReq();
        appTaskUpdateReq.setTaskCode(appTaskRestWorkingTaskDto.getTaskCode());
        appTaskUpdateReq.setTaskStatus(CommonConstant.TASK_STATUS_WORKING);
        String employeeCode = appTaskRestWorkingTaskDto.getEmployeeCode();
    	String employeeName =this.appTaskRestMapper.selectEmployeeName(employeeCode);
    	appTaskUpdateReq.setEmployeeCode(employeeCode);
    	appTaskUpdateReq.setEmployeeName(employeeName);
    	appTaskUpdateReq.setStartWorkTime(DateUtil.dateTimeToString(new Date()));
        this.appTaskRestMapper.updateTask(appTaskUpdateReq);
    }

    @Override
    public void finishTask(List<MultipartFile>list,AppTaskUpdateReq appTaskUpdateReq) throws IOException {
		String imgUrl = uploadPicture(list);
		appTaskUpdateReq.setTaskStatus(CommonConstant.TASK_STATUS_FINISH);
		appTaskUpdateReq.setFeedbackImgUrl(imgUrl);
		appTaskUpdateReq.setCompleteTime(DateUtil.dateTimeToString(new Date()));
        this.appTaskRestMapper.updateTask(appTaskUpdateReq);
    }

	private String uploadPicture(List<MultipartFile> list) throws IOException {
		String imgUrl = "";
		if(CollectionUtils.isNotEmpty(list)){
			for(int i = 0;i<list.size();i++){
				String path = fastDFSClientWrapper.uploadFile(list.get(i));
				if(i != list.size() -1){
					imgUrl += path + ",";
				}else {
					imgUrl += path;
				}
			}
		}
		return imgUrl;
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
			String selectAreaCode = appTaskCheckRestReq.getAreaCode();
			String lineNo = appTaskCheckRestReq.getLineNo();
			if(!StringUtils.isEmpty(startTime)){
				param.put("areaCode",areaCode);
				param.put("checkStatus",appTaskCheckRestReq.getCheckStatus());
				param.put("taskStatus",CommonConstant.TASK_STATUS_FINISH);
				param.put("taskStatus",CommonConstant.TASK_STATUS_FINISH);
				param.put("startTimePage", appTaskCheckRestReq.getStartTime());
				param.put("lineNo", appTaskCheckRestReq.getLineNo());
				/**
				 * 选择区域后，将岗位默认区域覆盖
				 */
				if(!StringUtils.isEmpty(selectAreaCode)) {
					param.put("areaCode",appTaskCheckRestReq.getAreaCode());
				}
				
				if(!StringUtils.isEmpty(lineNo)) {
					param.put("lineNo",appTaskCheckRestReq.getLineNo());
				}
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

	@Override
	public void checkTask(List<MultipartFile>list,AppTaskUpdateReq appTaskUpdateReq) throws IOException{
		String imgUrl = uploadPicture(list);
		
		appTaskUpdateReq.setCheckTime(DateUtil.dateTimeToString(new Date()));
		String checkEmpName =this.appTaskRestMapper.selectEmployeeName(appTaskUpdateReq.getCheckEmpCode());
		appTaskUpdateReq.setCheckEmpName(checkEmpName);
		
		//任务评价
		if(CommonConstant.TASK_CHECK_STATUS_QUALIFIED.equals(appTaskUpdateReq.getCheckStatus())) {
			appTaskUpdateReq.setCheckImgUrl(imgUrl);
			this.appTaskRestMapper.updateTask(appTaskUpdateReq);
		}
		

		//任务不合格
		if(CommonConstant.TASK_CHECK_STATUS_DISQUALIFIED.equals(appTaskUpdateReq.getCheckStatus())){
			appTaskUpdateReq.setFeedbackImgUrl(imgUrl);
			this.appTaskRestMapper.updateTask(appTaskUpdateReq);
			//1、查询任务  2、修改部分信息、重新新增到数据库
			String taskCode = appTaskUpdateReq.getTaskCode();
			if(StringUtils.isEmpty(taskCode)){
				throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "参数为空");
			}

			//重新设置任务开始、结束时间

			Calendar calendar = Calendar.getInstance();
			Date startTime = calendar.getTime();
			calendar.add(Calendar.HOUR_OF_DAY,1);
			Date endTime = calendar.getTime();
			TbTask task = this.appTaskRestMapper.selectTaskByCode(taskCode);
			String newTaskCode = CommonConstant.TASK_PREFIX_DQ + DateUtil.dateTimeToStringForLineNo(new Date());
			task.setTaskId(UUIDUtils.getUUID());
			task.setTaskCode(newTaskCode);
			task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
			task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
			task.setTaskImgUrl(imgUrl);
			task.setStartTime(startTime);
			task.setEndTime(endTime);
			task.setVersion(0);
			task.setCreateDate(new Date());
			task.setCreateEmp("由检查不合格任务生成,检查不合格任务编码："+taskCode);
			this.tempTaskMapper.insertTempTask(task);
			
//			JpushTools.pushSingle(task.getEmployeeCode(), "你有一个任务检查不合格,请在App内查看");
			messageRestSrvImpl.JpushMessage(task.getEmployeeCode(), task.getProgramCode(), "你有一个任务检查不合格,请在App内查看", "检查不合格任务");

		}
	}

	@Override
	public List<Map<String, Object>> listTempTask(AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq) {
		return this.appTaskRestMapper.listTempTask(appTaskShiftHistoryRestReq);
	}

	@Override
	public Map<String,Object> selectDefaultEmployee(String startTime,String areaCode){
		return tempTaskSrvImpl.selectDefaultEmployee(startTime, areaCode);
	};

	@Override
	public void addTempTask (List<MultipartFile>list,AppTaskAddRestReq appTaskAddRestReq) throws IOException, InvocationTargetException, IllegalAccessException {
		String imgUrl = uploadPicture(list);

		// 查询岗位
		String areaCode = appTaskAddRestReq.getAreaCode();
		Map<String, Object> positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(areaCode);
		if (MapUtils.isEmpty(positionMap)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"当前区域未设置岗位");
		}
		String positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
		String positionName = ToolUtil.toStr(positionMap.get("positionName"));
		
		//查询项目编码
		String programCode = this.appTaskRestMapper.selectProgramCodeByEmployeeCode(appTaskAddRestReq.getEmployeeCode());

		// 查询班次
		/**
		 *   时间  ———待确认
		 */
		Calendar calendar = Calendar.getInstance();
		Date now = calendar.getTime();
		calendar.add(Calendar.MINUTE,30);
		Date end = calendar.getTime();
		String queryTime = DateUtil.dateToString(now);
		Map<String, String> shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		if (shiftMap == null || shiftMap.isEmpty()) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"根据计划时间未查询到班次，请先设置班次");
		}
		String shift = shiftMap.get("shift");

		String taskCode = CommonConstant.TASK_PREFIX_T + DateUtil.dateTimeToStringForLineNo(now);

		TbTask task = new TbTask();
		BeanUtils.copyProperties(task,appTaskAddRestReq);
		task.setProgramCode(programCode);
		task.setTaskCode(taskCode);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setShift(shift);
		task.setStartTime(now);
		task.setEndTime(end);
		task.setCreateDate(new Date());
		task.setTaskImgUrl(imgUrl);
		task.setTaskId(UUIDUtils.getUUID());
		task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
		task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
		task.setTaskType(CommonConstant.TASK_TYPE_TEMP);
		task.setVersion(0);
		//是否检查、是否上传图片和扫码设置
		task.setCheckFlag(CommonConstant.TASK_CHECK_FLAG_MUST);
		task.setUploadPicFlag(CommonConstant.UPLOAD_PIC_FLAG_MUST);
		task.setStartScanFlag(CommonConstant.START_SCAN_FLAG_OPTIONAL);
		task.setEndScanFlag(CommonConstant.END_SCAN_FLAG_OPTIONAL);

		this.tempTaskMapper.insertTempTask(task);

		// 根据工号推送任务通知
		String [] employeeCodeArr = appTaskAddRestReq.getEmployeeCode().split(",");
		List<String> employeeCodeList = Arrays.asList(employeeCodeArr);
//		JpushTools.pushArray(employeeCodeList, "您有临时任务生成，请注意查阅");
		messageRestSrvImpl.JpushMessage(employeeCodeList, task.getProgramCode(), "您有临时任务生成，请注意查阅", "临时任务");
		
	};

	@Override
	public List<Map<String, Object>> queryPosition(AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq) {
		String workingDate = appTaskQueryArrangeRestReq.getWorkingDate();
		if(StringUtils.isEmpty(workingDate)){
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE,"调班时间不能为空");
		}
		// 封装查询条件
		Map<String, Object> param = new HashMap<>();

		// 处理日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.stringToDate(workingDate));

		int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		String queryDay = "day" + dayNum;
		String month = DateUtil.dateToMonth(new Date());

		param.put("shift", appTaskQueryArrangeRestReq.getShift());
		param.put("month", month);
		param.put("employeeCode", appTaskQueryArrangeRestReq.getEmployeeCode());
		param.put(queryDay, queryDay);
		// 查询当班人员
		return this.tempTaskMapper.selectCurrountWorkingEmployee(param);
	}

	@Override
	public Map<String, Object> selectShiftTime(AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq) {

		String workingDate = appTaskQueryArrangeRestReq.getWorkingDate();
		String shift = appTaskQueryArrangeRestReq.getShift();
		Map<String,Object> map = this.appTaskRestMapper.selectShiftTime(shift);

		if(MapUtils.isEmpty(map)){
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"该班次未设置上班开始、结束时间");
		}

		if(StringUtils.isEmpty(workingDate)){
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE,"调班日期不能为空");
		}

		String startTime = Objects.toString(map.get("startTime"),"");
		String endTime = Objects.toString(map.get("endTime"),"");
		if(CommonConstant.SHIFT_DAYTIME.equals(shift)){
			startTime = workingDate +" "+ startTime;
			endTime = workingDate +" "+ endTime;
		}

		//处理日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.stringToDate(workingDate));
		calendar.add(Calendar.DAY_OF_MONTH,1);
		Date nextDate = calendar.getTime();
		String nextDateStr = DateUtil.dateToString(nextDate);

		if(CommonConstant.SHIFT_NIGHT.equals(shift)){
			startTime = workingDate +" "+ startTime;
			endTime = nextDateStr +" "+ endTime;
		}

		map.put("startTime",startTime);
		map.put("endTime",endTime);

		return map;
	}

	@Override
	public List<Map<String, Object>> selectArrangeShiftHistory(AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq) {
		return this.appTaskRestMapper.selectArrangeShiftHistory(appTaskShiftHistoryRestReq);
	}

    @Override
    public void changeShift(AppTaskArrangeShiftRestReq appTaskArrangeShiftRestReq) throws InvocationTargetException, IllegalAccessException {

	    //校验传入的调班开始时间和结束时间
         String startTime = appTaskArrangeShiftRestReq.getStartTimeStr();
         String endTime = appTaskArrangeShiftRestReq.getEndTimeStr();
         if(StringUtils.isEmpty(startTime) || StringUtils.isEmpty(endTime)){
             throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE,"开始时间或结束时间不能为空");
         }
         if(startTime.compareTo(endTime) >= 0){
             throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"结束时间必须大于开始时间，请重新选择时间");
         }

        //获取班次设定中的上班开始、结束时间
         AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq = new AppTaskQueryArrangeRestReq();
         BeanUtils.copyProperties(appTaskQueryArrangeRestReq,appTaskArrangeShiftRestReq);

         Map<String,Object> shiftTimeMap = selectShiftTime(appTaskQueryArrangeRestReq);
         String shiftStartTime = Objects.toString(shiftTimeMap.get("startTime"));
         String shiftEndTime = Objects.toString(shiftTimeMap.get("endTime"));

         if(startTime.compareTo(shiftStartTime) < 0 || startTime.compareTo(shiftEndTime) >= 0){
         	 System.out.println(startTime.compareTo(shiftStartTime));
			 System.out.println(startTime.compareTo(shiftEndTime));
             throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"调班的开始时间必须大于等于"+shiftStartTime+",并小于"+shiftEndTime);
         }
        if(endTime.compareTo(shiftStartTime) <= 0 || endTime.compareTo(shiftEndTime) > 0){
			System.out.println(endTime.compareTo(shiftStartTime));
			System.out.println(endTime.compareTo(shiftEndTime));
            throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"调班的结束时间必须大于"+shiftStartTime+",并小于等于"+shiftEndTime);
        }

        String curTime = DateUtil.dateTimeToString(new Date());
         //获取配置中设置的上班开始前不允许进行调班的时间设置
        String forBidMinutes = RedisTools.getCommonConfig("ARRANGE_SHIFT_FORBID_TIME");
        boolean forBidMinutesAvailable = true;
        int forBidMins = 0;
        if(StringUtils.isEmpty(forBidMinutes)){
            forBidMinutesAvailable = false;
        }
        if(forBidMinutesAvailable){
            forBidMins = Integer.valueOf(forBidMinutes.trim());
        }

        //调整计划
        if(curTime.compareTo(shiftStartTime) < 0){
            String newCurTime = curTime.substring(0,10);
            String newShiftStartTime = shiftStartTime.substring(0,10);
            if(forBidMinutesAvailable){
            	//处理当前时间：当前时间加上禁止调班时间量
            	Calendar cal = Calendar.getInstance();
            	cal.setTime(DateUtil.stringToDateTime(curTime));
            	cal.add(Calendar.MINUTE,Math.abs(forBidMins));
            	String addCurTime = DateUtil.dateTimeToString(cal.getTime());

                if(shiftStartTime.compareTo(addCurTime) > 0){
                    //update排班表,并新增顶班数据到排班表
                    changeArrangeShift(appTaskArrangeShiftRestReq);
                }else{
                    //提示快要生成任务了，不允许进行调班
                    throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"即将批量生成任务了，不允许进行调班");
                }
            }else{
                if(newShiftStartTime.compareTo(newCurTime) > 0){
                    //update排班表,并新增顶班数据到排班表
                    changeArrangeShift(appTaskArrangeShiftRestReq);
                }
            }

        }

        //调整任务
        if(curTime.compareTo(shiftStartTime) >= 0 && curTime.compareTo(shiftEndTime) < 0){
        	//查询当前人员当天是否还有任务可以调
        	Map<String, Object> map = new HashMap<>();
        	String employeeCode = appTaskArrangeShiftRestReq.getEmployeeCode();
        	map.put("employeeCode", employeeCode);
        	map.put("startTime", startTime);
        	map.put("endTime", endTime);
        	List<String> taskList = this.appTaskRestMapper.selectIfCleanerHaveTasks(map);
        	if(CollectionUtils.isEmpty(taskList)) {
        		throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"该人员在当前时间段内没有任务，不能进行调班");
        	}
            this.appTaskRestMapper.updateCleanerTask(appTaskArrangeShiftRestReq);
        }

        //该班次已完成，不允许进行调班
        if(curTime.compareTo(shiftEndTime) >= 0){
            throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"该班次已完成，不允许进行调班");
        }

        //新增调班记录
		TbTaskArrangeShift arrangeShift = new TbTaskArrangeShift();
        BeanUtils.copyProperties(arrangeShift,appTaskArrangeShiftRestReq);

		arrangeShift.setArrangeShiftId(UUIDUtils.getUUID());
		arrangeShift.setChangeDate(DateUtil.stringToDate(appTaskArrangeShiftRestReq.getWorkingDate()));
		arrangeShift.setStartTime(DateUtil.stringToDateTime(appTaskArrangeShiftRestReq.getStartTimeStr()));
		arrangeShift.setEndTime(DateUtil.stringToDateTime(appTaskArrangeShiftRestReq.getEndTimeStr()));
		EntityTools.setCreateEmpAndTime(arrangeShift);
		arrangeShift.setCreateEmp(appTaskArrangeShiftRestReq.getLoginName());

		this.appTaskRestMapper.insertArrangeShift(arrangeShift);
		
		String employeeCode = appTaskArrangeShiftRestReq.getEmployeeCode();
		String programCode = this.appTaskRestMapper.selectProgramCodeByEmployeeCode(employeeCode);
		messageRestSrvImpl.JpushMessage(employeeCode, programCode, "调班成功：您的任务将分派给顶班人员", "调班");
		
		String extraEmployeeCode = appTaskArrangeShiftRestReq.getExtraEmployeeCode();
		String extraProgramCode = this.appTaskRestMapper.selectProgramCodeByEmployeeCode(extraEmployeeCode);
		messageRestSrvImpl.JpushMessage(extraEmployeeCode, extraProgramCode, "调班成功：您将接受其他人的任务，请注意查看", "调班");
    }
    
    @Override
    public Object selectIfTaskExist (AppTaskCheckTaskRestReq appTaskCheckTaskRestReq) {
		//检查扫码得到的岗位与是否有该任务
		String taskId = this.appTaskRestMapper.selectIfTaskExist(appTaskCheckTaskRestReq);
		if(StringUtils.isEmpty(taskId)) {
			return ResParams.newInstance(CommonCodeConstant.ERROR_CODE_40402, "该任务不属于当前扫码的岗位");
		}else {
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC);
		}
    }

    private int changeArrangeShift(AppTaskArrangeShiftRestReq appTaskArrangeShiftRestReq) throws InvocationTargetException, IllegalAccessException {
    	
    	/**
    	 * 调整推迟清洁的专项任务
    	 */
    	// this.appTaskRestMapper.updateCleanerTask(appTaskArrangeShiftRestReq);
    	
        String workingDateStr = appTaskArrangeShiftRestReq.getWorkingDate();
        String month = workingDateStr.substring(0,7).replace("-","");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.stringToDate(workingDateStr));
        int dayNum = calendar.get(Calendar.DAY_OF_MONTH);

        String sql = " UPDATE tb_task_arrangement " +
                "SET DAY_"+ dayNum+" = '休' " +
                "WHERE SHIFT = ? " +
                "AND MONTH = ? " +
                "AND EMPLOYEE_CODE = ? " +
                "AND POSITION_CODE = ? " +
                "AND DAY_"+ dayNum+" = '√' " +
                "AND DELETE_FLAG ='NORMAL' ";

        String shift = appTaskArrangeShiftRestReq.getShift();
        String employeeCode = appTaskArrangeShiftRestReq.getEmployeeCode();
        String positionCode = appTaskArrangeShiftRestReq.getPositionCode();
        String programCode = this.appTaskRestMapper.selectProgramCodeByEmployeeCode(employeeCode);
        String [] args ={shift,month,employeeCode,positionCode};

        int updateItem = jdbcTemplate.update(sql,args);

        if(updateItem > 0){
            String insertSql = "insert into tb_task_arrangement (" +
                    "ARRANGEMENT_ID, SHIFT, EMPLOYEE_CODE, " +
                    "EMPLOYEE_NAME, EMPLOYEE_TEL, POSITION_CODE, " +
                    "POSITION_NAME, MONTH, DELETE_FLAG,DAY_"+ dayNum+"," +
                    "CREATE_EMP,CREATE_DATE,VERSION,PROGRAM_CODE)" +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            String [] insertArgs = new String [14];
            insertArgs[0] = UUIDUtils.getUUID();
            insertArgs[1] = shift;
            insertArgs[2] = appTaskArrangeShiftRestReq.getExtraEmployeeCode();
            insertArgs[3] = appTaskArrangeShiftRestReq.getExtraEmployeeName();
            insertArgs[4] = appTaskArrangeShiftRestReq.getExtraEmployeeTel();
            insertArgs[5] = positionCode;
            insertArgs[6] = appTaskArrangeShiftRestReq.getPositionName();
            insertArgs[7] = month;
            insertArgs[8] = CommonConstant.DELETE_FLAG_NORMAL;
            insertArgs[9] = "√";
            insertArgs[10] = appTaskArrangeShiftRestReq.getLoginName();
            insertArgs[11] = DateUtil.dateTimeToString(new Date());
            insertArgs[12] = "0";
            insertArgs[13] = programCode;

            jdbcTemplate.update(insertSql,insertArgs);
        }

        return updateItem;
    }


    /**
	 * 查询任务、或统计任务时，
	 * 对于白班和夜班的任务时间进行处理
	 * @Title: getTaskQueryTime 
	 * @Description: 对于白班和夜班的任务时间进行处理
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
		String shift = this.appTaskRestMapper.selectShiftType(param);

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
//			map = queryTaskItemTime(shift, startTime, endTime, curDate, curTime, nextDate, lastDate);
			map = queryTaskItemTimeV2(shift, startTime, endTime, curDate, curTime, nextDate, lastDate);
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
	
	private Map<String, Object> queryTaskItemTimeV2(String shift, String startTime, String endTime, String curDate,
			String curTime, String nextDate, String lastDate) {
		// 组装查询条件
		String newStartTime = "";
		String newEndTime = "";
		Map<String, Object> map = new HashMap<>();
		if (CommonConstant.SHIFT_DAYTIME.equals(shift)) {
			/**
			 * 白班： 查询当班任务
			 */
			newStartTime = curDate +" " + startTime;
			newEndTime = curDate +" " + endTime;
		}
		
		if(CommonConstant.SHIFT_NIGHT.equals(shift)) {
			/**
			 * 夜班：查询当班任务
			 */
			if(curTime.compareTo(startTime)>=0) {
				newStartTime = curDate +" " + startTime;
				//往后推一天
				newEndTime = nextDate +" " + endTime;
			}
			
			if(curTime.compareTo(endTime)<= 0) {
				newStartTime = lastDate +" " + startTime;
				//往后推一天
				newEndTime = curDate +" " + endTime;
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
