package com.qcap.cac.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.poiEntity.EntryPoiEntity;
import com.qcap.cac.service.WarehouseEntryService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.poi.PoiUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 入库管理
 *
 * @author cindy
 * @Date 2018-10-09 19:20:15
 */
@Controller
@RequestMapping("/warehouseEntry")
public class WarehouseEntryController {


    @Autowired
    private WarehouseEntryService warehouseEntryService;


    /**
     * 入库管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(WarehouseEntryDto warehouseEntryDto) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(warehouseEntryDto.getStoreroomId())) {
            list = warehouseEntryService.getEntryList(warehouseEntryDto);
        }
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;

        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), pageList);
    }


    /**
     * 获取储藏室下拉框
     */
    @ResponseBody
    @RequestMapping(value = "/storeRoomList", method = RequestMethod.POST)
    public Object getStoreRoomlist() {
       List<Map> list =  this.warehouseEntryService.getStoreRoomList();
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "查询成功", list);
    }


    /*
     * 导入
     */
    @RequestMapping(method = RequestMethod.POST,value = "/importExcel")
    @ResponseBody
    public Object importExcel(MultipartFile file){
        //解析excel，
        List<EntryPoiEntity> entryList = PoiUtils.importExcel(file,0,1,EntryPoiEntity.class);
        Integer count = 0;
        try {
            count = this.warehouseEntryService.importExcel(entryList);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE, e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "成功导入" + count + "条记录", null);
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
