package com.qcap.cac.service;

import com.qcap.cac.dto.EquipListResp;

import java.util.List;

public interface EquipRestSrv {
    List<EquipListResp> getEquipList(String employeeNo);
}
