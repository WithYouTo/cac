package com.qcap.cac.dao;

import com.qcap.cac.dto.MyInfoResp;
import com.qcap.cac.dto.UserListResp;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.core.entity.TbManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface LoginRestMapper {
    TbManager selectManagerByWorkNo(@Param("employeeCode") String employeeCode);

    List<Map<String,Object>> getAppMenuByManagerId(String employeeId);

    MyInfoResp getAppUserInfoByManagerCode(@Param("employeeCode") String employeeCode);

    void updateManagerPwd(TbManager tbManager);

    TbAreaPosition selectAreaPositionByWorkNo(@Param("workNo") String workNo,@Param("shift") String shift);

    List<UserListResp> getUserListByRoleNum(@Param("roleNum") String roleNum,@Param("programCode") String programCode);

    List<UserListResp> getUserListByOrgCode(@Param("orgCode") String orgCode);

    List<UserListResp> getUserListByPositionCode(@Param("map") Map<String,Object> map);

    void updateImgByEmployeeId(@Param("employeeId") String employeeId,@Param("path") String path);

    List<UserListResp> getUserListByOrgCodeAndProgramCodes(@Param("map") Map<String,Object> map);
}
