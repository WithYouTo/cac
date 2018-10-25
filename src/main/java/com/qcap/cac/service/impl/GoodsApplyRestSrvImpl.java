package com.qcap.cac.service.impl;

import cn.jiguang.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.*;
import com.qcap.cac.dto.*;
import com.qcap.cac.entity.TbWarehouseDistribution;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.GoodsApplyRestSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class GoodsApplyRestSrvImpl implements GoodsApplyRestSrv {

    @Resource
    private GoodsApplyRestMapper goodsApplyRestMapper;

    @Resource
    private WarehouseReqDetailMapper warehouseReqDetailMapper;

    @Resource
    private WarehouseRequMapper warehouseRequMapper;

    @Resource
    private WarehouseStockMapper warehouseStockMapper;

    @Resource
    private WarehouseDistributionMapper warehouseDistributionMapper;

    @Override
    public List<GoodsReqRestReq> queryReqList(Map<String, String> paramMap) {
        paramMap.put("date", DateUtil.formatDate(new Date(),"yyyy-MM-dd"));
        paramMap.put("requStatus", CommonConstant.WAREHOUSE_REQ_STATUS_COMMIT);
        return this.goodsApplyRestMapper.queryReqList(paramMap);
    }

    @Override
    public List<GoodsReqDetailReq> queryReqDetailList(Map<String, String> paramMap) {
        paramMap.put("requStatus", CommonConstant.WAREHOUSE_REQ_STATUS_COMMIT);
        return this.goodsApplyRestMapper.queryReqDetailList(paramMap);
    }

    @Override
    public List<GoodsDistributionDetailReq> queryAvailDistributionList(Map<String, String> paramMap) {
        paramMap.put("requStatus",CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
        return this.goodsApplyRestMapper.queryAvailDistributionList(paramMap);
    }


    public void updateDelivery(GoodsOutListReq goodsOutListReq){

        List<GoodsOutReq> list = goodsOutListReq.getGoodsOutReqList();
        for(GoodsOutReq item : list){

            //查询库存余量，如果不足，直接弹提示
            String warehouseReqDetailId = item.getWarehouseReqDetailId();
            Integer realNum = item.getRealNum();
            if(StringUtils.isEmpty(warehouseReqDetailId)){
                throw new RuntimeException("出库的主键为空");
            }

            TbWarehouseReqdetail warehouseReqdetail = warehouseReqDetailMapper.selectById(warehouseReqDetailId);
            if(null == warehouseReqdetail){
                throw new RuntimeException("根据主键查询不到领用单明细信息");
            }

            String goodsNo = warehouseReqdetail.getGoodsNo();
            String goodsName = warehouseReqdetail.getGoodsName();

            //查询库存余量（库存表中-一个goodsNo对应一条记录，在入库时做了限制）
            QueryWrapper<TbWarehouseStock> wrapper = new QueryWrapper<>();
            wrapper.eq("GOODS_NO",goodsNo);
            TbWarehouseStock stock = warehouseStockMapper.selectOne(wrapper);
            if(null == stock){
                throw new RuntimeException("根据物品编码没有查询到库存信息");
            }

            Integer goodsNum = ToolUtil.toInt(stock.getGoodsNum());
            if(realNum > goodsNum){
                throw new RuntimeException("物品编码【" + goodsNo + "】/物品名称【" + goodsName + "】库存余量不足");
            }

            //正常出库
            warehouseReqdetail.setWarehouseReqdetailId(warehouseReqDetailId);
            warehouseReqdetail.setRealNum(item.getRealNum());
            warehouseReqdetail.setRequStatus(CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
            this.goodsApplyRestMapper.updateReqDetailByGoodsOut(EntityTools.setCreateEmpAndTime(warehouseReqdetail));

            TbWarehouseRequ requ = new TbWarehouseRequ();
            requ.setWarehouseRequId(warehouseReqdetail.getWarehouseRequId());
            requ.setRequStatus(CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
            this.warehouseRequMapper.updateById(requ);

            //更新库存
            stock.setGoodsNum(goodsNum - realNum);
            this.goodsApplyRestMapper.updateStockByGoodsOut(EntityTools.setUpdateEmpAndTime(stock));
        }

    }

    @Override
    public void updateDistribution(GoodsOutDistruListReq goodsOutDistruListReq) {
        //发放前---判断是否有余量
        List<GoodsOutDistruReq> list = goodsOutDistruListReq.getGoodsOutDistruReqList();
        for(GoodsOutDistruReq item : list){

            String employeeCode = item.getEmployeeCode();
            String positionCode = item.getPositionCode();
            String goodsNo = item.getGoodsNo();
            String goodsName = item.getGoodsName();
            Integer distruNum = item.getDistrNum();


            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("positionCode",positionCode);
            paramMap.put("employeeCode",employeeCode);
            paramMap.put("goodsNo",goodsNo);
            paramMap.put("requStatus",CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
            List<GoodsDistributionDetailReq> distruList = this.goodsApplyRestMapper.queryAvailDistributionList(paramMap);
            if(CollectionUtils.isEmpty(distruList)){
                throw new RuntimeException("根据岗位编码没有查询到待发放信息");
            }

            Integer availNum = distruList.get(0).getAvailNum();
            if(distruNum > availNum){
                throw new RuntimeException("物品编码【" + goodsNo + "】/物品名称【" + goodsName + "】待发放余量不足");
            }

            //新增发放记录表
            TbWarehouseDistribution distribution = new TbWarehouseDistribution();
            BeanUtils.copyProperties(item,distribution);
            distribution.setWarehouseDistributionId(UUIDUtils.getUUID());
            distribution.setWarehouseReqdetailId(item.getWarehouseReqDetailId());
            EntityTools.setCreateEmpAndTime(distribution);
            this.warehouseDistributionMapper.insert(distribution);

        }

    }


}
