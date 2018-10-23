package com.qcap.cac.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qcap.cac.dao.LoginRestMapper;
import com.qcap.cac.dto.ResetPasswordReq;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.LoginRestSrv;
import com.qcap.core.entity.TbManager;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.Md5Util;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class LoginRestSrvImpl implements LoginRestSrv {

    @Resource
    private LoginRestMapper loginRestMapper;

    @Resource
    private CommonSrv commonSrv;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public Map<String,Object> login(String workNo, String password) throws Exception{

        TbManager tbManager = this.loginRestMapper.selectManagerByWorkNo(workNo);
        TbAreaPosition position = this.loginRestMapper.selectAreaPositionByWorkNo(workNo);
        if (tbManager != null) {
            if (checkPassword(tbManager.getPassword(), password, tbManager.getSalt())) {
                String managerId = tbManager.getId();
                tbManager.setPassword("");
                tbManager.setSalt("");
                String str=JSONObject.toJSONString(tbManager);
                // 存储token的过期时间和用户ID
                redisUtil.set(AppUtils.getApplicationName() + ":manager:" + managerId, str);
                Map<String, Object> data = new HashMap<>(2);
                data.put("access_token", jwtTokenUtil.doGenerateToken(managerId));
                data.put("employee", tbManager);
                data.put("position", position);
                return data;
            } else {
                throw new BaseException("密码错误！");
            }
        } else {
            throw new BaseException("用户不存在！");
        }

    }

    @Override
    public List<Map<String,Object>> getAppMenuByManagerId(String employeeId) {
        return this.loginRestMapper.getAppMenuByManagerId(employeeId);
    }

    @Override
    public List<Map<String, Object>> getAppUserInfoByManagerCode(String employeeCode) {
        return this.loginRestMapper.getAppUserInfoByManagerCode(employeeCode);
    }

    @Override
    public void resetPassword(ResetPasswordReq resetPasswordDto) throws Exception{
        String employeeCode = resetPasswordDto.getEmployeeCode();
        String oldPassword = resetPasswordDto.getOldPassword();
        String newPassword = resetPasswordDto.getNewPassword();

        TbManager tbManager = this.loginRestMapper.selectManagerByWorkNo(employeeCode);

        if (tbManager != null) {
            if (checkPassword(tbManager.getPassword(), oldPassword, tbManager.getSalt())) {
                String newSalt = Md5Util.getSalt();
                String newMd5 = Md5Util.encrypt(newPassword, newSalt);
                tbManager.setSalt(newSalt);
                tbManager.setPassword(newMd5);
                this.loginRestMapper.updateManagerPwd(tbManager);
            } else {
                throw new BaseException("密码错误！");
            }
        } else {
            throw new BaseException("用户不存在！");
        }

    }

    @Override
    public Map<String, Object> getLoginInfo(String employeeCode) {
        TbManager tbManager = this.loginRestMapper.selectManagerByWorkNo(employeeCode);
        TbAreaPosition position = this.loginRestMapper.selectAreaPositionByWorkNo(employeeCode);
        Map<String, Object> data = new HashMap<>(2);
        data.put("employee", tbManager);
        data.put("position", position);
        return data;
    }

    private boolean checkPassword(String encryptPassword, String password, String salt) {
        return Objects.equals(encryptPassword, Md5Util.encrypt(password, salt));
    }
}
