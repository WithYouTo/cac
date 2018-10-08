package com.whxx.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.entity.TbMenu;
import com.whxx.core.model.MenuTree;
import com.whxx.core.model.ZTreeNode;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author PH
 * @since 2018-07-30
 */
public interface ITbMenuService
{
    List<MenuTree> getMenuTreeListFromToken(String token);

    /**
     * 分页查询
     *
     * @param menu 参数对象
     * @param page 分页对象
     * @return 分页对象
     */
    IPage<TbMenu> getMenuList(TbMenu menu, IPage<TbMenu> page);

    /**
     * 获取zTreeList
     *
     * @return List
     */
    List<ZTreeNode> getMenuTreeList();

    /**
     * 根据角色获取菜单树
     *
     * @param roleId 角色ID
     * @return List
     */
    List<ZTreeNode> getMenuTreeListByRoleId(String roleId);

    /**
     * 同级菜单上移
     *
     * @param menuId 菜单ID
     * @return ResParams
     */
    String upSeq(String menuId);

    /**
     * 同级菜单下移移
     *
     * @param menuId 菜单ID
     * @return ResParams
     */
    String downSeq(String menuId);

    /**
     * 插入菜单
     *
     * @param tbMenu 菜单对象
     */
    void insertItem(TbMenu tbMenu);

    /**
     * 更新菜单
     *
     * @param tbMenu 菜单对象
     */
    void updateItem(TbMenu tbMenu);


    /**
     * 批量删除菜单和子菜单
     *
     * @param tbMenuId 主键id
     */
    void deleteMenuAndSubMenu(String tbMenuId);
}
