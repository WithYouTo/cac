package com.qcap.core.dao;



import com.qcap.core.poiEntity.OrgPoiExportEntity;
import com.qcap.core.model.Org;
import com.qcap.core.model.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrgMapper {
    List<Map<String,Object>> listOrg(@Param("name") String orgName);

    String checkOrgByNum(@Param("num") String num);

    String getMaxCodeByPcode(@Param("pcode") String pCode);

    Integer getMaxSeqByPcode(@Param("pcode") String pCode);

    Org getOrgByPCode(@Param("pcode") String pCode);

    Map<String,Object> selectOrgById(@Param("id") String id);

    void insertOrg(Org org);

    List<ZTreeNode> orgTreeList();

    void updateOrgById(Org org);

    Org getPorg(Org org);

    void updateOrg(Org org);

    List<Org> listChildOrgByPcode(@Param("pcode") String pcode);

    List<Map<String,Object>> getChildOrgByCode(@Param("code") String code);

    List<Map<String,Object>> getOrgManagerById(@Param("id") String orgId);

    void delOrgById(@Param("id") String orgId);

    List<String> getOrgCodeById(@Param("id") String userId);

    List<ZTreeNode> orgTreeListByOrgCode(String[] strArray);

    Org getOrgByOrgCode(@Param("code") String code);

    void updateOrgBySearchCode(@Param("name") String name, @Param("searchCode") String searchCode, @Param("searchName") String searchName, @Param("replaceName") String replaceName);

    List<Map<String,Object>> getRolesByOrgId(@Param("id") String orgId);

    List<OrgPoiExportEntity> listExportOrg();

    Org getOrgByNum(String orgNum);

    Org getOrgByOrgId(@Param("orgId") String orgId);

    void updateOrgSeqUp(@Param("pCode") String pCode, @Param("seq") Integer seq);

    void updateOrgSeqDown(@Param("pCode") String pCode, @Param("seq") Integer seq);

    List<Map<String,Object>> listUserByOrg(@Param("fullCodes") String fullCodes);
}
