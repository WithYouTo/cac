package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.entity.TbEquip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipMapper extends BaseMapper<TbEquip>{

    List<Map<String,Object>> listEquip(IPage<Map<String, Object>> page, @Param("obj")@Valid EquipSearchDto equipSearchDto);

    int getMaxEquipNumByEquipType(@Param("equipType") String equipType,@Param("programCode") String programCode);

    TbEquip selectEquipByEquipId(String equipId);

    TbEquip selectEquipByEquipNo(@Param("equipNo")String equipNo);

    void updateEquip(TbEquip equip);

    void deleteEquipByEquipId(@Param("equipId")String equipId);

    void updateEquipWorkStatusByEquipNoAndStatus(@Param("equipNo")String equipNo, @Param("status")String status,@Param("employeeCode")String employeeCode);

    void updateEquipStatusAndWorkStatusByEquipNoAndStatus(@Param("equipNo")String equipNo,  @Param("status")String status, @Param("workStatus")String workStatus,@Param("employeeCode")String employeeCode);

    List<Map<String,Object>> getEquipOperateRecordByEquipId(IPage<Map<String, Object>> page, @Param("equipId")String equipId);

    List<TbEquip> selectEquipByNoticeDate(@Param("noticeDate")String noticeDate);
}