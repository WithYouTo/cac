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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.CleaningStandardMapper;
import com.qcap.cac.dto.CleaningStandardDetailDto;
import com.qcap.cac.dto.CleaningStandardDto;
import com.qcap.cac.entity.TbAreaStandard;
import com.qcap.cac.entity.TbAreaStandardDetail;
import com.qcap.cac.service.CleaningStandardSrv;
import com.qcap.cac.tools.RedisTools;
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
	 * 查询清洁标准列表主表
	 * @Title: list
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#list(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public List<Map<String , Object>> listTbAreaStandard(@Valid CleaningStandardDto cleaningStandardDto) {
		
		return this.cleaningStandardMapper.listTbAreaStandard(cleaningStandardDto);
	}
	
	
	
	/** 
	 * 查询清洁标准列表从表
	 * @Title: listTbAreaStandardDetail
	 * @param cleaningStandardDetailDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#listTbAreaStandardDetail(com.qcap.cac.dto.CleaningStandardDetailDto)
	 */
	@Override
	public List<Map<String, Object>> listTbAreaStandardDetail(String standardCode) {
		
		
		return this.cleaningStandardMapper.listTbAreaStandardDetail(standardCode);
	}

	/** 
	 * 新增清洁标准
	 * @Title: add
	 * @param cleaningStandardDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#add(com.qcap.cac.dto.CleaningStandardDto)
	 */
	@Override
	public Object add(@Valid CleaningStandardDto cleaningStandardDto,String list) {
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		int count = this.selectStandardName(cleaningStandardDto.getStandardName());
		
		if(count > 0){
			return ResParams.newInstance(300, "清洁标准名称重复，请重新编写名称。", null);
		}
		String newStandardCode = this.newStandardCode();
		Date date = new Date();
		
		//清洁标准主表新增
		TbAreaStandard tbAreaStandard = new TbAreaStandard();
		BeanUtils.copyProperties(cleaningStandardDto, tbAreaStandard);
		tbAreaStandard.setStandardId(UUIDUtils.getUUID());
		tbAreaStandard.setStandardCode(newStandardCode);
		tbAreaStandard.setCreateDate(date);
		tbAreaStandard.setUpdateDate(date);
		tbAreaStandard.setCreateEmp("sys");
		tbAreaStandard.setVersion(0);
		
		this.cleaningStandardMapper.addTbAreaStandard(tbAreaStandard); 
		
		//新增标准详情
		List<Map<String,Object>> dtolist = JSONObject.parseObject(list, List.class);
		if(!CollectionUtils.isEmpty(dtolist)){
			for (Map<String, Object> map : dtolist) {
				TbAreaStandardDetail tbAreaStandardDetail = new TbAreaStandardDetail();
				tbAreaStandardDetail.setStandardDetailId(UUIDUtils.getUUID());
				tbAreaStandardDetail.setStandardCode(newStandardCode);
				tbAreaStandardDetail.setCleanTools(map.get("cleanTools").toString());
				tbAreaStandardDetail.setMaterial(map.get("material").toString());
				tbAreaStandardDetail.setStandardRequirement(map.get("standardRequirement").toString());
				tbAreaStandardDetail.setStandardStep(map.get("standardStep").toString());
				String imgUrl=map.get("imgUrl").toString();
				if(!StringUtils.isEmpty(imgUrl)){
					if(imgUrl.indexOf(addressPrefix)!=-1){
						tbAreaStandardDetail.setImgUrl(imgUrl);
					}else {
						tbAreaStandardDetail.setImgUrl(addressPrefix+imgUrl);
					}
				}
				
				tbAreaStandardDetail.setCreateEmp("sys");
				tbAreaStandardDetail.setCreateDate(date);
				tbAreaStandardDetail.setUpdateDate(date);
				tbAreaStandardDetail.setVersion(0);
				
				this.cleaningStandardMapper.addTbAreaStandardDetail(tbAreaStandardDetail);
			}
			
			
		}
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "新增清洁标准成功", null);
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
		
		//判断标准是否重名，当系统没有该标准名称是则没有同名
		int countStandardName = this.selectStandardName(cleaningStandardDto.getStandardName());
		if(countStandardName <= 1){
			
			TbAreaStandard tbAreaStandard = new TbAreaStandard();
			BeanUtils.copyProperties(cleaningStandardDto, tbAreaStandard);
			
			
			this.cleaningStandardMapper.editTbAreaStandard(tbAreaStandard);
			
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准成功", null);
			
		}else{
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "清洁标准名称重复，请重新编写名称。", null);
		}
	}


	//判断标准名称是否重复
	public Integer selectStandardName(String standardName) {
		
		int count  = this.cleaningStandardMapper.selectStandardName(standardName);
		
		return count;
	}
	
	
	//判断该标准是否已经生成了相应的任务，如果生成了相应任务则不准许修改清洁标准
	public Integer selectTask(String standardName) {
		
		int count  = this.cleaningStandardMapper.selectTask(standardName);
		
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
		//1.判断是否有与该清洁标准相关已经生成但是还未执行的清洁任务
		
		//2.判断是否有与该清洁标准相关的清洁计划
		
		
		//3.删除清洁标准
		this.cleaningStandardMapper.deleteStandard(standardCode);
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准成功", null);
	}



	/** 修改清洁明细 
	 * @Title: editStandardDetail
	 * @param cleaningStandardDetailDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#editStandardDetail(com.qcap.cac.dto.CleaningStandardDetailDto)
	 */
	@Override
	public Object editStandardDetail(@Valid CleaningStandardDetailDto cleaningStandardDetailDto) {
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		String material = cleaningStandardDetailDto.getMaterial();
		String standardCode = cleaningStandardDetailDto.getStandardCode();
		String standardDetailId=cleaningStandardDetailDto.getStandardDetailId();
		String imgUrl=cleaningStandardDetailDto.getImgUrl();
		if(!StringUtils.isEmpty(imgUrl)){
			if(imgUrl.indexOf(addressPrefix)!=-1){
				cleaningStandardDetailDto.setImgUrl(imgUrl);
			}else {
				cleaningStandardDetailDto.setImgUrl(addressPrefix+imgUrl);
			}
		}
		Map<String ,String> map=new HashMap<String,String>();
		map.put("material", material);
		map.put("standardCode", standardCode);
		map.put("standardDetailId",standardDetailId);
		
		int count  = this.cleaningStandardMapper.selectStandardMaterial(map);
		
		if(count <=1){
			TbAreaStandardDetail tbAreaStandardDetail = new TbAreaStandardDetail();
			BeanUtils.copyProperties(cleaningStandardDetailDto, tbAreaStandardDetail);
			
			this.cleaningStandardMapper.editStandardDetail(tbAreaStandardDetail);
			
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准明细成功", null);
		}else{
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "清洁标准材质已经存在。", null);
		}
		
		
		
	}



	/** 删除清洁明细 
	 * @Title: deleteStandardDetail
	 * @param standardDetailId
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#deleteStandardDetail(java.lang.String)
	 */
	@Override
	public Object deleteStandardDetail(String standardDetailId) {
		
		this.cleaningStandardMapper.deleteStandardDetail(standardDetailId);
		
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "修改清洁标准明细成功", null);
	}



	/** 新增清洁明细
	 * @Title: addStandardDetail
	 * @param cleaningStandardDetailDto
	 * @return
	 * @see com.qcap.cac.service.CleaningStandardSrv#addStandardDetail(com.qcap.cac.dto.CleaningStandardDetailDto)
	 */
	@Override
	public Object addStandardDetail(@Valid CleaningStandardDetailDto cleaningStandardDetailDto) {
		//查询配置管理中存放的文件访问地址前缀
		String addressPrefix = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
		Date date = new Date();
		String material = cleaningStandardDetailDto.getMaterial();
		String standardCode = cleaningStandardDetailDto.getStandardCode();
		String standardDetailId=cleaningStandardDetailDto.getStandardDetailId();
		String imgUrl=cleaningStandardDetailDto.getImgUrl();
		if(!StringUtils.isEmpty(imgUrl)){
			if(imgUrl.indexOf(addressPrefix)!=-1){
				cleaningStandardDetailDto.setImgUrl(imgUrl);
			}else {
				cleaningStandardDetailDto.setImgUrl(addressPrefix+imgUrl);
			}
		}
		Map<String ,String> map=new HashMap<String,String>();
		map.put("material", material);
		map.put("standardCode", standardCode);
		map.put("standardDetailId",standardDetailId);
		
		int count  = this.cleaningStandardMapper.selectStandardMaterial(map);
		
		if(count > 0){
			return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "清洁标准材质已经存在。", null);
		}
		
		TbAreaStandardDetail tbAreaStandardDetail = new TbAreaStandardDetail();
		BeanUtils.copyProperties(cleaningStandardDetailDto, tbAreaStandardDetail);
		tbAreaStandardDetail.setStandardDetailId(UUIDUtils.getUUID());
		
		tbAreaStandardDetail.setCreateEmp("sys");
		tbAreaStandardDetail.setCreateDate(date);
		tbAreaStandardDetail.setUpdateDate(date);
		tbAreaStandardDetail.setVersion(0);
		this.cleaningStandardMapper.addTbAreaStandardDetail(tbAreaStandardDetail);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "新增清洁标准明细成功", null);
	}

	
	

}
