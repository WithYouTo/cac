package com.qcap.cac.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.service.LoginLogTestSrv;
import com.qcap.core.warpper.LogWarpper;
import com.qcap.core.controller.BaseController;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.LoginLog;
import com.qcap.core.model.OperationLog;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 日志管理的控制器
 *
 * @ClassName: LoginLogController
 *
 * @author huangxiang
 * @date 2017/12/26 16:51
 */
@Controller
@RequestMapping("/loginLogmanage")
public class LoginLogManageController extends BaseController {
    
	private static Logger logger = LoggerFactory.getLogger(LoginLogManageController.class);
    @Autowired
    private LoginLogTestSrv loginLogTestSrvImpl;
    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String userName,@RequestParam(required = false) String logName) {
        new PageFactory<OperationLog>().defaultPage();
        String endTime="";
        StringBuffer sb = new StringBuffer();
        StringBuffer sb1 = new StringBuffer();
        if(beginTime!=null&&!"".equals(beginTime)) {
        	  String[] date=beginTime.split(" ");
        	  sb.append(date[0]);
        	  sb.append(" ");
        	  sb.append(date[1]);
        	  beginTime=sb.toString();
              sb1.append(date[3]);
              sb1.append(" ");
              sb1.append(date[4]);
              endTime=sb1.toString();
        }
        Map params=new HashMap();
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("userName", userName);
        params.put("logName",logName);
        List<Map<String, Object>> list = loginLogTestSrvImpl.getLoginLogs(params);
        PageInfo pageInfo = new PageInfo( (List<LoginLog>) new LogWarpper(list).warp());
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE,"",pageInfo.getTotal(),list);
    }

    /**
     * 删除日志
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
   public Object delLoginLog(Model model, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> data = new HashMap<>();
        try{
            String id = request.getParameter("ids");
            if(id==null||"".equals(id)){
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }else{
                String[] idStr=id.split(",");
                int num = loginLogTestSrvImpl.delLoginLog(idStr);
                if(num>0){
                    return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, data);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResParams.newInstance(1006, "登录日志删除失败", data);
        }
        return null;
    }
}
