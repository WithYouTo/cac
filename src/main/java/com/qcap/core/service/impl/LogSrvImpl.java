package com.qcap.core.service.impl;

import com.qcap.core.dao.OperationLogMapper;
import com.qcap.core.model.OperationLog;
import com.qcap.core.service.LogSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogSrvImpl implements LogSrv {

    @Autowired
    private OperationLogMapper operationLogMapper;

    @Override
    public List<Map<String, Object>> getOperationLogs(String beginTime, String endTime, String logName, String s) {
        return operationLogMapper.getOperationLogs(beginTime,endTime,logName,s);
    }

    @Override
    public OperationLog selectById(String id) {
        return operationLogMapper.selectById(id);
    }

    @Override
    public void delete() {
        operationLogMapper.delete();
    }
}
