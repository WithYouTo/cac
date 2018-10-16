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

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.CleaningStandardMapper;
import com.qcap.cac.dto.CleaningStandardDto;
import com.qcap.cac.entity.TbAreaStandard;
import com.qcap.cac.service.CleaningStandardSrv;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ResParams;

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
	
	
	
	/** 
	 * 查询清洁标准列表
	 * @Title: list
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#list(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public List<Map<String , Object>> list(@Valid CleaningStandardDto cleaningStandardDto) {
		
		return this.cleaningStandardMapper.list(cleaningStandardDto);
	}

	/** 
	 * 新增清洁标准
	 * @Title: add
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#add(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public Object add(@Valid CleaningStandardDto cleaningStandardDto) {
		
		int count = this.selectStandardName(cleaningStandardDto.getStandardName());
		
		if(count > 0){
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "清洁标准名称已经存在", null);
		}
		
		TbAreaStandard tbAreaStandard = new TbAreaStandard();
		BeanUtils.copyProperties(cleaningStandardDto, tbAreaStandard);
		tbAreaStandard.setstandardId(UUIDUtils.getUUID());
		tbAreaStandard.setStandardCode(newStandardCode());
		tbAreaStandard.setCreateDate(new Date());
		tbAreaStandard.setUpdateDate(new Date());
		tbAreaStandard.setCreateEmp("sys");
		tbAreaStandard.setVersion(0);
		
		this.cleaningStandardMapper.add(tbAreaStandard); 
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准失败", null);
	}

	/** 
	 * 修改清洁标准
	 * @Title: edit
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#edit(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public Object edit(@Valid CleaningStandardDto cleaningStandardDto) {
		
		TbAreaStandard tbAreaStandard = new TbAreaStandard();
		BeanUtils.copyProperties(cleaningStandardDto, tbAreaStandard);
		
		this.cleaningStandardMapper.edit(tbAreaStandard);
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准成功", null);
	}


	//判断标准名称是否重复
	public Integer selectStandardName(String standardName) {
		
		int count  = this.cleaningStandardMapper.selectStandardName(standardName);
		
		return count;
	}


	//清洁编码
	public String newStandardCode() {
		
		String standardCode = this.cleaningStandardMapper.selectStandardCode();
		
		if(StringUtils.isEmpty(standardCode)){
			
			standardCode  = "10000";
			
		}
		
		return Integer.toString(Integer.parseInt(standardCode)+1);
	}

	/** 
	 * 清洁标准删除
	 * @Title: deleteStandard
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#deleteStandard(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public Object deleteStandard(String standardCode) {
		
		
		this.cleaningStandardMapper.delete(standardCode);
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准成功", null);
	}
	

}
