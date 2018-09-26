package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qcap.cac.model.Department;

/** 
 * @ClassName: DeptMapper 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月16日 上午9:01:27  
 */
@Repository
public interface DeptMapper {

	
	
	/**
	 * 分页查询
	 * @Title: selectPp01ByPage 
	 * @Description: TODO
	 * @param mapParam
	 * @return
	 * @throws Exception
	 * @return: List<Map<String,Object>>
	 */
	public abstract List<Map<String, Object>> selectPp01ByPage(Map<String, Object> mapParam) throws Exception;
	/**
	 * 增加组织结构
	 * @Title: insert 
	 * @Description: TODO
	 * @param bb04
	 * @throws Exception
	 * @return: void
	 */
	public void insert(Department bb04)throws Exception;
	
	
	/**
	 * 根据主键查询
	 * @Title: selectByPrimaryKey 
	 * @Description: TODO
	 * @param tbbb04Id
	 * @return
	 * @throws Exception
	 * @return: Tbbb04
	 */
	public Department selectByPrimaryKey(String tbbb04Id)throws Exception;
	
	/**
	 * 根据主键修改
	 * @Title: updateByPrimaryKey 
	 * @Description: TODO
	 * @param bb04
	 * @throws Exception
	 * @return: void
	 */
	public void updateByPrimaryKey(Department bb04)throws Exception;
	
	/**
	 * 修改公司名称
	 * @Title: updateCompanyName 
	 * @Description: TODO
	 * @param bb04
	 * @throws Exception
	 * @return: void
	 */
	public void updateCompanyName(Map map)throws Exception;
	
	/**
	 * 查询出所有的信息
	 * @Title: selectAll 
	 * @Description: TODO
	 * @return
	 * @throws Exception
	 * @return: List<Map<String,Object>>
	 */
	public List<Map<String,Object>> selectAll(Map param)throws Exception;
	
	/**
	 * 根据id进行修改（修改字段使信息隐藏）
	 * @Title: deleteByPrimaryKey 
	 * @Description: TODO
	 * @param tbbb04Id
	 * @throws Exception
	 * @return: void
	 */
	public void deleteByPrimaryKey(String tbbb04Id)throws Exception;
	
	/**
	 * 判断是否重名
	 * @Title: selectByName 
	 * @Description: TODO
	 * @param name
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int selectByName(String name)throws Exception;
	
	
	
	/**
	 * 查询最后的Id
	 * @Title: selectMaxId 
	 * @Description: TODO
	 * @param pId
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String selectMaxId(String pId )throws Exception;
	
	/**
	 * 修改有无子部门标识
	 * @Title: updateChild 
	 * @Description: TODO
	 * @param map
	 * @throws Exception
	 * @return: void
	 */
	public abstract void updateChild(Map<String, Object> map)throws Exception;
	
	/**
	 * 判断是否有孩子
	 * @Title: selectChild 
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public abstract int selectChild(String id)throws Exception;
	
	/**
	 * 新增：判断是否有子部门 
	 * @Title: queryChildDept 
	 * @Description: TODO
	 * @param tbbb04Id
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String queryChildDept(String tbbb04Id)throws Exception;
	
	/**
	 * 查询部门是否被使用
	 * @Title: selectIfUsed 
	 * @Description: TODO
	 * @param deptId
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public String selectIfUsed(String deptId) throws Exception;
	
	/**
	 * 判断是否重名 根据名称和父节点
	 * @Title: selectByName 
	 * @Description: TODO
	 * @param name
	 * @return
	 * @throws Exception
	 * @return: int
	 */
	public int selectByNameAndParentId(Map param)throws Exception;
	
	/**
	 * 根据当前登录人加载管理单位
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	public abstract List<Map> getManagementUnitList(String userId) throws Exception;
	
	
	/**
	 * 根据当前登录人加载楼宇
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	public abstract List<Map> getBuildingList(Map param) throws Exception;
}
