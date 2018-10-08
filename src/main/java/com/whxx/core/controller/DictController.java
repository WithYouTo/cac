//package com.whxx.core.controller;
//
//
//import com.whxx.core.annotation.BussinessLog;
//import com.whxx.core.dict.DictMap;
//import com.whxx.core.exception.BizExceptionEnum;
//import com.whxx.core.exception.BussinessException;
//import com.whxx.core.factory.ConstantFactory;
//import com.whxx.core.log.LogObjectHolder;
//import com.whxx.core.service.DictSrv;
//import com.whxx.core.warpper.DictWarpper;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * 字典控制器
// *
// * @author fengshuonan
// * @date 2017年4月26日 12:55:31
// */
//@RestController
//@RequestMapping("/dict")
//public class DictController
//{
//
//    @Autowired
//    private DictSrv dictSrv;
//
//
//    /**
//     * 新增字典
//     *
//     * @param dictValues 格式例如   "1:启用;2:禁用;3:冻结"
//     */
//    @BussinessLog(value = "添加字典记录", key = "dictName,dictValues", dict = DictMap.class)
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public Object add(String dictName, String dictValues) {
//        if (StringUtils.isAnyEmpty(dictName, dictValues))
//        {
//            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.dictSrv.addDict(dictName, dictValues);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 获取所有字典列表
//     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(String condition) {
//        List<Map<String, Object>> list = this.dictSrv.list(condition);
//        return super.warpObject(new DictWarpper(list));
//    }
//
//    /**
//     * 字典详情
//     */
//    @RequestMapping(value = "/detail/{dictId}")
//    @ResponseBody
//    public Object detail(@PathVariable("dictId") String dictId) {
//        return dictSrv.selectById(dictId);
//    }
//
//    /**
//     * 修改字典
//     */
//    @BussinessLog(value = "修改字典", key = "dictName,dictValues", dict = DictMap.class)
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(String dictId, String dictName, String dictValues) {
//        if (StringUtils.isAnyEmpty(dictId, dictName, dictValues))
//        {
//            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
//        }
//        dictSrv.editDict(dictId, dictName, dictValues);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 删除字典记录
//     */
//    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam String dictId) {
//
//        //缓存被删除的名称
//        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));
//
//        this.dictSrv.delteDict(dictId);
//        return SUCCESS_TIP;
//    }
//
//}
