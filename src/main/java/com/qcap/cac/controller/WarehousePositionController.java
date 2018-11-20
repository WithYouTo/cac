package com.qcap.cac.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WarehouseEntryDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbWarehousePosition;
import com.qcap.cac.service.WarehousePositionService;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public PageResParams list(IPage<TbWarehousePosition> page,WarehouseEntryDto warehouseEntryDto) {
        try {
            this.warehousePositionService.getPositionList(page,warehouseEntryDto);
        } catch (Exception e) {
            return PageResParams.newInstance(CoreConstant.FAIL_CODE, CommonCodeConstant.ERROR_CODE_40401_MSG, page.getTotal(), page.getRecords());
        }
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }


    /**
     * 仓库的详情
     */
    @ResponseBody
    @RequestMapping(value = "/storeRoomInfo", method = RequestMethod.POST)
    public Object getStoreRoomInfoInfo(String storeroomId) {
       TbArea area =  this.warehousePositionService.selectAreaById(storeroomId);
       return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, area);
    }


    /**
     * 新增库位
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Object savePosition(TbWarehousePosition position) {
        try {
            this.warehousePositionService.insertPosition(position);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_INSERT_DESC, null);
    }


    /**
     * 修改库位
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updatePosition(TbWarehousePosition position) {
        try {
            this.warehousePositionService.updatePosition(position);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }


    /**
     * 删除库位
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deletePosition(TbWarehousePosition position) {
        try {
            this.warehousePositionService.deletePosition(position);
        } catch (Exception e) {
            return ResParams.newInstance(CoreConstant.FAIL_CODE,e.getMessage(), null);
        }
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_DELETE_DESC, null);
    }

}
