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
package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.qcap.cac.dto.CleaningStandardDetailDto;
import com.qcap.cac.dto.CleaningStandardDto;

/** 
 *
 * @ClassName: CleaningStandardSrv 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:20:06  
 */
public interface CleaningStandardSrv {

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
	 * @Title: add 
	 * @param cleaningStandardAddDto
	 * @param list 
	 * @return
	 * @return: Object
	 */
	Object add(@Valid CleaningStandardDto cleaningStandardDto, String list);

	/** 
	 *
	 * @Title: edit 
	 * @param cleaningStandardDto
	 * @return
	 * @return: Object
	 */
	Object edit(@Valid CleaningStandardDto cleaningStandardDto);

	/** 
	 *
	 * @Title: deleteStandard 
	 * @param standardCode
	 * @return
	 * @return: Object
	 */
	Object deleteStandard(@Valid String standardCode);

	/** 
	 *
	 * @Title: listTbAreaStandardDetail 
	 * @param standardCode
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>> listTbAreaStandardDetail( String standardCode);

	/** 
	 *
	 * @Title: editStandardDetail 
	 * @param cleaningStandardDetailDto
	 * @return
	 * @return: Object
	 */
	Object editStandardDetail(@Valid CleaningStandardDetailDto cleaningStandardDetailDto);

	/** 
	 *
	 * @Title: deleteStandardDetail 
	 * @param standardDetailId
	 * @return
	 * @return: Object
	 */
	Object deleteStandardDetail(String standardDetailId);

	/** 
	 *
	 * @Title: addStandardDetail 
	 * @param cleaningStandardDetailDto
	 * @return
	 * @return: Object
	 */
	Object addStandardDetail(@Valid CleaningStandardDetailDto cleaningStandardDetailDto);


}
