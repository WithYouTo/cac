package com.qcap.cac.service;

import com.qcap.cac.dto.ResetPasswordReq;
import com.qcap.core.entity.TbManager;

import java.util.List;
import java.util.Map;

public interface LoginRestSrv {
    Map<String,Object> login(String workNo, String password) throws Exception;

    List<Map<String,Object>> getAppMenuByManagerId(String employeeId);

    List<Map<String,Object>> getAppUserInfoByManagerCode(String employeeCode);

    void resetPassword(ResetPasswordReq resetPasswordDto) throws Exception;

    Map<String,Object> getLoginInfo(String employeeCode);

    List<TbManager> getUserListByOrgCode(String orgCode, String positionCode);
}
