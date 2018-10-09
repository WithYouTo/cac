package com.qcap.core.dict.factory;

import java.lang.reflect.Method;

import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.ConstantFactory;
import com.qcap.core.factory.IConstantFactory;

/**
 * 字段的包装创建工厂
 *
 * @author fengshuonan
 * @date 2017-05-06 15:12
 */
public class DictFieldWarpperFactory {

	public static Object createFieldWarpper(Object field, String methodName) {
		IConstantFactory me = ConstantFactory.me();
		try {
			Method method = IConstantFactory.class.getMethod(methodName, field.getClass());
			Object result = method.invoke(me, field);
			return result;
		} catch (Exception e) {
			// e.printStackTrace();
			try {
				Method method = IConstantFactory.class.getMethod(methodName, Integer.class);
				Object result = method.invoke(me, Integer.parseInt(field.toString()));
				return result;
			} catch (Exception e1) {
				throw new BussinessException(BizExceptionEnum.ERROR_WRAPPER_FIELD);
			}
		}
	}

}
