package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dao.DeptMapper;
import com.qcap.cac.model.Department;
import com.qcap.cac.service.DeptSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @ClassName: DeptSrvImpl 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月16日 上午9:53:51  
 */
@Service
@Transactional
public class DeptSrvImpl implements DeptSrv {
	
	
	@Autowired
	private DeptMapper deptMapper;

	/** (non Javadoc) 
	 * @Title: insert
	 * @Description: TODO
	 * @param bb04
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#insert(com.whxx.esp.model.Department)
	 */
	@Override
	public void insert(Department bb04) throws Exception {
		// TODO Auto-generated method stub
		this.deptMapper.insert(bb04);
	}

	/** (non Javadoc) 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param tbbb04Id
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public Department selectByPrimaryKey(String tbbb04Id) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectByPrimaryKey(tbbb04Id);
	}

	/** (non Javadoc) 
	 * @Title: updateByPrimaryKey
	 * @Description: TODO
	 * @param bb04
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#updateByPrimaryKey(com.whxx.esp.model.Department)
	 */
	@Override
	public void updateByPrimaryKey(Department bb04) throws Exception {
		// TODO Auto-generated method stub
		this.deptMapper.updateByPrimaryKey(bb04);
	}

	/** (non Javadoc) 
	 * @Title: selectAll
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectAll(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> selectAll(Map param) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectAll(param);
	}

	/** (non Javadoc) 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param tbbb04Id
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public void deleteByPrimaryKey(String tbbb04Id) throws Exception {
		// TODO Auto-generated method stub
		this.deptMapper.deleteByPrimaryKey(tbbb04Id);
	}

	/** (non Javadoc) 
	 * @Title: selectByName
	 * @Description: TODO
	 * @param name
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectByName(java.lang.String)
	 */
	@Override
	public int selectByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectByName(name);
	}

	/** (non Javadoc) 
	 * @Title: selectMaxId
	 * @Description: TODO
	 * @param pId
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectMaxId(java.lang.String)
	 */
	@Override
	public String selectMaxId(String pId) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectMaxId(pId);
	}

	/** (non Javadoc) 
	 * @Title: updateChild
	 * @Description: TODO
	 * @param map
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#updateChild(java.util.Map)
	 */
	@Override
	public void updateChild(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		this.deptMapper.updateChild(map);
	}

	/** (non Javadoc) 
	 * @Title: selectChild
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectChild(java.lang.String)
	 */
	@Override
	public int selectChild(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectChild(id);
	}

	/** (non Javadoc) 
	 * @Title: queryChildDept
	 * @Description: TODO
	 * @param tbbb04Id
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#queryChildDept(java.lang.String)
	 */
	@Override
	public String queryChildDept(String tbbb04Id) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.queryChildDept(tbbb04Id);
	}

	/** (non Javadoc) 
	 * @Title: selectIfUsed
	 * @Description: TODO
	 * @param deptId
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectIfUsed(java.lang.String)
	 */
	@Override
	public String selectIfUsed(String deptId) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectIfUsed(deptId);
	}

	/** (non Javadoc) 
	 * @Title: selectPp01ByPage
	 * @Description: TODO
	 * @param mapParam
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectPp01ByPage(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> selectPp01ByPage(Map<String, Object> mapParam) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectPp01ByPage(mapParam);
	}

	/** (non Javadoc) 
	 * @Title: selectByNameAndParentId
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#selectByNameAndParentId(java.util.Map)
	 */
	@Override
	public int selectByNameAndParentId(Map param) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.selectByNameAndParentId(param);
	}

	@Override
	public List<Map> getManagementUnitList(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.getManagementUnitList(departmentId);
	}

	/** (non Javadoc) 
	 * @Title: getBuildingList
	 * @Description: TODO
	 * @param userId
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.DeptSrv#getBuildingList(java.lang.String)
	 */
	@Override
	public List<Map> getBuildingList(Map param) throws Exception {
		// TODO Auto-generated method stub
		return this.deptMapper.getBuildingList(param);
	}

	
	
}
