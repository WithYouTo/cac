package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.EquipUseSearchDto;
import com.qcap.cac.service.EquipUseSrv;
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
@RequestMapping("/equipUse")
public class EquipUseController{

    @Autowired
    private EquipUseSrv equipUseSrv;

    /**
     *
     * @Description: 获取设备使用记录列表
     *
     *
     * @MethodName: listEquipUse
     * @Parameters: [equipUseSearchDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/10 15:25
     */
    @ResponseBody
    @RequestMapping(value = "/listEquipUse", method = RequestMethod.POST)
    public Object listEquipUse(@Valid EquipUseSearchDto equipUseSearchDto){
        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> list = this.equipUseSrv.listEquipUse(equipUseSearchDto);
        PageInfo pageInfo = new PageInfo(list);
        for(Map<String, Object> map:list){
            String status = map.get("status").toString();
            CommonConstant.EQUIP_USE_STATUS.get(status);
            map.put("statusName", CommonConstant.EQUIP_USE_STATUS.get(status));
        }
        Page pageList = (Page) list;

        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), pageList);
    }


    /**
     *
     * @Description: 获取设备使用总时长
     *
     *
     * @MethodName: getUseTotalTimeByEquipId
     * @Parameters: [equipId]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/10 15:26
     */
    @ResponseBody
    @RequestMapping(value = "/getUseTotalTimeByEquipId", method = RequestMethod.POST)
    public Object getUseTotalTimeByEquipId(String equipId){
        String totalTime = this.equipUseSrv.getUseTotalTimeByEquipId(equipId);
        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, totalTime);
    }
}
