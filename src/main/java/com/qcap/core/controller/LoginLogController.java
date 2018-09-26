package com.qcap.core.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.warpper.LogWarpper;
import com.qcap.core.annotation.BussinessLog;
import com.qcap.core.annotation.Permission;
import com.qcap.core.model.OperationLog;
import com.qcap.core.service.LoginLogSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 日志管理的控制器
 *
 * @ClassName: LoginLogController
 *
 * @author huangxiang
 * @date 2017/12/26 16:51
 */
@Controller
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {

    private static String PREFIX = "/system/log/";

    @Autowired
    private LoginLogSrv loginLogSrv;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "login_log";
    }

    /**
     * 查询登录日志列表
     */

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        new PageFactory<OperationLog>().defaultPage();
        List<Map<String, Object>> list = loginLogSrv.getLoginLogs(beginTime, endTime, logName);
        PageInfo pageInfo = new PageInfo( (List<OperationLog>) new LogWarpper(list).warp());
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return result;
    }

    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @RequestMapping("/delLoginLog")
    @Permission
    @ResponseBody
    public Object delLog() {
        loginLogSrv.delete();
        return super.SUCCESS_TIP;
    }
}
