package com.qcap.core.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qcap.core.utils.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.core.kit.HttpKit;
import com.qcap.core.log.LogManager;
import com.qcap.core.log.factory.LogTaskFactory;
import com.qcap.core.model.MenuTree;
import com.qcap.core.service.LoginSrv;
import com.qcap.core.utils.Md5Utils;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.cac.model.TbUser;
import com.qcap.cac.service.WebUserSrv;

@CrossOrigin
@Controller
public class LoginController extends BaseController {

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private LoginSrv loginSrv;

	@Autowired
	private WebUserSrv webUserSrv;

	/**
	 * 跳转到登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {

		return "/login";
	}

	/**
	 * 点击登录执行的动作
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map loginVali(Model model, HttpServletRequest request, HttpServletResponse response) {
		String username = super.getPara("username").trim();
		String password = super.getPara("password").trim();

		TbUser manager = loginSrv.getTbUser(username);

		Map<String, Object> data = new HashMap<String, Object>();
		Map map = new HashMap<>();
		if (manager != null) {
			Boolean logFlag = checkPwd(manager, password);
			if (logFlag) {
				String managerId = manager.getId();

				LogManager.me().executeLog(LogTaskFactory.loginLog(manager.getId(), HttpKit.getIp()));

				String token = jwtTokenUtil.doGenerateToken(managerId);
				// 设置jwt的token过期时间为300分钟
				Date newDate = TenpayUtil.newDate(new Date(), Calendar.MINUTE, 300);
				long time = newDate.getTime();

				map.put("time", TenpayUtil.toString(time));
				map.put("userId", managerId);
				redisUtil.setMap(token, map);// 存储token的过期时间和用户ID
				redisUtil.setExpire(token, 300, TimeUnit.MINUTES);// 设置redis键的过期时间为300分钟

				data.put("access_token", token);

				return CommUtil.setMessage(200, "登录成功！", data);
			} else {
				LogManager.me().executeLog(LogTaskFactory.loginLog(username, "密码错误", HttpKit.getIp()));
				return CommUtil.setMessage(500, "密码错误:<span style='color:red;'>" + password + "<span>", null);
			}
		} else {
			LogManager.me().executeLog(LogTaskFactory.loginLog(username, "用户不存在", HttpKit.getIp()));

			return CommUtil.setMessage(500, "用户不存在！", null);
		}
	}

//	@ResponseBody
//	@RequestMapping(value = "/beforeInitMenu", method = RequestMethod.POST)
//	public Map<String, Object> beforeInitMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
//		String token = request.getHeader(jwtProperties.getTokenHeader());
//		String userId = redisUtil.getUserId(token);
//		Map<String, Object> data = new HashMap<String, Object>();
//
//		List<MenuTree> user_menus = loginSrv.getMenuList(userId);
//		// 房产菜单
//		List<MenuTree> house_menus = loginSrv.getMenuListByRoleName("房产");
//		// 楼宇菜单
//		List<MenuTree> building_menus = loginSrv.getMenuListByRoleName("楼宇");
//		// 公寓菜单
//		List<MenuTree> apartment_menus = loginSrv.getMenuListByRoleName("公寓");
//
//		// 通过判断集合的大小，来确定是否存在交集。不能通过方法返回的True和False来判断。
//		house_menus.retainAll(user_menus);
//		int num = 0;
//		if (house_menus.size() > 0) {
//			data.put("house", "1");
//			num = num + 1;
//		} else {
//			data.put("house", "0");
//		}
//
//		building_menus.retainAll(user_menus);
//		if (building_menus.size() > 0) {
//			data.put("building", "1");
//			num = num + 1;
//		} else {
//			data.put("building", "0");
//		}
//
//		apartment_menus.retainAll(user_menus);
//		if (apartment_menus.size() > 0) {
//			data.put("apartment", "1");
//			num = num + 1;
//		} else {
//			data.put("apartment", "0");
//		}
//		data.put("num", num);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("code", 200);
//		map.put("data", data);
//		map.put("desc", "");
//		return map;
//	}

	@ResponseBody
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public Map<String, Object> getMenu(Model model, HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(jwtProperties.getTokenHeader());
		String userId = redisUtil.getUserId(token);

//		List<MenuTree> sys_menus = new ArrayList<MenuTree>();

//		String flag = request.getParameter("flag");
//		if ("house".equals(flag)) {
//			sys_menus = loginSrv.getMenuListByRoleName("房产");
//		} else if ("building".equals(flag)) {
//			sys_menus = loginSrv.getMenuListByRoleName("楼宇");
//		} else if ("apartment".equals(flag)) {
//			sys_menus = loginSrv.getMenuListByRoleName("公寓");
//		}
		List<MenuTree> user_menus = loginSrv.getMenuList(userId);
//		user_menus.retainAll(sys_menus);

		user_menus = buildByRecursive(user_menus);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 200);
		map.put("data", user_menus);
		map.put("desc", null);
		return map;
	}

	/**
	 * 使用递归方法建树
	 * 
	 * @param menulist
	 * @return
	 */
	private static List<MenuTree> buildByRecursive(List<MenuTree> menulist) {
		List<MenuTree> trees = new ArrayList<MenuTree>();
		for (MenuTree menuTree : menulist) {
			if (menuTree.getPcode().equals("1")) {
				trees.add(findChildren(menuTree, menulist));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 * 
	 * @param menulist
	 * @return
	 */
	private static MenuTree findChildren(MenuTree menuTree, List<MenuTree> menulist) {
		for (MenuTree menu : menulist) {
			if (menuTree.getCode().equals(menu.getPcode())) {
				if (menuTree.getList() == null) {
					menuTree.setList(new ArrayList<MenuTree>());
				}
				menuTree.getList().add(findChildren(menu, menulist));
			}
		}
		return menuTree;
	}

	@ResponseBody
	@RequestMapping(value = "/personal", method = RequestMethod.POST)
	public Map<String, Object> getPersonal(Model model, HttpServletRequest request, HttpServletResponse response) {
		String token = request.getHeader(jwtProperties.getTokenHeader());
		// String userId = jwtTokenUtil.getUsernameFromToken(token);
		String userId = redisUtil.getUserId(token);
		Map user = this.webUserSrv.selectByPrimaryKey(userId);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("code", 200);
		result.put("desc", "登录成功");
		result.put("data", user);
		return result;
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		// LogManager.me().executeLog(LogTaskFactory.exitLog(getManager().getId(),
		// HttpKit.getIp()));
		HttpSession session = request.getSession(true);
		session.removeAttribute("manager");
		session.removeAttribute("menus");
		session.invalidate();
		return REDIRECT + "/login";
	}

	public Boolean checkPwd(TbUser manager, String password) {
		String salt = manager.getSalt();
		String dbPwd = manager.getPassword();
		if (dbPwd.equals(Md5Utils.encrypt(password, salt))) {
			return true;
		}
		return false;
	}

}
