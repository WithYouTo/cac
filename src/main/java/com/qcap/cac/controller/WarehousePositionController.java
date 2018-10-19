package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehousePosition;
import com.qcap.cac.service.WarehousePositionService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 库位管理
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehousePosition")
public class WarehousePositionController {


    @Autowired
    private WarehousePositionService warehousePositionService;


    /**
     * 获取库位列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(WarehouseEntryDto warehouseEntryDto) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<TbWarehousePosition> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(warehouseEntryDto.getStoreroomId())) {
            list = warehousePositionService.list(new QueryWrapper<TbWarehousePosition>()
                    .eq("storeroom_Id", warehouseEntryDto.getStoreroomId())
                    .eq("delete_flag","N"));
        }
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), pageList);
    }


    /**
     * 仓库的详情
     */
    @ResponseBody
    @RequestMapping(value = "/storeRoomInfo", method = RequestMethod.POST)
    public Object getStoreRoomInfoInfo(String storeroomId) {
       TbArea area =  this.warehousePositionService.selectAreaById(storeroomId);
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", area);
    }


    /**
     * 新增库位
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object savePosition(TbWarehousePosition position) {
        if(null == position){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "库位信息为空", position);
        }
        Integer exist = this.warehousePositionService.selectExistPosition(position);
        if(exist > 0){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "库位已经存在", position);
        }
        this.warehousePositionService.insertPosition(position);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "库位新增成功", null);
    }


    /**
     * 修改库位
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updatePosition(TbWarehousePosition position) {
        if(null == position){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "库位信息为空", position);
        }
        Integer exist = this.warehousePositionService.selectExistPosition(position);
        if(exist > 1){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "库位已经存在", position);
        }
        this.warehousePositionService.updatePosition(position);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "库位更新成功", null);
    }


    /**
     * 修改库位
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deletePosition(TbWarehousePosition position) {
        if(null == position){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "删除库位信息参数为空", position);
        }
        //判断仓库是否存在物品
        Integer exist = this.warehousePositionService.selectGoodsExistPosition(position.getWarehousePositionId());
        if(exist > 0){
            return ResParams.newInstance(CoreConstant.FAIL_CODE, "库位存在物品，不能删除", position);
        }
        this.warehousePositionService.updateById(position);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "库位删除成功", null);
    }

}
