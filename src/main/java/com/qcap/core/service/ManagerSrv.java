package com.qcap.core.service;

import com.qcap.core.model.Manager;
import com.qcap.core.poiEntity.ManagerPoiEntity;
import com.qcap.core.tips.Tip;

import java.util.List;
import java.util.Map;

public interface ManagerSrv {
    void insertManager(Manager manager);

    Manager getByAccount(String account);

    List<Map<String,Object>> selectUsers(String name, String beginTime, String endTime);

    Manager getByUserId(String userId);

    String getRoleNameById(String id);

    void updateManager(Manager manager);

    void setRoles(String userId, String roleIds);

    void setStatus(String userId, int code);

    void updateManagerPwd(Manager manager);

    List<ManagerPoiEntity> listManager();

    Tip setOrg(String userId, String orgIds);

    void insertManagerPoi(ManagerPoiEntity managerPoi);

    void insertManagerErrorDataPoi(ManagerPoiEntity managerPoi);

    List<ManagerPoiEntity> listErrorManagerByManagerId(String id);

    void delErrorManagerByManagerId(String id);
}
