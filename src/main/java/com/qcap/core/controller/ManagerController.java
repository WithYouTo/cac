package com.qcap.core.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.qcap.cac.dto.ResetPasswordReq;
import com.qcap.cac.service.LoginRestSrv;
import com.qcap.core.entity.UserInsertDto;
import com.qcap.core.utils.AppUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.entity.TbManager;
import com.qcap.core.exception.BizExceptionEnum;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;
import com.qcap.core.service.ITbManagerService;

import cn.hutool.core.util.StrUtil;

@RestController
@RequestMapping("/mgr")
public class ManagerController {

	@Resource
	private ITbManagerService managerService;

	@Resource
	private LoginRestSrv loginRestSrv;
	/**
	 * 添加用户
	 *
	 * @param userInsertDto
	 *            参数对象
	 * @param result
	 *            校验结果对象
	 * @return response封装
	 */
	@PostMapping("/add")
	public ResParams add(@Valid UserInsertDto userInsertDto, BindingResult result) throws Exception {
//		if (result.hasErrors()) {
//			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
//		}

		managerService.insertItem(userInsertDto);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.ADD_SUCCESS, null);

	}

	@PostMapping("/list")
	public PageResParams list(IPage<Map<String, Object>> page, @RequestParam(required = false) String userName,
			@RequestParam(required = false) String phone) {
		Map<String, Object> parameter = new HashMap<>();
		if (StringUtils.isNotEmpty(userName)) {
			parameter.put("userName", userName + "%");
		}
		if (StringUtils.isNotEmpty(phone)) {
			parameter.put("phone", phone + "%");
		}
		managerService.getUserList(page, parameter);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, "", page.getTotal(), page.getRecords());
	}

	/**
	 * 修改账户
	 *
	 */
	@PostMapping("/edit")
	public ResParams edit(@Valid UserInsertDto userInsertDto, BindingResult result) {
		if (result.hasErrors()) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		try {
			managerService.updateItem(userInsertDto);
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.EDIT_SUCCESS, null);
		} catch (Exception e) {
			e.printStackTrace();
			return ResParams.newInstance(CoreConstant.SUCCESS_CODE, e.getMessage(), null);
		}
	}

	/**
	 * 分配组织
	 */
	@PostMapping("/setOrg")
	public Object setOrg(@RequestParam("userId") String userId, @RequestParam("orgIds") String orgIds) {
		if (StrUtil.isEmpty(userId)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		String[] ids = orgIds.split(",");
		if (ids.length == 0 || ids.length > 1) {
			return ResParams.newInstance(CoreConstant.FAIL_CODE, CoreConstant.MANAGER_MULTI_ORG_MSG, null);
		}
		managerService.buildOrfForManager(userId, ids[0]);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.MANAGER_SET_ORG_SUCCESS, null);
	}

	/**
	 * 删除管理员（逻辑删除）
	 */
	@PostMapping("/delete")
	public ResParams delete(@RequestParam String ids) {
		if (StrUtil.isEmpty(ids)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		Arrays.stream(ids.split(",")).forEach(e -> managerService.deleteManagerById(e));
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.DELETE_SUCCESS, null);
	}

	/**
	 * 分配角色
	 */
	@PostMapping("/setRole")
	public ResParams setRole(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
		if (StringUtils.isAnyEmpty(userId, roleIds)) {
			throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
		}
		managerService.buildRoleForManager(userId, Arrays.asList(roleIds.split(",")));
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.MANAGER_SET_ROLE_SUCCESS, null);
	}

	 /**
	 * 修改当前用户的密码
	 */
	 @PostMapping("/changePwd")
	 public Object changePwd(HttpServletRequest request,@RequestParam String oldPass, @RequestParam String
			 newPass, @RequestParam String finalPass) {
		 if (!newPass.equals(finalPass)) {
			 throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
		 }

		 TbManager mgr = AppUtils.getLoginUser();

		 ResetPasswordReq resetPasswordDto = new ResetPasswordReq();
		 resetPasswordDto.setEmployeeCode(mgr.getAccount());
		 resetPasswordDto.setNewPassword(newPass);
		 resetPasswordDto.setOldPassword(oldPass);

		 this.managerService.changePassword(mgr,newPass,oldPass);

		 return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.MANAGER_CHANGE_PASS_SUCCESS, null);
	 }

	 /**
	  *
	  * @Description: 重置用户密码
	  *
	  *
	  * @MethodName: resetPass
	  * @Parameters: [account] 
	  * @ReturnType: java.lang.Object
	  *
	  * @author huangxiang
	  * @date 2018/11/6 9:59
	  */
	@PostMapping("/resetPass")
	public Object resetPass(String account) {
		this.managerService.resetPassword(account);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CoreConstant.MANAGER_RESET_PASS_SUCCESS, null);
	}


	// /**
	// * 重置管理员的密码
	// */
	// @PostMapping("/reset")
	// public Tip reset(@RequestParam String userId) {
	// if (StringUtils.isEmpty(userId))
	// {
	// throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
	// }
	// HttpSession session = getSession();
	// TbManager manager = (TbManager) session.getAttribute("manager");
	// String newSalt = Md5Util.getSalt();
	// String newMd5 = Md5Util.encrypt(Const.DEFAULT_PWD, newSalt);
	// manager.setSalt(newSalt);
	// manager.setPassword(newMd5);
	// managerSrv.updateManagerPwd(manager);
	// return SUCCESS_TIP;
	// }

	@Inject
	public void setManagerService(ITbManagerService managerService) {
		this.managerService = managerService;
	}
}
