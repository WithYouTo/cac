package com.qcap.cac.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.cac.common.service.SmiSrv;
import com.qcap.cac.common.service.UserSrv;
import com.qcap.cac.model.Tbsmi01;
import com.qcap.cac.service.WebUserSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.core.properties.ConstantProperties;
import com.qcap.core.utils.MSGUtil;
import com.qcap.core.utils.Md5Utils;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.UUIDUtil;

/** 
 * @ClassName: WebUpdatePassController 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年6月28日 下午1:58:33  
 */
@Controller
@RequestMapping("/rest/webUserUpdatePass")
public class WebUpdatePassController {
	
	@Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private UserSrv userSrv;
    
    @Autowired
    private WebUserSrv webUserSrv;
   
    @Autowired
    private SmiSrv smiSrv;
    
    @Autowired
	ConstantProperties constantProperties;
	
	@RequestMapping(value = "/sendMsg", method = RequestMethod.POST)
   	@ResponseBody
   	public Object sendMsg(Model model, HttpServletRequest request) throws Exception {
		String phone= TenpayUtil.toString(request.getParameter("phone"));
    	Map<String, String> resultMap = new HashMap<String, String>();
    	Map res=new HashMap<>();
   		try {
			//smsUrl
    		String smsUrl = constantProperties.getSmstoSend();
    		String smsLogin = constantProperties.getSmstoLogin();
        	//发送验证码
    	    String url = smsUrl + phone + smsLogin;
    		resultMap = MSGUtil.SMS(url);
    		if(ToolUtil.isEmpty(resultMap)) {
    			res.put("flag", "-1");
    			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
    		}
			if (!"OK".equals(resultMap.get("code"))) {
				res.put("flag", "-1");
    			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
			} else {
				// 调用成功
				Tbsmi01 pi = new Tbsmi01();
				pi.setTbsmi01Id(UUIDUtil.getUUID());
				pi.setMobile(phone);
				pi.setMessidtfNo(resultMap.get("authCode"));
				pi.setUserType("W");
				pi.setMessidtfTime(new Date());
				pi.setCreateDate(new Date());
				pi.setCreateEmp(phone);
				pi.setVersion(0);

				Calendar nowTime = Calendar.getInstance();
				nowTime.add(Calendar.MINUTE, 5);
				pi.setMessidtfTimeout(nowTime.getTime());
				this.smiSrv.insert(pi);
		}
   			
   		} catch (Exception e) {
   			e.printStackTrace();
   			res.put("flag", "-1");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
   		}
   		res.put("flag", "1");
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"短信发送成功", res);
   	}
	
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST)
	@ResponseBody
	public Object registe(Model model, HttpServletRequest request) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String mobile=TenpayUtil.toString(request.getParameter("cellphone"));
		String  newPass=TenpayUtil.toString(request.getParameter("password"));
		String  code=TenpayUtil.toString(request.getParameter("vercode"));
		
		Map res=new HashMap<>();
		Map params  = new HashMap<>();
		try {
			String userId=webUserSrv.selectWebUserIdByPhone(mobile);
			
			if(ToolUtil.isEmpty(userId)) {
				res.put("flag", "-1");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"用户不存在！", res);
			}
			
			params.put("mobile",mobile);
			params.put("userType","W");
			Map smi = smiSrv.selectCode(params);
			
			String nowtime = TenpayUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
			
			
			boolean isCodeTrue=code.equals(TenpayUtil.toString(smi.get("messidtfNo")));
			if(!isCodeTrue){
				res.put("flag", "-2");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"验证码不正确！", res);
			}
			boolean isExpire=nowtime.compareTo(TenpayUtil.toString(smi.get("messidtfTimeout"))) < 0;
			if(!isExpire) {
				res.put("flag", "-3");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"验证码已过期，请重新获取！", res);
			}
			
			
			if (code.equals(TenpayUtil.toString(smi.get("messidtfNo")))
					&& nowtime.compareTo(TenpayUtil.toString(smi.get("messidtfTimeout"))) < 0) {
				String newSalt = Md5Utils.getSalt();
			    String newMd5 = Md5Utils.encrypt(newPass, newSalt);
			    Map param=new HashMap<>();
			    param.put("id", userId);
			    param.put("salt", newSalt);
			    param.put("password", newMd5);
			    this.webUserSrv.updatePass(param);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.put("flag", "-6");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"重置新密码失败！", res);
		}
		res.put("flag", "1");
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"密码修改成功！", res);
	}
	
	

}
