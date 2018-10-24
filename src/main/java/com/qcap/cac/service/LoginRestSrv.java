package com.qcap.cac.service;

import com.qcap.cac.dto.MyInfoResp;
import com.qcap.cac.dto.ResetPasswordReq;
import com.qcap.cac.dto.UserListReq;
import com.qcap.cac.dto.UserListResp;
import com.qcap.core.entity.TbManager;

import java.util.List;
import java.util.Map;

public interface LoginRestSrv {
    Map<String,Object> login(String workNo, String password) throws Exception;

    List<Map<String,Object>> getAppMenuByManagerId(String employeeId);

    MyInfoResp getAppUserInfoByManagerCode(String employeeCode);

    void resetPassword(ResetPasswordReq resetPasswordDto) throws Exception;

    Map<String,Object> getLoginInfo(String employeeCode);

    List<UserListResp> getUserListByOrgCode(UserListReq userListReq);

    void updateImgByEmployeeId(String employeeId, String path);
}
