package com.qcap.core.dao;

import com.qcap.core.model.Manager;
import com.qcap.core.model.ManagerRole;
import com.qcap.core.poiEntity.ManagerPoiEntity;
import com.qcap.core.model.ManagerOrg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ManagerMapper {

    void insertManager(Manager manager);

    Manager getByAccount(String account);

    List<Map<String,Object>> selectUsers(@Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    Manager getByUserId(@Param("id") String userId);

    List<String> getRoleNameById(@Param("id") String userId);

    void updateManager(Manager manager);

    List<String> getRoleIdById(@Param("id") String userId);

    void deleteRolesById(@Param("id") String userId);

    void insertManagerRole(ManagerRole managerRole);

    void updateStatus(@Param("id") String userId, @Param("code") int code);

    void updateManagerPwd(Manager manager);

    List<ManagerPoiEntity> listManager();

    void deleteOrgById(@Param("id") String userId);

    void insertManagerOrg(ManagerOrg managerOrg);

    void insertManagerPoi(ManagerPoiEntity managerPoi);

    void insertManagerErrorDataPoi(ManagerPoiEntity managerPoi);

    List<ManagerPoiEntity> listErrorManagerByManagerId(String id);

    void delErrorManagerByManagerId(String id);
}