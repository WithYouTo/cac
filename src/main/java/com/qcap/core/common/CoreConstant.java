package com.qcap.core.common;

public class CoreConstant {

	public static final Integer SUCCESS_CODE = 200;

	public static final String SUCCESS_DESC = "SUCCESS";

	public static final Integer FAIL_CODE = 500;

	public static final String FAIL_DESC = "FAIL";
	// 组织
	public static final String ORG_FULLNAMES_SEPARATE = ".";

	public static final String ORG_FULLCODES_SEPARATE = ",";

	public static final int ORG_SUCCESS_CODE = 1000;

	public static final String ORG_SUCCESS_MSG = "操作成功";

	public static final int ORG_HAS_CHILD_CODE = 1001;

	public static final String ORG_HAS_CHILD_MSG = "该部门下已有子部门";

	public static final int ORG_HAS_PERSON_CODE = 1002;

	public static final String ORG_HAS_PERSON_MSG = "该部门已分配人员";

	public static final int ORG_HAS_ROLE_CODE = 1003;

	public static final String ORG_HAS_ROLE_MSG = "该部门已分配角色";

	public static final int ORG_IS_NOT_EMPTY_CODE = 1004;

	public static final String ORG_IS_NOT_EMPTY_MSG = "请先清空数据";

	public static final int ORG_IS_ROOT_CODE = 1005;

	public static final String ORG_IS_ROOT_MSG = "无法修改根组织";

	public static final int ORG_IS_FIRST_CODE = 1006;

	public static final String ORG_IS_FIRST_MSG = "该组织已是第一个";

	public static final int ORG_IS_LAST_CODE = 1007;

	public static final String ORG_IS_LAST_MSG = "该组织已是最后一个";

	public static final String ORG_PAR_TO_CHIL_ERR = "不能移到其子组织或自身";

	// 菜单
	public static final int MENU_IS_FIRST_CODE = 1016;

	public static final String MENU_IS_FIRST_MSG = "该菜单已是第一个";

	public static final int MENU_IS_LAST_CODE = 1017;

	public static final String MENU_IS_LAST_MSG = "该菜单已是最后一个";

	public static final String MENU_UP_SUCCESS = "上移完成";

	public static final String MENU_DOWN_SUCCESS = "下移完成";

	// 用户
	public static final String ADD_SUCCESS = "新增成功！";

	public static final String EDIT_SUCCESS = "修改成功！";

	public static final String DELETE_SUCCESS = "删除成功！";

	public static final String DEFAULT_PASSWORD = "111111";

	public static final int MANAGER_MULTI_ORG_CODE = 2001;

	public static final String MANAGER_MULTI_ORG_MSG = "不能分配多个组织";

	public static final int MANAGER_IMPORT_FILE_NOT_EXIST_CODE = 2002;

	public static final String MANAGER_IMPORT_FILE_NOT_EXIST_MSG = "请先选择文件";

	public static final int MANAGER_IMPORT_HAS_ERROR_DATA_CODE = 2003;

	public static final String MANAGER_IMPORT_HAS_ERROR_DATA_MSG = "有异常或重复数据，请核对";

	public static final int MANAGER_IM_ERROR_CODE = 2004;

	public static final String MANAGER_IM_ERROR_MSG = "云信初始化异常，请稍后重试";

	public static final String MANAGER_SET_ROLE_SUCCESS = "角色分配成功";

	public static final String MANAGER_SET_ORG_SUCCESS = "组织分配成功";

	public static final String MANAGER_RESET_PASS_SUCCESS = "重置密码成功";

	public static final String MANAGER_CHANGE_PASS_SUCCESS = "修改密码成功";



	// 角色
	public static final int ROLE_IS_ROOT_CODE = 3001;

	public static final String ROLE_IS_ROOT_MSG = "无法操作根节点";

	public static final int CHOOSE_ROLE_IS_ROOT_CODE = 3002;

	public static final String CHOOSE_ROLE_IS_ROOT_MSG = "不能选择根菜单";

	public static final int CHOOSE_MULTI_SUPER_ROLE_CODE = 3003;

	public static final String CHOOSE_MULTI_SUPER_ROLE_MSG = "不能分配多个超级用户";

	public static final int ROLE_HAS_MANAGER_CODE = 3004;

	public static final String ROLE_HAS_MANAGER_MSG = "该角色已分配用户";

	public static final String ROLE_SET_AUTH_SUCCESS = "权限分配成功";

	// excel导入
	public static final int EXCEL_IS_EMPTY_CODE = 9001;

	public static final String EXCEL_IS_EMPTY_MSG = "excel数据为空，请重新导入";

	public static final int EXCEL_ILLEGAL_USER_CODE = 9002;

	public static final String EXCEL_ILLEGAL_USER_MSG = "用户不存在";

	public static final int EXCEL_IMPORT_ERROR_CODE = 9003;

	public static final String EXCEL_IMPORT_ERROR_MSG = "数据有误，请重新导入";

	//系统默认密码
	public static final String SYS_DEFAULT_PASSWORD = "123456";


}
