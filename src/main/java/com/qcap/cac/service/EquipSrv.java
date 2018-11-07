package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;

import javax.validation.Valid;
import java.util.Map;

public interface EquipSrv {
    Map<String,String> insertParts(@Valid PartsInsertDto equipInsertParam);

    void listPartsByEquipId(IPage<Map<String, Object>> page, String equipId);

    void insertEquip(@Valid EquipInsertDto equipInsertDto);

    void listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto);

    Map<String,String> updateParts(@Valid PartsInsertDto partsInsertParam);

    void deletePartsByPartsId(String partsId);

    void deletePartsByEquipId(String equipId);

    void updateEquip(@Valid EquipInsertDto equipInsertDto);

    void updatePartsAndMaintTime(@Valid PartsInsertDto partsInsertDto);

    void insertPartsAndMaintTime(@Valid PartsInsertDto partsInsertDto);

    void deleteEquipByEquipId(String equipId);

    void getEquipOperateRecordByEquipId(IPage<Map<String, Object>> page,String equipId);

    void tempEquipNoticeJob();
}
