package com.qcap.cac.tools;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;

import com.qcap.core.utils.AppUtils;

public class EntityTools {

	public static <T> T setCreateEmpAndTime(T t) {
		if (null == t) {
			return t;
		}
		try {
			PropertyUtils.setProperty(t, "createEmp", AppUtils.getLoginUserAccount());
			PropertyUtils.setProperty(t, "createDate", new Date());
			PropertyUtils.setProperty(t, "version", 0);
		} catch (Exception e) {
		}
		return t;
	}

	public static <T> T setUpdateEmpAndTime(T t) {
		if (null == t) {
			return t;
		}
		try {
			PropertyUtils.setProperty(t, "updateEmp", AppUtils.getLoginUserAccount());
			PropertyUtils.setProperty(t, "updateDate", new Date());
		} catch (Exception e) {
		}
		return t;
	}
}
