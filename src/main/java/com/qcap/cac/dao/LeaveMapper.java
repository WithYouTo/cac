package com.qcap.cac.dao;

import com.qcap.cac.dto.LeaveSearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface LeaveMapper {
    List<Map<String, Object>> listLeave(LeaveSearchDto leaveSearchDto);
}
