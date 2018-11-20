package com.qcap.cac.dao;

import com.qcap.cac.dto.EventTaskRestDto;
import com.qcap.cac.dto.QueryHistoryFlightInfoReq;
import com.qcap.cac.dto.QueryHistoryFlightInfoResp;
import com.qcap.cac.entity.TbFlightInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EventTaskRestMapper {

	void insertFlightInfo(TbFlightInfo flightInfo);

	List<Map<String, String>> selectEventPlan(EventTaskRestDto evenTaskDto);

	String selectAdvanceTime(@Param("eventBasicType") String eventBasicType);

	List<QueryHistoryFlightInfoResp> selectFlightInfo(QueryHistoryFlightInfoReq req);

	List<Map<String,String>> selectFlightShiftInfo (@Param("programCode") String programCode);
}
