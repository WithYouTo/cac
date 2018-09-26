package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.controller.BaseController;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.*;
import com.qcap.core.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.common.ManagerStatus;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.model.PageResParams;
import com.qcap.core.utils.Const;
import com.qcap.core.utils.Md5Utils;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.UUIDUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.cac.common.service.PropertyCommonSrv;
import com.qcap.cac.model.TbUser;
import com.qcap.cac.model.ZTreeNode;
import com.qcap.cac.service.WebUserSrv;

/** 
 * @ClassName: WebUserController 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年4月20日 下午2:35:27  
 */
@Controller
@RequestMapping("/webUser")
public class WebUserController extends BaseController {

	private static String PREFIX = "/system/webuser/";
	
	
	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
    private RedisUtil redisUtil;
	
	 /**
     * 跳转到添加web用户管理
     */
    @RequestMapping("/webuser_add")
    public String webuserAdd() {
        return PREFIX + "webuser_add";
    }
    
    @Autowired
    private WebUserSrv webUserSrv;
    
    @Autowired
	private PropertyCommonSrv propertyCommonSrv;
    
    
    
 // 根据Unicode编码判断中文汉字和符号
 	private static boolean isChinese(char c) {
 		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
 		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
 				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
 				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
 				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
 				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
 				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
 				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
 			return true;
 		}
 		return false;
 	}
 	
 	
 	/**
	 * 获取登录人的账户
	 * @Title: getLoginUserAccount 
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	String getLoginUserAccount( HttpServletRequest request) {
		String token = request.getHeader(jwtProperties.getTokenHeader());
	    //String userId = jwtTokenUtil.getUsernameFromToken(token);
	    String userId = redisUtil.getUserId(token);
	    String workId=propertyCommonSrv.selectAccountByUserId(userId);
	    return workId;
	}

    @RequestMapping(value = "/selectPositionInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object selectPositionInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Map> list = new ArrayList<Map>();
		try {
			String companyCode = request.getParameter("companyCode");
			if(companyCode == null){
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", list);
			}
			char[] ch = companyCode.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (isChinese(ch[i])) {
					companyCode = this.propertyCommonSrv.selectCompCode(companyCode);
					break;
				}
			}
			list = this.webUserSrv.selectPositionInfo(companyCode);
		} catch (Exception e) {
			
		}
	//	return list;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", list);
	}
    
    /**
     * 根据公司查询 角色
     * @Title: selectRoleInfo 
     * @Description: TODO
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @return: List<Map>
     */
    @RequestMapping(value = "/selectRoleInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object selectRoleInfo(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Map> list = new ArrayList<Map>();
		try {
			String companyCode = request.getParameter("companyCode");
			if(companyCode == null){
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", list);
			}
			char[] ch = companyCode.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (isChinese(ch[i])) {
					companyCode = this.propertyCommonSrv.selectCompCode(companyCode);
					break;
				}
			}
			list = this.webUserSrv.selectRoleInfo(companyCode);
		} catch (Exception e) {
			
		}
		//return list;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", list);
	}
    
    /**
     * 跳转到更新web用户界面
     * @Title: selectInfoForDetail 
     * @Description: TODO
     * @param id
     * @param model
     * @return
     * @throws Exception
     * @return: String
     */
    @RequestMapping(value = "/toUpdate/{id}")
    @ResponseBody
	public Object selectInfoForDetail(@PathVariable String id, Model model)
			throws Exception {
		Map info = this.webUserSrv.selectByPrimaryKey(id);
		//model.addAttribute("info", info);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", info);
		//return PREFIX + "webuser_edit";
	}
    
    /**
     * 查询当前用户信息
     * @Title: selectInfoForDetail 
     * @Description: TODO
     * @param id
     * @param model
     * @return
     * @throws Exception
     * @return: String
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/webuserinfo")
    @ResponseBody
	public Object selectUserInfo(HttpServletRequest request, Model model)
			throws Exception {
    	String token = request.getHeader(jwtProperties.getTokenHeader());
	    //String userId = jwtTokenUtil.getUsernameFromToken(token);
	    String userId = redisUtil.getUserId(token);
    	Map param=new HashMap<>();
    	param.put("id",userId);
		Map info = this.webUserSrv.selectUserById(param);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", info);
	}
    
    
    /**
     * 查询当前用户信息
     * @Title: selectInfoForDetail 
     * @Description: TODO
     * @param id
     * @param model
     * @return
     * @throws Exception
     * @return: String
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/editpass")
    @ResponseBody
	public Object editUserPass(HttpServletRequest request, Model model)
			throws Exception {
    	Map res=new HashMap<>();
    	String token = request.getHeader(jwtProperties.getTokenHeader());
	    //String userId = jwtTokenUtil.getUsernameFromToken(token);
	    String userId = redisUtil.getUserId(token);
	    
	    String oldPass=TenpayUtil.toString(request.getParameter("oldPass"));
	    String newPass=TenpayUtil.toString(request.getParameter("newPass"));
	    
    	Map param=new HashMap<>();
    	param.put("id",userId);
		Map info = this.webUserSrv.selectUserById(param);
		
		String salt=TenpayUtil.toString(info.get("salt"));
		String encPass=TenpayUtil.toString(info.get("password"));
		
		//验证旧密码是否正确
		boolean isTrue=Md5Utils.isPasswordValid(encPass, oldPass, salt);
		if(!isTrue) {
			res.put("flag", "2");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
		}else {
			String newSalt = Md5Utils.getSalt();
		    String newMd5 = Md5Utils.encrypt(newPass, newSalt);
		    Map paramNew=new HashMap<>();
		    paramNew.put("id", userId);
		    paramNew.put("salt", newSalt);
		    paramNew.put("password", newMd5);
		    this.webUserSrv.updatePass(paramNew);
		    res.put("flag", "1");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
		}
		
	}
    
    
    /**
	 * 分页查询web用户信息
	 * @Title: selectCompaniesByPage 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public  Object list(Model model, HttpServletRequest request)
			throws Exception {
		Map map = new HashMap<>();
		String companyName=TenpayUtil.toString(request.getParameter("companyName"));
		String deptName=TenpayUtil.toString(request.getParameter("deptName"));
		String name=TenpayUtil.toString(request.getParameter("name"));
		String phone=TenpayUtil.toString(request.getParameter("phone"));
		
		map.put("companyCode", companyName);
		map.put("departmentId", deptName);
		map.put("name", name);
		map.put("phone", phone);

		
		new PageFactory<Map>().defaultPage();
		//查询web用户信息
        List<Map> list = webUserSrv.selectUserByPage(map);
        		
       
        PageInfo pageInfo = new PageInfo( (List<Map>) new LogWarpper(list).warp());
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
       // return result;
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}
	
	/**
	 * 删除web用户信息
	 * @Title: deleteWebUser 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "/delete" })
	@ResponseBody
	public Object deleteWebUser(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id = request.getParameter("userId");
			if (id == null || id.equals("")) {
				map.put("flag", "-1");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
			}
			String[] idArray=id.split(",");
			for(int i=0,size=idArray.length;i<size;i++) {
				String idItem=idArray[i];
				this.webUserSrv.deleteByPrimaryKey(idItem);
			}
			map.put("flag", "1");
		} catch (Exception e) {
			map.put("flag", "0");
		}
		//return map;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
	}
	
	/**
	 * 添加web用户
	 * @Title: addWebUser 
	 * @Description: TODO
	 * @param tbUser
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@RequestMapping({ "/add" })
	@ResponseBody
	public Object addWebUser(@Valid TbUser tbUser,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String phone=tbUser.getPhone();
		String account=tbUser.getAccount();
		String idCard=tbUser.getIdCard();
		
		Map paramAcc=new HashMap<>();
		paramAcc.put("account", account);
		String countAcc=this.webUserSrv.selectExistNum(paramAcc);
		//账号重复
		if(!"0".equals(countAcc)) {
			map.put("flag", "2");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
		}
		//校验手机号是否重复
		Map paramPh=new HashMap<>();
		paramPh.put("phone", phone);
		//param.put("account", account);
		//param.put("idCard", idCard);
		String countPh=this.webUserSrv.selectExistNum(paramPh);
		
		//手机号重复
		if(!"0".equals(countPh)) {
			map.put("flag", "3");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
		}
		
		if(!StringUtils.isEmpty(idCard)) {
			//校验身份号是否重复
			Map paramCard=new HashMap<>();
			paramCard.put("idCard", idCard);
			String countCard=this.webUserSrv.selectExistNum(paramCard);
			//身份证账号重复
			if(!"0".equals(countCard)) {
				map.put("flag", "4");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
			}
		}
		
		
		String uuid= UUIDUtil.getUUID();
		tbUser.setId(uuid);
		tbUser.setPassword(Const.DEFAULT_PWD);
		//设置默认密码
		// 完善账号信息
		tbUser.setSalt(Md5Utils.getSalt());
		tbUser.setPassword(Md5Utils.encrypt(tbUser.getPassword(), tbUser.getSalt()));
		tbUser.setStatus(ManagerStatus.OK.getCode());
		tbUser.setCreateTime(new Date());
		String workId=this.getLoginUserAccount(request);
		
		
		//账号  设置为手机号
		tbUser.setAccount(phone);
		
		
		String companyCode=tbUser.getCompanyCode();
		if(companyCode!=null&&!"".equals(companyCode)) {
			char[] ch = companyCode.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (isChinese(ch[i])) {
					try {
						companyCode = this.propertyCommonSrv.selectCompCode(companyCode);
						tbUser.setCompanyCode(companyCode);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		tbUser.setCreateEmp(workId);
		tbUser.setVersion(0);
		//设置用户类型是   "W" web系统用户
		tbUser.setUserType("W");
		
		try {
			this.webUserSrv.insert(tbUser);
			map.put("flag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "0");
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
		//return map;
	}
	
	
	/**
	 * 更新web用户
	 * @Title: update 
	 * @Description: TODO
	 * @param tbUser
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@RequestMapping({ "/update" })
	@ResponseBody
	public Object update(@Valid TbUser tbUser,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//修改时  校验  排除自身
		String id=tbUser.getId();
		
		String phone=tbUser.getPhone();
		String account=tbUser.getAccount();
		String idCard=tbUser.getIdCard();
		
		Map paramAcc=new HashMap<>();
		paramAcc.put("account", account);
		paramAcc.put("id", id);
		String countAcc=this.webUserSrv.selectExistNum(paramAcc);
		//账号重复                            
		if(!"0".equals(countAcc)) {
			map.put("flag", "2");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
		}
		//校验手机号是否重复
		Map paramPh=new HashMap<>();
		paramPh.put("phone", phone);
		//param.put("account", account);
		//param.put("idCard", idCard);
		paramPh.put("id", id);
		String countPh=this.webUserSrv.selectExistNum(paramPh);
		
		//手机号重复
		if(!"0".equals(countPh)) {
			map.put("flag", "3");
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
		}
		
		if(!StringUtils.isEmpty(idCard)) {
			//校验身份号是否重复
			Map paramCard=new HashMap<>();
			paramCard.put("idCard", idCard);
			paramCard.put("id", id);
			String countCard=this.webUserSrv.selectExistNum(paramCard);
			//身份证账号重复
			if(!"0".equals(countCard)) {
				map.put("flag", "4");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
			}
		}

		//账号设置为手机号
		tbUser.setAccount(phone);
		
		
		String companyCode=tbUser.getCompanyCode();
		if(companyCode!=null&&!"".equals(companyCode)) {
			char[] ch = companyCode.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (isChinese(ch[i])) {
					try {
						companyCode = this.propertyCommonSrv.selectCompCode(companyCode);
						tbUser.setCompanyCode(companyCode);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
		
		tbUser.setUpdateTime(new Date());

		String workId=this.getLoginUserAccount(request);
		tbUser.setUpdateEmp(workId);

		try {
			this.webUserSrv.updateByPrimaryKey(tbUser);
			map.put("flag", "1");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("flag", "0");
		}
		//return map;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
	}
	
	/**
	 * 
	 * 启用  冻结用户
	 * @Title: changeStatus 
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/changeStatus")
	@ResponseBody
	public Object changeStatus(Model model, HttpServletRequest request) {
		Map res=new HashMap<>();
		String id=TenpayUtil.toString(request.getParameter("id")) ;
		String status=TenpayUtil.toString(request.getParameter("status")) ;
		try {
			if(StringUtils.isEmpty(id)||StringUtils.isEmpty(status)) {
				//参数为空
				res.put("flag", "2");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
			}else {
				Map param=new HashMap<>();
				param.put("id", id);
				param.put("status", status);
				this.webUserSrv.changeUserStatus(param);
				res.put("flag", "1");
			}
		}catch(Exception e){
			res.put("flag", "0");
		}
		//return res;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
	}
	
	//重置密码
	@RequestMapping("/resetPwd")
	@ResponseBody
	public Object updatePass(Model model, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String id = request.getParameter("userId");
			if (id == null || id.equals("")) {
				map.put("flag", "-1");
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
			}
			String[] idArray=id.split(",");
			for(int i=0,size=idArray.length;i<size;i++) {
				String idItem=idArray[i];
				
				String newSalt = Md5Utils.getSalt();
			    String newMd5 = Md5Utils.encrypt(Const.DEFAULT_PWD, newSalt);
			    Map param=new HashMap<>();
			    param.put("id", idItem);
			    param.put("salt", newSalt);
			    param.put("password", newMd5);
			    this.webUserSrv.updatePass(param);
			}
			map.put("flag", "1");
		} catch (Exception e) {
			map.put("flag", "0");
		}
		//return map;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", map);
	}
	
	 /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeListByUserIdAndCom/{userId}/{companyCode}")
    @ResponseBody
    public Object roleTreeListByUserIdAndCom(@PathVariable String userId,@PathVariable String companyCode) {

    	List<ZTreeNode> nodes= webUserSrv.roleTreeListByRoleIdAndCompanyCode(userId,companyCode);

    	return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", nodes);
    }
	
    


    /**
     * 分配角色
     */
    
    @RequestMapping(value = "/setRole", method = RequestMethod.POST)
    @ResponseBody
    public Object setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
    	Map res=new HashMap<>();
//        if (ToolUtil.isOneEmpty(userId, roleIds)) {
//            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
//        }
        
        if(ToolUtil.isOneEmpty(userId)) {
        	 throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        
        //不勾选任何角色     删除用户所有绑定的角色
        if(ToolUtil.isOneEmpty(roleIds)) {
       	 	this.webUserSrv.deleteRoles(userId);
       	 	res.put("flag", "1");
        	return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
        }
       
        
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
        	res.put("flag", "0");
        	return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
        }

        this.webUserSrv.setRoles(userId, roleIds);
        res.put("flag", "1");
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"", res);
    }

	
}
