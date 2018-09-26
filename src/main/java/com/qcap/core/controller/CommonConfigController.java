package com.qcap.core.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.model.TbCommonConfig;
import com.qcap.core.service.CommonConfigSrv;
import com.qcap.core.utils.CommUtil;
import com.qcap.core.utils.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;


/**
 * 
 * @ClassName: CommonConfigController 
 * @Description: TODO
 * @author: 聂**
 * @date: 2018年5月21日 上午11:04:14
 */
@Controller
@RequestMapping("/commconfig")
public class CommonConfigController {
	
    @Autowired
    private CommonConfigSrv commonConfigSrv;
    
    //下拉菜单类型
    @SuppressWarnings("rawtypes")
	@RequestMapping(value="/types", method = RequestMethod.POST)
    @ResponseBody
    public Map getTypes(){
    	
    	Map map = new HashMap<>();
    	
    	List<Map> list= commonConfigSrv.selectTypes();
    	
    	map = CommUtil.setMessage(200, "", list);
    	return map; 		
    }
    
    //分页查询配置信息
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/listPage", method = RequestMethod.POST)
    @ResponseBody
    public Object selectPageList(@RequestParam(required=false)String key,@RequestParam(required=false)String type){
    	new PageFactory<Map<String, Object>>().defaultPage();
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("seckey",key);
    	param.put("sectype",type);
    	List<Map<String, Object>> list=null;
		try {
			list = commonConfigSrv.selectByPage(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	PageInfo pageInfo=new PageInfo(list);
    	return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
    	
    }
    
	//添加配置信息
    @RequestMapping(value="/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object insert(@Valid TbCommonConfig config, BindingResult result, HttpServletRequest request)throws Exception{
    	if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
    	if(config.getType().contains(".")){
    		return ResParams.newInstance(CoreConstant.FAIL_CODE, "类型中不能包含'.'符号", null);
    	}
    	Map<String,Object> param=new HashMap<>();
    	param.put("keyTest", config.getKey());
    	param.put("typeTest", config.getType());
    	String exist = commonConfigSrv.ifExist(param);
    	
    	if(!(exist==null||exist.equals(""))){
    		return ResParams.newInstance(CoreConstant.FAIL_CODE, "所选类型的Key已经存在", null);
    	}
    	config.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    	this.commonConfigSrv.insert(config);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
	}
    
    //修改配置信息
   @RequestMapping(value="/update", method = RequestMethod.POST)
   @ResponseBody
   public Object update(@Valid TbCommonConfig config, HttpServletRequest request){
	   	Map<String,Object> param=new HashMap<>();
	   	param.put("keyTest", config.getKey());
	   	param.put("typeTest", config.getType());
	   	String id = commonConfigSrv.ifExist(param);	   
	   //如果key存在，判断是否是当前修改的信息
	   if(!(id==null||id.equals(""))){
	   		if(!id.equals(config.getId())){
	   			return ResParams.newInstance(CoreConstant.FAIL_CODE, "所选类型的Key已经存在", null);
	   		}
	   }
	   this.commonConfigSrv.updateConfig(config);
	   return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
   }
   
   //批量删除配置信息
   @RequestMapping(value="/batchdelete", method = RequestMethod.POST)
   @ResponseBody
   public Object deleteBatch(@RequestParam(required=true) String ids){
		if (ToolUtil.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		String[] array=ids.split(",");
		this.commonConfigSrv.batchDeleteByIds(array);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
   }
   
   //类型菜单树查询
   @SuppressWarnings({ "unchecked", "rawtypes" })
   @RequestMapping(value="/select" , method = RequestMethod.POST)
   @ResponseBody
   public Object selectTree(){
	   List<Map> types = this.commonConfigSrv.selectTypes();
	   Map map=new HashMap();
	   map.put("type", "类型");
	   map.put("open",true);
	   map.put("mark", "abcdefg:");
	   map.put("children", types);
	   return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", map);
   }
   
   //刷新redis缓存信息
   @RequestMapping(value="/refreshCache",method=RequestMethod.POST)
   @ResponseBody
   public Object refreshConfigCache(){
	   
	   try {
		   	this.commonConfigSrv.inititalConfigCache();
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "刷新成功！",null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "刷新失败！", null);
		}
   }
}
