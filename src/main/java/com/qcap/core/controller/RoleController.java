package com.qcap.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.dict.RoleDict;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.factory.ConstantFactory;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.Role;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.utils.CommUtil;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.core.warpper.ManagerWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qcap.core.annotation.BussinessLog;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.log.LogObjectHolder;
import com.qcap.core.model.PageResParams;
import com.qcap.core.service.RoleSrv;
import com.qcap.cac.model.TbUser;
import com.qcap.cac.service.WebUserSrv;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	private static String PREFIX = "/system/role/";

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RoleSrv roleSrv;

	@Autowired
	private WebUserSrv webUserSrv;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 跳转到添加角色
	 */
	@RequestMapping(value = "/role_add")
	public String roleAdd() {
		return PREFIX + "role_add";
	}

	/**
	 * 跳转到修改角色
	 */
	@RequestMapping(value = "/role_edit/{roleId}")
	public String roleEdit(@PathVariable String roleId, Model model) {
		if (ToolUtil.isEmpty(roleId)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Role role = roleSrv.selectById(roleId);
		String pName = roleSrv.getPname(role.getPnum());
		model.addAttribute(role);
		model.addAttribute("pName", pName);
		return PREFIX + "role_edit";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/toEdit", method = RequestMethod.POST)
	@ResponseBody
	public Map toEdit(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			Role role = roleSrv.selectById(id);
			map = CommUtil.setMessage(200, "", role);
		} catch (Exception e) {
			e.printStackTrace();
			map = CommUtil.setMessage(500, "查询失败", null);
		}
		return map;
	}

	/**
	 * 跳转到角色分配
	 */
	@RequestMapping(value = "/role_assign/{id}")
	public String roleAssign(@PathVariable("id") String id, Model model) {
		if (ToolUtil.isEmpty(id)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		model.addAttribute("id", id);
		Role role = roleSrv.selectByRoleId(id);

		model.addAttribute("roleName", role.getName());
		return PREFIX + "role_assign";
	}

	/**
	 * 获取角色列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String roleName, HttpServletRequest request) {
		new PageFactory<Role>().defaultPage();
		Map map = new HashMap<>();

		String token = request.getHeader(jwtProperties.getTokenHeader());
		// String userId = jwtTokenUtil.getUsernameFromToken(token);

		String userId = redisUtil.getUserId(token);
		map = this.webUserSrv.selectByPrimaryKey(userId);

		String companyCode = request.getParameter("companyName");
		if (map != null && !map.isEmpty()) {
			companyCode = TenpayUtil.toString(map.get("companyCode"));
		}
		new PageFactory<Map>().defaultPage();

		List<Map<String, Object>> roles = roleSrv.selectRoles(super.getPara("role_name"), companyCode);

		PageInfo pageInfo = new PageInfo((List<TbUser>) new ManagerWarpper(roles).warp());

		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), roles);

	}

	/**
	 * 角色新增
	 */
	@BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map add(@Valid Role role, BindingResult result) {
		Map map = new HashMap<>();
		Map params = new HashMap<>();
		try {
			if (result.hasErrors()) {
				map = CommUtil.setMessage(401, "请求有错误", null);
				return map;
			}

			// 判断是否存在该编号
			/*
			 * String existedRoleName = roleSrv.checkNum(role.getNum()); if
			 * (ToolUtil.isNotEmpty(existedRoleName)) { map = CommUtil.setMessage(401,
			 * "菜单编号重复，不能添加", null); return map; }
			 */
			params.put("name", role.getName());
			String num = this.roleSrv.selectExistNum(params);
			if (!"0".equals(num)) {
				return CommUtil.setMessage(401, "角色名称已重复，不能添加", null);
			}
			roleSrv.insertRole(role);
			map = CommUtil.setMessage(200, "新增成功", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map = CommUtil.setMessage(500, "新增失败", null);
		}
		return map;
	}

	/**
	 * 获取角色列表
	 */
	@RequestMapping(value = "/roleTreeList")
	@ResponseBody
	public Map roleTreeList() {
		Map map = new HashMap<>();
		List<ZTreeNode> roleTreeList = roleSrv.roleTreeList();
		roleTreeList.add(ZTreeNode.createParent());
		map = CommUtil.setMessage(200, "", roleTreeList);
		return map;
	}

	/**
	 * 角色修改
	 */
	@BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Map edit(@Valid Role role, BindingResult result) {

		Map map = new HashMap<>();
		Map params = new HashMap<>();
		try {
			if (result.hasErrors()) {
				map = CommUtil.setMessage(401, "请求有错误", null);
				return map;
			}
			params.put("name", role.getName());
			params.put("id", role.getId());
			String num = this.roleSrv.selectExistNum(params);
			if (!"0".equals(num)) {
				return CommUtil.setMessage(401, "角色名称已存在，修改失败", null);
			}
			roleSrv.updateById(role);
			map = CommUtil.setMessage(200, "修改成功", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map = CommUtil.setMessage(500, "修改失败", null);
		}
		return map;
	}

	/**
	 * 删除角色
	 */
	@BussinessLog(value = "删除角色", key = "name", dict = RoleDict.class)
	@RequestMapping(value = "/remove")
	@ResponseBody
	public Map remove(@RequestParam String ids) {

		Map map = new HashMap<>();
		try {
			if (ToolUtil.isEmpty(ids)) {
				map = CommUtil.setMessage(401, "请求有错误", null);
				return map;
			}

			// 缓存被删除的角色名称
			LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(ids));

			String[] Id = ids.split(",");
			for (int i = 0; i < Id.length; i++) {
				this.roleSrv.delRoleById(Id[i]);
			}

			map = CommUtil.setMessage(200, "删除成功", null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map = CommUtil.setMessage(500, "删除失败", null);
		}
		return map;
	}

	/**
	 * 配置权限
	 */
	@RequestMapping("/setAuthority")
	@ResponseBody
	public Map setAuthority(@RequestParam("id") String id, @RequestParam("ids") String ids) {
		Map map = new HashMap<>();
		if (ToolUtil.isOneEmpty(id)) {
			map = CommUtil.setMessage(401, "请求有错误", null);
			return map;
		}
		this.roleSrv.setAuthority(id, ids);
		map = CommUtil.setMessage(200, "操作成功", null);
		return map;
	}

	/**
	 * 获取角色列表
	 */
	@RequestMapping(value = "/roleTreeListByUserId/{userId}")
	@ResponseBody
	public List<ZTreeNode> roleTreeListByUserId(@PathVariable String userId) {

		return roleSrv.roleTreeListByRoleId(userId);

	}

}
