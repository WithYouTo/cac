package com.qcap.cac.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.ProgramMapper;
import com.qcap.cac.dto.ProgramAddDto;
import com.qcap.cac.dto.ProgramSearchDto;
import com.qcap.cac.entity.TbProgram;
import com.qcap.cac.entity.TbSysFile;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.ProgramSrv;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;

import cn.hutool.core.util.StrUtil;
@Service
@Transactional
public class ProgramSrvImpl implements ProgramSrv{
	
	@Value("${FILE_DORMAIN}")
	private String fileDomain;
	
	@Resource
	private ProgramMapper programMapper;
	
	@Resource
	private TempTaskSrv tempTaskSrvImpl;
	
	@Resource
	private CommonSrv commonSrvImpl;
	
	@Override
	public List<Map<String, Object>> selectProgram(ProgramSearchDto programSearchDto) {
		// TODO Auto-generated method stub
		return programMapper.selectProgram(programSearchDto);
	}

	@Override
	public void addProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {
		
		String programId = UUIDUtils.getUUID();
		String contractFile="";
		//获取文件访问域名地址
		String urlPrefix = RedisTools.getCommonConfig(fileDomain);
		
		// 新增系统文件到数据库
		if(!StringUtils.isEmpty(programAddDto.getContractFile())) {
			contractFile = insertFile(programAddDto, programId, contractFile, urlPrefix);
		}

		TbProgram program = new TbProgram();
		BeanUtils.copyProperties(program, programAddDto);
		program.setProgramId(programId);
		program.setStartTime(DateUtil.stringToDate(programAddDto.getStartTimeStr()));
		program.setEndTime(DateUtil.stringToDate(programAddDto.getEndTimeStr()));
		program.setEffectDate(DateUtil.stringToDate(programAddDto.getEffectDateStr()));
		program.setCreateDate(new Date());
		//建筑平面图地址
		if(!StringUtils.isEmpty(programAddDto.getArchitecturalPic())) {
			program.setArchitecturalPic(urlPrefix + programAddDto.getArchitecturalPic());
		}
		
		//设置合同附件访问路径
		program.setContractFile(contractFile);
		
		/**
		 * TODO
		 */
		program.setCreateEmp("SYS");
		program.setVersion(0);
		
		this.programMapper.insertProgram(program);
		
	}

	@Override
	public void editProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {
		
		//获取文件访问域名地址
		String urlPrefix = RedisTools.getCommonConfig(fileDomain);
		
		String contractFile="";
		// 新增系统文件到数据库
		if(!StringUtils.isEmpty(programAddDto.getContractFile())) {
			contractFile = insertFile(programAddDto, programAddDto.getProgramId(), contractFile, urlPrefix);
		}
		
		TbProgram program = new TbProgram();
		BeanUtils.copyProperties(program, programAddDto);
		program.setStartTime(DateUtil.stringToDate(programAddDto.getStartTimeStr()));
		program.setEndTime(DateUtil.stringToDate(programAddDto.getEndTimeStr()));
		program.setEffectDate(DateUtil.stringToDate(programAddDto.getEffectDateStr()));
		program.setUpdateDate(new Date());
		//建筑平面图地址
		if(!StringUtils.isEmpty(programAddDto.getArchitecturalPic())) {
			program.setArchitecturalPic(urlPrefix + programAddDto.getArchitecturalPic());
		}
		
		//设置合同附件访问路径
		program.setContractFile(contractFile);
		/**
		 * TODO
		 */
		program.setUpdateEmp("SYS");
		
		this.programMapper.updateProgramByKey(program);
		
	}

	@Override
	public void deleteProgram(String programId) {
		if(StringUtils.isEmpty(programId)) {
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "参数为空");
		}
		this.programMapper.deleteProgramByKey(programId);
	}
	
	private String insertFile(ProgramAddDto programAddDto, String programId, String contractFile, String urlPrefix) {
		String[] fileurls = programAddDto.getContractFile().split(",");
		for (String url : fileurls) {
			contractFile += urlPrefix + url + ",";
			TbSysFile sysFile = new TbSysFile();
			sysFile.setFileId(UUIDUtils.getUUID());
			sysFile.setDownloadUrl(urlPrefix + url);
			sysFile.setFileName(url.substring(url.lastIndexOf(StrUtil.SLASH) + 1));
			sysFile.setFileType(url.substring(url.lastIndexOf(StrUtil.DOT) + 1));
			sysFile.setGroupId(programId);
			sysFile.setCreateDate(new Date());
			sysFile.setCreateEmp("SYS");
			sysFile.setVersion(0);

			commonSrvImpl.insertSysFile(sysFile);
		}
		return contractFile;
	}

}
