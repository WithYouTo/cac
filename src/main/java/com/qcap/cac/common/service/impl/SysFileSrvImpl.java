package com.qcap.cac.common.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.cac.common.dao.TbSysFileMapper;
import com.qcap.cac.common.service.SysFileSrv;
import com.qcap.cac.model.TbSysFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * @ClassName: SysFileSrvImpl 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年5月23日 下午6:16:56  
 */
@Service
@Transactional
public class SysFileSrvImpl implements SysFileSrv {
	
	@Autowired
	private TbSysFileMapper fileMapper;

	/** (non Javadoc) 
	 * @Title: deleteByPrimaryKey
	 * @Description: TODO
	 * @param tbFileId
	 * @see SysFileSrv#deleteByPrimaryKey(java.lang.String)
	 */
	@Override
	public void deleteByPrimaryKey(String tbFileId) {
		// TODO Auto-generated method stub
		this.fileMapper.deleteByPrimaryKey(tbFileId);
	}

	/** (non Javadoc) 
	 * @Title: deleteByGroupId
	 * @Description: TODO
	 * @param groupId
	 * @see SysFileSrv#deleteByGroupId(java.lang.String)
	 */
	@Override
	public void deleteByGroupId(String groupId) {
		// TODO Auto-generated method stub
		this.fileMapper.deleteByGroupId(groupId);
	}

	/** (non Javadoc) 
	 * @Title: insert
	 * @Description: TODO
	 * @param record
	 * @see SysFileSrv#insert(TbSysFile)
	 */
	@Override
	public void insert(TbSysFile record) {
		// TODO Auto-generated method stub
		this.fileMapper.insert(record);
	}

	/** (non Javadoc) 
	 * @Title: selectByPrimaryKey
	 * @Description: TODO
	 * @param tbFileId
	 * @return
	 * @see SysFileSrv#selectByPrimaryKey(java.lang.String)
	 */
	@Override
	public TbSysFile selectByPrimaryKey(String tbFileId) {
		// TODO Auto-generated method stub
		return this.fileMapper.selectByPrimaryKey(tbFileId);
	}

	/** (non Javadoc) 
	 * @Title: updateByPrimaryKey
	 * @Description: TODO
	 * @param record
	 * @see SysFileSrv#updateByPrimaryKey(TbSysFile)
	 */
	@Override
	public void updateByPrimaryKey(TbSysFile record) {
		// TODO Auto-generated method stub
		this.fileMapper.updateByPrimaryKey(record);
	}

	/** (non Javadoc) 
	 * @Title: selectFileByGroupId
	 * @Description: TODO
	 * @param groupId
	 * @return
	 * @see SysFileSrv#selectFileByGroupId(java.lang.String)
	 */
	@Override
	public List<Map> selectFileByGroupId(String groupId) {
		// TODO Auto-generated method stub
		return this.fileMapper.selectFileByGroupId(groupId);
	}

	
}
