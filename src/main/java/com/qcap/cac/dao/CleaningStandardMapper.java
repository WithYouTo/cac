/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: CleaningStandardMapper.java 
 * @Prject: cac
 * @Package: com.qcap.cac.dao 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:42:24 
 * @version: V1.0   
 */
package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.qcap.cac.dto.CleaningStandardDto;
import com.qcap.cac.entity.TbAreaStandard;
import com.qcap.cac.entity.TbAreaStandardDetail;

/** 
 *
 * @ClassName: CleaningStandardMapper 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:42:24  
 */
public interface CleaningStandardMapper {

	/** 
	 *
	 * @Title: list 
	 * @param cleaningStandardDto
	 * @return
	 * @return: List<Map>
	 */
	List<Map<String, Object>> listTbAreaStandard(@Valid CleaningStandardDto cleaningStandardDto);


	/** 
	 *
	 * @Title: addTbAreaStandard 
	 * @param tbAreaStandard
	 * @return: void
	 */
	void addTbAreaStandard(TbAreaStandard tbAreaStandard);
	
	
	/** 
	 *
	 * @Title: addTbAreaStandardDetail 
	 * @param TbAreaStandardDetail
	 * @return: void
	 */
	void addTbAreaStandardDetail(TbAreaStandardDetail tbAreaStandardDetail);

	/** 
	 *
	 * @Title: edit 
	 * @param tbAreaStandard
	 * @return: void
	 */
	void editTbAreaStandard(TbAreaStandard tbAreaStandard);

	/** 
	 *
	 * @Title: selectStandardName 
	 * @param standardName
	 * @return
	 * @return: int
	 */
	int selectStandardName(String standardName);

	/** 
	 *
	 * @Title: selectStandardCode 
	 * @return
	 * @return: String
	 */
	String selectStandardCode();

	/** 
	 *
	 * @Title: delete 
	 * @param standardCode
	 * @return: void
	 */
	void deleteStandard(String standardCode);

	/** 
	 *
	 * @Title: selectTask 
	 * @param standardName
	 * @return
	 * @return: int
	 */
	int selectTask(String standardName);

	/** 
	 *
	 * @Title: listTbAreaStandardDetail 
	 * @param standardCode
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> listTbAreaStandardDetail(String standardCode);


	/** 
	 *
	 * @Title: editStandardDetail 
	 * @param tbAreaStandardDetail
	 * @return: void
	 */
	void editStandardDetail(TbAreaStandardDetail tbAreaStandardDetail);


	/** 
	 *
	 * @Title: deleteStandardDetail 
	 * @param standardDetailId
	 * @return: void
	 */
	void deleteStandardDetail(String standardDetailId);



	

}
