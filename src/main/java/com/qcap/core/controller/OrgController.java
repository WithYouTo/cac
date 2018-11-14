package com.qcap.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.ITbOrgService;

import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/org")
public class OrgController {

	private ITbOrgService tbOrgService;

	/**
	 * 分页获取组织列表
	 * 
	 * @param org
	 *            参数对象
	 * @param page
	 *            分页对象
	 * @return PageResParams 结果包装
	 */
	@PostMapping("/list")
	public PageResParams list(TbOrg org, IPage<Map<String,String>> page) {
		tbOrgService.getOrgList(page, org);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", page.getTotal(), page.getRecords());
	}

	/**
	 * 新增菜单
	 */
	@PostMapping("/add")
	public ResParams add(@Valid TbOrg org, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		try {
			tbOrgService.insertItem(org);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "新增失败！", null);
		}

	}

	/**
	 * 删除菜单
	 */
	@PostMapping("/delete")
	public ResParams remove(@RequestParam("ids") String ids) {
		if (StrUtil.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		try {
			for (String id : ids.split(",")) {
				tbOrgService.deleteItemById(id);
			}
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);

		} catch (Exception e) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
		}
	}

	/**
	 * 修改菜单
	 */
	@PostMapping("/edit")
	public ResParams edit(@Valid TbOrg org, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		try {
			tbOrgService.updateItem(org);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
		}
	}

	/**
	 * 拼装树形菜单
	 *
	 * @return Object
	 */
	@PostMapping("/selectOrgTreeList")
	public ResParams selectOrgTreeList() {
		List<ZTreeNode> orgTreeList = tbOrgService.getOrgTreeList();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功！", orgTreeList);
	}

	/**
	 * 获取组织列表
	 */
	@PostMapping(value = "/orgTreeListByUserId")
	public ResParams orgTreeListByUserId(String userId) {
		List<ZTreeNode> orgTreeList = tbOrgService.getOrgTreeListByManagerId(userId);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功！", orgTreeList);
	}

	/**
	 * 同级上移
	 */
	@PostMapping(value = "/upSeq")
	public ResParams upSeq(@RequestParam String orgId) {
		String result = tbOrgService.upSeq(orgId);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, result, null);
	}

	/**
	 * 同级下移
	 */
	@PostMapping("/downSeq")
	public ResParams downSeq(@RequestParam String orgId) {
		String result = tbOrgService.downSeq(orgId);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, result, null);
	}

	// /**
	// * 获取组织列表
	// */
	// @RequestMapping(value = "/listUserByOrg")
	// @ResponseBody
	// public Object listUser(@RequestParam(required = false) String code) {
	// List<ManagerTool> users = orgSrv.listUserByOrg(code);
	// return users;
	// }

	/**
	 * 使用递归方法建树 组织新建及修改时用
	 * 
	 * @param menulist
	 * @return
	 */
	private static List<ZTreeNode> buildByRecursive(List<ZTreeNode> menulist) {
		List<ZTreeNode> trees = new ArrayList<ZTreeNode>();
		for (ZTreeNode menuTree : menulist) {
			if (Objects.equals("top", menuTree.getPid())) {
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

	@Inject
	public void setTbOrgService(ITbOrgService tbOrgService) {
		this.tbOrgService = tbOrgService;
	}
}
