package com.qcap.core.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.annotation.BussinessLog;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.common.ManagerStatus;
import com.qcap.core.dict.UserDict;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.Manager;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.poiEntity.ManagerPoiEntity;
import com.qcap.core.poiEntity.ManagerPoiExportEntity;
import com.qcap.core.service.ManagerSrv;
import com.qcap.core.tips.ErrorTip;
import com.qcap.core.tips.SuccessTip;
import com.qcap.core.tips.Tip;
import com.qcap.core.utils.Const;
import com.qcap.core.utils.Md5Utils;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.poi.PoiUtils;
import com.qcap.core.warpper.ManagerWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/mgr")
public class ManagerController extends BaseController{


    private static String PREFIX = "/system/user/";

    @Autowired
    private ManagerSrv managerSrv;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add";
    }

    /**
     * 跳转到角色分配页面
     */
    //@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable String userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Manager user = managerSrv.getByUserId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_roleassign";
    }

    @RequestMapping("/org_assign/{userId}")
    public String org_assign(@PathVariable String userId, Model model){
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Manager user = managerSrv.getByUserId(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "user_orgassign";
    }


    /**
     * 跳转到编辑管理员页面
     */
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable String userId, Model model) {
//        权限判断
//        assertAuth(userId);
        Manager manager = managerSrv.getByUserId(userId);
        model.addAttribute(manager);
        model.addAttribute("roleName", managerSrv.getRoleNameById(manager.getId()));
//        LogObjectHolder.me().set(user);
        return PREFIX + "user_edit";
    }


    /**
     * 添加管理员
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(@Valid Manager manager, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        Manager theManager = managerSrv.getByAccount(manager.getAccount());
        if (theManager != null) {
            throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        manager.setSalt(Md5Utils.getSalt());
        manager.setPassword(Md5Utils.encrypt(manager.getPassword(), manager.getSalt()));
        manager.setStatus(ManagerStatus.OK.getCode());
        manager.setCreatetime(new Date());
        this.managerSrv.insertManager(manager);
        Map<String,Object> data = new HashMap<String,Object>();

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, data);
    }


    @RequestMapping(value = "/list",  method = RequestMethod.POST)
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        new PageFactory<Manager>().defaultPage();

        List<Map<String, Object>> users = managerSrv.selectUsers(name, beginTime, endTime);
        for(Map manager: users){
            Integer status = (Integer) manager.get("status");
            if (status == ManagerStatus.OK.getCode()){
                manager.put("statusName", ManagerStatus.OK.getMessage());
            }else if(status == ManagerStatus.FREEZED.getCode()){
                manager.put("statusName", ManagerStatus.FREEZED.getMessage());
            }
        }

        PageInfo pageInfo = new PageInfo( (List<Manager>) new ManagerWarpper(users).warp());
        Page pageList = (Page) users;

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE,"",pageInfo.getTotal(),users);
    }


    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping(value ="/edit", method = RequestMethod.POST)
    @ResponseBody
    public Object edit(@Valid Manager manager, BindingResult result) {

        Map<String,Object> data = new HashMap<String,Object>();

        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        managerSrv.updateManager(manager);

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, data);
    }

    /**
     * 分配组织
     */
    @RequestMapping(value = "/setOrg", method = RequestMethod.POST)
    @ResponseBody
    public Tip setOrg(@RequestParam("userId") String userId, @RequestParam("orgIds") String orgIds) {
        if (ToolUtil.isOneEmpty(userId, orgIds)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        return  this.managerSrv.setOrg(userId, orgIds);
    }


    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam String ids) {
        if (ToolUtil.isEmpty(ids)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        String[] idArr=ids.split(",");
        for (String id : idArr) {
            this.managerSrv.setStatus(id, ManagerStatus.DELETED.getCode());
        }

        Map<String,Object> data = new HashMap<String,Object>();

        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, data);
    }


    /**
     * 冻结用户
     */
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/freeze", method = RequestMethod.POST)
    @ResponseBody
    public Tip freeze(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        this.managerSrv.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结用户
     */
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/unfreeze",  method = RequestMethod.POST)
    @ResponseBody
    public Tip unfreeze(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
//        assertAuth(userId);
        this.managerSrv.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }



    /**
     * 分配角色
     */
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    @ResponseBody
    public Tip setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
//        if (userId.equals(Const.ADMIN_ID)) {
//            throw new BussinessException(BizExceptionEnum.CANT_CHANGE_ADMIN);
//        }
//        assertAuth(userId);
        this.managerSrv.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }


    /**
     * 跳转到修改密码界面
     */
    @RequestMapping("/user_chpwd")
    public String chPwd() {
        return PREFIX + "user_chpwd";
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        HttpSession session = getSession();
        Manager manager  = (Manager) session.getAttribute("manager");


        String oldMd5 = Md5Utils.encrypt(oldPwd,manager.getSalt());
        if (manager.getPassword().equals(oldMd5)) {

            String newSalt = Md5Utils.getSalt();
            String newMd5 = Md5Utils.encrypt(newPwd, newSalt);

            manager.setSalt(newSalt);
            manager.setPassword(newMd5);

            managerSrv.updateManagerPwd(manager);
            return SUCCESS_TIP;
        } else {
            throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }


    /**
     * 重置管理员的密码
     */
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    @ResponseBody
    public Tip reset(@RequestParam String userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        HttpSession session = getSession();
        Manager manager  = (Manager) session.getAttribute("manager");
        String newSalt = Md5Utils.getSalt();
        String newMd5 = Md5Utils.encrypt(Const.DEFAULT_PWD, newSalt);

        manager.setSalt(newSalt);
        manager.setPassword(newMd5);

        managerSrv.updateManagerPwd(manager);
        return SUCCESS_TIP;
    }

    /**
     * 导入excel
     */
    @RequestMapping(method = RequestMethod.POST,value = "/importExcel")
    @ResponseBody
    public Tip importManagerExcel(MultipartFile file){
        Boolean flag = true;
        //解析excel
        if(!file.isEmpty()) {
            List<ManagerPoiEntity> personList = PoiUtils.importExcel(file, 0, 1, ManagerPoiEntity.class);
            for (ManagerPoiEntity managerPoi : personList) {
                Manager curManager = (Manager) getSession().getAttribute("manager");
                Manager theManager = managerSrv.getByAccount(managerPoi.getPhone());
                managerPoi.setCreateemp(curManager.getId());
                if (theManager != null || null == managerPoi.getPhone() || null == managerPoi.getName()) {
                    this.managerSrv.insertManagerErrorDataPoi(managerPoi);
                    flag = false;
                }else{
                    managerPoi.setId(UUID.randomUUID().toString());
                    // 完善账号信息
                    managerPoi.setSalt(Md5Utils.getSalt());
                    //设置默认密码
                    managerPoi.setPassword(Md5Utils.encrypt(CoreConstant.DEFAULT_PASSWORD, managerPoi.getSalt()));
                    managerPoi.setStatus(ManagerStatus.OK.getCode());
                    managerPoi.setAccount(managerPoi.getPhone());
                    managerPoi.setCreatetime(new Date());

                    this.managerSrv.insertManagerPoi(managerPoi);
                }
            }
            if(flag){
                return new SuccessTip();
            }
            return new ErrorTip(CoreConstant.MANAGER_IMPORT_HAS_ERROR_DATA_CODE, CoreConstant.MANAGER_IMPORT_HAS_ERROR_DATA_MSG);
        }else{
            return new ErrorTip(CoreConstant.MANAGER_IMPORT_FILE_NOT_EXIST_CODE, CoreConstant.MANAGER_IMPORT_FILE_NOT_EXIST_MSG);
        }
    }

    /**
     * 导出所有数据excel
     */
    @RequestMapping("/export")
    @ResponseBody
    public void export(HttpServletResponse response){
        //模拟从数据库获取需要导出的数据
        List<ManagerPoiEntity> personList = managerSrv.listManager();
        //导出
        PoiUtils.exportExcel(personList,"用户","用户", ManagerPoiExportEntity.class,"用户.xls",response);
    }


    /**
     * 导出异常数据excel
     */
    @RequestMapping("/exportErrorData")
    @ResponseBody
    public void exportErrorData(HttpServletResponse response){
        Manager curManager = (Manager) getSession().getAttribute("manager");
        //通过当前登录用户ID获取有该用户导入的异常信息
        List<ManagerPoiEntity> errorList = this.managerSrv.listErrorManagerByManagerId(curManager.getId());
        this.managerSrv.delErrorManagerByManagerId(curManager.getId());
        PoiUtils.exportExcel(errorList,"异常数据","异常数据",ManagerPoiEntity.class,"异常数据.xls",response);
    }

    /*
     * 导出模版
     */
    @RequestMapping("/excelModel")
    @ResponseBody
    public void excelModel(HttpServletResponse response){
        List<ManagerPoiEntity> list= new ArrayList<>();
        list.add(ManagerPoiEntity.newInstance("18888888888","张三","部长","1000","18888888888"));
        list.add(ManagerPoiEntity.newInstance("18666666666","李四","副部长","1001","18666666666"));
        PoiUtils.exportExcel(list,null,"用户模版",ManagerPoiEntity.class,"用户模版.xls",response);
    }

}
