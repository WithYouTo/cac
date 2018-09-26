package com.qcap.cac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 调用中间层接口业务日志注解
 * @ClassName: FaMeBussinessLog 
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年9月8日 上午10:01:02
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface FaMeBussinessLog {
	  String description()  default "";    
}
