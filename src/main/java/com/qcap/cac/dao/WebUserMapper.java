package com.qcap.cac.dao;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qcap.cac.model.TbUser;
import com.qcap.cac.model.ZTreeNode;

/**
 * 用户管理  用于   web系统用户
 * @ClassName: UserMapper 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月20日 下午1:56:01
 */
@Repository
public interface WebUserMapper {
	
	
	/**
	 * 查询用户信息
	 * @Title: selectUserByPage 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: List<Map>
	 */
	List<Map> selectUserByPage(Map param);
    

   /**
    * 删除用户信息
    * @Title: deleteByPrimaryKey 
    * @Description: TODO
    * @param id
    * @throws Exception
    * @return: void
    */
    void deleteByPrimaryKey(String id) throws Exception;

   /**
    * 新增用户信息
    * @Title: insert 
    * @Description: TODO
    * @param record
    * @throws Exception
    * @return: void
    */
    void insert(TbUser record) throws Exception;

    /**
     * 根据主键查询用户信息
     * @Title: selectByPrimaryKey 
     * @Description: TODO
     * @param id
     * @return
     * @return: TbUser
     */
    Map selectByPrimaryKey(String id);

    /**
     * 根据主键查询用户信息
     * @param id
     * @return
     */
    TbUser getByUserId(String id);
    
    /**
     * 更新用户信息
     * @Title: updateByPrimaryKey 
     * @Description: TODO
     * @param record
     * @throws Exception
     * @return: void
     */
    void updateByPrimaryKey(TbUser record) throws Exception;
    
    
    /**
     * 根据公司别查询职位信息
     * @Title: selectPositionInfo 
     * @Description: TODO
     * @param companyCode
     * @return
     * @throws Exception
     * @return: List<Map>
     */
  	public abstract List<Map> selectPositionInfo(String companyCode) throws Exception;
  	
  	
  	/**
     * 根据公司别查询角色信息
     * @Title: selectPositionInfo 
     * @Description: TODO
     * @param companyCode
     * @return
     * @throws Exception
     * @return: List<Map>
     */
  	public abstract List<Map> selectRoleInfo(String companyCode) throws Exception;
  	
  	
  	/**
	 * 防止工号，电话号码，身份证号重复
	 * 
	 * @Title: selectExistNum
	 * @Description: TODO
	 * @param str
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public abstract String selectExistNum(Map map);
	
	/**
	 * 改变用户的状态
	 * @Title: changeUserStatus 
	 * @Description: TODO
	 * @param param
	 * @return: void
	 */
	public void changeUserStatus(Map param);
	
	/**
	 * 更新密码
	 * @Title: updatePass 
	 * @Description: TODO
	 * @param param
	 * @return: void
	 */
	public void updatePass(Map param);
	
	 List<ZTreeNode> roleTreeListByRoleIdAndCom(@Param("companyCode")String companyCode,@Param("array")String[] strArray);
	
	 List<ZTreeNode> roleTreeListByCom(@Param("companyCode")String companyCode);
	 
	public Map selectUserById(Map param);
	
	public String selectWebUserIdByPhone(@Param("phone")String phone);
}