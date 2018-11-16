package com.qcap.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.common.ManagerStatus;
import com.qcap.core.dao.*;
import com.qcap.core.entity.*;
import com.qcap.core.log.LogManager;
import com.qcap.core.log.factory.LogTaskFactory;
import com.qcap.core.service.ITbManagerService;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.Md5Util;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author PH
 * @since 2018-07-29
 */
@Service
@Transactional
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
	private TbUserInfoMapper tbUserInfoMapper;
	@Resource
	private JwtTokenUtil jwtTokenUtil;

	@Resource
	private RedisUtil redisUtil;

	@Override
	public String login(String account, String password) throws Exception {
		String ip = AppUtils.getCurrentRequest() == null ? "" : AppUtils.getCurrentRequest().getRemoteAddr();
		TbManager tbManager = tbManagerMapper.selectOne(new QueryWrapper<TbManager>().eq("account", account));

		if (tbManager != null) {
			if (checkPassword(tbManager.getPassword(), password, tbManager.getSalt())) {
				String managerId = tbManager.getId();
				tbManager.setPassword("");
				tbManager.setSalt("");

				String str= JSONObject.toJSONString(tbManager);
				List<String> projectCodes = this.tbManagerMapper.getprojectCodesByManagerId(managerId);

//				String projectCodestr = StringUtils.join(projectCodes, ",");
				// 存储token的过期时间和用户ID
//				redisUtil.set(AppUtils.getApplicationName() + ":manager:" + managerId, tbManager);
				redisUtil.set(AppUtils.getApplicationName() + ":manager:" + managerId, str);
				redisUtil.set(AppUtils.getApplicationName() + ":programCodes:" + managerId, projectCodes);
//				redisUtil.set(AppUtils.getApplicationName()+"managerName:" + managerId, tbManager.getName());
				LogManager.me().executeLog(LogTaskFactory.loginLog(tbManager.getAccount(), ip));
				String token = jwtTokenUtil.doGenerateToken(managerId);
				redisUtil.set(AppUtils.getApplicationName() + ":token:" + managerId, token,180);
				return token;
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
		String programCode = params.get("programCode").toString();
		if("".equals(programCode)){
			String id = AppUtils.getLoginUserId();
			String orgCode = tbManagerOrgMapper.getOrgFullCodeByUserId(id);
			params.put("orgCode",orgCode);
		}
		List<Map<String, Object>> list = tbManagerMapper.getTbMangerList(page, params);
		for (Map<String, Object> map : list) {
			String id = Objects.toString(map.get("id"));
			String workStatus = Objects.toString(map.get("workStatus"));
			String roleName = tbRoleMapper.getRoleListByManagerId(id).stream().map(TbRole::getName)
					.collect(Collectors.joining(","));
			map.put("workStatusName",CommonConstant.USER_WORK_STATUS.get(workStatus));
			map.put("roleName", roleName);
		}
		page.setRecords(list);
	}

	@Override
	public void insertItem(UserInsertDto userInsertDto) throws Exception{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map = new HashMap<String,Object>();

		TbManager manager = new TbManager();
		manager.setId(UUIDUtils.getUUID());
		manager.setMail(userInsertDto.getEmail());
		manager.setPhone(userInsertDto.getMobile());
		manager.setAccount(userInsertDto.getWorkNo());
		manager.setName(userInsertDto.getUserName());

		try {
			TbUserInfo userInfo = new TbUserInfo();
			BeanUtils.copyProperties(userInsertDto, userInfo);

			TbManager tbManager = tbManagerMapper
					.selectOne(new QueryWrapper<TbManager>().eq("account", manager.getAccount()));
			if (tbManager != null) {
//				throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
				throw new BaseException(CommonCodeConstant.USER_ALREADY_REG);
			}
			// 完善信息
			manager.setSalt(Md5Util.getSalt());
			manager.setPassword(Md5Util.encrypt(CoreConstant.SYS_DEFAULT_PASSWORD, manager.getSalt()));
			manager.setStatus(ManagerStatus.OK.getCode());

			if(!Objects.isNull(userInsertDto.getBirth()) && !("").equals(userInsertDto.getBirth())){
				Date birth = format.parse(userInsertDto.getBirth());
				userInfo.setBirth(birth);
			}
			if(!Objects.isNull(userInsertDto.getWorkDate()) && !("").equals(userInsertDto.getWorkDate())) {
				Date workDate = format.parse(userInsertDto.getWorkDate());
				userInfo.setWorkDate(workDate);
			}
			userInfo.setUserInfoId(UUIDUtils.getUUID());
			userInfo.setUserId(manager.getId());

			EntityTools.setCreateEmpAndTime(userInfo);
			tbManagerMapper.insert(manager);
			this.tbUserInfoMapper.insert(userInfo);
		}catch(ParseException e){
			throw new RuntimeException();
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateItem(UserInsertDto userInsertDto) {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		TbManager manager = new TbManager();
		manager.setId(userInsertDto.getUserId());
		manager.setMail(userInsertDto.getEmail());
		manager.setPhone(userInsertDto.getMobile());
		manager.setName(userInsertDto.getUserName());

		try {
			TbUserInfo userInfo = new TbUserInfo();
			BeanUtils.copyProperties(userInsertDto,userInfo);
			if(!Objects.isNull(userInsertDto.getBirth()) && !("").equals(userInsertDto.getBirth())){
				Date birth = format.parse(userInsertDto.getBirth());
				userInfo.setBirth(birth);
			}
			if(!Objects.isNull(userInsertDto.getWorkDate()) && !("").equals(userInsertDto.getWorkDate())) {
				Date workDate = format.parse(userInsertDto.getWorkDate());
				userInfo.setWorkDate(workDate);
			}
			EntityTools.setUpdateEmpAndTime(userInfo);

			tbManagerMapper.updateById(manager);
			this.tbUserInfoMapper.updateById(userInfo);
		}catch(ParseException e){
			throw new RuntimeException();
		}
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

	@Override
	public void changePassword(TbManager mgr, String newPwd, String oldPwd) {
		TbManager tbManager = tbManagerMapper.selectOne(new QueryWrapper<TbManager>().eq("account", mgr.getAccount()));

		if (tbManager != null) {
			if (checkPassword(tbManager.getPassword(), oldPwd, tbManager.getSalt())) {
				String newSalt = Md5Util.getSalt();
				String newMd5 = Md5Util.encrypt(newPwd, newSalt);
				mgr.setSalt(newSalt);
				mgr.setPassword(newMd5);
				this.tbManagerMapper.updateManagerPwd(mgr);
			} else {
				throw new BaseException("密码错误！");
			}
		} else {
			throw new BaseException("用户不存在！");
		}
	}

	@Override
	public void resetPassword(String account) {
		TbManager mgr = new TbManager();
		String newSalt = Md5Util.getSalt();
		String newMd5 = Md5Util.encrypt(CoreConstant.SYS_DEFAULT_PASSWORD, newSalt);
		mgr.setAccount(account);
		mgr.setSalt(newSalt);
		mgr.setPassword(newMd5);

		EntityTools.setUpdateEmpAndTime(mgr);
		this.tbManagerMapper.updateManagerPwd(mgr);
	}

	private boolean checkPassword(String encryptPassword, String password, String salt) {
		return Objects.equals(encryptPassword, Md5Util.encrypt(password, salt));
	}
}
