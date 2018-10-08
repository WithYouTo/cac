package com.whxx.core.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.common.CoreConstant;
import com.whxx.core.entity.TbManager;
import com.whxx.core.exception.BizExceptionEnum;
import com.whxx.core.exception.BussinessException;
import com.whxx.core.model.PageResParams;
import com.whxx.core.model.ResParams;
import com.whxx.core.service.ITbManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mgr")
public class ManagerController
{

    private ITbManagerService managerService;


    /**
     * 添加用户
     *
     * @param manager  参数对象
     * @param result 校验结果对象
     * @return response封装
     */
    @PostMapping("/add")
    public ResParams add(@Valid TbManager manager, BindingResult result)
    {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        try
        {
            managerService.insertItem(manager);
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
        } catch (Exception e)
        {
            e.printStackTrace();
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, e.getMessage(), null);
        }
    }

    @PostMapping("/list")
    public PageResParams list(IPage<Map<String, Object>> page, @RequestParam(required = false) String userName, @RequestParam(required = false) String phone)
    {
        Map<String, Object> parameter = new HashMap<>();
        if (StringUtils.isNotEmpty(userName))
        {
            parameter.put("userName", userName + "%");
        }
        if (StringUtils.isNotEmpty(phone))
        {
            parameter.put("phone", phone + "%");
        }
        managerService.getUserList(page, parameter);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", page.getTotal(), page.getRecords());
    }


    /**
     * 修改账户
     *
     */
    @PostMapping("/edit")
    public ResParams edit(@Valid TbManager manager, BindingResult result)
    {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        managerService.updateItem(manager);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
    }

    /**
     * 分配组织
     */
    @PostMapping("/setOrg")
    public Object setOrg(@RequestParam("userId") String userId, @RequestParam("orgIds") String orgIds) {
        if (StrUtil.isEmpty(userId))
        {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        String[] ids = orgIds.split(",");
        if (ids.length == 0 || ids.length > 1)
        {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, CoreConstant.MANAGER_MULTI_ORG_MSG, null);
        }
        managerService.buildOrfForManager(userId, ids[0]);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.SUCCESS_DESC, null);
    }


    /**
     * 删除管理员（逻辑删除）
     */
    @PostMapping("/delete")
    public ResParams delete(@RequestParam String ids)
    {
        if (StrUtil.isEmpty(ids))
        {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Arrays.stream(ids.split(",")).forEach(e -> managerService.deleteManagerById(e));
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
    }


    /**
     * 分配角色
     */
    @PostMapping("/setRole")
    public ResParams setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds)
    {
        if (StringUtils.isAnyEmpty(userId, roleIds))
        {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        managerService.buildRoleForManager(userId, Arrays.asList(roleIds.split(",")));
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ROLE_SET_AUTH_SUCCESS, null);
    }


//    /**
//     * 修改当前用户的密码
//     */
//    @PostMapping("/changePwd")
//    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
//        if (!newPwd.equals(rePwd)) {
//            throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
//        }
//        HttpSession session = getSession();
//        TbManager manager  = (TbManager) session.getAttribute("manager");
//
//
//        String oldMd5 = Md5Util.encrypt(oldPwd,manager.getSalt());
//        if (manager.getPassword().equals(oldMd5)) {
//
//            String newSalt = Md5Util.getSalt();
//            String newMd5 = Md5Util.encrypt(newPwd, newSalt);
//
//            manager.setSalt(newSalt);
//            manager.setPassword(newMd5);
//
//            managerSrv.updateManagerPwd(manager);
//            return SUCCESS_TIP;
//        } else {
//            throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
//        }
//    }
//
//
//    /**
//     * 重置管理员的密码
//     */
//    @PostMapping("/reset")
//    public Tip reset(@RequestParam String userId) {
//        if (StringUtils.isEmpty(userId))
//        {
//            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
//        }
//        HttpSession session = getSession();
//        TbManager manager  = (TbManager) session.getAttribute("manager");
//        String newSalt = Md5Util.getSalt();
//        String newMd5 = Md5Util.encrypt(Const.DEFAULT_PWD, newSalt);
//        manager.setSalt(newSalt);
//        manager.setPassword(newMd5);
//        managerSrv.updateManagerPwd(manager);
//        return SUCCESS_TIP;
//    }

    @Inject
    public void setManagerService(ITbManagerService managerService)
    {
        this.managerService = managerService;
    }
}
