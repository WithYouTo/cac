package com.qcap.cac.service;

import com.qcap.cac.dto.*;
import com.qcap.core.model.ResParams;

import java.util.List;
import java.util.Map;

public interface EquipRestSrv {
    List<EquipListResp> getEquipList(EquipListReq equipListReq);

    List<ListUnrevertEquipResp> getUnrevertEquipList(String employeeNo);

    GetEquipStatusResp getEquipStatus(String equipNo);

    ResParams updateStopEquipStatus(UpdateStopEquipStatusReq updateStopEquipStatusReq);

    void updateUsingEquipStatus(UpdateUsingEquipStatusReq updateUsingEquipStatusReq);

    void updateEquipStatusInManagerMode(UpdateUsingEquipStatusReq updateUsingEquipStatusReq);
}
