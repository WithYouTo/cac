package com.qcap.cac.service;

import com.qcap.cac.dto.LeaveSearchParam;

import java.util.List;
import java.util.Map;

public interface LeaveSrv {
    List<Map<String, Object>> listLeave(LeaveSearchParam leaveSearchParam);
}
