package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.CommonMapper;
import com.qcap.cac.entity.TbSysFile;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.entity.TbUserInfo;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

import static com.qcap.core.utils.AppUtils.buildZTreeNodeByRecursive;

@Service
@Transactional
public class CommonSrvImpl implements CommonSrv {

	@Resource
	private CommonMapper commonMapper;
	
    @Resource
    private FastDFSClientWrapper dfsClient;
	@Override
	public List<Map<String, String>> getEquipNameByEquipType(String equipType,String programCode) {
		return this.commonMapper.getEquipNameByEquipType(equipType,programCode);
	}

	@Override
	public List<Map<String, String>> getPartsNameByEquipNo(String equipNo) {
		return this.commonMapper.getPartsNameByEquipNo(equipNo);
	}

	@Override
	public String getAreaNameByAreaCode(String areaCode) {
		List<Map<String, Object>> ls = commonMapper.getAreaNameByAreaCode(areaCode);
		if (CollectionUtils.isNotEmpty(ls)) {
			return Objects.toString(ls.get(0).get("areaName"));
		}
		return null;
	}

	@Override
	public String getStandardNameByStandardCode(String standardCode) {
		List<Map<String, Object>> ls = commonMapper.getStandardNameByStandardCode(standardCode);
		if (CollectionUtils.isNotEmpty(ls)) {
			return Objects.toString(ls.get(0).get("standardName"));
		}
		return null;
	}

	@Override
	public List<Map<String, String>> getEquipTypeList() {
		return this.commonMapper.getEquipTypeList();
	}

	@Override
	public String getAreaTypeByAreaCode(String areaCode) {
		List<Map<String, Object>> ls = commonMapper.getAreaTypeByAreaCode(areaCode);
		if (CollectionUtils.isNotEmpty(ls)) {
			return Objects.toString(ls.get(0).get("areaType"));
		}
		return null;
	}

	@Override
	public List<ZTreeNode> selectAreaItem() {
		List<ZTreeNode> ls = new ArrayList<>();
		List<Map<String, Object>> list = this.commonMapper.selectAreaItem();
		if (CollectionUtils.isNotEmpty(list)) {
			for (Map<String, Object> map : list) {
				ZTreeNode zTreeNode = new ZTreeNode();
				zTreeNode.setId(Objects.toString(map.get("id")));
				zTreeNode.setName(Objects.toString(map.get("name")));
				zTreeNode.setPid(Objects.toString(map.get("pId")));
				zTreeNode.setDesc1(Objects.toString(map.get("areaType")));
				if ("0".equals(map.get("pId"))) {
					zTreeNode.setOpen("true");
				} else {
					zTreeNode.setOpen("false");
				}
				ls.add(zTreeNode);
			}
		}
		ls.add(ZTreeNode.createParent());
		return buildZTreeNodeByRecursive(ls, new ArrayList<>(), e -> Objects.equals("0", e.getPid()));
	}

	@Override
	public List<Map<String, String>> selectStandardNameList() {
		return this.commonMapper.selectStandardNameList();
	}

	@Override
	public void insertSysFile(TbSysFile sysFile) {
		// TODO Auto-generated method stub
		this.commonMapper.insertSysFile(sysFile);
	}

	@Override
	public List<Map<String, String>> getListByCode(String str) {
		return this.commonMapper.getListByCode(str);
	}

	@Override
	public TbOrg getOrgByWorkNo(String workNo) {
		List<TbOrg> ls = this.commonMapper.getOrgByWorkNo(workNo);
		if (CollectionUtils.isNotEmpty(ls)) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public TbUserInfo getUserInfoByWorkNo(String workNo) {
		List<TbUserInfo> ls = this.commonMapper.getUserInfoByWorkNo(workNo);
		if (CollectionUtils.isNotEmpty(ls)) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, String>> getProgramCodes(List<String> programCodes) {
		List<Map<String, String>> ls = this.commonMapper.getProgramCodes(programCodes);
		return ls;
	}

	@Override
	public List<Map<String, String>> getAllProgramCodes() {
		List<Map<String, String>> ls = this.commonMapper.getAllProgramCodes();
		return ls;
	}

	@Override
	public Object uploadAndSaveFile(MultipartFile file) throws IOException {
    	//查询配置管理中存放的文件访问地址前缀
        String configValue = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        String path = dfsClient.uploadFile(file);
        TbSysFile sysFile = new TbSysFile();
        String fileId = UUIDUtils.getUUID();
        String fileName = file.getOriginalFilename();
        sysFile.setFileId(fileId);
        sysFile.setGroupId("nullValue");
        sysFile.setFileName(fileName);
        sysFile.setFileType(fileName.substring(fileName.indexOf(".")));
        sysFile.setStoreUrl(path);
        sysFile.setDownloadUrl(configValue+path);
        EntityTools.setCreateEmpAndTime(sysFile);
        this.insertSysFile(sysFile);
        Map<String, String> result = new HashMap<>();
        result.put("fileId", fileId);
        result.put("path", path);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPLOAD_DESC, result);
	}
	
	@Override
	public void deleteSavedFile(String url) {
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(url) && url.indexOf(",") != -1) {
			String [] arr = url.split(",");
			for(String str: arr) {
				dfsClient.deleteFile(str);
			}
		}else {
			dfsClient.deleteFile(url);
		}
	}

	@Override
	public void updateFileGroupId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		commonMapper.updateFileGroupId(map);
	}

	@Override
	public List<Map<String, String>> selectSysFileByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return this.commonMapper.selectSysFileByGroupId(groupId);
	}

	@Override
	public void deleteSysFileByGroupId(String groupId) {
		// TODO Auto-generated method stub
		this.commonMapper.deleteSysFileByGroupId(groupId);
	}

	@Override
	public void deleteSysFileByKey(String fileId) {
		// TODO Auto-generated method stub
		this.commonMapper.deleteSysFileByKey(fileId);
	}
}
