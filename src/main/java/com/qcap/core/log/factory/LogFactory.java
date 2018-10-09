package com.qcap.core.log.factory;

import java.time.LocalDateTime;

import com.qcap.core.common.LogSucceed;
import com.qcap.core.common.LogType;
import com.qcap.core.entity.TbLoginLog;
import com.qcap.core.entity.TbOperationLog;

/**
 *
 * 日志对象创建工厂
 *
 * @author huangxiang
 * @date 2017/12/26 10:01
 */
public class LogFactory {

	public static TbOperationLog createOperationLog(LogType logType, String userId, String businessName,
			String clazzName, String methodName, String msg, LogSucceed succeed) {
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
