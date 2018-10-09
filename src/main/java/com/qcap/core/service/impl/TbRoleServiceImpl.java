package com.qcap.core.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.dao.TbManagerRoleMapper;
import com.qcap.core.dao.TbRoleMapper;
import com.qcap.core.dao.TbRoleMenuMapper;
import com.qcap.core.entity.TbManagerRole;
import com.qcap.core.entity.TbRole;
import com.qcap.core.entity.TbRoleMenu;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.service.ITbRoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2018-07-31
 */
@Service
public class TbRoleServiceImpl implements ITbRoleService {

	@Resource
	private TbRoleMapper tbRoleMapper;
	@Resource
	private TbRoleMenuMapper tbRoleMenuMapper;
	@Resource
	private TbManagerRoleMapper tbManagerRoleMapper;

	@Override
	public void getRoleList(IPage<TbRole> page, String roleName) {
		QueryWrapper<TbRole> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(roleName)) {
			wrapper.lambda().like(TbRole::getName, roleName + "%");
		}
		tbRoleMapper.selectPage(page, wrapper);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertRole(TbRole tbRole) throws Exception {
		// 判断是否存在该编号
		TbRole role = tbRoleMapper.selectOne(new QueryWrapper<TbRole>().lambda().eq(TbRole::getName, tbRole.getName()));
		if (role != null) {
			throw new Exception("该角色名称重复！");
		}
		TbRole newRole = new TbRole();
		int maxNum = tbRoleMapper.selectMaxNum();
		newRole.setName(tbRole.getName());
		newRole.setRemark(tbRole.getRemark());
		newRole.setNum(maxNum);
		newRole.setStatus(1);
		tbRoleMapper.insert(newRole);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateRole(TbRole tbRole) throws Exception {
		TbRole role = tbRoleMapper.selectById(tbRole.getId());
		role.setName(tbRole.getName());
		role.setRemark(tbRole.getRemark());
		tbRoleMapper.updateById(tbRole);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteRoleById(String roleId) {
		TbRole role = tbRoleMapper.selectById(roleId);
		// 删除该角色所有的权限
		List<String> roleMenuIds = tbRoleMenuMapper
				.selectList(new QueryWrapper<TbRoleMenu>().lambda().eq(TbRoleMenu::getRoleNum, role.getNum())).stream()
				.map(TbRoleMenu::getId).collect(Collectors.toList());
		if (!roleMenuIds.isEmpty()) {

			tbRoleMenuMapper.deleteBatchIds(roleMenuIds);
		}
		// 删除与该角色关联的用户
		List<String> managerRoleIds = tbManagerRoleMapper
				.selectList(new QueryWrapper<TbManagerRole>().lambda().eq(TbManagerRole::getRoleId, role.getNum()))
				.stream().map(TbManagerRole::getRoleId).collect(Collectors.toList());
		if (!managerRoleIds.isEmpty()) {
			tbManagerRoleMapper.deleteBatchIds(managerRoleIds);
		}
		tbRoleMapper.deleteById(roleId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void assignMenuForRole(String roleId, String menuIds) {
		TbRole role = tbRoleMapper.selectById(roleId);
		// 删除该角色所有的权限
		tbRoleMenuMapper.delete(new UpdateWrapper<TbRoleMenu>().lambda().eq(TbRoleMenu::getRoleNum, role.getNum()));
		// 为角色配置新菜单
		Arrays.stream(menuIds.split(",")).filter(StringUtils::isNotEmpty).forEach(e -> {
			TbRoleMenu roleMenu = new TbRoleMenu();
			roleMenu.setRoleNum(role.getNum());
			roleMenu.setMenuCode(e);
			tbRoleMenuMapper.insert(roleMenu);
		});
	}

	@Override
	public List<ZTreeNode> getRoleTreeListByManagerId(String managerId) {
		return tbRoleMapper.getRoleTreeListByManagerId(managerId);
	}
}
