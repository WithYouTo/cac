package com.qcap.cac.common.service;

import java.util.List;
import java.util.Map;

import com.qcap.core.model.ManagerRole;
import com.qcap.cac.model.TbUser;
import org.apache.ibatis.annotations.Param;

import com.qcap.core.model.ManagerOrg;
import com.qcap.core.poiEntity.ManagerPoiEntity;
import com.qcap.core.tips.Tip;

public interface UserSrv {

	void insertManagerRole(ManagerRole managerRole);
	
	String getRoleNameById(@Param("id")String userId);

	List<String> getRoleIdById(@Param("id")String userId);
	
	TbUser getByAccount(String account);
	
	TbUser getById(@Param("id")String userId);
	
	TbUser getByPhone(String mobile);

    List<Map<String,Object>> selectUsers(@Param("name")String name, @Param("beginTime")String beginTime, @Param("endTime")String endTime);

    List<ManagerPoiEntity> listManager();
    
    void insertUser(TbUser tbUser);
    
    void updateUser(TbUser tbUser);
    
    void setRoles(String userId, String roleIds);

    void setStatus(String userId, int code);
    
    void insertManagerOrg(ManagerOrg managerOrg);
    
    void deleteRolesById(@Param("id")String userId);

    void updateStatus(@Param("id")String userId,@Param("code") int code);

    void deleteOrgById(@Param("id")String userId);
    
    Tip setOrg(String userId, String orgIds);
}
