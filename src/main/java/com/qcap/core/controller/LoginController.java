package com.qcap.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbManager;
import com.qcap.core.model.MenuTree;
import com.qcap.core.model.ResParams;
import com.qcap.core.service.ITbManagerService;
import com.qcap.core.service.ITbMenuService;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;

/**
 * @author huangxiang
 */
@RestController
public class LoginController {

	private ITbManagerService tbManagerService;

	private ITbMenuService tbMenuService;

	@Resource
	private RedisUtil redisUtil;
	
//	private static Logger logger = AppUtils.getLogger(LicenseCheckInterceptor.class,true);

	/**
	 * 点击登录执行的动作
	 */
	@PostMapping("/login")
	public ResParams login(@RequestParam("username") String username, @RequestParam("password") String password) {
		username = StringUtils.trimToEmpty(username);
		try {
			String token = tbManagerService.login(username, password);
			Map<String, Object> buttonAuthMap = tbMenuService.getButtonAuthFromToken(token);
			Map<String, Object> data = new HashMap<>(2);
			data.put("access_token", token);
			data.put("button_auth", buttonAuthMap);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "登录成功！", data);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
		}
	}
	
	/**
	 * 点击登录执行的动作
	 */
//	@PostMapping("/login")
//	public ResParams login(@RequestParam("username") String username, @RequestParam("password") String password) {
//		username = StringUtils.trimToEmpty(username);
//        try {
//            LicenseVerify licenseVerify = new LicenseVerify();
//            licenseVerify.verify();
//
//            try {
//                String token = tbManagerService.login(username, password);
//                Map<String, Object> buttonAuthMap = tbMenuService.getButtonAuthFromToken(token);
//                Map<String, Object> data = new HashMap<>(2);
//                data.put("access_token", token);
//                data.put("button_auth", buttonAuthMap);
//                return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "登录成功！", data);
//            } catch (Exception e) {
//                return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
//            }
//        }catch (Exception e) {
//            logger.error("证书校验失败",e);
//            return ResParams.newInstance(CoreConstant.FAIL_CODE, "您的证书无效，请核查服务器是否取得授权或重新申请证书", null);
//            //result.put("result", "您的证书无效，请核查服务器是否取得授权或重新申请证书！");
//        }
//	}

	@PostMapping("/personal")
	public ResParams getPersonal(@RequestHeader("access_token") String token) {
		Optional<TbManager> op = tbManagerService.getTbManagerFromToken(token);
		return op.map(e -> ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", e))
				.orElse(ResParams.newInstance(CoreConstant.FAIL_CODE, "", null));
	}

	@PostMapping("/menu")
	public ResParams getMenu(@RequestHeader("access_token") String token) {
		List<MenuTree> menus = tbMenuService.getMenuTreeListFromToken(token);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", menus);
	}

	/**
	 * 退出登录
	 */
	@PostMapping("/logout")
	public ResParams logout() {
		TbManager user = AppUtils.getLoginUser();
		Map<String, Object> data = new HashMap<>(2);

		if (user != null) {
			redisUtil.delete(AppUtils.getApplicationName() + ":manager:" + user.getId());
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "退出成功！", null);
	}

	@Inject
	public void setTbManagerService(ITbManagerService tbManagerService) {
		this.tbManagerService = tbManagerService;
	}

	@Inject
	public void setTbMenuService(ITbMenuService tbMenuService) {
		this.tbMenuService = tbMenuService;
	}
}
