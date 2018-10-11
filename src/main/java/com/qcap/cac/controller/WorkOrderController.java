package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.WorkOrderSearchParam;
import com.qcap.cac.service.AttenceSrv;
import com.qcap.cac.service.WorkOrderSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workOrder")
public class WorkOrderController {

    @Autowired
    private WorkOrderSrv workOrderSrv;

    @ResponseBody
    @RequestMapping(value = "/listWorkOrder", method = RequestMethod.POST)
    public Object listWorkOrder(WorkOrderSearchParam workOrderSearchParam){
        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> list = this.workOrderSrv.listWorkOrder(workOrderSearchParam);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;

        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), pageList);

    }
}
