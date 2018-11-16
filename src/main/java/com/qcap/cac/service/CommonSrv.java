package com.qcap.cac.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

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

    List<Map<String,String>> getProgramCodes(List<String> programCodes);

    List<Map<String,String>> getAllProgramCodes();
    
    Object uploadAndSaveFile(MultipartFile file) throws IOException;
    
    void deleteSavedFile(String url);
    
    void updateFileGroupId(Map<String, Object> map);
    
    List<Map<String, String>> selectSysFileByGroupId (String groupId);
    
    void deleteSysFileByGroupId (String groupId);
    
    void deleteSysFileByKey (String fileId);
}
