package com.qcap.core.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.Org;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.poiEntity.OrgPoiEntity;
import com.qcap.core.poiEntity.OrgPoiExportEntity;
import com.qcap.core.service.OrgSrv;
import com.qcap.core.utils.poi.PoiUtils;
import com.qcap.core.warpper.OrgWarpper;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.tips.Tip;
import com.qcap.core.utils.ToolUtil;
import com.qcap.cac.model.TbUser;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织控制器
 *
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {

    private static String PREFIX = "/system/org/";

    @Resource
    OrgSrv orgSrv;

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "org";
    }

    /**
     * 跳转到菜单列表列表页面
     */
    @RequestMapping(value = "/org_add")
    public String orgAdd() {
        return PREFIX + "org_add";
    }

    /**
     * 跳转到组织编辑页面
     */
    @RequestMapping(value = "/org_edit/{id}")
    public String orgEdit(@PathVariable String id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Map<String,Object> org = this.orgSrv.selectOrgById(id);
        //获取父级菜单的id
        Org pOrg = orgSrv.getOrgByPCode(org.get("pcode").toString());
        //如果父级是顶级菜单
        if (pOrg == null) {
            org.put("pcode","1");
            org.put("pcodeName","顶级");
        } else {
            //设置父级菜单的code为父级菜单的id
            org.put("pcode",pOrg.getCode());
            org.put("pcodeName", pOrg.getName());
        }
        model.addAttribute("org", org);
        return PREFIX + "org_edit";
    }

    /**
     * 获取组织列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name) {
        new PageFactory<Org>().defaultPage();
        List<Map<String, Object>> orgs = orgSrv.listOrg(name);
        PageInfo pageInfo = new PageInfo( (List<Org>) new OrgWarpper(orgs).warp());
        Page pageList = (Page) orgs;

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE,"",pageInfo.getTotal(),orgs);
    }

    /**
     * 新增菜单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Org org, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //判断是否存在该编号
        String existedOrgName = orgSrv.checkOrgByNum(org.getNum());
        if (ToolUtil.isNotEmpty(existedOrgName)) {
            throw new BussinessException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //获取当前登录用户信息
        TbUser manager = (TbUser) getSession().getAttribute("manager");
        org.setCreateemp(manager.getName());

        this.orgSrv.insertOrg(org);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object remove(@RequestParam String orgId) {
        if (ToolUtil.isEmpty(orgId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        this.orgSrv.delOrgById(orgId);
        Map<String,Object> data = new HashMap<String,Object>();
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, data);
    }

    /**
     * 修改菜单
     */
    @RequestMapping(value = "/edit")
    @ResponseBody
    public Tip edit(@Valid Org org, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        return this.orgSrv.updateOrg(org);
    }

    /*
    * 导入
    */
    @RequestMapping(method = RequestMethod.POST,value = "/importExcel")
    @ResponseBody
    public Tip importExcel(MultipartFile file){
        //解析excel，
        List<OrgPoiEntity> orgList = PoiUtils.importExcel(file,0,1,OrgPoiEntity.class);
        return this.orgSrv.importExcel(orgList );
    }


    /*
    * 导出
    */
    @RequestMapping("/export")
    @ResponseBody
    public void orgExport(HttpServletResponse response){
        //模拟从数据库获取需要导出的数据
        List<OrgPoiExportEntity> orgList = this.orgSrv.exportOrg();
        //导出
        PoiUtils.exportExcel(orgList,"组织","组织",OrgPoiExportEntity.class,"组织.xls",response);
    }


    /*
     * 导出模版
     */
    @RequestMapping("/excelModel")
    @ResponseBody
    public void excelModel(HttpServletResponse response){
        List<OrgPoiEntity> list= new ArrayList<>();
        list.add(OrgPoiEntity.newInstance("1000",null,"总公司"));
        list.add(OrgPoiEntity.newInstance("1001","1000","子公司"));
        PoiUtils.exportExcel(list,null,"组织模版",OrgPoiEntity.class,"组织模版.xls",response);
    }

    /*
    * 拼装树形菜单
    */
    @RequestMapping(value = "/selectOrgTreeList")
    @ResponseBody
    public List<ZTreeNode> selectOrgTreeList() {
        List<ZTreeNode> roleTreeList = orgSrv.orgTreeList();
//        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

    /**
     * 获取组织列表
     */
    @RequestMapping(value = "/orgTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> orgTreeListByUserId(@PathVariable String userId) {
        return this.orgSrv.orgTreeListByOrgCode(userId);
    }

    /**
     * 同级上移
     */
    @RequestMapping(value = "/upSeq")
    @ResponseBody
    public Tip upSeq(@RequestParam String orgId){
        return this.orgSrv.upSeq(orgId);
    }

    /**
     * 同级下移
     */
    @RequestMapping(value = "/downSeq")
    @ResponseBody
    public Tip downSeq(@RequestParam String orgId){
        return this.orgSrv.downSeq(orgId);
    }


    /**
     * 获取组织列表
     */
    @RequestMapping(value = "/listUserByOrg")
    @ResponseBody
    public Object listUser(@RequestParam(required = false) String code) {
        List<Map<String, Object>> users = orgSrv.listUserByOrg(code);
        return users;
    }
}
