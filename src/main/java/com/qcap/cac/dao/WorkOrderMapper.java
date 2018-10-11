package com.qcap.cac.dao;

import com.qcap.cac.dto.WorkOrderSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface WorkOrderMapper {

    List<Map<String, Object>> listWorkOrder(WorkOrderSearchParam workOrderSearchParam);
}
