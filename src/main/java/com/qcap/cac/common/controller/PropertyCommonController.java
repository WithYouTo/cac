package com.qcap.cac.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qcap.core.model.ResParams;
import com.qcap.core.utils.CommUtil;
import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.cac.common.service.PropertyCommonSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.core.common.CoreConstant;
import com.qcap.core.controller.BaseController;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.jwt.JwtTokenUtil;

@Controller
@RequestMapping("/property")
public class PropertyCommonController extends BaseController {

	@Resource
	private PropertyCommonSrv propertyCommonSrv;

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RedisUtil redisUtil;

	private static final String SUCCESS = "success";

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
	 * 
	 * @Title: getLoginUserAccount
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: String
	 */
	String getLoginUserAccount(HttpServletRequest request) {
		String token = request.getHeader(jwtProperties.getTokenHeader());
		String userId = redisUtil.getUserId(token);
		String workId = propertyCommonSrv.selectAccountByUserId(userId);
		return workId;
	}

	/**
	 * 根据登录人的账号 查询所在公司的编码
	 * 
	 * @Title: getCompanyCodeByWorkId
	 * @Description: TODO
	 * @param workId
	 * @return
	 * @return: Map
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/getCompanyCodeByWorkId")
	public Object getCompanyCodeByWorkId(@RequestParam String workId) {
		String companyCode = this.propertyCommonSrv.selectCompanyCodeByWorkId(workId);
		Map res = new HashMap();
		res.put("companyCode", companyCode);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", res);
	}

	/**
	 * 根据公司别展示部门信息
	 * 
	 * @Title: listDeptInfo
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	@RequestMapping(value = "/listDeptInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object listDeptInfo(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map> list = new ArrayList<Map>();
		List<Map> result = new ArrayList<Map>();
		Map map0 = new HashMap<>();
		try {
			String companyCode = request.getParameter("companyCode");
			if (companyCode == null) {
				return list;
			}
			char[] ch = companyCode.toCharArray();
			for (int i = 0; i < ch.length; i++) {
				if (isChinese(ch[i])) {
					companyCode = this.propertyCommonSrv.selectCompCode(companyCode);
					break;
				}
			}
			String workId = this.getLoginUserAccount(request);
			Map param = new HashMap<>();
			String user_companyCode = this.propertyCommonSrv.selectCompanyCodeByWorkId(workId);
			// if(!"0".equals(user_companyCode) && !"009".equals(user_companyCode)){
			// param.put("workId", workId);
			// }
			param.put("companyCode", companyCode);
			param.put("workId", workId);

			map0.put("id", "");
			map0.put("text", "请选择");
			result.add(map0);
			list = this.propertyCommonSrv.listDeptInfo(param);
			result.addAll(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResParams.newInstance(500, "获取部门信息失败", null);
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", result);
	}

	/**
	 * 公司下拉框(总公司不展示)
	 * 
	 * @Title: selectSubCompanies
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	@RequestMapping(value = "/selectSubCompanies", method = RequestMethod.POST)
	@ResponseBody
	public List<Map> selectSubCompanies(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String token = request.getHeader(jwtProperties.getTokenHeader());
		String userId = getUserId(token);
		String workId = this.propertyCommonSrv.selectAccountByUserId(userId);
		List<Map> list = new ArrayList<Map>();
		Map param = new HashMap<>();
		try {
			// String workId = this.getLoginUserAccount(request);
			String companyCode = this.propertyCommonSrv.selectCompanyCodeByWorkId(workId);
			if (!"0".equals(companyCode)) {
				param.put("companyCode", companyCode);
			}
			list = this.propertyCommonSrv.selectSubCompanies(param);
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * 公司下拉框(总公司不展示)
	 * 
	 * @Title: selectSubCompanies
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	@RequestMapping(value = "/selectSubCompaniesV2", method = RequestMethod.POST)
	@ResponseBody
	public Map selectSubCompaniesV2(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map map = new HashMap<>();
		List<Map> list = new ArrayList<Map>();
		Map param = new HashMap<>();
		try {
			String token = request.getHeader(jwtProperties.getTokenHeader());
			String userId = getUserId(token);
			String workId = this.propertyCommonSrv.selectAccountByUserId(userId);
			String companyCode = this.propertyCommonSrv.selectCompanyCodeByWorkId(workId);
			if (!"0".equals(companyCode)) {
				param.put("companyCode", companyCode);
			}
			list = this.propertyCommonSrv.selectSubCompanies(param);
			map = CommUtil.setMessage(200, "", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * PC端：从通用代码档中查询模板类型
	 * 
	 * @Title: selectTemplateTypes
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	// @RequestMapping(value = "/selectTemplateTypes", method = RequestMethod.POST)
	// @ResponseBody
	// public List<Map> selectTemplateTypes(Model model, HttpServletRequest request,
	// HttpServletResponse response)
	// throws Exception {
	// List<Map> list = new ArrayList<Map>();
	// try {
	// String configCode = request.getParameter("configCode");
	// list = this.propertyCommonSrv.selectTemplateTypes(configCode);
	// } catch (Exception e) {
	// }
	// return list;
	// }

	/**
	 * PC端：从通用代码档中查询模板类型
	 * 
	 * @Title: selectTemplateTypes
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	@RequestMapping(value = "/selectTemplateTypes", method = RequestMethod.POST)
	@ResponseBody
	public Object selectTemplateTypes(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Map> list = new ArrayList<Map>();
		try {
			String configCode = request.getParameter("configCode");
			list = this.propertyCommonSrv.selectTemplateTypes(configCode);
		} catch (Exception e) {
		}
		// return list;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, list);
	}

	/**
	 * PC端：根据父ID或者父名称获取地方名称
	 * 
	 * @Title: selectTemplateTypes
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: List<Map>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectLocationByParent", method = RequestMethod.POST)
	@ResponseBody
	public Object selectLocationByParent(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Map> list = new ArrayList<Map>();
		try {
			String pId = request.getParameter("pId");
			String pName = request.getParameter("pName");
			Map param = new HashMap<>();
			param.put("pId", pId);
			param.put("pName", pName);
			list = this.propertyCommonSrv.selectLocationByParent(param);
		} catch (Exception e) {
		}
		// return list;
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, list);
	}

	public String getUserId(String token) {
		Map data = redisUtil.getMap(token);// 去除redis中的原始数据
		String userId = TenpayUtil.toString(data.get("userId"));
		return userId;
	}
}
