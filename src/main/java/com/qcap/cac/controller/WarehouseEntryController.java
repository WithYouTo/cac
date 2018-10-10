package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.WarehouseEntry;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbMenu;
import com.qcap.core.log.LogObjectHolder;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 入库管理控制器
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseEntry")
public class WarehouseEntryController {


    @Autowired
    private IWarehouseEntryService warehouseEntryService;


    /**
     * 获取入库管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(WarehouseEntrySearchParam warehouseEntrySearchParam) {
        if (StringUtils.isEmpty(warehouseEntrySearchParam.getStoreRoomId())) {
            //return PageResParams.newInstance()
        }
        List<Map> list = warehouseEntryService.getEntryList(warehouseEntrySearchParam);
        PageInfo pageInfo = new PageInfo(list);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    }


    /**
     * 获取入库管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/storeRoomList", method = RequestMethod.POST)
    public Object getStoreRoomlist() {
       List<Map> list =  this.warehouseEntryService.getStoreRoomList();
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", list);
    }

    /**
     * 新增入库管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(WarehouseEntry warehouseEntry) {
        warehouseEntryService.save(warehouseEntry);
        return "SUCCES";
    }

    /**
     * 删除入库管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String warehouseEntryId) {
        warehouseEntryService.removeById(warehouseEntryId);
        return "SUCCES";
    }

    /**
     * 修改入库管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(WarehouseEntry warehouseEntry) {
        warehouseEntryService.updateById(warehouseEntry);
        return "SUCCES";
    }

    /**
     * 入库管理详情
     */
    @RequestMapping(value = "/detail/{warehouseEntryId}")
    @ResponseBody
    public Object detail(@PathVariable("warehouseEntryId") String warehouseEntryId) {
        return warehouseEntryService.getById(warehouseEntryId);
    }
}
