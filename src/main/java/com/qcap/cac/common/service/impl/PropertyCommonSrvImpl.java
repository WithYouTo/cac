package com.qcap.cac.common.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.cac.common.service.PropertyCommonSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.common.dao.PropertyCommonMapper;

@Service
@Transactional
public class PropertyCommonSrvImpl implements PropertyCommonSrv {

	@Autowired
	private PropertyCommonMapper propertyCommonMapper;
	
	@Override
	public String selectCompanyCodeByWorkId(String workId) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectCompanyCodeByWorkId(workId);
	}

	/** (non Javadoc) 
	 * @Title: selectCompCode
	 * @Description: TODO
	 * @param fullName
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectCompCode(java.lang.String)
	 */
	@Override
	public String selectCompCode(String fullName) throws Exception {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectCompCode(fullName);
	}

	/** (non Javadoc) 
	 * @Title: listDeptInfo
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#listDeptInfo(java.util.Map)
	 */
	@Override
	public List<Map> listDeptInfo(Map param) throws Exception {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.listDeptInfo(param);
	}

	/** (non Javadoc) 
	 * @Title: selectUserDeptId
	 * @Description: TODO
	 * @param workId
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectUserDeptId(java.lang.String)
	 */
	@Override
	public String selectUserDeptId(String workId) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectUserDeptId(workId);
	}

	/** (non Javadoc) 
	 * @Title: getAreaListByParam
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#getAreaListByParam(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getAreaListByParam(Map param) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.getAreaListByParam(param);
	}

	/** (non Javadoc) 
	 * @Title: areaListDropDown
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#areaListDropDown(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> areaListDropDown(Map param) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.areaListDropDown(param);
	}

	/** (non Javadoc) 
	 * @Title: selectSubCompanies
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectSubCompanies(java.util.Map)
	 */
	@Override
	public List<Map> selectSubCompanies(Map param) throws Exception {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectSubCompanies(param);
	}

	/** (non Javadoc) 
	 * @Title: selectTemplateTypes
	 * @Description: TODO
	 * @param configCode
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectTemplateTypes(java.lang.String)
	 */
	@Override
	public List<Map> selectTemplateTypes(String configCode) throws Exception {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectTemplateTypes(configCode);
	}

	@Override
	public String getCompanyCodeByCommunityId(String communityId) throws Exception {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.getCompanyCodeByCommunityId(communityId);
	}

	@Override
	public List<Map<String, Object>> buildingListDropDown(String communityId) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.buildingListDropDown(communityId);
	}

	@Override
	public List<Map<String, Object>> cellListDropDown(String blockId) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.cellListDropDown(blockId);
	}

	@Override
	public List<Map<String, Object>> roomListDropDown(String unitId) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.roomListDropDown(unitId);
	}

	/** (non Javadoc) 
	 * @Title: selectAccountByUserId
	 * @Description: TODO
	 * @param id
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectAccountByUserId(java.lang.String)
	 */
	@Override
	public String selectAccountByUserId(String id) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectAccountByUserId(id);
	}

	/** (non Javadoc) 
	 * @Title: selectLocationByParent
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectLocationByParent(java.util.Map)
	 */
	@Override
	public List<Map> selectLocationByParent(Map param) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectLocationByParent(param);
	}

	@Override
	public String selectCodeByUserId(String id) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectCodeByUserId(id);
	}

	/** (non Javadoc) 
	 * @Title: selectCompanyCodeByUserId
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectCompanyCodeByUserId(java.lang.String)
	 */
	@Override
	public String selectCompanyCodeByUserId(String userId) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectCompanyCodeByUserId(userId);
	}

	/** (non Javadoc) 
	 * @Title: selectCompanycodeByBuildingCode
	 * @Description: TODO
	 * @param buildingCode
	 * @return
	 * @see com.whxx.esp.common.service.PropertyCommonSrv#selectCompanycodeByBuildingCode(java.lang.String)
	 */
	@Override
	public String selectCompanycodeByBuildingCode(String buildingCode) {
		// TODO Auto-generated method stub
		return this.propertyCommonMapper.selectCompanycodeByBuildingCode(buildingCode);
	}

}
