package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dao.ProgramMapper;
import com.qcap.cac.dto.ProgramAddDto;
import com.qcap.cac.dto.ProgramSearchDto;
import com.qcap.cac.entity.TbProgram;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.CommonSrv;
import com.qcap.cac.service.ProgramSrv;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ProgramSrvImpl implements ProgramSrv{
	
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

		//查询项目编号是否被占用
		Map<String,Object> map = new HashMap<>();
		map.put("programCode",programAddDto.getProgramCode());
		String programId = this.programMapper.selectIfCodeExist(map);

		if(StringUtils.isEmpty(programId)){
			TbProgram program = new TbProgram();
			BeanUtils.copyProperties(program, programAddDto);
			program.setProgramId(UUIDUtils.getUUID());
			program.setStartTime(DateUtil.stringToDate(programAddDto.getStartTimeStr()));
			program.setEndTime(DateUtil.stringToDate(programAddDto.getEndTimeStr()));
			program.setEffectDate(DateUtil.stringToDate(programAddDto.getEffectDateStr()));
			program.setVersion(0);
			/**设置新增时间和新增人**/
			EntityTools.setCreateEmpAndTime(program);

			this.programMapper.insertProgram(program);

		}else {
			throw  new BaseException(CommonCodeConstant.ERROR_CODE_40402, "项目编号已存在，请更换项目编号");
		}

		
	}

	@Override
	public void editProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException {

		//查询项目编号是否被占用
		Map<String,Object> map = new HashMap<>();
		map.put("programCode",programAddDto.getProgramCode());
		map.put("programId",programAddDto.getProgramId());
		String programId = this.programMapper.selectIfCodeExist(map);

		if(StringUtils.isEmpty(programId)){
			TbProgram program = new TbProgram();
			BeanUtils.copyProperties(program, programAddDto);
			program.setStartTime(DateUtil.stringToDate(programAddDto.getStartTimeStr()));
			program.setEndTime(DateUtil.stringToDate(programAddDto.getEndTimeStr()));
			program.setEffectDate(DateUtil.stringToDate(programAddDto.getEffectDateStr()));
			/**设置更新时间和更新人**/
			EntityTools.setUpdateEmpAndTime(program);

			this.programMapper.updateProgramByKey(program);
		}else{
			throw new BaseException(CommonCodeConstant.ERROR_CODE_40402,"该项目编号已经被占用，请换个项目编号");
		}

		
	}

	@Override
	public void deleteProgram(String programId) {
		if(StringUtils.isEmpty(programId)) {
			throw new BaseException(CommonCodeConstant.PARAM_EMPTY_CODE, "参数为空");
		}
		this.programMapper.deleteProgramByKey(programId);
	}
	
}
