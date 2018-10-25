package com.qcap.cac.dao;

import com.qcap.cac.dto.EquipListResp;
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

    List<EquipListResp> getUnrevertEquipList(@Param("employeeCode")String employeeCode);

    Map<String,Object> getEquipStatus(@Param("equipNo")String equipNo);
}
