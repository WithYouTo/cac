package com.qcap.core.service;


import com.qcap.core.model.Menu;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.poiEntity.MenuPoiEntity;
import com.qcap.core.tips.Tip;

import java.util.List;
import java.util.Map;

public interface MenuSrv {
    List<Map<String,Object>> listMenu(Menu menu);


    List<ZTreeNode> menuTreeList();

//    Menu selectById(int code);

    void insert(Menu menu);

    String checkCode(String code);

    void delMenuContainSubMenus(String menuId);

    Tip updateById(Menu menu);

    List<Long> getMenuIdsByRoleNum(String id);

    List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds);

    Menu selectByCode(String code);

    Map<String,Object> selectMapById(String id);

    Menu selectPmenu(Menu temp);

    Tip importExcel(List<MenuPoiEntity> menuList);

    List<MenuPoiEntity> exportMenu();

    Tip upSeq(String menuId);

    Tip downSeq(String menuId);
}
