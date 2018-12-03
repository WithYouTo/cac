package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.TempTaskMapper;
import com.qcap.cac.dto.TempTaskDto;
import com.qcap.cac.dto.TempTaskSearchParam;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

import static com.qcap.core.utils.AppUtils.buildZTreeNodeByRecursive;

@Service
@Transactional
public class TempTaskSrvImpl implements TempTaskSrv {

	@Resource
	private TempTaskMapper tempTaskMapper;
	
    @Resource
    private MessageRestSrv messageRestSrvImpl;

	@Override
	public List<Map<String, Object>> listTask(TempTaskSearchParam paramDto) {
		// TODO Auto-generated method stub
		return tempTaskMapper.listTask(paramDto);
	}

	@Override
	public ResParams deleteTempTask(String taskCode) {

		String taskStatus = this.tempTaskMapper.selectTaskStatus(taskCode);
		if (CommonConstant.TASK_STATUS_WAIT.equals(taskStatus)) {
			this.tempTaskMapper.deleteTempTask(taskCode);
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "该任务已成功取消");
		}
		if (CommonConstant.TASK_STATUS_WORKING.equals(taskStatus)) {
			return ResParams.newInstance(CommonCodeConstant.ERROR_CODE_40402, "该任务正在进行中，不允许取消");
		}
		if (CommonConstant.TASK_STATUS_FINISH.equals(taskStatus)) {
			return ResParams.newInstance(CommonCodeConstant.ERROR_CODE_40402, "该任务已完成，不允许取消");
		}
		if (CommonConstant.TASK_STATUS_CANCLE.equals(taskStatus)) {
			return ResParams.newInstance(CommonCodeConstant.ERROR_CODE_40402, "该任务已取消");
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> selectStandardItem() {
		// TODO Auto-generated method stub
		String standardCode = null;
		return this.tempTaskMapper.selectStandardItem(standardCode);
	}

	@Override
	public List<ZTreeNode> selectAreaItem(String programCode) {
		if(StringUtils.isEmpty(programCode)) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"参数为空");
		}
		List<ZTreeNode> ls = new ArrayList<>();
		List<Map<String, Object>> list = this.tempTaskMapper.selectAreaItem(programCode);
		if (CollectionUtils.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				ZTreeNode zTreeNode = new ZTreeNode();
				zTreeNode.setId(Objects.toString(map.get("id")));
				zTreeNode.setName(Objects.toString(map.get("name")));
				zTreeNode.setPid(Objects.toString(map.get("pId")));
				if("".equals(Objects.toString(map.get("pId"),""))) {
					zTreeNode.setPid("-1");
				}
				if ("-1".equals(map.get("pId"))) {
					zTreeNode.setOpen("true");
				} else {
					zTreeNode.setOpen("false");
				}
				ls.add(zTreeNode);
			}
		}
		ls.add(ZTreeNode.createParent());
		return buildZTreeNodeByRecursive(ls, new ArrayList<>(), e -> Objects.equals("0", e.getPid()));
	}

	@Override
	public Map<String, Object> insertTempTask(TempTaskDto taskDto) {

		Map<String, Object> map = new HashMap<>();
		String areaCode = taskDto.getAreaCode();
		String areaName = taskDto.getAreaName();
		// String standardCode = taskDto.getStandardCode();
		// String standardName = taskDto.getStandardName();
		String startTime = taskDto.getStartTime();
		String endTime = taskDto.getEndTime();
		String spec = taskDto.getSpec();
		String positionCode;
		String positionName;
		// 查询标准详细信息
		// List<Map<String,Object>> standardList =
		// this.tempTaskMapper.selectStandardItem(standardCode);
		// if (standardList == null || standardList.isEmpty()) {
		// map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
		// map.put(CommonConstant.BACK_MESSAGE, "该标准不存在");
		// return map;
		// }
		// String uploadPicFlag =
		// ToolUtil.toStr(standardList.get(0).get("uploadPicFlag"));
		// String checkFlag =
		// ToolUtil.toStr(standardList.get(0).get("checkFlag"));
		// 查询岗位
		Map<String, Object> positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(areaCode);
		if (positionMap != null && !positionMap.isEmpty()) {
			positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
			positionName = ToolUtil.toStr(positionMap.get("positionName"));
		} else {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "该区域未设置岗位");
			return map;
		}

		// 查询班次
		String queryTime = startTime.substring(11);
		Map<String, String> shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		if (MapUtils.isEmpty(shiftMap)) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "根据计划时间未查询到班次，请先设置班次");
			return map;
		}
		String shift = shiftMap.get("shift");

		Date now = new Date();
		String taskCode = CommonConstant.TASK_PREFIX_T + DateUtil.dateTimeToStringForLineNo(now);
		TbTask task = new TbTask();
		task.setTaskId(UUIDUtils.getUUID());
		task.setTaskCode(taskCode);
		// task.setPlanId(planId);
		task.setTaskType(CommonConstant.TASK_TYPE_TEMP);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setAreaCode(areaCode);
		task.setAreaName(areaName);
		task.setProgramCode(taskDto.getProgramCode());
		// task.setStandardCode(standardCode);
		// task.setStandardName(standardName);
		task.setShift(shift);
		task.setSpec(spec);
		// task.setCompleteTime(completeTime);
		task.setStartTime(DateUtil.stringToDateTime(startTime));
		task.setEndTime(DateUtil.stringToDateTime(endTime));
		task.setTaskStatus(CommonConstant.TASK_STATUS_WAIT);
		task.setCheckStatus(CommonConstant.TASK_CHECK_STATUS_TOCHECK);
		// task.setTaskScore(taskScore);
		// task.setCheckFlag(checkFlag);
		// task.setUploadPicFlag(uploadPicFlag);
		// task.setTaskAdvice(taskAdvice);
		task.setVersion(0);
		/**设置新增时间和新增人**/
		EntityTools.setCreateEmpAndTime(task);
		
		
		//处理任务执行人员
		String employeeCode = "";
		String employeeName = "";
		String employeeTel = "";
		if(StringUtils.isEmpty(taskDto.getEmployeeCode())) {

		}else {
			employeeCode = taskDto.getEmployeeCode();
			employeeName = taskDto.getEmployeeName();
			employeeTel = taskDto.getEmployeeTel();
		}
		
		task.setEmployeeCode(employeeCode);
		task.setEmployeeName(employeeName);
		task.setEmployeeTel(employeeTel);
		task.setTaskImgUrl(taskDto.getFileUrl());
		//是否检查、是否上传图片和扫码设置
		 task.setCheckFlag(CommonConstant.TASK_CHECK_FLAG_MUST);
		task.setUploadPicFlag(CommonConstant.UPLOAD_PIC_FLAG_MUST);
		task.setStartScanFlag(CommonConstant.START_SCAN_FLAG_OPTIONAL);
		task.setEndScanFlag(CommonConstant.END_SCAN_FLAG_OPTIONAL);
		
		this.tempTaskMapper.insertTempTask(task);

		// 根据工号推送任务通知
		String [] employeeCodeArr = employeeCode.split(",");
		List<String> employeeCodeList = Arrays.asList(employeeCodeArr);
//		JpushTools.pushArray(employeeCodeList, "您有临时任务生成，请注意查阅");
		messageRestSrvImpl.JpushMessage(employeeCodeList, taskDto.getProgramCode(), "您有临时任务生成，请注意查阅", "临时任务");

		map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_SUCCESS_FLAG);
		map.put(CommonConstant.BACK_MESSAGE, "新增临时任务成功");
		return map;
	}
	
	@Override
	public  Map<String,Object> selectDefaultEmployee(String startTime,String areaCode) {
		Map<String,Object> map = new HashMap<>();
		String positionCode=null;
		
		// 查询岗位
		Map<String, Object> positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(areaCode);
		if (positionMap != null && !positionMap.isEmpty()) {
			positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
		} else {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "该区域未设置岗位");
			return map;
		}

		// 查询班次
		String queryTime = startTime.substring(11);
		Map<String, String> shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		if (MapUtils.isEmpty(shiftMap)) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "根据计划时间未查询到班次，请先设置班次");
			return map;
		}
		String shift = shiftMap.get("shift");
		// 处理日期
		Date startDate = DateUtil.stringToDate(startTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		String queryDay = "day" + dayNum;
		String month = DateUtil.dateToMonth(startDate);
		// 封装查询条件
		Map<String, Object> param = new HashMap<>();
		param.put("shift", shift);
		param.put("month", month);
		param.put("positionCode", positionCode);
		param.put(queryDay, queryDay);
		// 查询当班人员
		List<Map<String, Object>> list = this.tempTaskMapper.selectWorkingEmployee(param);
		if (ToolUtil.isEmpty(list)) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "未查询到当班人员");
			return map;
		}
		List<String> employeeCodeList = new ArrayList<>();
		List<String> employeeNameList = new ArrayList<>();
		List<String> employeeTelList = new ArrayList<>();
		for (Map<String, Object> m : list) {
			employeeCodeList.add(ToolUtil.toStr(m.get("employeeCode")));
			employeeNameList.add(ToolUtil.toStr(m.get("employeeName")));
			employeeTelList.add(ToolUtil.toStr(m.get("employeeTel")));
		}
		String employeeCode = String.join(",", employeeCodeList);
		String employeeName = String.join(",", employeeNameList);
		String employeeTel = String.join(",", employeeTelList);
		
		map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_SUCCESS_FLAG);
		map.put("employeeCode", employeeCode);
		map.put("employeeName", employeeName);
		map.put("employeeTel", employeeTel);
		return map;
	}

	@Override
	public Map<String, Object> updateTempTask(TempTaskDto taskDto) {
		
		Map<String, Object> map = new HashMap<>();
		String taskCode = taskDto.getTaskCode();
		String areaCode = taskDto.getAreaCode();
		String areaName = taskDto.getAreaName();
		String startTime = taskDto.getStartTime();
		String endTime = taskDto.getEndTime();
		String spec = taskDto.getSpec();
		String positionCode;
		String positionName;
		/**
		 * 查询任务状态，只有未开始的任务才能修改
		 */
		String taskStatus = this.tempTaskMapper.selectTaskStatus(taskCode);
		if (CommonConstant.TASK_STATUS_WORKING.equals(taskStatus)) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "该任务正在执行中，不允许修改");
			return map;
		} else if (CommonConstant.TASK_STATUS_FINISH.equals(taskStatus)) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "该任务已完成，不允许修改");
			return map;
		} else if (CommonConstant.TASK_STATUS_CANCLE.equals(taskStatus)) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "该任务已取消，不允许修改");

			return map;
		}

		// 查询岗位
		Map<String, Object> positionMap = this.tempTaskMapper.selectPositionInfoByAreaCode(areaCode);
		if (positionMap != null && !positionMap.isEmpty()) {
			positionCode = ToolUtil.toStr(positionMap.get("positionCode"));
			positionName = ToolUtil.toStr(positionMap.get("positionName"));
		} else {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "该区域未设置岗位");
			return map;
		}

		// 查询班次
		String queryTime = startTime.substring(11);
		Map<String, String> shiftMap = this.tempTaskMapper.selectShiftByTime(queryTime);
		if (shiftMap == null || shiftMap.isEmpty()) {
			map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_FAIL_FLAG);
			map.put(CommonConstant.BACK_MESSAGE, "根据计划时间未查询到班次，请先设置班次");
			return map;
		}
		String shift = shiftMap.get("shift");

		TbTask task = new TbTask();
		task.setTaskCode(taskCode);
		task.setPositionCode(positionCode);
		task.setPositionName(positionName);
		task.setAreaCode(areaCode);
		task.setAreaName(areaName);
		// task.setStandardCode(standardCode);
		// task.setStandardName(standardName);
		task.setShift(shift);
		task.setSpec(spec);
		task.setStartTime(DateUtil.stringToDateTime(startTime));
		task.setEndTime(DateUtil.stringToDateTime(endTime));
		// task.setCheckFlag(checkFlag);
		// task.setUploadPicFlag(uploadPicFlag);
		// task.setTaskAdvice(taskAdvice);
		/**设置更新时间和更新人**/
		EntityTools.setUpdateEmpAndTime(task);
		
		//处理任务执行人员
		String employeeCode = "";
		String employeeName = "";
		String employeeTel = "";
		if(StringUtils.isEmpty(taskDto.getEmployeeCode())) {
			// 处理日期
			Date startDate = DateUtil.stringToDate(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
			String queryDay = "day" + dayNum;
			String month = DateUtil.dateToMonth(startDate);
			// 封装查询条件
			Map<String, Object> param = new HashMap<>();
			param.put("shift", shift);
			param.put("month", month);
			param.put("positionCode", positionCode);
			param.put(queryDay, queryDay);
			// 查询当班人员
			List<Map<String, Object>> list = this.tempTaskMapper.selectWorkingEmployee(param);
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
			employeeCode = String.join(",", employeeCodeList);
			employeeName = String.join(",", employeeNameList);
			employeeTel = String.join(",", employeeTelList);
		}else {
			employeeCode = taskDto.getEmployeeCode();
			employeeName = taskDto.getEmployeeName();
			employeeTel = taskDto.getEmployeeTel();
		}
		

		task.setEmployeeCode(employeeCode);
		task.setEmployeeName(employeeName);
		task.setEmployeeTel(employeeTel);
		task.setTaskImgUrl(taskDto.getFileUrl());
		this.tempTaskMapper.updateTempTask(task);

		map.put(CommonConstant.BACK_FLAG, CommonConstant.BACK_SUCCESS_FLAG);
		map.put(CommonConstant.BACK_MESSAGE, "临时任务修改成功");
		return map;
	}

	@Override
	public List<Map<String, Object>> selectAllEmployee(String monthNo) {
		// TODO Auto-generated method stub
		return this.tempTaskMapper.selectAllEmployee(monthNo);
	}

	@Override
	public List<Map<String, Object>> selectCurrountWorkingEmployee(String employeeCode) {
		// 封装查询条件
		Map<String, Object> param = new HashMap<>();
		
		// 处理日期
		Calendar calendar = Calendar.getInstance();
		int dayNum = calendar.get(Calendar.DAY_OF_MONTH);
		String queryDay = "day" + dayNum;
		String month = DateUtil.dateToMonth(new Date());
		
		param.put("month", month);
		param.put("employeeCode", employeeCode);
		param.put(queryDay, queryDay);
		/**
		 * 添加班次查询
		 */
		Date curDate = calendar.getTime();
		String curTime = DateUtil.dateTimeToString(curDate).substring(11);
		Map<String ,String > shiftMap = this.tempTaskMapper.selectShiftByTime(curTime);
		if(MapUtils.isEmpty(shiftMap)){
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"未设置班次");
		}

		String shift = shiftMap.get("shift");
		param.put("shift",shift);
		// 查询当班人员
		return this.tempTaskMapper.selectCurrountWorkingEmployee(param);
		
	}

}
