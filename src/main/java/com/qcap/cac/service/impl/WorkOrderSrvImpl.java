package com.qcap.cac.service.impl;

import com.qcap.cac.dao.WorkOrderMapper;
import com.qcap.cac.dto.WorkOrderSearchParam;
import com.qcap.cac.service.WorkOrderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class WorkOrderSrvImpl implements WorkOrderSrv{

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Override
    public List<Map<String, Object>> listWorkOrder(WorkOrderSearchParam workOrderSearchParam) {
        return this.workOrderMapper.listWorkOrder(workOrderSearchParam);
    }
}
