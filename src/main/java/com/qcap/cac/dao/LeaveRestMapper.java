package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.AppLeaveReq;
import com.qcap.cac.entity.TbLeave;

import java.util.List;
import java.util.Map;

public interface LeaveRestMapper  extends BaseMapper<TbLeave> {

	String queryLoginOrgCode(String employeeCode);

	List<AppLeaveReq> queryLeaveList(Map<String,Object> paramMap);


	AppLeaveReq selectLeaveDetailById(String leaveId);
}
