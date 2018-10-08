package com.whxx.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.entity.TbRole;
import com.whxx.core.model.ZTreeNode;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author PH
 * @since 2018-07-31
 */
public interface ITbRoleService
{
    void getRoleList(IPage<TbRole> page, String roleName);

    /**
     * 新增角色
     *
     * @param tbRole 角色对象
     * @throws Exception 检查型异常
     */
    void insertRole(TbRole tbRole) throws Exception;

    /**
     * 更新角色
     *
     * @param tbRole 角色对象
     * @throws Exception 检查型异常
     */
    void updateRole(TbRole tbRole) throws Exception;

    /**
     * 删除角色
     *
     * @param roleId 角色Id
     */
    void deleteRoleById(String roleId);

    /**
     * 为角色配置菜单
     *
     * @param roleId  角色Id
     * @param menuIds 菜单Id
     */
    void assignMenuForRole(String roleId, String menuIds);

    /**
     * 根据用户查询角色生成树
     *
     * @param managerId 用户Id
     * @return List
     */
    List<ZTreeNode> getRoleTreeListByManagerId(String managerId);
}
