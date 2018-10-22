package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.LeaveSearchDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface LeaveMapper {
    List<Map<String, Object>> listLeave(IPage<Map<String, Object>> page, @Param("obj") LeaveSearchDto leaveSearchDto);
}
