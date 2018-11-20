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
import com.qcap.cac.dto.QueryHistoryFlightInfoReq;
import com.qcap.cac.dto.QueryHistoryFlightInfoResp;

import java.util.List;
import java.util.Map;

public interface EventTaskRestSrv {

	void geneEventTask(EventTaskRestDto eventTaskDto);

	List<QueryHistoryFlightInfoResp> queryHistoryFlightInfo(QueryHistoryFlightInfoReq req);

	List<Map<String,String>> selectFlightShiftInfo ();

}
