/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: AppUtils.java 
 * @Prject: whxx-auth
 * @Package: com.whxx.auth.utils
 * @author: 彭浩
 * @date: 2018年5月9日 下午2:32:26 
 * @version: V1.0   
 */
package com.qcap.core.utils;

import java.io.File;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.qcap.core.kit.StrKit;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;

/** 
 * AppUtils工具类
 * @ClassName: AppUtils
 * @author: 彭浩
 * @date: 2018年5月9日 下午2:32:26  
 */
public final class AppUtils {
	
    /**
     * spring.application.name
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
     * @param clazz 目标类
     * @param isWriteToLogFile 是否写入到文件
     * @return org.slf4j.Logger
     */
    public static org.slf4j.Logger getLogger(Class<?> clazz, boolean isWriteToLogFile) {

        Logger result = (Logger)LoggerFactory.getLogger(clazz);
        result.detachAndStopAllAppenders();
        
        
           
        if (isWriteToLogFile) {
            LoggerContext loggerContext = result.getLoggerContext();

            // we are not interested in auto-configuration
            //loggerContext.reset();

            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(loggerContext);
            encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p [%C %M %L] - %m%n");
            encoder.start();

            FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
            String fileName = clazz.getSimpleName() + "_" + DateUtil.format(new Date(),"yyyy-MM-dd") + ".log";
            
            /*File classPath = new File(clazz.getClassLoader().getResource("").getPath());
            
            System.out.println("----------------------------------");
            System.out.println(classPath.getParentFile().getPath());
            System.out.println("----------------------------------");*/
            
            /*String filePath = classPath.getParentFile().getPath() + "/log/" + clazz.getSimpleName() + "/" + fileName;*/
            
            
            String os = System.getProperty("os.name");
            String rootPath =  "";
            String contextName =  "/";
    		if(os.toLowerCase().startsWith("win")){ 
    			File classPath = new File(clazz.getClassLoader().getResource("").getPath());
                String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
                /*System.out.println("***********");
                System.out.println(contextPath);
                System.out.println("***********");*/
    			rootPath = "c:/whxxlogs";
    			if(contextPath.length>1){
    			contextName = "/"+contextPath[contextPath.length-1] + "/";
    			}
    		}
    		else
    		{
    			
    			File classPath = new File(clazz.getClassLoader().getResource("").getPath());
    			String[] contextPath = classPath.getParentFile().getParent().split("/");
    			/*System.out.println("***********");
                System.out.println(contextPath);
                System.out.println("***********");*/
    			rootPath = "/usr/local/whxxlogs";
    			if(contextPath.length>1){
    			contextName = "/" + contextPath[contextPath.length-1] + "/";
        		}
    			
    			
    		}
    		/* String filePath = rootPath +contextName + clazz.getSimpleName() + "/" + fileName;*/
    	    
    		StringBuffer sb = new StringBuffer();
    		String filePath = sb.append(rootPath).append(contextName)
    				            .append(clazz.getSimpleName()).append("/").append(fileName).toString();
            
//    		System.out.println("----------------------------------");
//    		System.out.println(filePath);
//    		System.out.println("----------------------------------");
            
            File file = new File(filePath);
            if (file.getParentFile().exists() == false){
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
     * @param name 指定业务名称
     * @param isWriteToLogFile 是否写入到文件
     * @return org.slf4j.Logger
     */
    public static org.slf4j.Logger getLogger(String name, boolean isWriteToLogFile) {

    	 Logger result = (Logger)LoggerFactory.getLogger(name);
    	 result.detachAndStopAllAppenders();
    	
        if (isWriteToLogFile) {
            //LoggerContext loggerContext = result.getLoggerContext();
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            
            //若重置，日志不会出现在控制台中，
            //loggerContext.reset();

            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(loggerContext);
            encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p [%C %M %L] - %m%n");
            encoder.start();

            FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
            String fileName = name + "_" + DateUtil.format(new Date(),"yyyy-MM-dd") + ".log";
            
            /*File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
            String filePath = classPath.getParentFile().getPath() + "/log/" + name + "/" + fileName;*/
            
           /* String os = System.getProperty("os.name");
            String rootPath =  null;
    		if(os.toLowerCase().startsWith("win")){ 
    		
    			rootPath = "c:/whxxlogs/";
    		}
    		else
    		{
    			rootPath = "/usr/local/whxxlogs/";
    		}
    		 String filePath = rootPath + name + "/" + fileName;*/
            
            
            
            String os = System.getProperty("os.name");
            String rootPath = "";
            String contextName =  "/";
    		if(os.toLowerCase().startsWith("win")){ 
    			File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
                String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
                
    			rootPath = "c:/whxxlogs/"; 
    			if(contextPath.length>1){
    				String tmp = contextPath[contextPath.length-1];
    				//whxx-auth截取
        			//contextName = "/" + tmp.substring(0, tmp.lastIndexOf("-")) + "/";
    				//whhotel
    				contextName = "/" + tmp + "/";
            	}
    		}
    		else
    		{
    			File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
                String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
    			rootPath = "/usr/local/whxxlogs/";
    			if(contextPath.length>1){
    				String tmp = contextPath[contextPath.length-1];
        			//contextName = "/" + tmp.substring(0, tmp.lastIndexOf("-")) + "/";
        			contextName = "/" + tmp + "/";
            	}
    		}
    		
    		StringBuffer sb = new StringBuffer();
    		String definePath = "";
    		String[] pathNames =  name.split("-");
    		
    		for(int i=0; i<pathNames.length; i++) {
    			
    			definePath = sb.append(pathNames[i]).append("/").toString();
    			 
    		}
    		
    		String filePath = rootPath + contextName + definePath + fileName;
            
            
            File file = new File(filePath);
            if (file.getParentFile().exists() == false){
                file.getParentFile().mkdirs();
            }
            appender.setFile(filePath);
            appender.setContext(loggerContext);
            appender.setEncoder(encoder);
            appender.start();
            appender.setAppend(true);//日志追加到结尾
            
            result.addAppender(appender);
        }
        return result;
    }
    
    public static org.slf4j.Logger getErrorLogger(String name, boolean isWriteToLogFile) {
    	
    	Logger result = (Logger)LoggerFactory.getLogger(name);
    	result.detachAndStopAllAppenders();
    	result.setLevel(Level.ERROR);
    	
        if (isWriteToLogFile) {
            
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            


            PatternLayoutEncoder encoder = new PatternLayoutEncoder();
            encoder.setContext(loggerContext);
            encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p [%C %M %L] - %m%n");
            encoder.start();

            FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
            String fileName = name + "_" + DateUtil.format(new Date(),"yyyy-MM-dd") + ".log";
            
            String os = System.getProperty("os.name");
            String rootPath = "";
            String contextName =  "/";
    		if(os.toLowerCase().startsWith("win")){ 
    			File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
                String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
                
    			rootPath = "c:/whxxlogs/"; 
    			if(contextPath.length>1){
    				String tmp = contextPath[contextPath.length-1];
        			contextName = "/" + tmp.substring(0, tmp.lastIndexOf("-")) + "/";
            	}
    		}
    		else
    		{
    			File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
                String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
    			rootPath = "/usr/local/whxxlogs/";
    			if(contextPath.length>1){
    				String tmp = contextPath[contextPath.length-1];
        			contextName = "/" + tmp.substring(0, tmp.lastIndexOf("-")) + "/";
            	}
    		}
    		
    		StringBuffer sb = new StringBuffer();
    		String definePath = "";
    		String[] pathNames =  name.split("-");
    		
    		for(int i=0; i<pathNames.length; i++) {
    			
    			definePath = sb.append(pathNames[i]).append("/").toString();
    			 
    		}
    		
    		String filePath = rootPath + contextName + definePath + fileName;
            
            File file = new File(filePath);
            if (file.getParentFile().exists() == false){
                file.getParentFile().mkdirs();
            }
            appender.setFile(filePath);
            appender.setContext(loggerContext);
            appender.setEncoder(encoder);
            appender.start();
            appender.setAppend(true);//日志追加到结尾
            
            result.addAppender(appender);
        }
        return result;
    }
    
    
    /** 
     * 获取应用名,按以下优先级获取
     * <br>
     * 1.若配置文件定义了spring.application.name,取此值
     * <br>
     * 2.若配置文件未定义spring.application.name,取发布jar名，以pom.xml中finalName为准
     * <br>
     * 3.无jar包，取classpath路径下工程名
     * @Title: getApplicationName
     * @return
     * @return: String
     */
    public static String getApplicationName(){
    	
    	Environment env = SpringContextHolder.getBean(Environment.class);
    	String applicationName = env.getProperty(SPRING_APPLICATION_NAME);
    	
    	if(StrKit.isBlank(applicationName)){
    		
    		File classPath = new File(AppUtils.class.getClassLoader().getResource("").getPath());
            String[] contextPath = classPath.getParentFile().getParent().split("\\\\");
    		if(contextPath.length>1){
    			applicationName = contextPath[contextPath.length-1];
    			if(applicationName.contains(JAR_SUFFIX)){
    				applicationName = applicationName.substring(0, applicationName.indexOf(".jar"));
    			}
        	}
    	}
    	
    	return applicationName;
    }
    
    
	/** 
	 * 从缓存中获取业务配置
	 * @Title: getProperties
	 * @param type 类型
	 * @param key
	 * @return
	 * @return: String
	 */
	public static String getProperties(String type, String key) {

		try {
			RedisUtil ru = SpringContextHolder.getBean(RedisUtil.class);
			return ru.get(getApplicationName() + REDIS_KEY_SEPARATOR + type + REDIS_KEY_SEPARATOR + key);
		} catch (Exception e) {
		}
		return null;

	}
    
	
	/** 
	 * 从缓存中获取业务配置
	 * @Title: getProperties
	 * @param type
	 * @param key
	 * @param defaultValue
	 * @return
	 * @return: String
	 */
	public static String getProperties(String type, String key, String defaultValue) {

		String value = getProperties(type, key);
		if (StrKit.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}

}
