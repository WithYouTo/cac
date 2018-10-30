/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: EventTaskRestController.java 
 * @Prject: cac
 * @Package: com.qcap.cac.rest 
 * @Description: TODO
 * @author: 张天培(2017004)   
 * @date: 2018年10月11日 上午9:16:00 
 * @version: V1.0   
 */
package com.qcap.cac.rest;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AppLeaveReq;
import com.qcap.cac.service.LeaveRestSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ResParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: LeaveRestController
 * @Description: TODO
 * @author: 曾欣
 * @date: 2018年10月25日 上午9:16:00
 */
@Api(description="请假申请")
@RestController
@RequestMapping(value="/rest/leave",headers="api_version=v1")
public class LeaveRestController {
	
	@Resource
	private LeaveRestSrv leaveRestSrv;

	@RequestMapping(value="/leaveApply",method=RequestMethod.POST)
	@ApiOperation(value="提交请假申请",notes="提交请假申请",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object insertLeaveApply(MultipartHttpServletRequest req) {
		try {
			//图片流
			Map<String, MultipartFile> mapFile = req.getFileMap();
			//req除图片外的请求参数
			this.leaveRestSrv.insertLeaveApply(req,mapFile);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS,null);
	}


	@RequestMapping(value="leaveHistoryList",method=RequestMethod.POST)
	@ApiOperation(value="我的请假记录",notes="我的请假记录",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object getMyLeaveHistoryList(
			@ApiParam(value = "登录人工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
			@ApiParam(value = "lineNo", required = false) @RequestParam(value = "lineNo", required = false) String lineNo) {
		Map<String,Object> map = new HashMap<>();
		try {
			Map<String,Object> paramMap = new HashMap();
			paramMap.put("employeeCode",employeeCode);
			paramMap.put("lineNo",lineNo);
			List<AppLeaveReq> list = this.leaveRestSrv.queryLeaveList(paramMap);
			map.put("leaveHistory",list);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
	}

	@RequestMapping(value="getLeaveDetailById",method=RequestMethod.POST)
	@ApiOperation(value="请假详情",notes="请假详情",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object leaveDetailById(
			@ApiParam(value = "请假主键", required = true) @RequestParam(value = "leaveId", required = true) String leaveId) {
		Map<String,Object> map = new HashMap<>();
		try {
            AppLeaveReq detail = this.leaveRestSrv.detailById(leaveId);
			map.put("leaveDetail",detail);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
	}

	@RequestMapping(value="auditingList",method=RequestMethod.POST)
	@ApiOperation(value="待审批记录",notes="待审批记录",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object getAuditingList(
			@ApiParam(value = "登录人工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
			@ApiParam(value = "lineNo", required = false) @RequestParam(value = "lineNo", required = false) String lineNo) {
		Map<String,Object> map = new HashMap<>();
		try {
			Map<String,Object> paramMap = new HashMap();
			paramMap.put("employeeCode",employeeCode);
			paramMap.put("lineNo",lineNo);
			List<AppLeaveReq> list = this.leaveRestSrv.queryAuditingList(paramMap);
			map.put("auditingList",list);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
	}


    @RequestMapping(value="auditedList",method=RequestMethod.POST)
    @ApiOperation(value="我已审批记录",notes="我已审批记录",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public Object getMyAuditedList(
            @ApiParam(value = "登录人工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
            @ApiParam(value = "lineNo", required = false) @RequestParam(value = "lineNo", required = false) String lineNo) {
        Map<String,Object> map = new HashMap<>();
        try {
            Map<String,Object> paramMap = new HashMap();
            paramMap.put("auditCode",employeeCode);
            paramMap.put("lineNo",lineNo);
            List<AppLeaveReq> list = this.leaveRestSrv.queryLeaveList(paramMap);
            map.put("auditedList",list);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
        }
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,map);
    }


	@RequestMapping(value="cancelMyLeaveApply",method=RequestMethod.POST)
	@ApiOperation(value="撤销我的请假单/审批通过",notes="撤销我的请假单/审批通过",response=Map.class,httpMethod="POST")
	@ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
	public Object cancelMyLeaveApply(
			@ApiParam(value = "登录人工号", required = true) @RequestParam(value = "employeeCode", required = true) String employeeCode,
			@ApiParam(value = "请假单主键", required = true) @RequestParam(value = "leaveId", required = false) String leaveId,
			@ApiParam(value = "操作类型（撤销CANCEL/通过PASS）", required = true) @RequestParam(value = "operaType", required = false) String operaType) {
		try {
			Map<String,Object> paramMap = new HashMap();
			paramMap.put("employeeCode",employeeCode);
			paramMap.put("leaveId",leaveId);
			paramMap.put("operaType",operaType);
			this.leaveRestSrv.cancelLeave(paramMap);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
		}
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC,null);
	}


    @RequestMapping(value="refuseMyLeaveApply",method=RequestMethod.POST)
    @ApiOperation(value="驳回请假单",notes="驳回请假单",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public Object refuseMyLeaveApply(MultipartHttpServletRequest req) {
        Map<String, MultipartFile> mapFile = req.getFileMap();
        String employeeCode = req.getParameter("employeeCode");
        String auditReason = req.getParameter("refuseReason");
        String leaveId = req.getParameter("leaveId");
        try {
            if(StringUtils.isEmpty(employeeCode)){
                return ResParams.newInstance(CoreConstant.FAIL_CODE, "用户工号为空",null);
            }
            this.leaveRestSrv.auditLeave(mapFile,employeeCode,auditReason,leaveId);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(),null);
        }
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC,null);
    }

}
