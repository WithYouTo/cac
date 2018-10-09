package com.qcap.core.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.common.ManagerStatus;
import com.qcap.core.dao.TbManagerMapper;
import com.qcap.core.dao.TbManagerOrgMapper;
import com.qcap.core.dao.TbManagerRoleMapper;
import com.qcap.core.dao.TbOrgMapper;
import com.qcap.core.dao.TbRoleMapper;
import com.qcap.core.entity.TbManager;
import com.qcap.core.entity.TbManagerOrg;
import com.qcap.core.entity.TbManagerRole;
import com.qcap.core.entity.TbOrg;
import com.qcap.core.entity.TbRole;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.log.LogManager;
import com.qcap.core.log.factory.LogTaskFactory;
import com.qcap.core.service.ITbManagerService;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.Md5Util;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.jwt.JwtTokenUtil;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2018-07-29
 */
@Service
public class TbManagerServiceImpl implements ITbManagerService {
	@Resource
	private TbManagerMapper tbManagerMapper;
	@Resource
	private TbRoleMapper tbRoleMapper;
	@Resource
	private TbManagerRoleMapper tbManagerRoleMapper;
	@Resource
	private TbManagerOrgMapper tbManagerOrgMapper;
	@Resource
	private TbOrgMapper tbOrgMapper;

	@Resource
	private JwtTokenUtil jwtTokenUtil;

	@Resource
	private RedisUtil redisUtil;

	@Override
	public String login(String account, String password) throws Exception {
		String ip = AppUtils.getCurrentRequest() == null ? "" : AppUtils.getCurrentRequest().getRemoteAddr();
		TbManager tbManager = tbManagerMapper.selectOne(new QueryWrapper<TbManager>().eq("account", account));
		// Optional<TbManager> op = Optional.ofNullable(
		// tbManagerMapper.selectOne((new
		// QueryWrapper<TbManager>().lambda().eq(TbManager::getAccount,
		// account))));
		// if (op.isPresent()) {
		// TbManager tbManager = op.get();
		if (tbManager != null) {
			if (checkPassword(tbManager.getPassword(), password, tbManager.getSalt())) {
				String managerId = tbManager.getId();
				tbManager.setPassword("");
				tbManager.setSalt("");
				// 存储token的过期时间和用户ID
				redisUtil.set(AppUtils.getApplicationName() + ":manager:" + managerId, tbManager);
				LogManager.me().executeLog(LogTaskFactory.loginLog(tbManager.getAccount(), ip));
				return jwtTokenUtil.doGenerateToken(managerId);
			} else {
				LogManager.me().executeLog(LogTaskFactory.loginLog(account, "密码错误", ip));
				throw new Exception("密码错误！");
			}
		} else {
			LogManager.me().executeLog(LogTaskFactory.loginLog(account, "用户不存在", ip));

			throw new Exception("用户不存在！");
		}
	}

	@Override
	public Optional<TbManager> getTbManagerFromToken(String token) {
		String id = jwtTokenUtil.getUsernameFromToken(token);
		return Optional.ofNullable(tbManagerMapper.selectById(id));
	}

	@Override
	public void getUserList(IPage<Map<String, Object>> page, Map<String, Object> params) {
		List<Map<String, Object>> list = tbManagerMapper.getTbMangerList(page, params);
		for (Map<String, Object> map : list) {
			String id = Objects.toString(map.get("id"));
			String roleName = tbRoleMapper.getRoleListByManagerId(id).stream().map(TbRole::getName)
					.collect(Collectors.joining(","));
			map.put("roleName", roleName);
		}
		page.setRecords(list);
	}

	@Override
	public void insertItem(TbManager manager) throws Exception {
		TbManager tbManager = tbManagerMapper
				.selectOne(new QueryWrapper<TbManager>().lambda().eq(TbManager::getAccount, manager.getAccount()));
		if (tbManager != null) {
			throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
		}
		// 完善信息
		manager.setSalt(Md5Util.getSalt());
		manager.setPassword(Md5Util.encrypt(manager.getPassword(), manager.getSalt()));
		manager.setStatus(ManagerStatus.OK.getCode());
		tbManagerMapper.insert(manager);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateItem(TbManager manager) {
		TbManager old = tbManagerMapper.selectById(manager.getId());
		old.setName(manager.getName());
		old.setMail(manager.getMail());
		old.setPhone(manager.getPhone());
		tbManagerMapper.updateById(old);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteManagerById(String id) {
		TbManager old = tbManagerMapper.selectById(id);
		if (old != null) {
			tbManagerMapper.update(old, new UpdateWrapper<TbManager>().set("status", "3").eq("id", id));
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void buildRoleForManager(String managerId, Collection<String> roleNumbers) {
		// 删除该用户所有的角色
		tbManagerRoleMapper.delete(new UpdateWrapper<TbManagerRole>().eq("manager_id", managerId));
		// 添加新的角色
		roleNumbers.forEach(e -> {
			TbManagerRole managerRole = new TbManagerRole();
			managerRole.setManagerId(managerId);
			managerRole.setRoleId(e);
			tbManagerRoleMapper.insert(managerRole);
		});

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void buildOrfForManager(String managerId, String orgId) {
		// 删除该用户所有的组织
		tbManagerOrgMapper.delete(new UpdateWrapper<TbManagerOrg>().lambda().eq(TbManagerOrg::getManagerId, managerId));
		// 添加新的组织
		TbOrg org = tbOrgMapper.selectOne(new QueryWrapper<TbOrg>().lambda().eq(TbOrg::getCode, orgId));
		if (org != null) {
			TbManagerOrg managerOrg = new TbManagerOrg();
			managerOrg.setManagerId(managerId);
			managerOrg.setOrgId(org.getId());
			tbManagerOrgMapper.insert(managerOrg);
		}

	}

	private boolean checkPassword(String encryptPassword, String password, String salt) {
		return Objects.equals(encryptPassword, Md5Util.encrypt(password, salt));
	}
}
