package com.qcap.cac.service;

import com.qcap.cac.dto.*;

import java.util.List;
import java.util.Map;

public interface GoodsApplyRestSrv {


    List<GoodsReqRestReq> queryReqList(Map<String,String> paramMap);

    List<GoodsReqDetailReq> queryReqDetailList(Map<String,String> paramMap);

    List<GoodsDistributionDetailReq> queryAvailDistributionList(Map<String,String> paramMap);

    void updateDelivery(GoodsOutListReq goodsOutListReq);

    void updateDistribution(GoodsOutDistruListReq goodsOutDistruListReq);
}
