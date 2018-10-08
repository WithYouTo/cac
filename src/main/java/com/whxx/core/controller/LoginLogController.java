package com.whxx.core.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.common.CoreConstant;
import com.whxx.core.entity.TbLoginLog;
import com.whxx.core.model.PageResParams;
import com.whxx.core.model.ResParams;
import com.whxx.core.service.ITbLoginLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 日志管理的控制器
 *
 *s @author huangXiang
 * @date 2017/12/26 16:51
 */
@RestController
@RequestMapping("/loginLog")
public class LoginLogController
{

    private ITbLoginLogService loginLogService;


    /**
     * 查询登录日志列表
     */

    @PostMapping("/list")
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName, IPage<TbLoginLog> page)
    {
        Map<String, String> params = new HashMap<>();
        if (StringUtils.isNotEmpty(beginTime))
        {
            String[] time = beginTime.split(" - ");
            String one = time[0];
            String two = time[1];
            if (!StringUtils.isNoneEmpty(one, two) && Objects.equals(one, two))
            {
                return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功！", 0, null);
            } else
            {
                params.put("startTime", one);
                params.put("endTime", two);
            }
        }
        params.put("logName", logName);
        loginLogService.getLoginLogList(page, params);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", page.getTotal(), page.getRecords());
    }

    /**
     * 清空日志
     */
    @PostMapping("/deleteAll")
    public Object delLog() {
        loginLogService.deleteLoginLogAll();
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
    }

    /**
     * 批量删除日志
     */
    @PostMapping("/deleteBatch")
    public ResParams delLogBatch(@RequestParam("ids") String ids)
    {
    	String[] id=ids.split(",");
        loginLogService.batchDeleteLoginLog(ids);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
    }

    @Inject
    public void setLoginLogService(ITbLoginLogService loginLogService)
    {
        this.loginLogService = loginLogService;
    }
}
