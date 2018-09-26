package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import com.qcap.cac.model.Company;
import com.qcap.cac.model.Department;
import org.springframework.stereotype.Repository;

/** 
 * @ClassName: CompanyMapper 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月12日 上午11:08:30  
 */
@Repository
public interface CompanyMapper {
	
	
	/**
	 * 查询公司信息
	 * @Title: selectCompaniesByPage 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	public abstract List<Map<String,Object>> getCompaniesByPage(Map param);
	
	/**
	 * 根据主键查询公司信息
	 * @Title: selectDetailByPk 
	 * @Description: TODO
	 * @param tbbb08Id
	 * @return
	 * @
	 * @return: Map
	 */
	public abstract Map selectDetailByPk(String tbbb08Id);
	
	/**
	 * 查询某些字段是否存在相同的数据
	 * @Title: selectExistNum 
	 * @Description: TODO
	 * @param map
	 * @return
	 * @
	 * @return: String
	 */
	public abstract String selectExistNum(Map map);
	
	/**
	 * 查询TBBB04表编码是否重复
	 * @Title: selectByCode 
	 * @Description: TODO
	 * @param deptCode
	 * @return
	 * @
	 * @return: String
	 */
	public abstract String selectCodeNum(Map map);
	
	/**
	 * 查询物业公司数
	 * @Title: selectCompNum 
	 * @Description: TODO
	 * @param layer
	 * @return
	 * @
	 * @return: String
	 */
	public abstract String selectCompNum(String layer);
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
	 * @
	 * @return: String
	 */
	public abstract String selectNewCompCode(String newId);
	
	/**
	 * 根据TBBB08_ID_查询公司编码
	 * @Title: selectCompCodeByBb08Id 
	 * @Description: TODO
	 * @param tbbb08Id
	 * @return
	 * @
	 * @return: String
	 */
	public abstract String selectCompCodeByBb08Id(String tbbb08Id);
	
	/**
	 * 新增公司信息
	 * @Title: insertNewComp 
	 * @Description: TODO
	 * @param tbbb08
	 * @
	 * @return: void
	 */
	public abstract void insertNewComp(Company tbbb08);
	
	/**
	 * 修改公司信息
	 * @Title: updateCompanyInfo 
	 * @Description: TODO
	 * @param tbbb08
	 * @
	 * @return: void
	 */
	public abstract void updateCompanyInfo(Company tbbb08);
	
	/**
	 * 删除公司信息
	 * @Title: deleteInfoByPk 
	 * @Description: TODO
	 * @param tbbb08Id
	 * @
	 * @return: void
	 */
	public abstract void deleteInfoByPk(String companyCode);
	
	/**
	 * 公司下拉框(总公司不展示)
	 * @Title: selectSubCompanies 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @
	 * @return: List<Map>
	 */
	public abstract List<Map> selectSubCompanies(Map param);
	
	/**
	 * 增加组织结构
	 * @Title: insert 
	 * @Description: TODO
	 * @param bb04
	 * @
	 * @return: void
	 */
	public void insert(Department bb04);
	
	/**
	 * 修改公司名称
	 * @Title: updateCompanyName 
	 * @Description: TODO
	 * @param bb04
	 * @
	 * @return: void
	 */
	public void updateCompanyName(Map map);
	

	/**
	 * 根据id进行修改（修改字段使信息隐藏）
	 * @Title: deleteByPrimaryKey 
	 * @Description: TODO
	 * @param tbbb04Id
	 * @
	 * @return: void
	 */
	public void deleteByPrimaryKey(String tbbb04Id);
}
