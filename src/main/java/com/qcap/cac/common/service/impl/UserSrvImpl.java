package com.qcap.cac.common.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.core.dao.OrgMapper;
import com.qcap.core.model.ManagerRole;
import com.qcap.core.model.Org;
import com.qcap.core.poiEntity.ManagerPoiEntity;
import com.qcap.core.tips.ErrorTip;
import com.qcap.core.tips.SuccessTip;
import com.qcap.core.tips.Tip;
import com.qcap.cac.common.service.UserSrv;
import com.qcap.cac.model.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ManagerOrg;
import com.qcap.cac.common.dao.UserMapper;

@Service
@Transactional
public class UserSrvImpl implements UserSrv {

	@Autowired
	private UserMapper userMapper;
	 
    @Autowired
    private OrgMapper orgMapper;
    
	@Override
	public void insertManagerRole(ManagerRole managerRole) {
		// TODO Auto-generated method stub
		userMapper.insertManagerRole(managerRole);
	}

	@Override
	public String getRoleNameById(String userId) {
		// TODO Auto-generated method stub
		List<String> roles = userMapper.getRoleNameById(userId);
        StringBuffer roleNames = new StringBuffer();
        if(!roles.isEmpty()){
            for(String roleName:roles){
                roleNames.append(",");
                roleNames.append(roleName);

            }
            return  roleNames.toString().substring(1);
        }
        return  roleNames.toString().substring(0);
	}

	@Override
	public List<String> getRoleIdById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.getRoleIdById(userId);
	}

	@Override
	public TbUser getByAccount(String account) {
		// TODO Auto-generated method stub
		return userMapper.getByAccount(account);
	}

	@Override
	public TbUser getById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.getById(userId);
	}

	@Override
	public TbUser getByPhone(String mobile) {
		// TODO Auto-generated method stub
		return userMapper.getByPhone(mobile);
	}

	@Override
	public List<Map<String, Object>> selectUsers(String name, String beginTime, String endTime) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> userList =  userMapper.selectUsers(name,beginTime,endTime);
        for(Map manager:userList){
            if(manager.containsKey("id")) {
                String managerId = manager.get("id").toString();
                List<String> roles =  userMapper.getRoleNameById(managerId);
                StringBuffer roleNames = new StringBuffer();
                if(!roles.isEmpty()) {
                    for (String roleName : roles) {
                        roleNames.append(",");
                        roleNames.append(roleName);
                    }
                    manager.put("roleName", roleNames.toString().substring(1));
                }else{
                    manager.put("roleName", roleNames.toString().substring(0));
                }
            }
        }
        return userList;
	}

	@Override
	public List<ManagerPoiEntity> listManager() {
		// TODO Auto-generated method stub
		return userMapper.listManager();
	}

	@Override
	public void insertUser(TbUser tbUser) {
		// TODO Auto-generated method stub
		userMapper.insertUser(tbUser);
	}

	@Override
	public void updateUser(TbUser tbUser) {
		// TODO Auto-generated method stub
		userMapper.updateUser(tbUser);
	}

	@Override
	public void insertManagerOrg(ManagerOrg managerOrg) {
		// TODO Auto-generated method stub
		userMapper.insertManagerOrg(managerOrg);
	}

	@Override
	public void deleteRolesById(String userId) {
		// TODO Auto-generated method stub
		userMapper.deleteRolesById(userId);
	}

	@Override
	public void updateStatus(String userId, int code) {
		// TODO Auto-generated method stub
		userMapper.updateStatus(userId, code);
	}

	@Override
	public void deleteOrgById(String userId) {
		// TODO Auto-generated method stub
		userMapper.deleteOrgById(userId);
	}

	@Override
	public void setRoles(String userId, String roleIds) {
		// TODO Auto-generated method stub
		// 删除该用户所有的角色
        this.userMapper.deleteRolesById(userId);

        // 添加新的角色
        for (String id : roleIds.split(",")) {
            ManagerRole managerRole = new ManagerRole();
            managerRole.setManagerid(userId);
            managerRole.setRoleid(id);

            this.userMapper.insertManagerRole(managerRole);
        }
	}

	@Override
	public void setStatus(String userId, int code) {
		// TODO Auto-generated method stub
		userMapper.updateStatus(userId,code);
	}

	@Override
	public Tip setOrg(String userId, String orgIds) {
		// TODO Auto-generated method stub
		String[] cds = orgIds.split(",");
        if(cds.length>1){
            return new ErrorTip(CoreConstant.MANAGER_MULTI_ORG_CODE, CoreConstant.MANAGER_MULTI_ORG_MSG);
        }
        // 删除该用户所有的组织
        this.userMapper.deleteOrgById(userId);
        // 添加新的角色
        for (String code : orgIds.split(",")) {
            Org org = this.orgMapper.getOrgByOrgCode(code);
            ManagerOrg managerOrg = new ManagerOrg();
            managerOrg.setManagerId(userId);
            managerOrg.setOrgId(org.getId());
            this.userMapper.insertManagerOrg(managerOrg);
        }
        return new SuccessTip();
	}

}
