package com.qcap.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbRole;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.ITbRoleService;

import cn.hutool.core.util.StrUtil;

/**
 * @author 帝都架构师nyj
 */
@RestController
@RequestMapping("/role")
public class RoleController {

	private ITbRoleService tbRoleService;

	/**
	 * 获取角色列表
	 */
	@PostMapping(value = "/list")
	public PageResParams list(IPage<TbRole> page, @RequestParam(required = false) String roleName) {
		tbRoleService.getRoleList(page, roleName);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功！", page.getTotal(), page.getRecords());
	}

	/**
	 * 角色新增
	 */
	@PostMapping("/add")
	public ResParams add(@Valid TbRole role, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		try {
			tbRoleService.insertRole(role);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);

		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);

		}
	}

	// /**
	// * 获取角色列表
	// */
	// @RequestMapping(value = "/roleTreeList")
	// @ResponseBody
	// public Object roleTreeList() {
	//
	// List<ZTreeNode> roleTreeList = roleSrv.roleTreeList();
	// return
	// ResParams.newInstance(CoreConstant.SUCCESS_CODE,CoreConstant.ROLE_SET_AUTH_SUCCESS,roleTreeList);
	//
	// }

	/**
	 * 角色修改
	 */
	@PostMapping(value = "/edit")
	public ResParams edit(@Valid TbRole role, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		try {
			tbRoleService.updateRole(role);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
		}
	}

	/**
	 * 删除角色
	 */
	@PostMapping("/remove")
	public Object remove(@RequestParam String ids) {

		if (StrUtil.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Arrays.stream(ids.split(",")).filter(StringUtils::isNotEmpty).forEach(e -> tbRoleService.deleteRoleById(e));
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
	}

	/**
	 * 配置权限
	 */
	@PostMapping("/setAuthority")
	public Object setAuthority(@RequestParam("id") String roleId, @RequestParam("ids") String menuIds) {

		if (StringUtils.isAnyEmpty(roleId)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		tbRoleService.assignMenuForRole(roleId, menuIds);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ROLE_SET_AUTH_SUCCESS, null);
	}

	/**
	 * 获取角色列表
	 */
	@PostMapping(value = "/roleTreeListByUserId")
	public ResParams roleTreeListByUserId(String userId) {
		List<ZTreeNode> roleTreeList = tbRoleService.getRoleTreeListByManagerId(userId);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ROLE_SET_AUTH_SUCCESS, roleTreeList);

	}

	/**
	 * 使用递归方法建树
	 * 
	 * @param menuList
	 *            菜单list
	 * @return List
	 */
	private static List<ZTreeNode> buildByRecursive(List<ZTreeNode> menuList) {
		List<ZTreeNode> trees = new ArrayList<>();
		for (ZTreeNode menuTree : menuList) {
			if (Objects.equals(menuTree.getPid(), "top")) {
				trees.add(findChildren(menuTree, menuList));
			}
		}
		return trees;
	}

	@Autowired
	public void setTbRoleService(ITbRoleService tbRoleService) {
		this.tbRoleService = tbRoleService;
	}

	/**
	 * 递归查找子节点
	 * 
	 * @param menulist
	 * @return
	 */
	private static ZTreeNode findChildren(ZTreeNode menuTree, List<ZTreeNode> menulist) {
		for (ZTreeNode menu : menulist) {
			if (menuTree.getId().equals(menu.getPid())) {
				if (menuTree.getChildren() == null) {
					menuTree.setChildren(new ArrayList<ZTreeNode>());
				}
				menuTree.getChildren().add(findChildren(menu, menulist));
			}
		}
		return menuTree;
	}

}
