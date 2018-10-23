package com.qcap.cac.dao;

import com.qcap.cac.dto.GoodsDistributionDetailReq;
import com.qcap.cac.dto.GoodsReqDetailReq;
import com.qcap.cac.dto.GoodsReqRestReq;

import java.util.List;
import java.util.Map;

public interface GoodsApplyRestMapper {


   List<GoodsReqRestReq> queryReqList(Map<String,String> paramMap);

   List<GoodsReqDetailReq> queryReqDetailList(Map<String,String> paramMap);

   List<GoodsDistributionDetailReq> queryAvailDistributionList(Map<String,String> paramMap);
}
