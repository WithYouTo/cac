package com.qcap.core.service.impl;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.dao.OrgMapper;
import com.qcap.core.model.*;
import com.qcap.core.poiEntity.ManagerPoiEntity;
import com.qcap.core.service.ManagerSrv;
import com.qcap.core.tips.SuccessTip;
import com.qcap.core.tips.Tip;
import com.qcap.core.dao.ManagerMapper;
import com.qcap.core.model.Org;
import com.qcap.core.tips.ErrorTip;
import com.qcap.core.model.ManagerOrg;
import com.qcap.core.model.ManagerRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ManagerSrvImpl implements ManagerSrv {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public void insertManager(Manager manager) {
        managerMapper.insertManager(manager);
    }

    @Override
    public Manager getByAccount(String account) {
        return managerMapper.getByAccount(account);
    }

    @Override
    public List<Map<String, Object>> selectUsers(String name, String beginTime, String endTime) {
        List<Map<String, Object>> userList =  managerMapper.selectUsers(name,beginTime,endTime);
        for(Map manager:userList){
            if(manager.containsKey("id")) {
                String managerId = manager.get("id").toString();
                List<String> roles =  managerMapper.getRoleNameById(managerId);
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
    public Manager getByUserId(String userId) {
        return managerMapper.getByUserId(userId);
    }

    @Override
    public String getRoleNameById(String userId) {
        List<String> roles =  managerMapper.getRoleNameById(userId);
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
    public void updateManager(Manager manager) {
        managerMapper.updateManager(manager);
    }

    @Override
    public void setRoles(String userId, String roleIds) {
        // 删除该用户所有的角色
        this.managerMapper.deleteRolesById(userId);

        // 添加新的角色
        for (String id : roleIds.split(",")) {
            ManagerRole managerRole = new ManagerRole();
            managerRole.setManagerid(userId);
            managerRole.setRoleid(id);

            this.managerMapper.insertManagerRole(managerRole);
        }
    }

    @Override
    public void setStatus(String userId, int code) {
        managerMapper.updateStatus(userId,code);
    }

    @Override
    public void updateManagerPwd(Manager manager) {
        managerMapper.updateManagerPwd(manager);
    }

    @Override
    public List<ManagerPoiEntity> listManager() {
        return managerMapper.listManager();
    }

    @Override
    public Tip setOrg(String userId, String codes) {
        String[] cds = codes.split(",");
        if(cds.length>1){
            return new ErrorTip(CoreConstant.MANAGER_MULTI_ORG_CODE, CoreConstant.MANAGER_MULTI_ORG_MSG);
        }
        // 删除该用户所有的组织
        this.managerMapper.deleteOrgById(userId);
        // 添加新的角色
        for (String code : codes.split(",")) {
            Org org = this.orgMapper.getOrgByOrgCode(code);
            ManagerOrg managerOrg = new ManagerOrg();
            managerOrg.setManagerId(userId);
            managerOrg.setOrgId(org.getId());
            this.managerMapper.insertManagerOrg(managerOrg);
        }
        return new SuccessTip();
    }


    @Override
    public void insertManagerPoi(ManagerPoiEntity managerPoi) {
        this.managerMapper.insertManagerPoi(managerPoi);
        if(null != managerPoi.getOrgNum()){
            Org org = this.orgMapper.getOrgByNum(managerPoi.getOrgNum());
            ManagerOrg mo = new ManagerOrg();
            mo.setManagerId(managerPoi.getId());
            mo.setOrgId(org.getId());
            this.managerMapper.insertManagerOrg(mo);
        }
    }

    @Override
    public void insertManagerErrorDataPoi(ManagerPoiEntity managerPoi) {
        this.managerMapper.insertManagerErrorDataPoi(managerPoi);
    }

    @Override
    public List<ManagerPoiEntity> listErrorManagerByManagerId(String id) {
        return this.managerMapper.listErrorManagerByManagerId(id);
    }

    @Override
    public void delErrorManagerByManagerId(String id) {
        this.managerMapper.delErrorManagerByManagerId(id);
    }
}
