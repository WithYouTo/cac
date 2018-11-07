package com.qcap.cac.rest;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.*;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.AppTaskRestSrv;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

@Api(description = "app端任务模块接口")
@Controller
@RequestMapping("/rest/appTask")
public class AppTaskRestController {

	@Resource
	public AppTaskRestSrv appTaskRestSrv;

	@ResponseBody
	@RequestMapping(value = "/queryTaskItem", method = RequestMethod.POST)
	@ApiOperation(value = "清洁人员查询各状态任务数量", notes = "清洁人员查询各状态任务数量", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryTaskItem(
			@ApiParam(value = "员工工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode) {
		Map<String, Object> map = this.appTaskRestSrv.queryTaskItem(employeeCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}

	@ResponseBody
	@RequestMapping(value = "/queryHistoryTask", method = RequestMethod.POST)
	@ApiOperation(value = "清洁人员查询任务历史记录", notes = "清洁人员查询任务历史记录", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryHistoryTask(@Valid AppTaskRestReq appTaskRestDto) {
		List<Map<String, Object>> list = null;
		try {
			list = this.appTaskRestSrv.queryHistoryTask(appTaskRestDto);
		} catch (Exception e) {
			throw new BaseException(CommonCodeConstant.SYS_EXCEPTION_CODE, CommonCodeConstant.SYS_EXCEPTION_MSG);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/queryUnfinishTask", method = RequestMethod.POST)
	@ApiOperation(value = "清洁人员查询待处理任务列表、进行中任务列表", notes = "清洁人员查询待处理任务列表、进行中任务列表", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryUnfinishTask(@Valid AppTaskRestReq appTaskRestDto) {
		List<Map<String, Object>> list = this.appTaskRestSrv.queryUnfinishTask(appTaskRestDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/queryTaskDetail", method = RequestMethod.POST)
	@ApiOperation(value = "清洁人员查询任务详情", notes = "清洁人员查询任务详情", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryTaskDetail(
			@ApiParam(value = "任务编号", required = true) @RequestParam("taskCode") String taskCode) {
		Map<String, Object> map = this.appTaskRestSrv.queryTaskDetail(taskCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}

	@ResponseBody
	@RequestMapping(value = "/queryFinishAndCheckTask", method = RequestMethod.POST)
	@ApiOperation(value = "已完成任务、检查任务、已检查任务列表", notes = "已完成任务、检查任务、已检查任务列表", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryFinishAndCheckTask(@Valid AppTaskRestReq appTaskRestReq) {
		List<Map<String, Object>> list = this.appTaskRestSrv.queryFinishAndCheckTask(appTaskRestReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/selectStandardDetail", method = RequestMethod.POST)
	@ApiOperation(value = "查询标准详情", notes = "查询标准详情", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object selectStandardDetail(
			@ApiParam(value = "标准明细Id", required = true) @RequestParam("standardDetailId") String standardDetailId) {
		Map<String, Object> map = this.appTaskRestSrv.selectStandardDetailInfo(standardDetailId);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}

	@ResponseBody
	@RequestMapping(value = "/workingTask", method = RequestMethod.POST)
	@ApiOperation(value = "开始清洁", notes = "开始清洁", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object workingTask(@ApiParam(value = "任务编号", required = true) @RequestParam("taskCode") String taskCode) {
		this.appTaskRestSrv.workingTask(taskCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@ResponseBody
	@RequestMapping(value = "/finishTask", method = RequestMethod.POST)
	@ApiOperation(value = "完成清洁任务", notes = "完成清洁任务", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object finishTask(MultipartHttpServletRequest request, @Valid AppTaskUpdateReq appTaskUpdateReq) {
		try {
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			Map<String, MultipartFile> mapfile = request.getFileMap();
			if (mapfile != null && !mapfile.isEmpty()) {
				for (Entry<String, MultipartFile> ent : mapfile.entrySet()) {
					fileList.add(ent.getValue());
				}
			}
			this.appTaskRestSrv.finishTask(fileList, appTaskUpdateReq);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(CommonCodeConstant.SYS_EXCEPTION_CODE, CommonCodeConstant.SYS_EXCEPTION_MSG);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@ResponseBody
	@RequestMapping(value = "/queryCheckTaskItem", method = RequestMethod.POST)
	@ApiOperation(value = "获取检查人员检查任务数量", notes = "获取检查人员检查任务数量", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryCheckTaskItem(
			@ApiParam(value = "员工编号", required = true) @RequestParam("employeeCode") String employeeCode) {
		Map<String, Object> map = this.appTaskRestSrv.queryCheckTaskItem(employeeCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}

	@ResponseBody
	@RequestMapping(value = "/queryCheckTask", method = RequestMethod.POST)
	@ApiOperation(value = "检查列表、已检查列表", notes = "检查列表、已检查列表", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryCheckTask(@Valid AppTaskCheckRestReq appTaskCheckRestReq) {
		List<Map<String, Object>> list = this.appTaskRestSrv.queryCheckTask(appTaskCheckRestReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/checkTask", method = RequestMethod.POST)
	@ApiOperation(value = "检查任务：合格|不合格", notes = "检查任务：合格|不合格", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object checkTask(MultipartHttpServletRequest request, @Valid AppTaskUpdateReq appTaskUpdateReq) {
		try {
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			Map<String, MultipartFile> mapfile = request.getFileMap();
			if (mapfile != null && !mapfile.isEmpty()) {
				for (Entry<String, MultipartFile> ent : mapfile.entrySet()) {
					fileList.add(ent.getValue());
				}
			}
			this.appTaskRestSrv.checkTask(fileList, appTaskUpdateReq);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "检查任务失败");
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}

	@ResponseBody
	@RequestMapping(value = "/listTempTask", method = RequestMethod.POST)
	@ApiOperation(value = "临时任务-历史查询", notes = "临时任务-历史查询", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object listTempTask( @Valid AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq) {
		List<Map<String, Object>> list = this.appTaskRestSrv.listTempTask(appTaskShiftHistoryRestReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/selectDefaultEmployee", method = RequestMethod.POST)
	@ApiOperation(value = "临时任务：查询默认清洁人员", notes = "临时任务：查询默认清洁人员", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object selectDefaultEmployee(
			@ApiParam(value = "区域编码", required = true) @RequestParam("areaCode") String areaCode) {
		Map<String, Object> map = this.appTaskRestSrv.selectDefaultEmployee(DateUtil.dateTimeToString(new Date()),
				areaCode);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}

	@ResponseBody
	@RequestMapping(value = "/addTempTask", method = RequestMethod.POST)
	@ApiOperation(value = "临时任务发布", notes = "临时任务发布", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object addTempTask(MultipartHttpServletRequest request, @Valid AppTaskAddRestReq appTaskAddRestReq) {
		try {
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			Map<String, MultipartFile> mapfile = request.getFileMap();
			if (mapfile != null && !mapfile.isEmpty()) {
				for (Entry<String, MultipartFile> ent : mapfile.entrySet()) {
					fileList.add(ent.getValue());
				}
			}
			this.appTaskRestSrv.addTempTask(fileList, appTaskAddRestReq);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC);
	}

	/**
	 * 调班接口
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPosition", method = RequestMethod.POST)
	@ApiOperation(value = "查询清洁人员上班岗位", notes = "查询清洁人员上班岗位", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object queryPosition(@Valid AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq) {
		List<Map<String, Object>> list = this.appTaskRestSrv.queryPosition(appTaskQueryArrangeRestReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/selectShiftTime", method = RequestMethod.POST)
	@ApiOperation(value = "查询班次的时间", notes = "查询班次的时间", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object selectShiftTime(@Valid AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq) {
		Map<String, Object> map = this.appTaskRestSrv.selectShiftTime(appTaskQueryArrangeRestReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, map);
	}

	@ResponseBody
	@RequestMapping(value = "/changeShift", method = RequestMethod.POST)
	@ApiOperation(value = "调班", notes = "调班", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object changeShift(@Valid AppTaskArrangeShiftRestReq appTaskArrangeShiftRestReq) {
		try {
			this.appTaskRestSrv.changeShift(appTaskArrangeShiftRestReq);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (BaseException e) {
			throw e;
		}catch (Exception e) {
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "调班失败");
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "调班成功");
	}

	@ResponseBody
	@RequestMapping(value = "/selectArrangeShiftHistory", method = RequestMethod.POST)
	@ApiOperation(value = "查询调班历史记录", notes = "查询调班历史记录", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object selectArrangeShiftHistory( @Valid AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq) {
		List<Map<String, Object>> list = this.appTaskRestSrv.selectArrangeShiftHistory(appTaskShiftHistoryRestReq);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, list);
	}

	@ResponseBody
	@RequestMapping(value = "/selectIfTaskExist", method = RequestMethod.POST)
	@ApiOperation(value = "检查任务是否是当前扫码岗位下的任务（检查|清洁）", notes = "检查任务是否是当前扫码岗位下的任务（检查|清洁）", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object selectIfTaskExist(@Valid AppTaskCheckTaskRestReq appTaskCheckTaskRestReq) {
		return this.appTaskRestSrv.selectIfTaskExist(appTaskCheckTaskRestReq);
	}
	
	@ResponseBody
	@RequestMapping(value = "/delaySpecialTask", method = RequestMethod.POST)
	@ApiOperation(value = "专项任务推迟进行", notes = "专项任务推迟进行", response = Map.class, httpMethod = "POST")
	@ApiImplicitParam(paramType = "header", name = "api_version", defaultValue = "v1", required = true, dataType = "String")
	public Object delaySpecialTask(
			@ApiParam(value = "任务编号", required = true) @RequestParam("taskCode") String taskCode) {
		 return this.appTaskRestSrv.delaySpecialTask(taskCode);
	}

}
