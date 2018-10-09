package com.qcap.core.log.factory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qcap.core.common.LogSucceed;
import com.qcap.core.common.LogType;
import com.qcap.core.entity.TbLoginLog;
import com.qcap.core.entity.TbOperationLog;
import com.qcap.core.log.LogManager;
import com.qcap.core.service.ITbLoginLogService;
import com.qcap.core.service.ITbOperationLogService;
import com.qcap.core.utils.SpringContextHolder;

/**
 * 
 * @Description: 日志操作任务创建工厂
 *
 * @ClassName: LogTaskFactory
 * 
 * @author huangxiang
 * @date 2017/12/26 10:19
 */
public class LogTaskFactory {

	private static Logger logger = LoggerFactory.getLogger(LogManager.class);

	private static ITbLoginLogService loginLogService = SpringContextHolder.getBean(ITbLoginLogService.class);
	private static ITbOperationLogService operationLogService = SpringContextHolder
			.getBean(ITbOperationLogService.class);

	public static TimerTask loginLog(final String userId, final String ip) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					TbLoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, "", ip);
					loginLogService.insertItem(loginLog);
				} catch (Exception e) {
					logger.error("创建登录日志异常!", e);
				}
			}
		};
	}

	public static TimerTask loginLog(final String username, final String msg, final String ip) {
		return new TimerTask() {
			@Override
			public void run() {
				TbLoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg,
						ip);
				try {
					loginLogService.insertItem(loginLog);
				} catch (Exception e) {
					logger.error("创建登录失败异常!", e);
				}
			}
		};
	}

	public static TimerTask exitLog(final String userId, final String ip) {
		return new TimerTask() {
			@Override
			public void run() {
				TbLoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null, ip);
				try {
					loginLogService.insertItem(loginLog);
				} catch (Exception e) {
					logger.error("创建退出日志异常!", e);
				}
			}
		};
	}

	public static TimerTask businessLog(final String userId, final String businessName, final String clazzName,
			final String methodName, final String msg) {
		return new TimerTask() {
			@Override
			public void run() {
				TbOperationLog operationLog = LogFactory.createOperationLog(LogType.BUSSINESS, userId, businessName,
						clazzName, methodName, msg, LogSucceed.SUCCESS);
				try {
					operationLogService.insertItem(operationLog);
				} catch (Exception e) {
					logger.error("创建业务日志异常!", e);
				}
			}
		};
	}

	public static TimerTask exceptionLog(final String userId, final Exception exception) {
		return new TimerTask() {
			@Override
			public void run() {

				StringWriter sw = new StringWriter();
				try {
					exception.printStackTrace(new PrintWriter(sw));
				} finally {
					try {
						sw.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				String msg = sw.getBuffer().toString().replaceAll("\\$", "T");
				// 错误信息堆栈长度超过2000时只截取2000，数据库设置不超过4000
				if (msg.length() > 2000) {
					msg = msg.substring(0, 2000);
				}

				TbOperationLog operationLog = LogFactory.createOperationLog(LogType.EXCEPTION, userId, "", null, null,
						msg, LogSucceed.FAIL);
				try {
					operationLogService.insertItem(operationLog);
				} catch (Exception e) {
					logger.error("创建异常日志异常!", e);
				}
			}
		};
	}
}
