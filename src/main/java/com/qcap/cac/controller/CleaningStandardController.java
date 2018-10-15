package com.qcap.cac.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.CleaningStandardDto;
import com.qcap.cac.service.CleaningStandardSrv;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
/**
 * 清洁标准管理
 *
 * @ClassName: CleaningStandardController 
 * @author: 夏胜专
 * @date: 2018年10月14日 下午6:08:14
 */
@Controller
@RequestMapping("/cleaningStandard")
public class CleaningStandardController {
	
	@Resource
	private CleaningStandardSrv cleaningStandardSrvImpl;
	
	//PC查询清洁标准列表
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public Object selectStandard(@Valid CleaningStandardDto cleaningStandardDto) {
		
		new PageFactory<Map<String, Object>>().defaultPage();
		List<Map<String , Object>>list = this.cleaningStandardSrvImpl.list(cleaningStandardDto);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(list);
		
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), list);
	}
	
	
	
	//pc管理端增加清洁标准
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object addStandard(@Valid CleaningStandardDto cleaningStandardDto) {
		
		return this.cleaningStandardSrvImpl.add(cleaningStandardDto);
	}
	
	
	//pc管理端修改清洁标准
	@ResponseBody
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public Object editStandard(@Valid CleaningStandardDto cleaningStandardDto) {
		
		return this.cleaningStandardSrvImpl.edit(cleaningStandardDto);
		
	}
	
	//pc管理端修改清洁标准
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public Object deleteStandard(String standardCode) {
		
		return this.cleaningStandardSrvImpl.deleteStandard(standardCode);
		
	}

}
