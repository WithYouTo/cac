package com.qcap.cac.service;

import com.qcap.cac.dto.ResetPasswordDto;
import com.qcap.core.entity.TbMenu;

import java.util.List;
import java.util.Map;

public interface LoginRestSrv {
    Map<String,Object> login(String workNo, String password) throws Exception;

    List<Map<String,Object>> getAppMenuByManagerId(String employeeId);

    List<Map<String,Object>> getAppUserInfoByManagerCode(String employeeCode);

    void resetPassword(ResetPasswordDto resetPasswordDto) throws Exception;
}
