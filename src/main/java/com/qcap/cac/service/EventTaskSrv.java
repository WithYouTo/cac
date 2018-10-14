/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: EventTaskSrv.java 
 * @Prject: cac
 * @Package: com.qcap.cac.service 
 * @Description: TODO
 * @author: 张天培(2017004)   
 * @date: 2018年10月11日 上午10:11:19 
 * @version: V1.0   
 */
package com.qcap.cac.service;

import com.qcap.cac.dto.EventTaskRestDto;


public interface EventTaskSrv {
	
	void geneEventTask(EventTaskRestDto eventTaskDto);
}
