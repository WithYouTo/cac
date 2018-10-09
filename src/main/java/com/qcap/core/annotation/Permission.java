package com.qcap.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @Description: 权限注解 用于检查权限
 * @ClassName: Permission
 * @Package: com.whxx.erp.annotation
 * @author Capricornus
 * @date 2017/12/25 15:59
 */

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Permission {
	String[] value() default {};
}
