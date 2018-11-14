package com.qcap.cac.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.LoginRestMapper;
import com.qcap.cac.dto.MyInfoResp;
import com.qcap.cac.dto.ResetPasswordReq;
import com.qcap.cac.dto.UserListReq;
import com.qcap.cac.dto.UserListResp;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.LoginRestSrv;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.core.entity.TbManager;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.Md5Util;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.jwt.JwtTokenUtil;

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

    @Resource
    private TempTaskSrv tempTaskSrv;

    @Override
    public Map<String,Object> login(String employeeCode, String password) throws Exception{
        TbManager tbManager = this.loginRestMapper.selectManagerByWorkNo(employeeCode);
        List<Map<String,Object>> positionList = this.tempTaskSrv.selectCurrountWorkingEmployee(employeeCode);
        String positions = getPositionsFromList(positionList);
        if (tbManager != null) {
            if (checkPassword(tbManager.getPassword(), password, tbManager.getSalt())) {
                String managerId = tbManager.getId();
                tbManager.setPassword("");
                tbManager.setSalt("");
                String str=JSONObject.toJSONString(tbManager);
                // 存储token的过期时间和用户ID
                redisUtil.set(AppUtils.getApplicationName() + ":manager:" + managerId, str);
                Map<String, Object> data = new HashMap<>();
                data.put("access_token", jwtTokenUtil.doGenerateToken(managerId));
                data.put("employeeId", tbManager.getId());
                data.put("employeeCode", tbManager.getAccount());
                data.put("positionCodes", positions);
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
    public MyInfoResp getAppUserInfoByManagerCode(String employeeCode) {
        MyInfoResp info = this.loginRestMapper.getAppUserInfoByManagerCode(employeeCode);
        String baseUrl = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        String url = baseUrl+info.getUrl();
        info.setUrl(url);
        return info;

    }

    @Override
    public void resetPassword(ResetPasswordReq resetPasswordDto) {
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
                throw new BaseException(CommonCodeConstant.SUCCESS_CODE,"密码错误！");
            }
        } else {
            throw new BaseException(CommonCodeConstant.SUCCESS_CODE,"用户不存在！");
        }

    }

    @Override
    public Map<String, Object> getLoginInfo(String employeeCode) {
        TbManager tbManager = this.loginRestMapper.selectManagerByWorkNo(employeeCode);
        List<Map<String,Object>> positionList = this.tempTaskSrv.selectCurrountWorkingEmployee(employeeCode);

        String positions = getPositionsFromList(positionList);
        Map<String, Object> data = new HashMap<>();
        data.put("access_token", jwtTokenUtil.doGenerateToken(tbManager.getId()));
        data.put("employeeId", tbManager.getId());
        data.put("employeeCode", tbManager.getAccount());
        data.put("positionCodes", positions);
        return data;
    }

    @Override
    public List<UserListResp> getUserListByOrgCode(UserListReq userListReq) {
        DateFormat format = new SimpleDateFormat("yyyyMM");
        String s = format.format(new Date());
        String orgCode = userListReq.getOrgCode();
        String positionCode = userListReq.getPositionCode();
        String roleNum = userListReq.getRoleNum();
        String shift =userListReq.getShift();

//        Set<UserListResp> set = new HashSet<UserListResp>();
        if(StringUtils.isNotBlank(positionCode) || StringUtils.isNotBlank(shift)){
            List<UserListResp> poList = this.loginRestMapper.getUserListByPositionCode(positionCode,s,shift);
            return poList;
        }else if (!("").equals(roleNum) && !Objects.isNull(roleNum)){
            List<UserListResp> roleList = this.loginRestMapper.getUserListByOrgCode(roleNum);
            return roleList;
        }else{
            List<UserListResp> orgList = this.loginRestMapper.getUserListByOrgCode(orgCode);
            return orgList;
        }

//        CollectionUtils.addAll(set, orgList);
//        CollectionUtils.addAll(set, poList);
//        CollectionUtils.addAll(set, roleList);
//        List<UserListResp> result = new ArrayList(set);

    }

    @Override
    public void updateImgByEmployeeId(String employeeId, String path) {
        this.loginRestMapper.updateImgByEmployeeId(employeeId,path);
    }

    private boolean checkPassword(String encryptPassword, String password, String salt) {
        return Objects.equals(encryptPassword, Md5Util.encrypt(password, salt));
    }


    private String getPositionsFromList(List<Map<String, Object>> positionList) {
        StringBuilder sb = new StringBuilder("");
        for (Map<String,Object> map : positionList){
            sb.append(ToolUtil.toStr(map.get("positionCode")));
        }
        return sb.toString();
    }
}
