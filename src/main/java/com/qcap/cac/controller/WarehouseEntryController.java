package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.dto.WarehouseEntrySearchParam;
import com.qcap.cac.entity.WarehouseEntry;
import com.qcap.cac.poiEntity.EntryPoiEntity;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbMenu;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.log.LogObjectHolder;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.poi.PoiUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
    private IWarehouseEntryService warehouseEntryService;


    /**
     * 入库管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public PageResParams list(WarehouseEntrySearchParam warehouseEntrySearchParam) {

        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(warehouseEntrySearchParam.getStoreroomId())) {
            list = warehouseEntryService.getEntryList(warehouseEntrySearchParam);
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
