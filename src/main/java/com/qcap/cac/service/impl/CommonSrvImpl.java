package com.qcap.cac.service.impl;

import static com.qcap.core.utils.AppUtils.buildZTreeNodeByRecursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.CommonMapper;
import com.qcap.cac.entity.TbSysFile;
import com.qcap.cac.service.CommonSrv;
import com.qcap.core.model.ZTreeNode;

@Service
@Transactional
public class CommonSrvImpl implements CommonSrv {

	@Resource
	private CommonMapper commonMapper;

	@Override
	public List<Map<String, String>> getEquipNameByEquipType(String equipType) {
		return this.commonMapper.getEquipNameByEquipType(equipType);
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
}
