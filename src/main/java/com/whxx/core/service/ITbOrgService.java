package com.whxx.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.entity.TbOrg;
import com.whxx.core.model.ZTreeNode;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public interface ITbOrgService
{
    void getOrgList(IPage<TbOrg> page, TbOrg org);

    List<ZTreeNode> getOrgTreeList();

    List<ZTreeNode> getOrgTreeListByManagerId(String managerId);

    void insertItem(TbOrg org) throws Exception;

    void updateItem(TbOrg org) throws Exception;

    void deleteItemById(String id) throws Exception;

    String upSeq(String orgId);

    String downSeq(String orgId);
}
