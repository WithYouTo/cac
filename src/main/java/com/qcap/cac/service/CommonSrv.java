package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.entity.TbSysFile;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.entity.TbUserInfo;
import com.qcap.core.model.ZTreeNode;

public interface CommonSrv {
	List<Map<String, String>> getEquipNameByEquipType(String equipType);

	List<Map<String, String>> getPartsNameByEquipNo(String equipNo);

	String getAreaNameByAreaCode(String areaCode);

	String getStandardNameByStandardCode(String standardCode);

	List<Map<String, String>> getEquipTypeList();

	String getAreaTypeByAreaCode(String areaCode);

	List<ZTreeNode> selectAreaItem();

	List<Map<String, String>> selectStandardNameList();

	void insertSysFile(TbSysFile sysFile);

	List<Map<String, String>> getListByCode(String initCardTypeSelect);

	TbOrg getOrgByWorkNo(String workNo);

	TbUserInfo getUserInfoByWorkNo(String workNo);
}
