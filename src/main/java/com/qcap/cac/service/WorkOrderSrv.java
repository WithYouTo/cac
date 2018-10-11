package com.qcap.cac.service;

import com.qcap.cac.dto.WorkOrderSearchParam;

import java.util.List;
import java.util.Map;

public interface WorkOrderSrv {
    List<Map<String, Object>> listWorkOrder(WorkOrderSearchParam workOrderSearchParam);
}
