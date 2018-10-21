package com.qcap.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.annotation.BussinessLog;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbMenu;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.ITbMenuService;

import io.swagger.annotations.ApiOperation;

/**
 * 菜单控制器
 *
 * @author 帝都架构师NYJ
 * @date 2017年2月12日21:59:14
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
	private ITbMenuService tbMenuService;

	/**
	 * 获取菜单列表
	 * 
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@BussinessLog("查询菜单")
	@PostMapping("/list")
	public PageResParams list(TbMenu menu, IPage<TbMenu> page) throws Exception {
		tbMenuService.getMenuList(menu, page);
		List<TbMenu> ls = page.getRecords();
		List<Map<String, String>> lsRecord = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(ls)) {
			for (TbMenu menuTemp : ls) {
				Map<String, String> map = BeanUtils.describe(menuTemp);
				String isMenu = map.get("isMenu");
				if ("1".equals(isMenu)) {
					map.put("isMenuName", "PC菜单");
				} else if ("2".equals(isMenu)) {
					map.put("isMenuName", "APP菜单");
				} else {
					map.put("isMenuName", "");
				}

				lsRecord.add(map);
			}
		}
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", page.getTotal(), lsRecord);
	}

	/**
	 * 获取菜单树
	 * 
	 * @return ResParams
	 */
	@ApiOperation(value = "获取菜单树", notes = "请求获取菜单树数据")
	@PostMapping("/selectMenuTreeList")
	public ResParams selectMenuTreeList() {
		List<ZTreeNode> list = tbMenuService.getMenuTreeList();
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
	}

	/**
	 * 新增菜单
	 *
	 * @param menu
	 *            TbMenu实体
	 * @param result
	 *            数据验证绑定结果
	 * @return 返回操作信息
	 */
	@PostMapping("/add")
	public ResParams add(@Valid TbMenu menu, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		tbMenuService.insertItem(menu);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
	}

	/**
	 * 删除菜单
	 */
	@PostMapping("/remove")
	public ResParams remove(@RequestParam String ids) {
		if (StringUtils.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		String[] idArr = ids.split(",");
		for (String id : idArr) {
			tbMenuService.deleteMenuAndSubMenu(id);
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
	}

	/**
	 * 修改菜单
	 */
	@PostMapping(value = "/edit")
	public ResParams edit(@Valid TbMenu menu, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		tbMenuService.updateItem(menu);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
	}

	/**
	 * 获取角色列表
	 */
	@PostMapping("/menuTreeListByRoleId")
	public ResParams menuTreeListByRoleId(@RequestParam String id) {
		List<ZTreeNode> list = tbMenuService.getMenuTreeListByRoleId(id);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功！", list);
	}

	/**
	 * 同级上移
	 */
	@PostMapping("/upSeq")
	public ResParams upSeq(@RequestParam String menuId) {
		String result = tbMenuService.upSeq(menuId);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, result, null);
	}

	/**
	 * 菜单下移
	 */
	@PostMapping("/downSeq")
	public ResParams downSeq(@RequestParam String menuId) {
		String result = tbMenuService.downSeq(menuId);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, result, null);
	}

	@Autowired
	public void setTbMenuService(ITbMenuService tbMenuService) {
		this.tbMenuService = tbMenuService;
	}
}
