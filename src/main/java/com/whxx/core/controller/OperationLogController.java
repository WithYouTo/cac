package com.whxx.core.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.annotation.BussinessLog;
import com.whxx.core.common.CoreConstant;
import com.whxx.core.entity.TbOperationLog;
import com.whxx.core.model.PageResParams;
import com.whxx.core.model.ResParams;
import com.whxx.core.service.ITbOperationLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  日志管理的控制器 TokenLogController
 *
 * @author huangxiang
 * @date 2017/12/26 14:28
 */
@RestController
@RequestMapping("/operationLog")
public class OperationLogController
{
    private ITbOperationLogService operationLogService;

    /**
     * 查询操作日志列表
     */
    @PostMapping("/list")
    public Object list(@RequestParam(required = false) String rangeTime, @RequestParam(required = false) String logName, @RequestParam(required = false) String logType, IPage<TbOperationLog> page)
    {
        Map<String, String> params = new HashMap<>();
        if (StrUtil.isNotEmpty(rangeTime))
        {
            String[] time = rangeTime.split(" - ");
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
        params.put("logType", logType);
        operationLogService.getOperationLogList(page, params);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", page.getTotal(), page.getRecords());

    }

    /**
     * 清空日志
     */
    @BussinessLog(value = "清空业务日志")
    @PostMapping("/deleteAll")
    public ResParams deleteOperationLogAll()
    {
        operationLogService.deleteOperationLogAll();
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
    }


    /**
     * 批量删除日志
     */
    @BussinessLog(value = "批量删除日志")
    @PostMapping("/deleteBatch")
    public Object delLogBatch(@RequestParam("ids")String ids) {
        operationLogService.batchDeleteOperationLog(Arrays.asList(ids.split(",")));
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
    }

    @Inject
    public void setOperationLogService(ITbOperationLogService operationLogService)
    {
        this.operationLogService = operationLogService;
    }
}
