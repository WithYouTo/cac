package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipChargeSearchDto;
import com.qcap.cac.service.EquipChargeSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/equipCharge")
public class EquipChargeController {

    @Autowired
    private EquipChargeSrv equipChargeSrv;

    /**
     *
     * @Description: 获取设备充电记录列表
     *
     *
     * @MethodName: listEquipCharge
     * @Parameters: [equipChargeSearchDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/10 15:24
     */
    @ResponseBody
    @RequestMapping(value = "/listEquipCharge", method = RequestMethod.POST)
    public Object listEquipCharge(IPage<Map<String, Object>> page, @Valid EquipChargeSearchDto equipChargeSearchDto){
        this.equipChargeSrv.listEquipCharge(page,equipChargeSearchDto);
        List<Map<String, Object>> list = page.getRecords();

        for(Map<String, Object> map:list){
            String status = map.get("status").toString();
            CommonConstant.EQUIP_CHARGE_STATUS.get(status);
            map.put("statusName", CommonConstant.EQUIP_CHARGE_STATUS.get(status));
        }
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(),list);
    }


    /**
     *
     * @Description: 获取设备充电总时长
     *
     *
     * @MethodName: getChargeTotalTimeByEquipId
     * @Parameters: [equipId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/10 15:24
     */
    @ResponseBody
    @RequestMapping(value = "/getChargeTotalTimeByEquipId", method = RequestMethod.POST)
    public Object getChargeTotalTimeByEquipId(String equipId){
        String totalTime = this.equipChargeSrv.getChargeTotalTimeByEquipId(equipId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, totalTime);
    }
}
