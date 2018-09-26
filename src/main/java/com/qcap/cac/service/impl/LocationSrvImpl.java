package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.dao.LocationMapper;
import com.qcap.cac.model.TbSysLocation;
import com.qcap.cac.service.LocationSrv;


/** 
 * @ClassName: LocalSrvImpl 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月25日 下午1:42:44  
 */
@Service
@Transactional
public class LocationSrvImpl implements LocationSrv{
	
	@Autowired
	private  LocationMapper locationMapper;

	/** (non Javadoc) 
	 * @Title: selectLocationByPage
	 * @Description: TODO
	 * @param mapParam
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#selectLocationByPage(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> selectLocationByPage(Map<String, Object> mapParam) throws Exception {
		// TODO Auto-generated method stub
		return this.locationMapper.selectLocationByPage(mapParam);
	}

	/** (non Javadoc) 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @see com.whxx.esp.service.locationMapper#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public void deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		this.locationMapper.deleteByPrimaryKey(id);
	}

	/** (non Javadoc) 
	 * @Title: updateLocationName
	 * @Description: TODO
	 * @param map
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#updateLocationName(java.util.Map)
	 */
	@Override
	public void updateLocationName(Map map) throws Exception {
		// TODO Auto-generated method stub
		this.locationMapper.updateLocationName(map);
	}

	/** (non Javadoc) 
	 * @Title: insert
	 * @Description: TODO
	 * @param record
	 * @return
	 * @see com.whxx.esp.service.locationMapper#insert(com.whxx.esp.model.TbSysLocation)
	 */
	@Override
	public void insert(TbSysLocation record) {
		// TODO Auto-generated method stub
		this.locationMapper.insert(record);
	}

	/** (non Javadoc) 
	 * @Title: selectAll
	 * @Description: TODO
	 * @param param
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#selectAll(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> selectAll(Map param) throws Exception {
		// TODO Auto-generated method stub
		return this.locationMapper.selectAll(param);
	}

	/** (non Javadoc) 
	 * @Title: selectByName
	 * @Description: TODO
	 * @param name
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#selectByName(java.lang.String)
	 */
	@Override
	public int selectByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return this.locationMapper.selectByName(name);
	}

	/** (non Javadoc) 
	 * @Title: selectChild
	 * @Description: TODO
	 * @param id
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#selectChild(java.lang.String)
	 */
	@Override
	public int selectChild(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.locationMapper.selectChild(id);
	}

	/** (non Javadoc) 
	 * @Title: queryChildLocation
	 * @Description: TODO
	 * @param tbbb04Id
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#queryChildLocation(java.lang.String)
	 */
	@Override
	public String queryChildLocation(String tbbb04Id) throws Exception {
		// TODO Auto-generated method stub
		return this.locationMapper.queryChildLocation(tbbb04Id);
	}

	/** (non Javadoc) 
	 * @Title: selectMaxId
	 * @Description: TODO
	 * @param pId
	 * @return
	 * @throws Exception
	 * @see com.whxx.esp.service.locationMapper#selectMaxId(java.lang.String)
	 */
	@Override
	public String selectMaxId(String pId) throws Exception {
		// TODO Auto-generated method stub
		return this.locationMapper.selectMaxId(pId);
	}

	/** (non Javadoc) 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param id
	 * @return
	 * @see com.whxx.esp.service.locationMapper#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public TbSysLocation selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return this.locationMapper.selectByPrimaryKey(id);
	}

	/** (non Javadoc) 
	 * @Title: updateByPrimaryKey
	 * @Description: TODO
	 * @param record
	 * @return
	 * @see com.whxx.esp.service.locationMapper#updateByPrimaryKey(com.whxx.esp.model.TbSysLocation)
	 */
	@Override
	public void updateByPrimaryKey(TbSysLocation record) {
		// TODO Auto-generated method stub
		this.locationMapper.updateByPrimaryKey(record);
	}

	
}
