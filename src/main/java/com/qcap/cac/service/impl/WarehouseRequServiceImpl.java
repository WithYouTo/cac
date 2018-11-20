package com.qcap.cac.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.WarehouseReqDetailMapper;
import com.qcap.cac.dao.WarehouseRequMapper;
import com.qcap.cac.dto.WarehouseReqDto;
import com.qcap.cac.entity.TbWarehouseRequ;
import com.qcap.cac.service.TempTaskSrv;
import com.qcap.cac.service.WarehouseRequService;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-09
 */
@Service
public class WarehouseRequServiceImpl extends ServiceImpl<WarehouseRequMapper, TbWarehouseRequ> implements WarehouseRequService {


    @Resource
    private WarehouseRequMapper warehouseRequMapper;

    @Resource
    private WarehouseReqDetailMapper warehouseReqDetailMapper;

    @Resource
    private TempTaskSrv tempTaskSrv;


    @Override
    public void getRequList(IPage<Map<String,String>> page,@Valid WarehouseReqDto warehouseReqDto) {
        List<Map<String,String>> list = this.warehouseRequMapper.getRequGoodsList(page,warehouseReqDto);
        page.setRecords(list);
    }

    @Override
    public void commitRequ(TbWarehouseRequ warehouseRequ) {

        String warehouseRequId = warehouseRequ.getWarehouseRequId();
        if(StringUtils.isEmpty(warehouseRequId)){
            throw  new RuntimeException("请先选择一条领用单记录");
        }

        warehouseRequ = this.warehouseRequMapper.selectById(warehouseRequId);
        if(null == warehouseRequ){
            throw  new RuntimeException("根据主键没有查询到记录");
        }

        if (!CommonConstant.WAREHOUSE_REQ_STATUS_INIT.equals(warehouseRequ.getRequStatus())){
            throw  new RuntimeException("选择的记录不是已申请，无法进行修改操作");
        }

        //更新领用主表的状态为已提交
        warehouseRequ.setRequStatus(CommonConstant.WAREHOUSE_REQ_STATUS_COMMIT);
        //warehouseRequ.setStoreroomId(warehouseRequ.getStoreroomId());
        EntityTools.setUpdateEmpAndTime(warehouseRequ);
        this.warehouseRequMapper.updateById(warehouseRequ);
        //更新领用明细表的状态为已提交
        this.warehouseReqDetailMapper.updateReqDetailStatus(warehouseRequ);
    }

    @Override
    public void delete(String warehouseRequId) {

        if(StringUtils.isEmpty(warehouseRequId)){
            throw  new RuntimeException("请先选择一条记录");
        }

        TbWarehouseRequ warehouseRequ = this.warehouseRequMapper.selectById(warehouseRequId);
        if(null == warehouseRequ){
            throw  new RuntimeException("根据选择记录没有查询到信息");
        }

        if (!CommonConstant.WAREHOUSE_REQ_STATUS_INIT.equals(warehouseRequ.getRequStatus())){
            throw  new RuntimeException("选择的记录不是已申请，无法进行删除操作");
        }
        //删除主表
        this.warehouseRequMapper.deleteById(warehouseRequId);
        //删除明细表
        this.warehouseReqDetailMapper.deleteReqDetailStatus(warehouseRequ);
    }

    @Override
    public void insertWarehouseRequ(TbWarehouseRequ warehouseRequ,String employeeCode) {

        //查询当前登录人的岗位
        List<Map<String,Object>> positionList = tempTaskSrv.selectCurrountWorkingEmployee(employeeCode);

        if(CollectionUtils.isNotEmpty(positionList)){
            List<String> positionCodeList = new ArrayList<>();
            List<String> positionNameList = new ArrayList<>();
            for (Map<String, Object> m : positionList) {
                positionCodeList.add(ToolUtil.toStr(m.get("positionCode")));
                positionNameList.add(ToolUtil.toStr(m.get("positionName")));
            }
            String positionCode = String.join(",", positionCodeList);
            String positionName = String.join(",", positionNameList);

            warehouseRequ.setPositionCode(positionCode);
            warehouseRequ.setPositionName(positionName);
        }

        warehouseRequ.setRequBatchNo(UUIDUtils.getBatchNo());
        warehouseRequ.setRequStatus("INIT");
        EntityTools.setCreateEmpAndTime(warehouseRequ);
        this.warehouseRequMapper.insert(warehouseRequ);
    }
}