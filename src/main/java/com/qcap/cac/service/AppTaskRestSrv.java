package com.qcap.cac.service;

import com.qcap.cac.dto.*;
import com.qcap.core.model.ResParams;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface AppTaskRestSrv {

    Map<String, Object> queryTaskItem(String employeeCode);

    List<Map<String, Object>> queryHistoryTask(AppTaskRestReq appTaskRestDto) throws IllegalAccessException, InvocationTargetException;

    List<Map<String, Object>> queryUnfinishTask(AppTaskRestReq appTaskRestDto);

    Map<String, Object> queryTaskDetail(String taskCode);

    List<Map<String, Object>> queryFinishAndCheckTask(AppTaskRestReq appTaskRestReq);

    Map<String, Object> selectStandardDetailInfo (String standardDetailId);

    void workingTask(AppTaskRestWorkingTaskDto appTaskRestWorkingTaskDto);

    void finishTask(List<MultipartFile>list,AppTaskUpdateReq appTaskUpdateReq) throws IOException;

    //----------------检查人员接口
    Map<String, Object> queryCheckTaskItem (String employeeCode);

    List<Map<String, Object>> queryCheckTask(AppTaskCheckRestReq appTaskCheckRestReq);

    void checkTask(List<MultipartFile>list, AppTaskUpdateReq appTaskUpdateReq) throws IOException;

    List<Map<String,Object>> listTempTask(AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq);

    Map<String,Object> selectDefaultEmployee(String startTime,String areaCode);

    void addTempTask (List<MultipartFile>list,AppTaskAddRestReq appTaskAddRestReq) throws IOException, InvocationTargetException, IllegalAccessException;

    //--------------调班接口
    List<Map<String,Object>> queryPosition(AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq);

    Map<String,Object> selectShiftTime (AppTaskQueryArrangeRestReq appTaskQueryArrangeRestReq);

    List<Map<String,Object>> selectArrangeShiftHistory (AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq);

    void changeShift (AppTaskArrangeShiftRestReq appTaskArrangeShiftRestReq) throws InvocationTargetException, IllegalAccessException;
    
    Object selectIfTaskExist (AppTaskCheckTaskRestReq appTaskCheckTaskRestReq);
    
    Object delaySpecialTask(String taskCode) ;

}
