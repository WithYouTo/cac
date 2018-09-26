package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.core.dao.ManagerMapper;
import com.qcap.core.dao.RoleMapper;
import com.qcap.core.model.ManagerRole;
import com.qcap.cac.dao.WebUserMapper;
import com.qcap.cac.model.TbUser;
import com.qcap.cac.service.WebUserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.common.dao.UserMapper;
import com.qcap.cac.model.ZTreeNode;

/** 
 * @ClassName: WebUserSrvImpl 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月20日 下午2:30:21  
 */
@Service
@Transactional
public class WebUserSrvImpl implements WebUserSrv{
	
	@Autowired
	private WebUserMapper webUserMapper;

	
	@Autowired 
	private UserMapper userMapper;
	
	 @Autowired
	private RoleMapper roleMapper;

	    @Autowired
	private ManagerMapper managerMapper;
	/** (non Javadoc) 
	 * @Title: selectUserByPage
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see com.whxx.auth.service.WebUserSrv#selectUserByPage(java.util.Map)
	 */
	@Override
	public List<Map> selectUserByPage(Map param) {
		// TODO Auto-generated method stub
		List<Map> userList=this.webUserMapper.selectUserByPage(param);
		
		 for(Map manager:userList){
	            if(manager.containsKey("id")) {
	                String managerId = manager.get("id").toString();
	                List<String> roles =  userMapper.getRoleNameById(managerId);
	                StringBuffer roleNames = new StringBuffer();
	                if(!roles.isEmpty()) {
	                    for (String roleName : roles) {
	                        roleNames.append(",");
	                        roleNames.append(roleName);
	                    }
	                    manager.put("roleId", roleNames.toString().substring(1));
	                }else{
	                    manager.put("roleId", roleNames.toString().substring(0));
	                }
	            }
	        }
	        return userList;
	}

	/** (non Javadoc) 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @throws Exception
	 * @see com.whxx.auth.service.WebUserSrv#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public void deleteByPrimaryKey(String id) throws Exception {
		// TODO Auto-generated method stub
		this.webUserMapper.deleteByPrimaryKey(id);
	}

	/** (non Javadoc) 
	 * @Title: insert
	 * @Description: TODO
	 * @param record
	 * @throws Exception
	 * @see com.whxx.auth.service.WebUserSrv#insert(TbUser)
	 */
	@Override
	public void insert(TbUser record) throws Exception {
		// TODO Auto-generated method stub
		this.webUserMapper.insert(record);
	}

	/** (non Javadoc) 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @see com.whxx.auth.service.WebUserSrv#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Map selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return this.webUserMapper.selectByPrimaryKey(id);
	}

	/** (non Javadoc) 
	 * @Title: updateByPrimaryKey
	 * @Description: TODO
	 * @param record
	 * @throws Exception
	 * @see com.whxx.auth.service.WebUserSrv#updateByPrimaryKey(TbUser)
	 */
	@Override
	public void updateByPrimaryKey(TbUser record) throws Exception {
		// TODO Auto-generated method stub
		this.webUserMapper.updateByPrimaryKey(record);
	}

	/** (non Javadoc) 
	 * @Title: selectPositionInfo
	 * @Description: TODO
	 * @param companyCode
	 * @return
	 * @throws Exception
	 * @see com.whxx.auth.service.WebUserSrv#selectPositionInfo(java.lang.String)
	 */
	@Override
	public List<Map> selectPositionInfo(String companyCode) throws Exception {
		// TODO Auto-generated method stub
		return this.webUserMapper.selectPositionInfo(companyCode);
	}

	/** (non Javadoc) 
	 * @Title: selectRoleInfo
	 * @Description: TODO
	 * @param companyCode
	 * @return
	 * @throws Exception
	 * @see com.whxx.auth.service.WebUserSrv#selectRoleInfo(java.lang.String)
	 */
	@Override
	public List<Map> selectRoleInfo(String companyCode) throws Exception {
		// TODO Auto-generated method stub
		return this.webUserMapper.selectRoleInfo(companyCode);
	}

	/** (non Javadoc) 
	 * @Title: selectExistNum
	 * @Description: TODO
	 * @param map
	 * @return
	 * @throws Exception
	 * @see WebUserSrv#selectExistNum(java.util.Map)
	 */
	@Override
	public String selectExistNum(Map map){
		// TODO Auto-generated method stub
		return this.webUserMapper.selectExistNum(map);
	}

	/** (non Javadoc) 
	 * @Title: changeUserStatus
	 * @Description: TODO
	 * @param param
	 * @see WebUserSrv#changeUserStatus(java.util.Map)
	 */
	@Override
	public void changeUserStatus(Map param) {
		// TODO Auto-generated method stub
		this.webUserMapper.changeUserStatus(param);
	}

	/** (non Javadoc) 
	 * @Title: updatePass
	 * @Description: TODO
	 * @param param
	 * @see WebUserSrv#updatePass(java.util.Map)
	 */
	@Override
	public void updatePass(Map param) {
		// TODO Auto-generated method stub
		this.webUserMapper.updatePass(param);
	}

	/** (non Javadoc) 
	 * @Title: roleTreeListByRoleIdAndCom
	 * @Description: TODO
	 * @param companyCode
	 * @param strArray
	 * @return
	 * @see WebUserSrv#roleTreeListByRoleIdAndCom(java.lang.String, java.lang.String[])
	 */
	@Override
	public List<ZTreeNode> roleTreeListByRoleIdAndCompanyCode(String userId,String companyCode){
		 List<String> roleids = this.managerMapper.getRoleIdById(userId);
//       String roleid = theUser.getRoleid();
       if (roleids.isEmpty()) {
           List<ZTreeNode> roleTreeList = this.webUserMapper.roleTreeListByCom(companyCode);
           return roleTreeList;
       } else {

           int size = roleids.size();
           String[] strArray =  (String[])roleids.toArray(new String[size]);
           List<ZTreeNode> roleTreeListByUserId = this.webUserMapper.roleTreeListByRoleIdAndCom(companyCode,strArray);
           return roleTreeListByUserId;
       }
		
		
	}

	/** (non Javadoc) 
	 * @Title: setRoles
	 * @Description: TODO
	 * @param userId
	 * @param roleIds
	 * @see WebUserSrv#setRoles(java.lang.String, java.lang.String)
	 */
	@Override
	public void setRoles(String userId, String roleIds) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				// 删除该用户所有的角色
		        this.userMapper.deleteRolesById(userId);

		        // 添加新的角色
		        for (String id : roleIds.split(",")) {
		            ManagerRole managerRole = new ManagerRole();
		            managerRole.setManagerid(userId);
		            managerRole.setRoleid(id);

		            this.userMapper.insertManagerRole(managerRole);
		        }
	}

	@Override
	public TbUser getByUserId(String id) {
		// TODO Auto-generated method stub
		return this.webUserMapper.getByUserId(id);
	}

	/** (non Javadoc) 
	 * @Title: deleteRoles
	 * @Description: TODO
	 * @param userId
	 * @see WebUserSrv#deleteRoles(java.lang.String)
	 */
	@Override
	public void deleteRoles(String userId) {
		// TODO Auto-generated method stub
		// 删除该用户所有的角色
        this.userMapper.deleteRolesById(userId);
	}

	/** (non Javadoc) 
	 * @Title: selectUserById
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see WebUserSrv#selectUserById(java.util.Map)
	 */
	@Override
	public Map selectUserById(Map param) {
		// TODO Auto-generated method stub
		Map manager= this.webUserMapper.selectUserById(param);
		
		 String managerId = manager.get("id").toString();
         List<String> roles =  userMapper.getRoleNameById(managerId);
         StringBuffer roleNames = new StringBuffer();
         if(!roles.isEmpty()) {
             for (String roleName : roles) {
                 roleNames.append(",");
                 roleNames.append(roleName);
             }
             manager.put("roleId", roleNames.toString().substring(1));
         }else{
             manager.put("roleId", roleNames.toString().substring(0));
         }
         return manager;
	}

	/** (non Javadoc) 
	 * @Title: selectWebUserIdByPhone
	 * @Description: TODO
	 * @param phone
	 * @return
	 * @see WebUserSrv#selectWebUserIdByPhone(java.lang.String)
	 */
	@Override
	public String selectWebUserIdByPhone(String phone) {
		// TODO Auto-generated method stub
		return this.webUserMapper.selectWebUserIdByPhone(phone);
	}
	
	
	
	

}
