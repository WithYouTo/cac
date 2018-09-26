package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.model.Company;

/**
 * 公司管理
 * @ClassName: CompanySrv 
 * @Description: TODO
 * @author: baojainxing
 * @date: 2018年4月12日 下午6:49:08
 */
public interface CompanySrv {
	
	
	public abstract List<Map<String,Object>> getCompaniesByPage(Map param);
	
	/**
	 * 根据主键查询公司信息
	 * @Title: selectDetailByPk 
	 * @Description: TODO
	 * @param tbbb08Id
	 * @return
	 * @throws Exception
	 * @return: Map
	 */
	public abstract Map selectDetailByPk(String tbbb08Id);
	
	/**
	 * 查询某些字段是否存在相同的数据
	 * @Title: selectExistNum 
	 * @Description: TODO
	 * @param map
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String selectExistNum(Map map);
	
	/**
	 * 查询tb_sys_company表编码是否重复
	 * @Title: selectByCode 
	 * @Description: TODO
	 * @param deptCode
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String selectCodeNum(Map map);
	/**
	 * 生成新的公司编码
	 * @Title: selectNewCompanyCode 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public abstract String selectNewCompanyCode();
	
	/**
	 * 生成新的公司编码
	 * @Title: selectNewCompCode 
	 * @Description: TODO
	 * @param newId
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String selectNewCompCode(String newId);
	
	/**
	 * 查询物业公司数
	 * @Title: selectCompNum 
	 * @Description: TODO
	 * @param layer
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String selectCompNum(String layer);
	
	/**
	 * 新增公司信息
	 * @Title: insertNewComp 
	 * @Description: TODO
	 * @param tbbb08
	 * @throws Exception
	 * @return: void
	 */
	public abstract void insertNewComp(Company tbbb08);
	
	/**
	 * 修改公司信息
	 * @Title: updateCompanyInfo 
	 * @Description: TODO
	 * @param tbbb08
	 * @throws Exception
	 * @return: void
	 */
	public abstract void updateCompanyInfo(Company tbbb08);
	
	/**
	 * 删除公司信息
	 * @Title: deleteInfoByPk 
	 * @Description: TODO
	 * @param tbbb08Id
	 * @throws Exception
	 * @return: void
	 */
	public abstract String deleteInfoByPk(String[] code);
	
	/**
	 * 公司下拉框(总公司不展示)
	 * @Title: selectSubCompanies 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	public abstract List<Map> selectSubCompanies(Map param);
	
}
