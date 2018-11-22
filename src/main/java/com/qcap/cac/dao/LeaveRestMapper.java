package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.AppLeaveReq;
import com.qcap.cac.entity.TbLeave;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;
import java.util.Map;

public interface LeaveRestMapper  extends BaseMapper<TbLeave> {

	//String queryLoginOrgCode(String employeeCode);

	List<String> queryProgramCodesByEmpCode(String employeeCode);

	List<AppLeaveReq> queryLeaveList(Map<String,Object> paramMap);


	AppLeaveReq selectLeaveDetailById(String leaveId);
	
	String selectAreaCodeByEmployCode (@Param("employeeCode") String employeeCode);
}
