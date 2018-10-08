package com.whxx.core.log.factory;


import com.whxx.core.common.LogSucceed;
import com.whxx.core.common.LogType;
import com.whxx.core.entity.TbLoginLog;
import com.whxx.core.entity.TbOperationLog;

import java.time.LocalDateTime;

/**
 *
 *  日志对象创建工厂
 *
 * @author huangxiang
 * @date 2017/12/26 10:01
 */
public class LogFactory {

    public static TbOperationLog createOperationLog(LogType logType, String userId, String businessName, String clazzName, String methodName, String msg, LogSucceed succeed)
    {
    	TbOperationLog operationLog = new TbOperationLog();
        operationLog.setLogType(logType.getMessage());
        operationLog.setLogName(businessName);
        operationLog.setUserid(userId);
        operationLog.setClassName(clazzName);
        operationLog.setMethod(methodName);
        operationLog.setCreateTime(LocalDateTime.now());
        operationLog.setSucceed(succeed.getMessage());
        operationLog.setMessage(msg);
        return operationLog;
    }


    public static TbLoginLog createLoginLog(LogType logType, String userId, String msg, String ip) {
        TbLoginLog loginLog = new TbLoginLog();
        loginLog.setLogName(logType.getMessage());
        loginLog.setUserid(userId);
        loginLog.setCreateTime(LocalDateTime.now());
        loginLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        loginLog.setIp(ip);
        loginLog.setMessage(msg);
        return loginLog;
    }
}
