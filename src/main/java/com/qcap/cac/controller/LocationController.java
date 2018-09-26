package com.qcap.cac.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qcap.core.controller.BaseController;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.core.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.core.common.CoreConstant;
import com.qcap.cac.service.LocationSrv;

/** 
 * @ClassName: LocationController 
 * @Description: TODO
 * @author: baojianxing
 * update:lijiangsha
 * @date: 2018年4月25日 下午1:51:28  
 */
@Controller
@RequestMapping("/location")
public class LocationController extends BaseController {
	
	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private LocationSrv locationSrv;
	/**
	 * 加载省市区结构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/select")
	@ResponseBody
	public Object selectAll(HttpServletRequest request, HttpServletResponse response) {
		Map param= new HashMap<>();
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			list = this.locationSrv.selectAll(param);
			resultMap.put("locationList", list);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"显示子节点成功",resultMap);
		} catch (Exception e) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			e.printStackTrace();
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE,"显示子节点失败",resultMap);
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@RequestMapping(value = "/selectLocationByPage", method = RequestMethod.POST)
	@ResponseBody
	public Object selectLocationByPage(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map params=new HashMap<>(); 
		String pId = TenpayUtil.toString(request.getParameter("pId"))  ;
		if (pId != null && !pId.isEmpty()) {
			params.put("pId", pId);
		}else {
			params.put("pId", "-1");
		}
		new PageFactory<Map>().defaultPage();
        List<Map<String,Object>> list = this.locationSrv.selectLocationByPage(params);
       
        PageInfo pageInfo = new PageInfo( (List<Map>) new LogWarpper(list).warp());
        Page pageList = (Page) list;
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("total",pageInfo.getTotal());
        result.put("rows", pageList);
        return PageResParams.newInstance(CoreConstant.SUCCESS_CODE,"",pageInfo.getTotal(),list);
	}
	
	
}
