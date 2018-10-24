package com.qcap.cac.service;

import com.qcap.cac.dto.AppTaskCheckRestReq;
import com.qcap.cac.dto.AppTaskFinishReq;
import com.qcap.cac.dto.AppTaskRestReq;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface AppTaskRestSrv {

    Map<String, Object> queryTaskItem(String employeeCode);

    List<Map<String, Object>> queryHistoryTask(AppTaskRestReq appTaskRestDto) throws IllegalAccessException, InvocationTargetException;

    List<Map<String, Object>> queryUnfinishTask(AppTaskRestReq appTaskRestDto);

    Map<String, Object> queryTaskDetail(String taskCode);

    List<Map<String, Object>> queryFinishAndCheckTask(AppTaskCheckRestReq appTaskRestCheckReq);

    Map<String, Object> selectStandardDetailInfo (String standardDetailId);

    void workingTask(String taskCode);

    void finishTask(AppTaskFinishReq appTaskFinishReq) throws InvocationTargetException, IllegalAccessException;

}
