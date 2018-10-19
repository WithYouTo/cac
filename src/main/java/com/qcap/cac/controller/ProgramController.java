package com.qcap.cac.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.ProgramAddDto;
import com.qcap.cac.dto.ProgramSearchDto;
import com.qcap.cac.service.ProgramSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;

@RestController
@RequestMapping("/program")
public class ProgramController {
	
	@Resource
	private ProgramSrv programSrvImpl;
	
	@RequestMapping("/list")
	public Object selectProgram(@Valid ProgramSearchDto programSearchDto) {
		new PageFactory<>().defaultPage();
		List<Map<String, Object>> list = this.programSrvImpl.selectProgram(programSearchDto);
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
		return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}
	
	@RequestMapping("/add")
	public Object addProgram(@Valid ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {
		this.programSrvImpl.addProgram(programAddDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC);
	}
	
	@RequestMapping("/edit")
	public Object editaProgram(@Valid ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {
		this.programSrvImpl.editProgram(programAddDto);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC);
	}
	
	@RequestMapping("/delete")
	public Object deleteProgram(@RequestParam("programId") String programId) {
		this.programSrvImpl.deleteProgram(programId);
		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC);
	}

}
