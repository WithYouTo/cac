package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbSysFile;
import com.qcap.core.entity.TbManager;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.entity.TbUserInfo;

@Repository
public interface CommonMapper extends BaseMapper<TbManager> {

	List<Map<String, String>> getEquipNameByEquipType(@Param("equipType") String equipType);

	List<Map<String, String>> getPartsNameByEquipNo(@Param("equipNo") String equipNo);

	List<Map<String, Object>> getAreaNameByAreaCode(String areaCode);

	List<Map<String, Object>> getStandardNameByStandardCode(String standardCode);

	List<Map<String, String>> getEquipTypeList();

	List<Map<String, Object>> getAreaTypeByAreaCode(String areaCode);

	List<Map<String, Object>> selectAreaItem();

	List<Map<String, String>> selectStandardNameList();

	void insertSysFile(TbSysFile sysFile);

	List<Map<String, String>> getListByCode(@Param("code") String code);

	List<TbOrg> getOrgByWorkNo(String workNo);

	List<TbUserInfo> getUserInfoByWorkNo(String workNo);

    List<Map<String,String>> getProgramCodes(@Param("programCodes") List<String> programCodes);
}
