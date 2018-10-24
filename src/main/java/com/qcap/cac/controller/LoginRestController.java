package com.qcap.cac.controller;

import com.qcap.cac.dto.*;
import com.qcap.cac.service.LoginRestSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbManager;
import com.qcap.core.model.ResParams;
import com.qcap.core.warpper.FastDFSClientWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.File;
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
@Api(description="APP首页相关接口")
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
    @ApiOperation(value="登录",notes="登录",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams login(LoginRestReq loginReq) {
        String employeCode = loginReq.getEmployeeCode();
        employeCode = StringUtils.trimToEmpty(employeCode);
        try {
            Map<String,Object> data = this.loginRestSrv.login(employeCode, loginReq.getPassword());
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
    @ApiOperation(value="打开App调用方法",notes="打开App调用方法",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
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
    @ApiOperation(value="APP首页获取菜单",notes="APP首页获取菜单",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
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
    @ApiOperation(value="APP首页获取我的信息",notes="APP首页获取我的信息",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams myInfo(String employeeCode){
        MyInfoResp info = this.loginRestSrv.getAppUserInfoByManagerCode(employeeCode);
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
    @ApiOperation(value="APP端重置密码",notes="APP端重置密码",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
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
    @ApiOperation(value="APP端上传头像",notes="APP端上传头像",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams updateHeadImg(MultipartHttpServletRequest req) throws Exception{
        Map<Object, Object> resultmap = new HashMap<>();
        Map<String, MultipartFile> mapfile = req.getFileMap();
        String employeeId = req.getParameter("employeeId");

        String url = "";
        try {
            // 判断是否有文件
            if (mapfile != null && !mapfile.isEmpty()) {
                String baseUrl = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
                String path = "";
                for (Map.Entry<String, MultipartFile> ent : mapfile.entrySet()) {
                    MultipartFile mf = ent.getValue();
                    path = dfsClient.uploadFile(mf);
                }
                url = baseUrl +  path;
                resultmap.put("url",url);
                this.loginRestSrv.updateImgByEmployeeId(employeeId,path);
            }
        }catch(Exception e){
            return ResParams.newInstance(500, "头像上传失败",null);

        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", resultmap);
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
    @ApiOperation(value="获取APP下载地址",notes="获取APP下载地址",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
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
    @ApiOperation(value="获取人员列表",notes="获取人员列表",response=Map.class,httpMethod="POST")
    @ApiImplicitParam(paramType="header",name="api_version",defaultValue="v1",required=true,dataType="String")
    public ResParams getUserListByOrgCode(UserListReq userListReq){
        List<UserListResp> list = this.loginRestSrv.getUserListByOrgCode(userListReq);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
    }

}
