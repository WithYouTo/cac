package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

public interface CommonSrv {
	List<Map<String, String>> getEquipNameByEquipType(String equipType);

	List<Map<String, String>> getPartsNameByEquipNo(String equipNo);

	String getAreaNameByAreaCode(String areaCode);

	String getStandardNameByStandardCode(String standardCode);
}
