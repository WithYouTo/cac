package com.qcap.core.controller;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.common.MenuStatus;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.Menu;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.poiEntity.MenuPoiEntity;
import com.qcap.core.service.MenuSrv;
import com.qcap.core.tips.Tip;
import com.qcap.core.utils.CommUtil;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.core.utils.poi.PoiUtils;
import com.qcap.core.warpper.MenuWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单控制器
 *
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private static String PREFIX = "/system/menu/";

    @Resource
    MenuSrv menuSrv;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "menu";
    }

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/menu_add")
    public String menuAdd() {
        return PREFIX + "menu_add";
    }

    /**
     * 获取菜单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Menu menu) {
        new PageFactory<Menu>().defaultPage();
        List<Map<String, Object>> menus = menuSrv.listMenu(menu);

        PageInfo pageInfo = new PageInfo( (List<Menu>) new MenuWarpper(menus).warp());
        Page pageList = (Page) menus;
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, pageList.getTotal(), menus);
    }

    /**
     *
     * @Description: 获取菜单树
     *
     *
     * @MethodName: selectMenuTreeList
     * @Parameters: []
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/5/14 16:18
     */

    @RequestMapping(value = "/selectMenuTreeList")
    @ResponseBody
    public Object selectMenuTreeList(HttpServletRequest request) {
        List<ZTreeNode> roleTreeList = menuSrv.menuTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        roleTreeList =  buildByRecursive(roleTreeList);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "",  buildByRecursive(roleTreeList));
    }


    /**
     * 新增菜单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Map<String,Object> data = new HashMap<String,Object>();

        //判断是否存在该编号
        String existedMenuName = menuSrv.checkCode(menu.getCode());
        if (ToolUtil.isNotEmpty(existedMenuName)) {
            throw new BussinessException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //设置父级菜单编号
        menu.setStatus(MenuStatus.ENABLE.getCode());
        this.menuSrv.insert(menu);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, data);
    }



    /**
     * 删除菜单
     */
    @RequestMapping(value = "/remove")
    @ResponseBody
    public Object remove(@RequestParam String ids) {
        if (ToolUtil.isEmpty(ids)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        String[] idArr=ids.split(",");
        for (String id : idArr) {
            this.menuSrv.delMenuContainSubMenus(id);
        }

        Map<String,Object> data = new HashMap<String,Object>();

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, data);
    }


    /**
     * 修改菜单
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Object edit(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Map<String,Object> data = new HashMap<String,Object>();
        this.menuSrv.updateById(menu);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, data);
    }


    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/menuTreeListByRoleId", method = RequestMethod.POST)
    @ResponseBody
    public Map menuTreeListByRoleId(Model model, HttpServletRequest request, HttpServletResponse response) {
    	Map map = new HashMap<>();
    	String id = request.getParameter("id");
        List<Long> menuIds = this.menuSrv.getMenuIdsByRoleNum(id);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> roleTreeList = this.menuSrv.menuTreeList();
            roleTreeList.add(ZTreeNode.createParent());
            roleTreeList =  buildByRecursive(roleTreeList);
            map = CommUtil.setMessage(200, "", roleTreeList);
        } else {
            List<ZTreeNode> roleTreeListByUserId = this.menuSrv.menuTreeListByMenuIds(menuIds);
            roleTreeListByUserId.add(ZTreeNode.createParent());
            roleTreeListByUserId =  buildByRecursive(roleTreeListByUserId);
            map = CommUtil.setMessage(200, "", roleTreeListByUserId);
        }
        return map;
    }

    /*
   * 导入
   */
    @RequestMapping(method = RequestMethod.POST,value = "/importExcel")
    @ResponseBody
    public Tip importExcel(MultipartFile file){
        //解析excel，
        List<MenuPoiEntity> menuList = PoiUtils.importExcel(file,0,1,MenuPoiEntity.class);
        return this.menuSrv.importExcel(menuList);
    }


    /*
    * 导出
    */
    @RequestMapping("/export")
    @ResponseBody
    public void orgExport(HttpServletResponse response){
        //模拟从数据库获取需要导出的数据
        List<MenuPoiEntity> menuList = this.menuSrv.exportMenu();
        //导出
        PoiUtils.exportExcel(menuList,"菜单","菜单",MenuPoiEntity.class,"菜单.xls",response);
    }


    /*
     * 导出模版
     */
    @RequestMapping("/excelModel")
    @ResponseBody
    public void excelModel(HttpServletResponse response){
        List<MenuPoiEntity> list= new ArrayList<>();
        list.add(MenuPoiEntity.newInstance("1","","主菜单","#",1));
        list.add(MenuPoiEntity.newInstance("1100","1","一级子菜单","/xxx",1));
        PoiUtils.exportExcel(list,null,"菜单模版",MenuPoiEntity.class,"菜单模版.xls",response);
    }

    /**
     * 同级上移
     */
    @RequestMapping(value = "/upSeq")
    @ResponseBody
    public Tip upSeq(@RequestParam String menuId){
        return this.menuSrv.upSeq(menuId);
    }

    /**
     * 同级下移
     */
    @RequestMapping(value = "/downSeq")
    @ResponseBody
    public Tip downSeq(@RequestParam String menuId){
        return this.menuSrv.downSeq(menuId);
    }


    /**
     * 使用递归方法建树
     * @param menulist
     * @return
     */
    private static List<ZTreeNode> buildByRecursive(List<ZTreeNode> menulist) {
        List<ZTreeNode> trees = new ArrayList<ZTreeNode>();
        for (ZTreeNode menuTree : menulist) {
            if (menuTree.getpId().equals("top")) {
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
    private static ZTreeNode findChildren(ZTreeNode menuTree, List<ZTreeNode> menulist) {
        for (ZTreeNode menu : menulist) {
            if(menuTree.getId().equals(menu.getpId())) {
                if (menuTree.getChildren() == null) {
                    menuTree.setChildren(new ArrayList<ZTreeNode>());
                }
                menuTree.getChildren().add(findChildren(menu,menulist));
            }
        }
        return menuTree;
    }


}
