package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.LeaveSearchDto;

import java.util.List;
import java.util.Map;

public interface LeaveSrv {
    void listLeave(IPage<Map<String, Object>> page, LeaveSearchDto leaveSearchDto);
}
