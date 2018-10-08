package com.whxx.core.controller;

import com.whxx.core.common.CoreConstant;
import com.whxx.core.entity.TbManager;
import com.whxx.core.model.MenuTree;
import com.whxx.core.model.ResParams;
import com.whxx.core.service.ITbManagerService;
import com.whxx.core.service.ITbMenuService;
import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


/**
 * @author huangxiang
 */
@RestController
public class LoginController
{

    private ITbManagerService tbManagerService;

    private ITbMenuService tbMenuService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 点击登录执行的动作
     */
    @PostMapping("/login")
    public ResParams login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        username = StringUtils.trimToEmpty(username);
        try
        {
            String token = tbManagerService.login(username, password);
            Map<String, Object> data = new HashMap<>(2);
            data.put("access_token", token);
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "登录成功！", data);
        } catch (Exception e)
        {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
    }

    @PostMapping("/personal")
    public ResParams getPersonal(@RequestHeader("access_token") String token)
    {
        Optional<TbManager> op = tbManagerService.getTbManagerFromToken(token);
        return op.map(e -> ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", e)).orElse(ResParams.newInstance(CoreConstant.FAIL_CODE, "", null));
    }

    @PostMapping("/menu")
    public ResParams getMenu(@RequestHeader("access_token") String token)
    {
        List<MenuTree> menus = tbMenuService.getMenuTreeListFromToken(token);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", menus);
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public ResParams logout()
    {
        TbManager user = AppUtils.getLoginUser();
        if (user != null)
        {
            redisUtil.delete(AppUtils.getApplicationName() + ":manager:" + user.getId());
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "退出成功！", null);
    }

    @Inject
    public void setTbManagerService(ITbManagerService tbManagerService)
    {
        this.tbManagerService = tbManagerService;
    }

    @Inject
    public void setTbMenuService(ITbMenuService tbMenuService)
    {
        this.tbMenuService = tbMenuService;
    }
}
