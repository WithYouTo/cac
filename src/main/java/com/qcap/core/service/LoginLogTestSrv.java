package com.qcap.core.service;

import java.util.List;
import java.util.Map;

public interface LoginLogTestSrv {  



	List<Map<String, Object>> getLoginLogs(Map params);

    int delLoginLog(String[] idStr);
}
