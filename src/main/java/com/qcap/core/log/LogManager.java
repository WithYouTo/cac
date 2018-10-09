package com.qcap.core.log;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 *
 * 日志管理器
 *
 *
 * @author huangxiang
 * @date 2017/12/26 9:55
 */
public class LogManager {

	/**
	 * 日志记录操作延时
	 */
	private final int OPERATE_DELAY_TIME = 10;

	/**
	 * 异步操作记录日志的线程池
	 */
	private ScheduledExecutorService executor;
	{
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("log-pool-%d").build();
		executor = new ScheduledThreadPoolExecutor(10, namedThreadFactory);
	}

	private LogManager() {
	}

	public static LogManager logManager = new LogManager();

	public static LogManager me() {
		return logManager;
	}

	public void executeLog(TimerTask task) {
		executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
	}

}
