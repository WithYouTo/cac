/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: Pp01SrvImpl.java 
 * @Prject: Whhotel
 * @Package: com.whxx.hotel.service.impl
 * @author: 彭浩
 * @date: 2018年4月2日 上午10:03:37 
 * @version: V1.0   
 */
package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.core.utils.UUIDUtil;
import com.qcap.cac.dao.PubCodeMapper;
import com.qcap.cac.model.Tbpubcode;
import com.qcap.cac.model.Tbpubcode01;
import com.qcap.cac.service.PubCodeSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * 通用代码档
 * @ClassName: PubCodeSrvImpl 
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年4月10日 下午6:59:27
 */
@Service
@Transactional
@SuppressWarnings("rawtypes")
public class PubCodeSrvImpl implements PubCodeSrv {

	@Autowired
    PubCodeMapper pubCodeMapper;

	@Override
	public List<Map> selectPubCodeByPage(Map param) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectPubCodeByPage(param);
	}

	@Override
	public List<Map> selectPubCode01ByPage(Map param) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectPubCode01ByPage(param);
	}

	@Override
	public void insertPubCode(Tbpubcode tbpubCode) {
		// TODO Auto-generated method stub
		tbpubCode.setTbpubcodeId(UUIDUtil.getUUID());;
		tbpubCode.setDelFlag("0");
		tbpubCode.setVersion(0);
		pubCodeMapper.insertPubCode(tbpubCode);
	}

	@Override
	public void insertPubCode01(Tbpubcode01 tbpubCode01) {
		// TODO Auto-generated method stub
		tbpubCode01.setTbpubcode01Id(UUIDUtil.getUUID());;
		tbpubCode01.setVersion(0);
		pubCodeMapper.insertPubCode01(tbpubCode01);
	}

	@Override
	public List<Map> selectConfigCode(Map map) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectConfigCode(map);
	}

	@Override
	public List<Map> selectConfigCodeApp(Map map) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectConfigCodeApp(map);
	}

	@Override
	public List<Map> selectIfDelete(String tbpubcodeId) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectIfDelete(tbpubcodeId);
	}

	@Override
	public String selectRepeatSeq(Map param) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectRepeatSeq(param);
	}

	@Override
	public String selectRepeatCode(Map param) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectRepeatCode(param);
	}

	@Override
	public Map selectPubCodeById(String tbpubcodeId) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectPubCodeById(tbpubcodeId);
	}

	@Override
	public Map selectPubCode01ById(String tbpubcode01Id) {
		// TODO Auto-generated method stub
		return pubCodeMapper.selectPubCode01ById(tbpubcode01Id);
	}

	@Override
	public void updatePubCode(Tbpubcode tbpubCode) {
		// TODO Auto-generated method stub
		pubCodeMapper.updatePubCode(tbpubCode);
	}

	@Override
	public void updatePubCode01(Tbpubcode01 tbpubCode01) {
		// TODO Auto-generated method stub
		pubCodeMapper.updatePubCode01(tbpubCode01);
	}

	@Override
	public void deletePubCode(String tbpubcodeId) {
		// TODO Auto-generated method stub
		pubCodeMapper.deletePubCode(tbpubcodeId);
	}

	@Override
	public void deletePubCode01(String tbpubcode01Id) {
		// TODO Auto-generated method stub
		pubCodeMapper.deletePubCode01(tbpubcode01Id);
	}

	@Override
	public String getFileBasePath() {
		// TODO Auto-generated method stub
		return this.pubCodeMapper.getFileBasePath();
	}
	
	
	

}
