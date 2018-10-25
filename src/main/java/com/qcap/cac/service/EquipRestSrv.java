package com.qcap.cac.service;

import com.qcap.cac.dto.EquipListResp;
import com.qcap.cac.dto.UpdateEquipStatusReq;
import com.qcap.cac.dto.UpdateStopEquipStatusReq;
import com.qcap.cac.dto.UpdateUsingEquipStatusReq;
import com.qcap.core.model.ResParams;

import java.util.List;
import java.util.Map;

public interface EquipRestSrv {
    List<EquipListResp> getEquipList(String employeeNo);

    List<EquipListResp> getUnrevertEquipList(String employeeNo);

    Map<String,Object> getEquipStatus(String equipNo);

    ResParams updateStopEquipStatus(UpdateStopEquipStatusReq updateStopEquipStatusReq);

    void updateUsingEquipStatus(UpdateUsingEquipStatusReq updateUsingEquipStatusReq);
}
