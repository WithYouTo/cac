package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipRepairSearchParam;
import com.qcap.cac.service.EquipRepairSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipRepair")
public class EquipRepairController{

    @Autowired
    private EquipRepairSrv equipRepairSrv;


    /**
     *
     * @Description: 获取设备保修记录列表
     *
     *
     * @MethodName: listEquipRepair
     * @Parameters: [equipRepairSearchParam]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/10 15:23
     */
    @ResponseBody
    @RequestMapping(value = "/listEquipRepair", method = RequestMethod.POST)
    public Object listEquipRepair(IPage<Map<String, Object>> page,@Valid EquipRepairSearchParam equipRepairSearchParam){
        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> list = this.equipRepairSrv.listEquipRepair(equipRepairSearchParam);
        PageInfo pageInfo = new PageInfo(list);
        for(Map<String, Object> map:list){
            String status = map.get("status").toString();
            CommonConstant.EQUIP_REPAIR_STATUS.get(status);
            map.put("statusName", CommonConstant.EQUIP_REPAIR_STATUS.get(status));
        }
        Page pageList = (Page) list;

        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), pageList);
    }

    /**
     *
     * @Description: 修改设备报修记录状态
     *
     *
     * @MethodName: updateEquipRepair
     * @Parameters: [equipRepairId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/10 15:27
     */
    @ResponseBody
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public Object updateEquipRepair(String equipRepairId){
        this.equipRepairSrv.updateEquipRepair(equipRepairId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_UPDATE_DESC, null);
    }
}
