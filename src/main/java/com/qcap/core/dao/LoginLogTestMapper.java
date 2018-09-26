package com.qcap.core.dao;

import java.util.List;
import java.util.Map;

public interface LoginLogTestMapper {


	List<Map<String, Object>> getLoginLogs(Map params);

	int delLoginLog(String id);
}
