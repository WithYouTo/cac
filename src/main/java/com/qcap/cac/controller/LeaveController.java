package com.qcap.cac.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AttenceSearchParam;
import com.qcap.cac.dto.LeaveSearchParam;
import com.qcap.cac.service.AttenceSrv;
import com.qcap.cac.service.LeaveSrv;
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
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveSrv leaveSrv;

    @ResponseBody
    @RequestMapping(value = "/listLeave", method = RequestMethod.POST)
    public Object listLeave(LeaveSearchParam leaveSearchParam){
        new PageFactory<Map<String, Object>>().defaultPage();

        List<Map<String, Object>> list = this.leaveSrv.listLeave(leaveSearchParam);
        PageInfo pageInfo = new PageInfo(list);
        Page pageList = (Page) list;

        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, pageInfo.getTotal(), pageList);
    }
}
