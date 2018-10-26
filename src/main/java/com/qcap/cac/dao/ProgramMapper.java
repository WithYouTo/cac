package com.qcap.cac.dao;

import com.qcap.cac.dto.ProgramSearchDto;
import com.qcap.cac.entity.TbProgram;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProgramMapper {
	
	List<Map<String,Object>> selectProgram (ProgramSearchDto programSearchDto);
	
	void insertProgram (TbProgram tbProgram);
	
	void updateProgramByKey (TbProgram tbProgram);
	
	void deleteProgramByKey (@Param("programId") String programId);

	String selectIfCodeExist (Map<String,Object> map );

}
