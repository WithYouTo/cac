/**
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 *
 * @Title: AppUtils.java
 * @Prject: whxx-erp
 * @Package: com.whxx.erp.utils
 * @author: 彭浩
 * @date: 2018年5月9日 下午2:32:26
 * @version: V1.0
 */
package com.qcap.core.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import com.qcap.core.entity.TbManager;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.function.Predicate;

/**
 * AppUtils工具类
 *
 * @ClassName: AppUtils
 * @author: 彭浩
 * @date: 2018年5月9日 下午2:32:26
 */
public final class AppUtils {

	/**
	 * spring.application.name
	 *
	 * @fieldName: SPRING_APPLICATION_NAME
	 * @fieldType: String
	 */
	private static final String SPRING_APPLICATION_NAME = "spring.application.name";
	/**
	 *
	 * @fieldName: JAR_SUFFIX
	 * @fieldType: String
	 */
	private static final String JAR_SUFFIX = ".jar";

	/**
	 *
	 * @fieldName: REDIS_KEY_SEPARATOR
	 * @fieldType: String
	 */
	private static final String REDIS_KEY_SEPARATOR = ":";

	/**
	 * 获取日志--文件方式（以类名命名文件，并记录日志）
	 *
	 * @param clazz
	 *            目标类
	 * @param isWriteToLogFile
	 *            是否写入到文件
	 * @return org.slf4j.Logger
	 */
	public static org.slf4j.Logger getLogger(Class<?> clazz, boolean isWriteToLogFile) {

		Logger result = (Logger) LoggerFactory.getLogger(clazz);
		result.detachAndStopAllAppenders();

		if (isWriteToLogFile) {
			LoggerContext loggerContext = result.getLoggerContext();

			PatternLayoutEncoder encoder = new PatternLayoutEncoder();
			encoder.setContext(loggerContext);
			encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p [%C %M %L] - %m%n");
			encoder.start();

			FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
			String fileName = clazz.getSimpleName() + "_" + DateUtil.format(new Date(), "yyyy-MM-dd") + ".log";

			String os = System.getProperty("os.name");
			String rootPath = "";
			String contextName = "/";
			if (os.toLowerCase().startsWith("win")) {
				File classPath = new File(clazz.getClassLoader().getResource("").getPath());
				String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
				rootPath = "c:/whxxlogs";
				if (contextPath.length > 1) {
					contextName = "/" + contextPath[contextPath.length - 1] + "/";
				}
			} else {

				File classPath = new File(clazz.getClassLoader().getResource("").getPath());
				String[] contextPath = classPath.getParentFile().getParent().split("/");
				rootPath = "/usr/local/whxxlogs";
				if (contextPath.length > 1) {
					contextName = "/" + contextPath[contextPath.length - 1] + "/";
				}
			}

			StringBuffer sb = new StringBuffer();
			String filePath = sb.append(rootPath).append(contextName).append(clazz.getSimpleName()).append("/")
					.append(fileName).toString();

			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			appender.setFile(filePath);
			appender.setContext(loggerContext);
			appender.setEncoder(encoder);
			appender.start();
			appender.setAppend(true);
			result.addAppender(appender);
		}
		return result;
	}

	/**
	 * 获取日志--文件方式（以指定业务名称命名文件，并记录日志）
	 *
	 * @param name
	 *            指定业务名称
	 * @param isWriteToLogFile
	 *            是否写入到文件
	 * @return org.slf4j.Logger
	 */
	public static org.slf4j.Logger getLogger(String name, boolean isWriteToLogFile) {

		Logger result = (Logger) LoggerFactory.getLogger(name);
		result.detachAndStopAllAppenders();

		if (isWriteToLogFile) {
			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

			// 若重置，日志不会出现在控制台中，
			PatternLayoutEncoder encoder = new PatternLayoutEncoder();
			encoder.setContext(loggerContext);
			encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p [%C %M %L] - %m%n");
			encoder.start();
			FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
			String fileName = name + "_" + DateUtil.format(new Date(), "yyyy-MM-dd") + ".log";
			String os = System.getProperty("os.name");
			String rootPath = "";
			String contextName = "/";
			if (os.toLowerCase().startsWith("win")) {
				File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
				String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
				rootPath = "c:/whxxlogs/";
				if (contextPath.length > 1) {
					String tmp = contextPath[contextPath.length - 1];
					// whxx-auth截取
					// contextName = "/" + tmp.substring(0,
					// tmp.lastIndexOf("-")) + "/";
					// whhotel
					contextName = "/" + tmp + "/";
				}
			} else {
				File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
				String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
				rootPath = "/usr/local/whxxlogs/";
				if (contextPath.length > 1) {
					String tmp = contextPath[contextPath.length - 1];
					// contextName = "/" + tmp.substring(0,
					// tmp.lastIndexOf("-")) + "/";
					contextName = "/" + tmp + "/";
				}
			}

			StringBuffer sb = new StringBuffer();
			String definePath = "";
			String[] pathNames = name.split("-");

			for (int i = 0; i < pathNames.length; i++) {
				definePath = sb.append(pathNames[i]).append("/").toString();
			}

			String filePath = rootPath + contextName + definePath + fileName;

			File file = new File(filePath);
			if (file.getParentFile().exists() == false) {
				file.getParentFile().mkdirs();
			}
			appender.setFile(filePath);
			appender.setContext(loggerContext);
			appender.setEncoder(encoder);
			appender.start();
			// 日志追加到结尾
			appender.setAppend(true);
			result.addAppender(appender);
		}
		return result;
	}

	public static org.slf4j.Logger getErrorLogger(String name, boolean isWriteToLogFile) {

		Logger result = (Logger) LoggerFactory.getLogger(name);
		result.detachAndStopAllAppenders();
		result.setLevel(Level.ERROR);

		if (isWriteToLogFile) {

			LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

			PatternLayoutEncoder encoder = new PatternLayoutEncoder();
			encoder.setContext(loggerContext);
			encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p [%C %M %L] - %m%n");
			encoder.start();

			FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
			String fileName = name + "_" + DateUtil.format(new Date(), "yyyy-MM-dd") + ".log";

			String os = System.getProperty("os.name");
			String rootPath = "";
			String contextName = "/";
			if (os.toLowerCase().startsWith("win")) {
				File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
				String[] contextPath = classPath.getParentFile().getParent().split("\\\\");

				rootPath = "c:/whxxlogs/";
				if (contextPath.length > 1) {
					String tmp = contextPath[contextPath.length - 1];
					contextName = "/" + tmp.substring(0, tmp.lastIndexOf("-")) + "/";
				}
			} else {
				File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
				String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
				rootPath = "/usr/local/whxxlogs/";
				if (contextPath.length > 1) {
					String tmp = contextPath[contextPath.length - 1];
					contextName = "/" + tmp.substring(0, tmp.lastIndexOf("-")) + "/";
				}
			}

			StringBuffer sb = new StringBuffer();
			String definePath = "";
			String[] pathNames = name.split("-");

			for (int i = 0; i < pathNames.length; i++) {

				definePath = sb.append(pathNames[i]).append("/").toString();

			}

			String filePath = rootPath + contextName + definePath + fileName;

			File file = new File(filePath);
			if (file.getParentFile().exists() == false) {
				file.getParentFile().mkdirs();
			}
			appender.setFile(filePath);
			appender.setContext(loggerContext);
			appender.setEncoder(encoder);
			appender.start();
			// 日志追加到结尾
			appender.setAppend(true);

			result.addAppender(appender);
		}
		return result;
	}

	/**
	 * 获取应用名,按以下优先级获取 <br>
	 * 1.若配置文件定义了spring.application.name,取此值 <br>
	 * 2.若配置文件未定义spring.application.name,取发布jar名，以pom.xml中finalName为准 <br>
	 * 3.无jar包，取classpath路径下工程名
	 *
	 * @Title: getApplicationName
	 * @return: String
	 */
	public static String getApplicationName() {

		Environment env = SpringContextHolder.getBean(Environment.class);
		String applicationName = env.getProperty(SPRING_APPLICATION_NAME);
		if (StringUtils.isBlank(applicationName)) {
			File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
			String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
			if (contextPath.length > 1) {
				applicationName = contextPath[contextPath.length - 1];
				if (applicationName.contains(JAR_SUFFIX)) {
					applicationName = applicationName.substring(0, applicationName.indexOf(JAR_SUFFIX));
				}
			}
		}
		return applicationName;
	}

	/**
	 * 从缓存中获取业务配置
	 *
	 * @Title: getProperties
	 * @param type
	 *            类型
	 * @param key
	 * @return String
	 */
	public static String getProperties(String type, String key) {
		try {
			RedisUtil ru = SpringContextHolder.getBean(RedisUtil.class);
			return ru.get(getApplicationName() + REDIS_KEY_SEPARATOR + type + REDIS_KEY_SEPARATOR + key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 从缓存中获取业务配置
	 *
	 * @Title: getProperties
	 * @param type
	 * @param key
	 * @param defaultValue
	 * @return
	 * @return: String
	 */
	public static String getProperties(String type, String key, String defaultValue) {
		String value = getProperties(type, key);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 获取当前登录对象 TbManager
	 * 
	 * @author 彭浩
	 * @date 2018/6/19 10:13
	 */
	public static TbManager getLoginUser() {
		RedisUtil ru = SpringContextHolder.getBean(RedisUtil.class);
		JwtTokenUtil jtu = SpringContextHolder.getBean(JwtTokenUtil.class);
		HttpServletRequest request = getCurrentRequest();
		if (request != null) {
			String token = request.getHeader(JwtProperties.getTokenHeader());
			if(StringUtils.isNotEmpty(token)){
				String managerId = jtu.getUsernameFromToken(token);
				return ru.get(getApplicationName() + ":manager:" + managerId, TbManager.class);
			}
		}
		return null;
	}


	/**
	 * 获取当前登录用户的项目编码
	 *
	 * @author 彭浩
	 * @date 2018/6/19 10:13
	 */
	public static List<String> getLoginUserProjectCodes() {
		RedisUtil ru = SpringContextHolder.getBean(RedisUtil.class);
		JwtTokenUtil jtu = SpringContextHolder.getBean(JwtTokenUtil.class);
		HttpServletRequest request = getCurrentRequest();
		if (request != null) {
			String token = request.getHeader(JwtProperties.getTokenHeader());
			if(StringUtils.isNotEmpty(token)){
				String managerId = jtu.getUsernameFromToken(token);
				return ru.get(getApplicationName() + ":programCodes:" + managerId, List.class);
			}
		}
		return null;
	}

	/**
	 * 获取当前登录对象名称 TbManager.name
	 *
	 * @return java.lang.String
	 * @date 2018/6/19 10:14
	 */
	public static String getLoginUserName() {
		return getLoginUser() == null ? "" : getLoginUser().getName();
	}

	/**
	 * 获取当前登录对象账号 TbManager.Account
	 *
	 * @date 2018/6/19 10:15
	 * @return java.lang.String
	 */
	public static String getLoginUserAccount() {
		return getLoginUser() == null ? "" : getLoginUser().getAccount();
	}

	/**
	 * 获取当前登录对象id TbManager.Id
	 *
	 * @date 2018/6/19 10:15
	 * @return java.lang.String
	 */
	public static String getLoginUserId() {
		return getLoginUser() == null ? "" : getLoginUser().getId();
	}

	/**
	 * 获取随机唯一ID
	 */
	public final static String getUUID() {
		return UUID.randomUUID().toString();
	}

	public static HttpServletRequest getCurrentRequest() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		return attributes == null ? null : ((ServletRequestAttributes) attributes).getRequest();
	}

	public static List<ZTreeNode> buildZTreeNodeByRecursive(List<ZTreeNode> source, List<ZTreeNode> target,
			Predicate<ZTreeNode> predicate) {
		List<ZTreeNode> list = source.parallelStream().filter(predicate).reduce(target, (u, t) -> {
			t.setChildren(
					buildZTreeNodeByRecursive(source, new ArrayList<>(), e -> Objects.equals(t.getId(), e.getPid())));
			u.add(t);
			return u;
		}, (u, t) -> u);
		return list.isEmpty() ? null : list;
	}


	/**
	 * 使用递归方法建树
	 * @param menulist
	 * @return
	 */
	public static List<ZTreeNode> buildByRecursive(List<ZTreeNode> menulist) {
		List<ZTreeNode> trees = new ArrayList<ZTreeNode>();
		for (ZTreeNode menuPoiEntity : menulist) {
			if (menuPoiEntity.getId() == null) {
				trees.add(findChildren(menuPoiEntity,menulist));
			}
		}
		return trees;
	}
	/**
	 * 递归查找子节点
	 * @param menulist
	 * @return
	 */
	public static ZTreeNode findChildren(ZTreeNode menuPoiEntity, List<ZTreeNode> menulist) {
		for (ZTreeNode menu : menulist) {
			if(menuPoiEntity.getId().equals(menu.getPid())) {
				if (menuPoiEntity.getChildren() == null) {
					menuPoiEntity.setChildren(new ArrayList<ZTreeNode>());
				}
				menuPoiEntity.getChildren().add(findChildren(menu,menulist));
			}
		}
		return menuPoiEntity;
	}

}
