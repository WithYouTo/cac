package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.*;
import com.qcap.cac.dto.*;
import com.qcap.cac.entity.TbWarehouseDistribution;
import com.qcap.cac.entity.TbWarehouseReqdetail;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.entity.TbWarehouseStock;
import com.qcap.cac.service.GoodsApplyRestSrv;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Resource
    private LoginRestMapper loginRestMapper;


    @Resource
    private TempTaskSrv tempTaskSrv;

    @Resource
    private MessageRestSrv messageRestSrv;

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
        paramMap.put("date", cn.hutool.core.date.DateUtil.formatDate(new Date()));
        return this.goodsApplyRestMapper.queryAvailDistributionList(paramMap);
    }


    /**
     * 物品出库
     * @param goodsOutListReq
     */
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

            String warehouseReqId = warehouseReqdetail.getWarehouseRequId();
            TbWarehouseRequ requ = this.warehouseRequMapper.selectById(warehouseReqId);
            if(null == requ){
                throw new RuntimeException("根据主键查询不到领用单信息");
            }

            String currProgramCode = requ.getProgramCode();
            String goodsNo = warehouseReqdetail.getGoodsNo();
            String goodsName = warehouseReqdetail.getGoodsName();

            //查询库存余量（库存表中-一个goodsNo对应一条记录，在入库时做了限制）
//            QueryWrapper<TbWarehouseStock> wrapper = new QueryWrapper<>();
//            wrapper.eq("GOODS_NO",goodsNo);
//            wrapper.eq("PROGRAM_CODE",currProgramCode);
            TbWarehouseStock stock = goodsApplyRestMapper.selectExistGood(currProgramCode,goodsNo);
            if(null == stock){
                throw new RuntimeException("根据物品编码没有查询到库存信息");
            }

            //库存数量
            Integer goodsNum = ToolUtil.toInt(stock.getGoodsNum());
            //最低警戒线
            Integer limitStore = ToolUtil.toInt(stock.getLimitStore());


            if(realNum > goodsNum){
                throw new RuntimeException("物品编码【" + goodsNo + "】/物品名称【" + goodsName + "】库存余量不足");
            }

            //正常出库
            warehouseReqdetail.setWarehouseReqdetailId(warehouseReqDetailId);
            warehouseReqdetail.setRealNum(item.getRealNum());
            warehouseReqdetail.setRequStatus(CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
            this.goodsApplyRestMapper.updateReqDetailByGoodsOut(EntityTools.setCreateEmpAndTime(warehouseReqdetail));

            requ.setWarehouseRequId(warehouseReqdetail.getWarehouseRequId());
            requ.setRequStatus(CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
            this.warehouseRequMapper.updateById(requ);

            //更新库存
            stock.setGoodsNum(goodsNum - realNum);
            this.goodsApplyRestMapper.updateStockByGoodsOut(EntityTools.setUpdateEmpAndTime(stock));

            //判断库存数量是否小于最低警戒线（库存和最低警戒线的单位在导入必须保持一致）

            if(goodsNum - realNum  < limitStore){
//                String programCode = "";
//                List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
//                if(!CollectionUtils.isEmpty(programCodes)){
//                    programCodes.removeAll(Collections.singleton(""));
//                    programCode =  StringUtils.join(programCodes,",");
//                }
                //向角色为库管的人员推送消息
                String roleNum = RedisTools.getCommonConfig("CAC_WAREHOUSE_ROLE_NUM");
                String message = RedisTools.getCommonConfig("CAC_LIMIT_STORE_MESSAGE");
                List<UserListResp> userList = loginRestMapper.getUserListByRoleNum(roleNum,currProgramCode);

                List<String> pushList = userList.stream().map(UserListResp::getEmployeeCode).collect(Collectors.toList());
                message = "物品编码【" + goodsNo + "】/物品名称【" + goodsName + "】的" + message;
                String title = "库管物品出库";
                messageRestSrv.JpushMessage(pushList, currProgramCode,message,title);
            }
        }

    }

    @Override
    public void updateDistribution(GoodsOutDistruListReq goodsOutDistruListReq) {
        //发放前---判断是否有余量
        List<GoodsOutDistruReq> list = goodsOutDistruListReq.getGoodsOutDistruReqList();
        for(GoodsOutDistruReq item : list){

            String requDetailId = item.getWarehouseReqDetailId();
            TbWarehouseReqdetail warehouseReqdetail = this.warehouseReqDetailMapper.selectById(requDetailId);
            if(null == warehouseReqdetail){
                throw new RuntimeException("根据主键查询不到领用明细单信息");
            }
            TbWarehouseRequ warehouseRequ = this.warehouseRequMapper.selectById(warehouseReqdetail.getWarehouseRequId());
            if(null == warehouseRequ){
                throw new RuntimeException("根据主键查询不到领用主单信息");
            }

            String employeeCode = item.getEmployeeCode();
            String positionCode = item.getPositionCode();
            String goodsNo = item.getGoodsNo();
            String goodsName = item.getGoodsName();
            String areaCode = item.getAreaId();
            Integer distruNum = item.getDistrNum();

            if(StringUtils.isEmpty(areaCode)){
                throw new RuntimeException("请选择区域");
            }

            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("positionCode",positionCode);
            paramMap.put("employeeCode",employeeCode);
            paramMap.put("goodsNo",goodsNo);
            paramMap.put("date", DateUtil.formatDate(new Date(),"yyyy-MM-dd"));
            paramMap.put("requStatus",CommonConstant.WAREHOUSE_REQ_STATUS_RECEIVE);
            List<GoodsDistributionDetailReq> distruList = this.goodsApplyRestMapper.queryAvailDistributionList(paramMap);
            if(CollectionUtils.isEmpty(distruList)){
                throw new RuntimeException("根据岗位编码没有查询到待发放信息");
            }

            Integer availNum = distruList.get(0).getAvailNum();
            if(distruNum > availNum){
                throw new RuntimeException("物品编码【" + goodsNo + "】/物品名称【" + goodsName + "】待发放余量不足");
            }

            //根据岗位查询当班人员
            Map<String, Object> user =  this.tempTaskSrv.selectDefaultEmployee(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"),areaCode);

            //新增发放记录表
            TbWarehouseDistribution distribution = new TbWarehouseDistribution();
            BeanUtils.copyProperties(item,distribution);
            distribution.setDistrDate(DateUtil.dateToString(new Date()));
            distribution.setWarehouseDistributionId(UUIDUtils.getUUID());
            distribution.setWarehouseReqdetailId(item.getWarehouseReqDetailId());
            distribution.setProgramCode(warehouseRequ.getProgramCode());
            EntityTools.setCreateEmpAndTime(distribution);
            if(MapUtils.isNotEmpty(user) && "1".equals(ToolUtil.toStr(user.get("flag")))){
                distribution.setUserCode(ToolUtil.toStr(user.get("employeeCode")));
                distribution.setUserName(ToolUtil.toStr(user.get("employeeName")));
            }
            this.warehouseDistributionMapper.insert(distribution);

        }

    }


}
