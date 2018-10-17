package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface EquipSrv {
    Map<String,String> insertParts(@Valid PartsInsertDto equipInsertParam);

    void listPartsByEquipId(IPage<Map<String, Object>> page, String equipId);

    void insertEquip(@Valid EquipInsertDto equipInsertDto) throws Exception;

    void listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto);

    Map<String,String> updateParts(@Valid PartsInsertDto partsInsertParam);

    void deletePartsByPartsId(String partsId);

    void deletePartsByEquipId(String equipId);
}
