package com.qcap.core.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.model.ZTreeNode;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public interface ITbOrgService {
	void getOrgList(IPage<Map<String,String>> page, TbOrg org);

	List<ZTreeNode> getOrgTreeList();

	List<ZTreeNode> getOrgTreeListByManagerId(String managerId);

	void insertItem(TbOrg org) throws Exception;

	void updateItem(TbOrg org) throws Exception;

	void deleteItemById(String id) throws Exception;

	String upSeq(String orgId);

	String downSeq(String orgId);
}
