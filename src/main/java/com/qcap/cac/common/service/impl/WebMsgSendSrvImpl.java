package com.qcap.cac.common.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.cac.common.dao.WebMsgSendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.cac.common.service.WebMsgSendSrv;
import com.qcap.cac.model.TbMsg;

/** 
 * @ClassName: WebMsgSendSrvImpl 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年8月10日 下午3:39:20  
 */
@Service
@Transactional
public class WebMsgSendSrvImpl implements WebMsgSendSrv{

	@Autowired
	private WebMsgSendMapper webMsgSendMapper;
	
	/** (non Javadoc) 
	 * @Title: insertTbMsgBatch
	 * @Description: TODO
	 * @param records
	 * @see WebMsgSendSrv#insertTbMsgBatch(java.util.List)
	 */
	@Override
	public void insertTbMsgBatch(List<TbMsg> records) {
		// TODO Auto-generated method stub
		this.webMsgSendMapper.insertTbMsgBatch(records);
	}

	/** (non Javadoc) 
	 * @Title: selectUserIdListByRole
	 * @Description: TODO
	 * @param param
	 * @return
	 * @see WebMsgSendSrv#selectUserIdListByRole(java.util.Map)
	 */
	@Override
	public List<String> selectUserIdListByRole(Map param) {
		// TODO Auto-generated method stub
		return this.webMsgSendMapper.selectUserIdListByRole(param);
	}

	
	
	
}
