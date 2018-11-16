package com.qcap.cac.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.DeleteProgramSysFileDto;
import com.qcap.cac.dto.ProgramAddDto;
import com.qcap.cac.dto.ProgramSearchDto;

public interface ProgramSrv {
	
	List<Map<String,Object>> selectProgram (ProgramSearchDto programSearchDto);
	
	void addProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException;
	
	void editProgram(ProgramAddDto programAddDto) throws IllegalAccessException, InvocationTargetException;
	
	void deleteProgram(String programId);
	
	void deleteSysFile(DeleteProgramSysFileDto deleteProgramSysFileDto);
}
