package com.qcap.cac.dao;

import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.core.entity.TbManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface LoginRestMapper {
    TbManager selectManagerByWorkNo(String workNo);

    List<Map<String,Object>> getAppMenuByManagerId(String employeeId);

    List<Map<String,Object>> getAppUserInfoByManagerCode(@Param("employeeCode") String employeeCode);

    void updateManagerPwd(TbManager tbManager);

    TbAreaPosition selectAreaPositionByWorkNo(String workNo);
}
