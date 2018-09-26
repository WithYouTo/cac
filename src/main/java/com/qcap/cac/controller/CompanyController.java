package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.controller.BaseController;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.ToolUtil;
import com.qcap.core.utils.UUIDUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;
import com.qcap.core.warpper.LogWarpper;
import com.qcap.cac.common.service.PropertyCommonSrv;
import com.qcap.cac.model.Company;
import com.qcap.cac.service.CompanySrv;
import com.qcap.cac.service.WebUserSrv;

/**
 * 公司管理
 * 
 * @ClassName: CompanyController
 * @Description: TODO
 * @author: 鲍建兴(2017004)
 * @date: 2018年4月12日 下午3:25:55
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private RedisUtil redisUtil;
	 
	@Resource
	private WebUserSrv webUserSrv;

	private String PREFIX = "/system/company/";

	/**
	 * 跳转到公司管理首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "company";
	}

	/**
	 * 跳转到添加公司管理
	 */
	@RequestMapping("/company_add")
	public String companyAdd() {
		return PREFIX + "company_add";
	}

	@Autowired
	private CompanySrv companySrv;

	@Autowired
	private PropertyCommonSrv propertyCommonSrv;

	@RequestMapping(value = "/toUpdate/{companyId}")
	public String selectInfoForDetail(@PathVariable String companyId, Model model) throws Exception {
		Map info = this.companySrv.selectDetailByPk(companyId);
		model.addAttribute("info", info);
		return PREFIX + "company_edit";
	}

	/**
	 * 分页查询公司信息
	 * 
	 * @Title: selectCompaniesByPage
	 * @Description: TODO
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Map
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		new PageFactory<Company>().defaultPage();
		String compName = ToolUtil.toStr(request.getParameter("compName"));
		String companyCode = ToolUtil.toStr(request.getParameter("companyCode"));
		 
		 Map map = new HashMap<>();
		if ("0".equals(companyCode)) {
			map.put("companyCode", "%"+companyCode+"%");
		}else if (companyCode != null && !companyCode.isEmpty()){
			map.put("companyCode", companyCode);
		}
		// 公司简称
		if (compName != null && !compName.isEmpty()) {
			map.put("compName", "%" + compName + "%");
		}
		// 查询公司信息
		List<Map<String, Object>> list = companySrv.getCompaniesByPage(map);

		PageInfo pageInfo = new PageInfo((List<Company>) new LogWarpper(list).warp());
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object insertNewComp(@Valid Company company, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		String compName = ToolUtil.toStr(company.getCompName());
		String fullName = ToolUtil.toStr(company.getFullName());
		String compBrand = ToolUtil.toStr(company.getCompBrand());
		//设置ID
		company.setCompanyId(UUIDUtil.getUUID());
		//获取公司编码（可直接使用）
		String companyCode=this.companySrv.selectNewCompanyCode();
		company.setCompanyCode(companyCode);
		company.setCreateDate(new Date());
		company.setCreateEmp("SYS");
		company.setVersion(0);
		// 查询TBBB08表的compName是否已存在
		Map param2 = new HashMap<>();
		param2.put("compName", compName);
		String num2 = this.companySrv.selectExistNum(param2);
		if (!"0".equals(num2)) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司简称已存在", null);
		}

		// 查询TBBB08表的fullName是否已存在
		Map param3 = new HashMap<>();
		param3.put("fullName", fullName);
		String num3 = this.companySrv.selectExistNum(param3);
		if (!"0".equals(num3)) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司全称已存在", null);
		}

		// 查询TBBB08表的compBrand是否已存在
		Map param4 = new HashMap<>();
		param4.put("compBrand", compBrand);
		String num4 = this.companySrv.selectExistNum(param4);
		if (!"0".equals(num4)) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司品牌已存在", null);
		}
		// String token = request.getHeader(jwtProperties.getTokenHeader());
		// String userId = jwtTokenUtil.getUsernameFromToken(token);
		// Map<String,Object> userMap=webUserSrv.selectByPrimaryKey(userId);
		// String account=ToolUtil.toStr(userMap.get("account"));
		// Date now=new Date();
		this.companySrv.insertNewComp(this.setCreateEmpAndTime(company));
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
	}

	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateComp(@Valid Company company, BindingResult result, HttpServletRequest request) {
		String oldCompName = request.getParameter("oldCompName"); // 旧企业简称
		String oldFullName = request.getParameter("oldFullName"); // 旧全称
		String oldCompBrand = request.getParameter("oldCompBrand"); // 旧企业品牌
		String newCompName = company.getCompName(); // 新企业简称
		String newFullName = company.getFullName(); // 新全称
		String newCompBrand = company.getCompBrand();

		if (newCompName == null || newCompName.equals("")) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司简称为空", null);
		}
		if (newFullName == null || newFullName.equals("")) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司全称为空", null);
		}
		if (newCompBrand == null || newCompBrand.equals("")) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司品牌为空", null);
		}

		if (!oldCompName.equals(newCompName)) {
			Map param1 = new HashMap<>();
			param1.put("compName", newCompName);
			String num1 = this.companySrv.selectExistNum(param1);
			if (!"0".equals(num1)) {
				return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司简称已存在", null);
			}
		}

		if (!oldFullName.equals(newFullName)) {
			Map param2 = new HashMap<>();
			param2.put("fullName", newFullName);
			String num2 = this.companySrv.selectExistNum(param2);
			if (!"0".equals(num2)) {
				return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司全称已存在", null);
			}
		}

		if (!oldCompBrand.equals(newCompBrand)) {
			Map param4 = new HashMap<>();
			param4.put("compBrand", newCompBrand);
			String num4 = this.companySrv.selectExistNum(param4);
			if (!"0".equals(num4)) {
				return ResParams.newInstance(CoreConstant.FAIL_CODE, "公司品牌已存在", null);
			}
		}
		this.companySrv.updateCompanyInfo(this.setUpdateEmpAndTime(company));
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
	}

	/**
	 * 删除公司信息
	 * 
	 * @Title: deleteComp
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
	public Object deleteComp(@RequestParam(required = true) String companyCodes) {
		if (ToolUtil.isEmpty(companyCodes)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String[] code = companyCodes.split(",");
		String result = this.companySrv.deleteInfoByPk(code);
		if ("1".equals(result)) {
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
		} else if ("-2".equals(result)) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, "该公司已经被使用，无法删除", null);// 该公司已经被使用，无法删除
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
	}

	// 公司下拉框(总公司不展示)
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectSubCompanies", method = RequestMethod.POST)
	@ResponseBody
	public Object selectSubCompanies(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Map> list = new ArrayList<Map>();
		Map param = new HashMap<>();
		String token = request.getHeader(jwtProperties.getTokenHeader());
		//String userId = jwtTokenUtil.getUsernameFromToken(token);
		String userId = redisUtil.getUserId(token);
		Map<String, Object> userMap = webUserSrv.selectByPrimaryKey(userId);
		String companyCode = ToolUtil.toStr(userMap.get("companyCode"));
		if (!"0".equals(companyCode)) {
			param.put("companyCode", companyCode);
		}
		list = this.companySrv.selectSubCompanies(param);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
	}

	// 公司下拉框初始化
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/selectSingleCompany", method = RequestMethod.POST)
	@ResponseBody
	public Object selectSingleCompany(Model model, HttpServletRequest request, HttpServletResponse response) {
		List<Map> list = new ArrayList<Map>();
		Map param = new HashMap<>();
		String companyCode = request.getParameter("companyCode");
		param.put("companyCode", companyCode);
		list = this.companySrv.selectSubCompanies(param);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
	}

}
