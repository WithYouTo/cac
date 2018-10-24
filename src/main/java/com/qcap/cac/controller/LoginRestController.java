package com.qcap.cac.controller;

import com.qcap.cac.dto.ResetPasswordReq;
import com.qcap.cac.service.LoginRestSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbManager;
import com.qcap.core.model.ResParams;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description: APP首页相关接口
 *
 * @author huangxiang
 * @date 2018/10/22 14:13
 */
@RestController
@RequestMapping(value="/rest/login",headers = "api_version=v1")
public class LoginRestController {

    @Resource
    private LoginRestSrv loginRestSrv;

    @Resource
    private FastDFSClientWrapper dfsClient;


    /**
     *
     * @Description:APP端登录
     *
     *
     * @MethodName: login
     * @Parameters: [employeeCode, password]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/22 14:14
     */
    @PostMapping("/login")
    public ResParams login(@RequestParam("employeeCode") String employeeCode, @RequestParam("password") String password) {

        employeeCode = StringUtils.trimToEmpty(employeeCode);
        try {
            Map<String,Object> data = this.loginRestSrv.login(employeeCode, password);
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "登录成功！", data);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
    }

    /**
     *
     * @Description: 打开App调用方法
     *
     *
     * @MethodName: login
     * @Parameters: [employeeCode] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/23 10:47
     */
    @PostMapping("/getLoginInfo")
    public ResParams getLoginInfo(@RequestParam("employeeCode") String employeeCode) {

        employeeCode = StringUtils.trimToEmpty(employeeCode);
        try {
            Map<String,Object> data = this.loginRestSrv.getLoginInfo(employeeCode);
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "获取信息成功！", data);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
    }


    /**
     *
     * @Description: APP首页获取菜单
     *
     *
     * @MethodName: getMenu
     * @Parameters: [employeeId]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/22 14:17
     */
    @PostMapping("/getMenu")
    public ResParams getMenu(String employeeId){
        List<Map<String,Object>> menus = this.loginRestSrv.getAppMenuByManagerId(employeeId);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", menus);
    }

    
    /**
     *
     * @Description: APP首页获取我的信息
     *
     *
     * @MethodName: myInfo
     * @Parameters: [employeeCode] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/22 14:18
     */
    @PostMapping("/myInfo")
    public ResParams myInfo(String employeeCode){
        List<Map<String,Object>> info = this.loginRestSrv.getAppUserInfoByManagerCode(employeeCode);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", info);
    }

    /**
     *
     * @Description: APP端重置密码
     *
     *
     * @MethodName: resetPassword
     * @Parameters: [resetPasswordDto]
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/22 14:20
     */
    @PostMapping("/resetPassword")
    public ResParams resetPassword(ResetPasswordReq resetPasswordDto) throws Exception{
        this.loginRestSrv.resetPassword(resetPasswordDto);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", null);
    }

    /**
     *
     * @Description: APP端上传头像
     *
     *
     * @MethodName: resetPassword
     * @Parameters: [resetPasswordDto] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/22 15:10
     */
    @PostMapping("/updateHeadImg")
    public ResParams updateHeadImg(MultipartFile file) throws Exception{
        String path = dfsClient.uploadFile(file);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", path);
    }

    /**
     *
     * @Description:获取APP下载地址
     *
     *
     * @MethodName: getAppUrl
     * @Parameters: [] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/22 16:15
     */
    @PostMapping("/getAppUrl")
    public ResParams getAppUrl(){
        String appUrl = RedisTools.getCommonConfig("APP_DONW_URL");
        Map<String,Object> map = new HashMap<>();
        map.put("url",appUrl);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
    }

    /**
     *
     * @Description: 获取人员列表
     *
     *
     * @MethodName: getUserListByOrgCode
     * @Parameters: [orgCode, positionCode] 
     * @ReturnType: com.qcap.core.model.ResParams
     *
     * @author huangxiang
     * @date 2018/10/23 15:09
     */
    @PostMapping("/getUserListByOrgCode")
    public ResParams getUserListByOrgCode(String orgCode,String positionCode){
        List<TbManager> list = this.loginRestSrv.getUserListByOrgCode(orgCode,positionCode);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }

}
