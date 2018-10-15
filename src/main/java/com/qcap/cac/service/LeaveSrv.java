package com.qcap.cac.service;

import com.qcap.cac.dto.LeaveSearchDto;

import java.util.List;
import java.util.Map;

public interface LeaveSrv {
    List<Map<String, Object>> listLeave(LeaveSearchDto leaveSearchDto);
}
