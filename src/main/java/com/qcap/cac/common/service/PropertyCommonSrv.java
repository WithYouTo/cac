package com.qcap.cac.common.service;

import java.util.List;
import java.util.Map;

public interface PropertyCommonSrv {

	
	/**
	 * 根据登录人的workId查询所属公司
	 * 
	 * @Title: selectCompanyCodeByWorkId 
	 * @Description: TODO
	 * @param workId
	 * @return
	 * @return: String
	 */
	public String selectCompanyCodeByWorkId(String workId);
	
	
	/**
	 *  根据登录人查询    公司编码
	 * @Title: selectCompanyCodeByUserId 
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @return: String
	 */
	public String selectCompanyCodeByUserId(String userId);
	
	/**
	 * 根据用户ID查询所属部门的CODE
	 * 
	 * @Title: selectCodeByUserId 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: String
	 */
	public String selectCodeByUserId(String id);
	
	/**
	 * 根据公司全称查询公司别
	 * @Title: selectCompCode 
	 * @Description: TODO
	 * @param fullName
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public String selectCompCode(String fullName)throws Exception;
	
	
	/**
	 * 根据公司别展示部门信息
	 * @Title: listDeptInfo 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract List<Map> listDeptInfo(Map param) throws Exception;
	
	/**
	 * 根据工号查询用户部门ID
	 * @Title: selectUserDeptId 
	 * @Description: TODO
	 * @param workId
	 * @return
	 * @return: String
	 */
	String selectUserDeptId(String workId);
	
	
	/**
	 * 只适用于部门管理：物业费查询小区(除去公正路)
	 * @Title: getAreaListByParam 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> getAreaListByParam(Map param);
	
	/**
	 * 物业费查询下拉下去列表
	 * @Title: areaListDropDown 
	 * @Description: TODO
	 * @param workId
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> areaListDropDown(Map param);
	
	/**
	 * buildingListDropDown
	 * @param communityId
	 * @return
	 */
	List<Map<String, Object>> buildingListDropDown(String communityId);
	
	/**
	 * cellListDropDown
	 * @param blockId
	 * @return
	 */
	List<Map<String, Object>> cellListDropDown(String blockId);
	
	/**
	 * roomListDropDown
	 * @param unitId
	 * @return
	 */
	List<Map<String, Object>> roomListDropDown(String unitId);
	
	/**
	 * 公司下拉框(总公司不展示)
	 * @Title: selectSubCompanies 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	public abstract List<Map> selectSubCompanies(Map param) throws Exception;
	
	/**
	 * 从通用代码档中查询模板类型
	 * 
	 * @Title: selectTemplateTypes
	 * @Description: TODO
	 * @param configCode
	 * @return
	 * @throws Exception
	 * @return: List<String>
	 */
	List<Map> selectTemplateTypes(String configCode) throws Exception;
	
	/**
	 * 根据小区ID查询companyCode
	 * @param communityId
	 * @return
	 * @throws Exception
	 */
	String getCompanyCodeByCommunityId(String communityId) throws Exception;
	/**
	 * 根据用户ID查询account
	 * @Title: selectAccountByUserId 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @return: String
	 */
	String selectAccountByUserId(String id);
	
	/**
	 * 根据parentId查询地区名称
	 * @Title: selectLocationByParent 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	public List<Map> selectLocationByParent(Map param);
	
	
	/**
	 *   根据  楼宇 ID或者小区ID 查询 公司
	 * @Title: selectCompanycodeByBuildingCode 
	 * @Description: TODO
	 * @param buildingCode
	 * @return
	 * @return: String
	 */
	public String selectCompanycodeByBuildingCode(String buildingCode);
}
