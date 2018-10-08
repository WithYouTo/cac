/**   
 * @Title: I18nUtil.java
 * @Package: com.whxx.erp.utils
 * @author: 彭浩
 * @date: 2018年3月24日 下午12:53:05 
 * @version: V1.0   
 */
package com.whxx.core.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化消息工具类
 * 
 * @ClassName: I18nUtil
 * @author: 彭浩
 * @date: 2018年3月24日 下午4:15:08  
 */
@Component
public class I18nUtil {

	@Autowired
	MessageSource messageSource;

	/** 
	 * 获取当前语言环境下的code对应message
	 * @Title: getMessage
	 * @param code
	 * @return
	 * @return: String
	 */
	public String getMessage(String code) {
		return getMessage(code, "");
	}

	public String getMessage(String code, String defautMessage) {
		return getMessage(code, defautMessage, null);
	}

	public String getMessage(String code, String defautMessage, Object[] args) {

		Locale locale = LocaleContextHolder.getLocale();

		return messageSource.getMessage(code, args, defautMessage, locale);
	}

}
