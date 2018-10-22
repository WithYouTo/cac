package com.qcap.cac.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.dto.AttenceSearchDto;
import com.qcap.cac.service.AttenceSrv;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attence")
public class AttenceController {

    @Autowired
    private AttenceSrv attenceSrv;

    /**
     *
     * @Description: 获取考勤列表
     *
     *
     * @MethodName: listAttence
     * @Parameters: [attenceSearchDto]
     * @ReturnType: java.lang.Object
     *
     * @author huangxiang
     * @date 2018/10/11 20:25
     */
    @ResponseBody
    @RequestMapping(value = "/listAttence", method = RequestMethod.POST)
    public Object listAttence(IPage<Map<String, Object>> page, @Valid AttenceSearchDto attenceSearchDto){
//        new PageFactory<Map<String, Object>>().defaultPage();
        this.attenceSrv.listAttence(page,attenceSearchDto);
//        PageInfo pageInfo = new PageInfo(list);
//        Page pageList = (Page) list;
        return PageResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, page.getTotal(), page.getRecords());
    }
}
