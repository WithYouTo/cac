package com.qcap.cac.dao;

import com.qcap.cac.dto.GoodsDistributionDetailReq;
import com.qcap.cac.dto.GoodsReqDetailReq;
import com.qcap.cac.dto.GoodsReqRestReq;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseStock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GoodsApplyRestMapper {


   List<GoodsReqRestReq> queryReqList(Map<String,String> paramMap);

   List<GoodsReqDetailReq> queryReqDetailList(Map<String,String> paramMap);

   List<GoodsDistributionDetailReq> queryAvailDistributionList(Map<String,String> paramMap);

   Integer updateReqDetailByGoodsOut(TbWarehouseReqdetail warehouseReqdetail);

   Integer updateStockByGoodsOut(TbWarehouseStock warehouseStock);

   /**
    * 根据父级查询项目编码
    * @param programCode,goodsNo
    * @return
    */
   @Select("select * from tb_warehouse_stock  WHERE `PROGRAM_CODE` = #{programCode} and GOODS_NO = #{goodsNo} ")
   TbWarehouseStock selectExistGood(@Param("programCode") String programCode, @Param("goodsNo")String goodsNo);
}
