package com.qcap.cac.dao;

import com.qcap.cac.dto.EquipListReq;
import com.qcap.cac.dto.EquipListResp;
import com.qcap.cac.dto.GetEquipStatusResp;
import com.qcap.cac.dto.ListUnrevertEquipResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipRestMapper {
    List<EquipListResp> getEquipList(String employeeNo);

    void getWorkOrder(@Param("map") Map<String, Object> map);

    Map<String,String> getShiftTimeByShift(@Param("shift") String shift);

    List<ListUnrevertEquipResp> getUnrevertEquipList(@Param("employeeCode")String employeeCode);

    GetEquipStatusResp getEquipStatus(@Param("equipNo")String equipNo);

    List<EquipListResp> getEquipTypeList(EquipListReq equipListReq);
}
