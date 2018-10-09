package com.qcap.core.log;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.qcap.core.utils.SpringContextHolder;

/**
 * 
 * @Description: 被修改的bean临时存放的地方
 *
 * @ClassName: LogObjectHolder
 * 
 * @author huangxiang
 * @date 2017/12/26 10:20
 */
@Component
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION)
public class LogObjectHolder implements Serializable {

	private Object object = null;

	public void set(Object obj) {
		this.object = obj;
	}

	public Object get() {
		return object;
	}

	public static LogObjectHolder me() {
		LogObjectHolder bean = SpringContextHolder.getBean(LogObjectHolder.class);
		return bean;
	}
}
