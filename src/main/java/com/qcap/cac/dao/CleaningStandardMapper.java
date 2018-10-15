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
	List<Map<String, Object>> list(@Valid CleaningStandardDto cleaningStandardDto);

	/** 
	 *
	 * @Title: add 
	 * @param cleaningStandardDto
	 * @return
	 * @return: Integer
	 */
	Integer add(@Valid CleaningStandardDto cleaningStandardDto);

	/** 
	 *
	 * @Title: edit 
	 * @param cleaningStandardDto
	 * @return
	 * @return: Integer
	 */
	Integer edit(@Valid CleaningStandardDto cleaningStandardDto);

	

}
