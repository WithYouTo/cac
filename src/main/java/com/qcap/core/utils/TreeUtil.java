package com.qcap.core.utils;

import com.qcap.core.model.MenuTree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    /**
     * 使用递归方法建树
     * @param menulist
     * @return
     */
    private static List<MenuTree> buildByRecursive(List<MenuTree> menulist) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        for (MenuTree menuTree : menulist) {
            if (menuTree.getPcode().equals("1")) {
                trees.add(findChildren(menuTree,menulist));
            }
        }
        return trees;
    }


    /**
     * 递归查找子节点
     * @param menulist
     * @return
     */
    private static MenuTree findChildren(MenuTree menuTree, List<MenuTree> menulist) {
        for (MenuTree menu : menulist) {
            if(menuTree.getCode().equals(menu.getPcode())) {
                if (menuTree.getList() == null) {
                    menuTree.setList(new ArrayList<MenuTree>());
                }
                menuTree.getList().add(findChildren(menu,menulist));
            }
        }
        return menuTree;
    }

}
