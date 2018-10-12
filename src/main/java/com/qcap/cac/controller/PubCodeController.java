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

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qcap.cac.entity.Tbpubcode;
import com.qcap.cac.entity.Tbpubcode01;
import com.qcap.cac.service.PubCodeSrv;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.factory.PageFactory;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;

@Controller
@RequestMapping({ "/pubCode" })
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PubCodeController {

	// private static String PREFIX = "/esp/pubCode/";

	@Resource
	public PubCodeSrv pubCodeSrv;

	// /**
	// * 跳转代码主档管理主页
	// *
	// * @Title: topubCode
	// * @return
	// * @return: String
	// */
	// @RequestMapping("")
	// public String topubCode(Model model) {
	// return PREFIX + "pubCode";
	// }
	//
	// /**
	// * 跳转到新增代码主档页面
	// */
	// @RequestMapping(value = "/toadd")
	// public String toadd() {
	// return PREFIX + "pubCode_add";
	// }
	//
	// /**
	// * 修改页面
	// */
	// @RequestMapping(value = "/tomodify/{id}")
	// public String tomodify(@PathVariable String id, Model model) {
	// if (ToolUtil.isEmpty(id)) {
	// throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
	// }
	// Map pubCode = this.pubCodeSrv.selectPubCodeById(id);
	//
	// model.addAttribute("pubCode", pubCode);
	// return PREFIX + "pubCode_modify";
	// }

	/**
	 * 获取代码主档列表
	 * 
	 * @Title: list
	 * @param reuqest
	 * @param response
	 * @return
	 * @return: Object
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String configCode,
			@RequestParam(required = false) String configName) {
		new PageFactory<Map<String, Object>>().defaultPage();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("configCode", configCode);
		param.put("configName", configName);
		List<Map> list = pubCodeSrv.selectPubCodeByPage(param);
		PageInfo pageInfo = new PageInfo(list);
		Page pageList = (Page) list;

		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}

	/**
	 * 新增代码主档
	 */
	@RequestMapping(value = "/pubCodeAdd")
	@ResponseBody
	public Object add(@Valid Tbpubcode pubcode, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Map param = new HashMap();
		param.put("configCode", pubcode.getConfigCode());
		param.put("AreaId", pubcode.getAreaId());
		param.put("companyCode", pubcode.getCompanyCode());
		if (null != pubCodeSrv.selectRepeatCode(param)) {
			ResParams.newInstance(CoreConstant.MANAGER_IMPORT_HAS_ERROR_DATA_CODE,
					CoreConstant.MANAGER_IMPORT_HAS_ERROR_DATA_MSG, null);
		}
		/**
		 * 人员管理没做，暂时将createEmp设置为sys
		 */
		pubcode.setCreateEmp("sys");
		pubcode.setCreateDate(new Date());
		pubCodeSrv.insertPubCode(pubcode);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
	}

	/**
	 * 删除代码主档
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam String ids, @RequestParam String codes) {
		if (ToolUtil.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		String[] idArr = ids.split(",");
		String[] codeArr = codes.split(",");
		for (int i = 0; i < idArr.length; i++) {
			String tbpubcodeId = idArr[i];
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("tbpubcodeId", tbpubcodeId);
			List<Map> list = pubCodeSrv.selectPubCode01ByPage(param);
			if (list.isEmpty()) {
				pubCodeSrv.deletePubCode(tbpubcodeId);
			} else {
				return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "项目代码为" + codeArr[i] + "的通用代码档下还有二级菜单，不允许删除",
						null);
			}

		}

		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
	}

	/**
	 * 修改代码主档
	 */
	@RequestMapping(value = "/pubCodeEdit")
	@ResponseBody
	public Object edit(@Valid Tbpubcode tbpubCode, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		/**
		 * 人员管理没做，暂时将createEmp设置为sys
		 */
		tbpubCode.setUpdateEmp("sys");
		tbpubCode.setUpdateDate(new Date());
		this.pubCodeSrv.updatePubCode(tbpubCode);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
	}

	// -----------------------------代码从档--------------------------------------

	// /**
	// * 跳转代码从档管理主页
	// *
	// * @Title: topubCode
	// * @return
	// * @return: String
	// */
	// @RequestMapping("/pubCode01/{tbpubcodeId}")
	// public String topubCode01(Model model, @PathVariable String tbpubcodeId,
	// HttpServletRequest reuqest) {
	// model.addAttribute("tbpubcodeId", tbpubcodeId);
	// model.addAttribute("configCode", reuqest.getParameter("configCode"));
	// return PREFIX + "pubCode01";
	// }
	//
	// /**
	// * 跳转到新增代码从档页面
	// */
	// @RequestMapping(value = "/toAddPubCode01")
	// public String toAddPubCode01(Model model, @RequestParam String
	// configCode, @RequestParam String tbpubcodeId) {
	// model.addAttribute("configCode", configCode);
	// model.addAttribute("tbpubcodeId", tbpubcodeId);
	// return PREFIX + "pubCode01_add";
	// }
	//
	// /**
	// * 修改页面
	// */
	// @RequestMapping(value = "/toModifyPubCode01/{id}")
	// public String tomodifypubCode01(@PathVariable String id, Model model) {
	// if (ToolUtil.isEmpty(id)) {
	// throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
	// }
	// Map pubCode01 = this.pubCodeSrv.selectPubCode01ById(id);
	//
	// model.addAttribute("pubCode01", pubCode01);
	// return PREFIX + "pubCode01_modify";
	// }

	/**
	 * 获取代码从档列表
	 * 
	 * @Title: list
	 * @param reuqest
	 * @param response
	 * @return
	 * @return: Object
	 */
	@RequestMapping(value = "/listPubCode01")
	@ResponseBody
	public Object listPubCode01(@RequestParam(required = false) String tbpubcodeId) {
		new PageFactory<Map<String, Object>>().defaultPage();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tbpubcodeId", tbpubcodeId);
		List<Map> list = pubCodeSrv.selectPubCode01ByPage(param);

		PageInfo pageInfo = new PageInfo(list);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", pageInfo.getTotal(), list);
	}

	/**
	 * 新增代码从档
	 */
	@RequestMapping(value = "/pubCode01Add")
	@ResponseBody
	public Object addPubCode01(HttpServletRequest request, HttpServletResponse response) {
		String tbpubcodeId = ToolUtil.toStr(request.getParameter("tbpubcodeId"));
		String configCode = ToolUtil.toStr(request.getParameter("configCode01"));
		String seq = ToolUtil.toStr(request.getParameter("seq"));
		String desc = ToolUtil.toStr(request.getParameter("desc"));
		String desc1 = ToolUtil.toStr(request.getParameter("desc1"));
		String desc2 = ToolUtil.toStr(request.getParameter("desc2"));
		String desc3 = ToolUtil.toStr(request.getParameter("desc3"));
		Map param = new HashMap();
		param.put("configCode", configCode);
		param.put("seq", seq);

		if (null != pubCodeSrv.selectRepeatSeq(param)) {
			// return new ErrorTip(403, "顺序号不能重复");
		}

		Tbpubcode01 tbpubCode01 = new Tbpubcode01();
		tbpubCode01.setTbpubcode01Id(UUIDUtils.getUUID());
		tbpubCode01.setConfigCode(configCode);
		tbpubCode01.setTbpubcodeId(tbpubcodeId);
		tbpubCode01.setSeq(seq);
		tbpubCode01.setDesc(desc);
		tbpubCode01.setDesc1(desc1);
		tbpubCode01.setDesc2(desc2);
		tbpubCode01.setDesc3(desc3);
		tbpubCode01.setVersion(0);
		/**
		 * 人员管理没做，暂时将createEmp设置为sys
		 */
		tbpubCode01.setCreateEmp("sys");
		tbpubCode01.setCreateDate(new Date());
		Map pubCode = pubCodeSrv.selectPubCodeById(tbpubcodeId);

		if (null != pubCode) {
			tbpubCode01.setAreaId(ToolUtil.toStr(pubCode.get("areaId")));
			tbpubCode01.setCompanyCode(ToolUtil.toStr(pubCode.get("companyCode")));
		}
		pubCodeSrv.insertPubCode01(tbpubCode01);

		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);
	}

	/**
	 * 修改代码从档
	 */
	@RequestMapping(value = "/pubCode01Edit")
	@ResponseBody
	public Object editpubCode01(HttpServletRequest request, HttpServletResponse response) {
		String tbpubcode01Id = ToolUtil.toStr(request.getParameter("tbpubcode01Id"));
		String desc1 = ToolUtil.toStr(request.getParameter("desc1"));
		String desc2 = ToolUtil.toStr(request.getParameter("desc2"));
		String desc3 = ToolUtil.toStr(request.getParameter("desc3"));

		Tbpubcode01 tbpubCode01 = new Tbpubcode01();
		tbpubCode01.setTbpubcode01Id(tbpubcode01Id);
		tbpubCode01.setDesc1(desc1);
		tbpubCode01.setDesc2(desc2);
		tbpubCode01.setDesc3(desc3);
		tbpubCode01.setUpdateEmp("sys");
		tbpubCode01.setUpdateDate(new Date());
		this.pubCodeSrv.updatePubCode01(tbpubCode01);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
	}

	/**
	 * 删除代码从档
	 */
	@RequestMapping(value = "/deletePubCode01")
	@ResponseBody
	public Object deletePubCode01(@RequestParam(required = true) String ids) {
		if (ToolUtil.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}

		String[] idArr = ids.split(",");
		for (String tbpubcode01Id : idArr) {
			pubCodeSrv.deletePubCode01(tbpubcode01Id);
		}
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
	}

	/**
	 * 代码主档选择下拉框
	 */
	@RequestMapping(value = "/selectConfigCode")
	@ResponseBody
	public Object selectConfigCode(HttpServletRequest reuqest) {

		Map map = new HashMap();
		map.put("configCode", reuqest.getParameter("configCode"));
		map.put("desc", reuqest.getParameter("desc"));
		map.put("areaId", reuqest.getParameter("areaId"));
		map.put("companyCode", reuqest.getParameter("companyCode"));
		List<Map> list = pubCodeSrv.selectConfigCode(map);

		List<Map> result = new ArrayList<Map>();
		Map data = new HashMap<>();
		data.put("id", "");
		data.put("text", "请选择");
		result.add(data);
		result.addAll(list);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", result);
	}

	/**
	 * 代码从档选择下拉框
	 */
	@RequestMapping(value = "/selectGroupDropdown")
	@ResponseBody
	public Object selectGroupDropdown(HttpServletRequest reuqest) {

		Map map = new HashMap();
		map.put("configCode", reuqest.getParameter("configCode"));
		map.put("areaId", reuqest.getParameter("areaId"));
		map.put("companyCode", reuqest.getParameter("companyCode"));
		List<Map> list = pubCodeSrv.selectConfigCodeApp(map);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", list);
	}

}