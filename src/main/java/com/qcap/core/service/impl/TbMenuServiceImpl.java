package com.qcap.core.service.impl;

import static com.qcap.core.common.CoreConstant.ORG_FULLCODES_SEPARATE;
import static com.qcap.core.utils.AppUtils.buildZTreeNodeByRecursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.dao.TbMenuMapper;
import com.qcap.core.dao.TbRoleMenuMapper;
import com.qcap.core.entity.TbMenu;
import com.qcap.core.entity.TbRoleMenu;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.MenuTree;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.ITbMenuService;
import com.qcap.core.utils.jwt.JwtTokenUtil;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author NYJ
 * @since 2018-07-30
 */
@Service
public class TbMenuServiceImpl implements ITbMenuService {
	@Resource
	private JwtTokenUtil jwtTokenUtil;

	@Resource
	private TbMenuMapper tbMenuMapper;

	@Resource
	private TbRoleMenuMapper tbRoleMenuMapper;

	@Override
	public List<MenuTree> getMenuTreeListFromToken(String token) {
		String userId = jwtTokenUtil.getUsernameFromToken(token);
		List<MenuTree> menus = tbMenuMapper.getMenuTreeListByManagerId(userId);
		return buildMenuTreeByRecursive(menus, new ArrayList<>(), "1");
	}

	@Override
	public IPage<TbMenu> getMenuList(TbMenu menu, IPage<TbMenu> page) {
		QueryWrapper<TbMenu> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(TbMenu::getStatus, "1");
		if (StringUtils.isNotEmpty(menu.getName())) {
			wrapper.lambda().like(TbMenu::getName, menu.getName() + "%");
		}
		if (menu.getLevel() != null) {
			wrapper.lambda().eq(TbMenu::getLevel, menu.getLevel());
		}
		if (StringUtils.isNotEmpty(menu.getCode())) {
			wrapper.lambda().eq(TbMenu::getCode, menu.getCode());
		}
		wrapper.orderByAsc("level", "seq");
		return tbMenuMapper.selectPage(page, wrapper);
	}

	@Override
	public List<ZTreeNode> getMenuTreeList() {
		List<ZTreeNode> list = tbMenuMapper.getMenuTreeList();
		list.add(ZTreeNode.createParent());
		return buildZTreeNodeByRecursive(list, new ArrayList<>(), e -> Objects.equals("0", e.getPid()));
	}

	@Override
	public List<ZTreeNode> getMenuTreeListByRoleId(String roleId) {
		List<TbRoleMenu> list = tbRoleMenuMapper.getRoleMenuByRoleId(roleId);
		List<Long> res = list.stream().mapToLong(e -> NumberUtils.toLong(e.getMenuCode())).boxed()
				.collect(Collectors.toList());
		if (res.isEmpty()) {
			return getMenuTreeList();
		} else {
			List<ZTreeNode> menuTreeList = tbMenuMapper.getMenuTreeListByMenuCode(res);
			menuTreeList.add(ZTreeNode.createParent());
			menuTreeList = buildZTreeNodeByRecursive(menuTreeList, new ArrayList<>(),
					e -> Objects.equals("0", e.getPid()));
			return menuTreeList;
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public String upSeq(String menuId) {
		TbMenu tbMenu = tbMenuMapper.selectById(menuId);
		TbMenu fontTbMenu = tbMenuMapper
				.selectOne(new QueryWrapper<TbMenu>().lambda().eq(TbMenu::getParentCode, tbMenu.getParentCode())
						.lt(TbMenu::getSeq, tbMenu.getSeq()).last("order by seq desc limit 1"));
		if (fontTbMenu == null) {
			return CoreConstant.MENU_IS_FIRST_MSG;
		} else {
			exeChangeSeq(tbMenu, fontTbMenu);
			tbMenuMapper.updateById(tbMenu);
			tbMenuMapper.updateById(fontTbMenu);
			return CoreConstant.MENU_UP_SUCCESS;
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public String downSeq(String menuId) {
		TbMenu tbMenu = tbMenuMapper.selectById(menuId);
		TbMenu behindTbMenu = tbMenuMapper
				.selectOne(new QueryWrapper<TbMenu>().lambda().eq(TbMenu::getParentCode, tbMenu.getParentCode())
						.gt(TbMenu::getSeq, tbMenu.getSeq()).last("order by seq desc limit 1"));
		if (behindTbMenu == null) {
			return CoreConstant.MENU_IS_LAST_MSG;
		} else {
			exeChangeSeq(tbMenu, behindTbMenu);
			tbMenuMapper.updateById(tbMenu);
			tbMenuMapper.updateById(behindTbMenu);
			return CoreConstant.MENU_UP_SUCCESS;
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void insertItem(TbMenu tbMenu) {
		buildSeqAndCodeByParentCode(tbMenu, tbMenu.getParentCode());
		buildTbMenuByParentCode(tbMenu, tbMenu.getParentCode());
		Integer maxNum = tbMenuMapper.selectMaxNum();
		tbMenu.setNum(String.valueOf(maxNum));
		tbMenu.setIcon(StringUtils.trimToEmpty(tbMenu.getIcon()));
		tbMenu.setStatus(1);
		tbMenuMapper.insert(tbMenu);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateItem(TbMenu tbMenu) {
		// 获取需要修改的组织
		TbMenu menu = tbMenuMapper.selectById(tbMenu.getId());
		menu.setName(tbMenu.getName());
		menu.setUrl(tbMenu.getUrl());
		menu.setIsMenu(tbMenu.getIsMenu());
		// 如果没有修改父节点，直接保存
		if (Objects.equals(menu.getParentCode(), tbMenu.getParentCode())
				|| StringUtils.isEmpty(tbMenu.getParentCode())) {
			tbMenuMapper.updateById(menu);
		}
		// 如果修改了父节点那么对子节点也进行转移
		else {
			// 通过parent_code 获取修改后父节点
			TbMenu parentMenu = tbMenuMapper
					.selectOne(new QueryWrapper<TbMenu>().lambda().eq(TbMenu::getCode, tbMenu.getParentCode()));
			if (parentMenu != null) {
				String oldParentCode = menu.getCode();
				buildSeqAndCodeByParentCode(tbMenu, parentMenu.getCode());
				tbMenu.setParentCode(parentMenu.getCode());
				tbMenu.setParentNum(parentMenu.getNum());
				tbMenu.setLevel(parentMenu.getLevel() + 1);
				tbMenu.setFullCodes(parentMenu.getFullCodes() + tbMenu.getCode() + ORG_FULLCODES_SEPARATE);

				// 查询出所有的关联的菜单
				List<TbMenu> children = tbMenuMapper.selectList(
						new QueryWrapper<TbMenu>().lambda().like(TbMenu::getFullCodes, "%" + oldParentCode + "%"));
				if (!children.isEmpty()) {
					updateMenuRecursive(children, oldParentCode, tbMenu);
				}
				tbRoleMenuMapper.updateRoleMenuByMenuCode(oldParentCode, tbMenu.getCode());
				tbMenuMapper.updateById(tbMenu);
			}
		}
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deleteMenuAndSubMenu(String tbMenuId) {
		TbMenu tbMenu = tbMenuMapper.selectById(tbMenuId);
		deleteMenuRecursive(tbMenu);
	}

	/**
	 * 归纳了删除一个菜单所需要做的事情
	 */
	private void deleteMenuRecursive(TbMenu parent) {
		String code = parent.getCode();
		List<TbMenu> menus = tbMenuMapper.selectList(new QueryWrapper<TbMenu>().eq("parent_code", code));
		if (menus.isEmpty()) {
			// 删除角色菜单表
			tbRoleMenuMapper.delete(new UpdateWrapper<TbRoleMenu>().lambda().eq(TbRoleMenu::getMenuCode, code));
			tbMenuMapper.deleteById(parent.getId());

		} else {
			menus.parallelStream().forEach(this::deleteMenuRecursive);
		}
	}

	private void updateMenuRecursive(List<TbMenu> source, String oldParentCode, TbMenu newParent) {
		source.stream().filter(e -> Objects.equals(oldParentCode, e.getParentCode())).forEach(e -> {
			String parentCode = e.getCode();
			String code = newParent.getCode() + e.getCode().substring(e.getCode().length() - 2);
			e.setParentCode(newParent.getCode());
			e.setCode(code);
			e.setLevel(newParent.getLevel() + 1);
			e.setFullCodes(newParent.getFullCodes() + code + ORG_FULLCODES_SEPARATE);
			updateMenuRecursive(source, parentCode, e);
			tbRoleMenuMapper.updateRoleMenuByMenuCode(oldParentCode, e.getCode());
			tbMenuMapper.updateById(e);
		});
	}

	/**
	 * 交换2者的序号
	 *
	 * @param master
	 *            主
	 * @param slave
	 *            从
	 */
	private void exeChangeSeq(TbMenu master, TbMenu slave) {
		int one = master.getSeq();
		int two = slave.getSeq();
		// 交换2者的seq
		one ^= two;
		two ^= one;
		one ^= two;
		master.setSeq(one);
		slave.setSeq(two);
	}

	/**
	 * 使用递归方法建树
	 *
	 * @param source
	 *            源
	 * @param target
	 *            目标
	 * @param parentCode
	 *            父编码
	 * @return List
	 */
	private static List<MenuTree> buildMenuTreeByRecursive(List<MenuTree> source, List<MenuTree> target,
			String parentCode) {
		return source.stream().filter(e -> Objects.equals(parentCode, e.getParentCode())).reduce(target, (u, t) -> {
			t.setList(buildMenuTreeByRecursive(source, new ArrayList<>(), t.getCode()));
			u.add(t);
			return u;
		}, (u, t) -> u);
	}

	private synchronized void buildSeqAndCodeByParentCode(TbMenu menu, String pCode) {
		String code;
		int seq;
		// 查询最大编码
		String maxCode = tbMenuMapper.selectMaxCodeByParentCode(pCode);
		if (StringUtils.isEmpty(maxCode)) {
			code = pCode + "10";
			seq = 0;
		} else {
			String partOne = maxCode.substring(0, maxCode.length() - 2);
			String partTwo = maxCode.substring(maxCode.length() - 2);
			code = partOne + String.valueOf(NumberUtils.toInt(partTwo) + 1);
			seq = tbMenuMapper.selectMaxSeqByParentCode(pCode) + 1;
		}
		menu.setCode(code);
		menu.setSeq(seq);
	}

	private void buildTbMenuByParentCode(TbMenu menu, String pCode) {
		String parentCode, parentNum, fullCodes;
		int level;

		// parentCode 为空或者不为根节点
		if (StringUtils.isEmpty(menu.getParentCode()) || Objects.equals(pCode, "0")) {
			parentCode = "1";
			parentNum = "1";
			level = 1;
			fullCodes = "1" + ORG_FULLCODES_SEPARATE + menu.getCode() + ORG_FULLCODES_SEPARATE;

		} else { // 获取父级节点
			TbMenu pMenu = tbMenuMapper.selectOne(new QueryWrapper<TbMenu>().eq("code", pCode));
			if (Objects.equals(menu.getCode(), menu.getParentCode())) {
				throw new BussinessException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
			}
			parentCode = pMenu.getCode();
			parentNum = pMenu.getNum();
			level = pMenu.getLevel() + 1;
			fullCodes = pMenu.getFullCodes() + menu.getCode() + ORG_FULLCODES_SEPARATE;
		}
		// 开始塞值
		menu.setParentNum(parentNum);
		menu.setParentCode(parentCode);
		menu.setLevel(level);
		menu.setFullCodes(fullCodes);
	}

}
