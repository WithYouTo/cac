/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: CleaningStandardSrv.java 
 * @Prject: cac
 * @Package: com.qcap.cac.service 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:20:06 
 * @version: V1.0   
 */
package com.qcap.cac.service.impl;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qcap.cac.dao.CleaningStandardMapper;
import com.qcap.cac.dto.CleaningStandardDto;
import com.qcap.cac.service.CleaningStandardSrv;

/** 
 *
 * @ClassName: CleaningStandardSrv 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:20:06  
 */
@Service
public class CleaningStandardSrvImpl implements CleaningStandardSrv {
	
	@Autowired
	private CleaningStandardMapper cleaningStandardMapper;
	
	/** (non Javadoc) 
	 * @Title: list
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#list(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public List<Map<String , Object>> list(@Valid CleaningStandardDto cleaningStandardDto) {
		return this.cleaningStandardMapper.list(cleaningStandardDto);
	}

	/** (non Javadoc) 
	 * @Title: add
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#add(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public Integer add(@Valid CleaningStandardDto cleaningStandardDto) {
		return this.cleaningStandardMapper.add(cleaningStandardDto);
	}

	/** (non Javadoc) 
	 * @Title: edit
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#edit(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public Integer edit(@Valid CleaningStandardDto cleaningStandardDto) {
		return this.cleaningStandardMapper.edit(cleaningStandardDto);
	}


	

}
