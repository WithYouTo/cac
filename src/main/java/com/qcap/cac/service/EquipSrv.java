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

    Map<String,String> insertEquip(@Valid EquipInsertDto equipInsertDto);

    void listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto);
}
