package com.qcap.cac.constant;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonConstant {

	// 任务开始时是否必须扫码
	public final static String START_SCAN_FLAG_MUST = "MUST";
	public final static String START_SCAN_FLAG_OPTIONAL = "OPTIONAL";
	public static Map<String, String> START_SCAN_FLAG = new LinkedHashMap<String, String>();
	static {
		START_SCAN_FLAG.put("MUST", "必须");
		START_SCAN_FLAG.put("OPTIONAL", "非必须");
	}

	// 任务结束时是否必须扫码
	public final static String END_SCAN_FLAG_MUST = "MUST";
	public final static String END_SCAN_FLAG_OPTIONAL = "OPTIONAL";
	public static Map<String, String> END_SCAN_FLAG = new LinkedHashMap<String, String>();
	static {
		END_SCAN_FLAG.put("MUST", "必须");
		END_SCAN_FLAG.put("OPTIONAL", "非必须");
	}

	public final static SimpleDateFormat sdf_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 设备状态
	public final static String EQUIP_WORK_STATUS_INSTOP = "INSTOP";
	public final static String EQUIP_WORK_STATUS_INCHARGE = "INCHARGE";
	public final static String EQUIP_WORK_STATUS_INUSE = "INUSE";
	public final static String EQUIP_WORK_STATUS_INREPAIR = "INREPAIR";
	public final static String EQUIP_WORK_STATUS_INDAMAGE = "INDAMAGE";
	public final static String EQUIP_WORK_STATUS_INABORT = "INABORT";

	public static Map<String, String> EQUIP_WORK_STATUS = new LinkedHashMap<String, String>();
	static {
		EQUIP_WORK_STATUS.put("INSTOP", "停泊中");
		EQUIP_WORK_STATUS.put("INCHARGE", "充电中");
		EQUIP_WORK_STATUS.put("INUSE", "使用中");
		EQUIP_WORK_STATUS.put("INREPAIR", "维修中");
		EQUIP_WORK_STATUS.put("INDAMAGE", "损坏中");
		EQUIP_WORK_STATUS.put("INABORT", "已停用");
	}

	// 员工状态：在职，离职
	public final static String USER_WORK_STATUS_INPOSITION = "INPOSITION";
	public final static String USER_WORK_STATUS_LEAVE = "LEAVE";
	public static Map<String, String> USER_WORK_STATUS = new LinkedHashMap<String, String>();
	static {
		USER_WORK_STATUS.put("INPOSITION", "在职");
		USER_WORK_STATUS.put("LEAVE", "离职");
	}

	// APP端设备领用选项
	public static Map<String, String> EQUIP_INUSE_OPTION = new LinkedHashMap<String, String>();
	static {
		EQUIP_INUSE_OPTION.put("title", "使用");
		EQUIP_INUSE_OPTION.put("code", "INUSE");
	}
	public static Map<String, String> EQUIP_INCHARGE_OPTION = new LinkedHashMap<String, String>();
	static {
		EQUIP_INCHARGE_OPTION.put("title", "充电");
		EQUIP_INCHARGE_OPTION.put("code", "INCHARGE");
	}
	public static Map<String, String> EQUIP_INSTOP_OPTION = new LinkedHashMap<String, String>();
	static {
		EQUIP_INSTOP_OPTION.put("title", "停泊");
		EQUIP_INSTOP_OPTION.put("code", "INSTOP");
	}
	public static Map<String, String> EQUIP_INREPAIR_OPTION = new LinkedHashMap<String, String>();
	static {
		EQUIP_INREPAIR_OPTION.put("title", "损坏");
		EQUIP_INREPAIR_OPTION.put("code", "INREPAIR");
	}

	// 设备状态
	public final static String EQUIP_STATUS_NORMAL = "NORMAL";
	public final static String EQUIP_STATUS_ABORT = "ABORT";
	public static Map<String, String> EQUIP_STATUS = new LinkedHashMap<String, String>();
	static {
		EQUIP_STATUS.put("NORMAL", "正常");
		EQUIP_STATUS.put("ABORT", "损坏");
	}

	// 设备操作状态，用于在设备信息中显示设备操作记录，设备充电状态与设备使用状态的合集
	public final static String EQUIP_OPERATE_STATUS_INCHARGE = "INCHARGE";
	public final static String EQUIP_OPERATE_STATUS_CHARGED = "CHARGED";
	public final static String EQUIP_OPERATE_STATUS_INUSE = "INUSE";
	public final static String EQUIP_OPERATE_STATUS_USED = "USED";
	public static Map<String, String> EQUIP_OPERATE_STATUS = new LinkedHashMap<String, String>();
	static {
		EQUIP_OPERATE_STATUS.put("INCHARGE", "充电中");
		EQUIP_OPERATE_STATUS.put("CHARGED", "充电完成");
		EQUIP_OPERATE_STATUS.put("INUSE", "使用中");
		EQUIP_OPERATE_STATUS.put("USED", "使用完毕");
	}

	// 设备充电状态
	public final static String EQUIP_CHARGE_STATUS_INCHARGE = "INCHARGE";
	public final static String EQUIP_CHARGE_STATUS_CHARGED = "CHARGED";
	public static Map<String, String> EQUIP_CHARGE_STATUS = new LinkedHashMap<String, String>();
	static {
		EQUIP_CHARGE_STATUS.put("INCHARGE", "充电中");
		EQUIP_CHARGE_STATUS.put("CHARGED", "充电完成");
	}

	// 设备使用状态
	public final static String EQUIP_USE_STATUS_INUSE = "INUSE";
	public final static String EQUIP_USE_STATUS_USED = "USED";
	public static Map<String, String> EQUIP_USE_STATUS = new LinkedHashMap<String, String>();
	static {
		EQUIP_USE_STATUS.put("INUSE", "使用中");
		EQUIP_USE_STATUS.put("USED", "使用完毕");
	}

	// 设备维保类型
	public final static String MAINT_TYPE_EQUIP = "EQUIP";
	public final static String MAINT_TYPE_PARTS = "PARTS";
	public static Map<String, String> MAINT_TYPE = new LinkedHashMap<String, String>();
	static {
		MAINT_TYPE.put("EQUIP", "设备");
		MAINT_TYPE.put("PARTS", "配件");
	}

	// 设备维修状态
	public final static String EQUIP_REPAIR_STATUS_REPAIR = "REPAIR";
	public final static String EQUIP_REPAIR_STATUS_REPAIRED = "REPAIRED";
	public static Map<String, String> EQUIP_REPAIR_STATUS = new LinkedHashMap<String, String>();
	static {
		EQUIP_REPAIR_STATUS.put("REPAIR", "维修中");
		EQUIP_REPAIR_STATUS.put("REPAIRED", "维修完成");
	}

	// 任务状态
	public final static String TASK_STATUS_WAIT = "WAIT";
	public final static String TASK_STATUS_WORKING = "WORKING";
	public final static String TASK_STATUS_FINISH = "FINISH";
	public final static String TASK_STATUS_CANCLE = "CANCLE";
	public static Map<String, String> TASK_STATUS = new LinkedHashMap<String, String>();
	static {
		TASK_STATUS.put("WAIT", "未开始");
		TASK_STATUS.put("WORKING", "进行中");
		TASK_STATUS.put("FINISH", "已完成");
		TASK_STATUS.put("CANCLE", "已取消");
	}

	// 任务编码前缀
	public final static String TASK_PREFIX_T = "T";
	public final static String TASK_PREFIX_R = "R";
	public final static String TASK_PREFIX_S = "S";
	public final static String TASK_PREFIX_E = "E";
	public final static String TASK_PREFIX_DQ = "DQ";
	public static Map<String, String> TASK_PREFIX = new LinkedHashMap<String, String>();
	static {
		TASK_PREFIX.put("T", "临时任务前缀");
		TASK_PREFIX.put("S", "专项任务前缀");
		TASK_PREFIX.put("R", "周期性任务前缀");
		TASK_PREFIX.put("E", "事件性任务前缀");
		TASK_PREFIX.put("DQ", "不合格任务前缀");
	}

	// 任务类型
	public final static String TASK_TYPE_TEMP = "TEMP";
	public final static String TASK_TYPE_REGULAR = "REGULAR";
	public final static String TASK_TYPE_EVENT = "EVENT";
	public final static String TASK_TYPE_SPECIAL = "SPECIAL";
	public static Map<String, String> TASK_TYPE = new LinkedHashMap<String, String>();
	static {
		TASK_TYPE.put("TEMP", "临时任务");
		TASK_TYPE.put("REGULAR", "周期性任务");
		TASK_TYPE.put("EVENT", "事件性任务");
		TASK_TYPE.put("SPECIAL", "专项任务");
	}

	// 任务检查状态
	public final static String TASK_CHECK_STATUS_TOCHECK = "TOCHECK";
	public final static String TASK_CHECK_STATUS_QUALIFIED = "QUALIFIED";
	public final static String TASK_CHECK_STATUS_DISQUALIFIED = "DISQUALIFIED";
	public static Map<String, String> TASK_CHECK_STATUS = new LinkedHashMap<String, String>();
	static {
		TASK_CHECK_STATUS.put("TOCHECK", "待检查");
		TASK_CHECK_STATUS.put("QUALIFIED", "合格");
		TASK_CHECK_STATUS.put("DISQUALIFIED", "不合格");
	}

	// 任务是否必须检查标识
	public final static String TASK_CHECK_FLAG_MUST = "MUST";
	public final static String TASK_CHECK_FLAG_OPTIONAL = "OPTIONAL";
	public static Map<String, String> TASK_CHECK_FLAG = new LinkedHashMap<String, String>();
	static {
		TASK_CHECK_FLAG.put("MUST", "必须");
		TASK_CHECK_FLAG.put("OPTIONAL", "非必须");
	}

	// 班次类型
	public final static String SHIFT_DAYTIME = "DAYTIME";
	public final static String SHIFT_NIGHT = "NIGHT";
	public static Map<String, String> SHIFT = new LinkedHashMap<String, String>();
	static {
		SHIFT.put("DAYTIME", "白班");
		SHIFT.put("NIGHT", "夜班");
	}

	// 任务是否必须上传图片标识
	public final static String UPLOAD_PIC_FLAG_MUST = "MUST";
	public final static String UPLOAD_PIC_FLAG_OPTIONAL = "OPTIONAL";
	public static Map<String, String> UPLOAD_PIC_FLAG = new LinkedHashMap<String, String>();
	static {
		UPLOAD_PIC_FLAG.put("MUST", "必须");
		UPLOAD_PIC_FLAG.put("OPTIONAL", "非必须");
	}

	// 返回状态
	public final static String BACK_FLAG = "flag";
	public final static String BACK_SUCCESS_FLAG = "1";
	public final static String BACK_FAIL_FLAG = "-1";
	public final static String BACK_MESSAGE = "message";

	// 领用状态
	public final static String WAREHOUSE_REQ_STATUS_INIT = "INIT";
	public final static String WAREHOUSE_REQ_STATUS_COMMIT = "COMMIT";
	public final static String WAREHOUSE_REQ_STATUS_RECEIVE = "RECEIVE";
	public static Map<String, String> WAREHOUSE_REQ_STATUS = new LinkedHashMap<String, String>();
	static {
		WAREHOUSE_REQ_STATUS.put("INIT", "已申请");
		WAREHOUSE_REQ_STATUS.put("COMMIT", "已提交");
		WAREHOUSE_REQ_STATUS.put("RECEIVE", "已领用");
	}

	// 周期性计划时间类型
	public final static String PLAN_TIME_TYPE_DAY = "DAY";
	public final static String PLAN_TIME_TYPE_WEEK = "WEEK";
	public final static String PLAN_TIME_TYPE_MONTH = "MONTH";
	public final static String PLAN_TIME_TYPE_YEAR = "YEAR";
	public static Map<String, String> PLAN_TIME_TYPE = new LinkedHashMap<String, String>();
	static {
		PLAN_TIME_TYPE.put("DAY", "每天");
		PLAN_TIME_TYPE.put("WEEK", "每周");
		PLAN_TIME_TYPE.put("MONTH", "每月");
		PLAN_TIME_TYPE.put("YEAR", "每年");
	}
	// 周期性计划，当为时间类型为每天时
	public final static String DAY_ALL = "ALL";

	// 删除标识
	public final static String DELETE_FLAG_DELETE = "DELETE";
	public final static String DELETE_FLAG_NORMAL = "NORMAL";
	public static Map<String, String> DELETE_FLAG = new LinkedHashMap<String, String>();
	static {
		DELETE_FLAG.put("DELETE", "删除");
		DELETE_FLAG.put("NORMAL", "正常");
	}

	// 事件类型
	public final static String EVENT_TYPE_ARRIVE = "ARRIVE";
	public final static String EVENT_TYPE_LEAVE = "LEAVE";
	public static Map<String, String> EVENT_TYPE = new LinkedHashMap<String, String>();
	static {
		EVENT_TYPE.put("ARRIVE", "到达");
		EVENT_TYPE.put("LEAVE", "离港");
	}

	// 保障等级
	public final static String GUARANTEE_TYPE_NORMAL = "NORMAL";
	public final static String GUARANTEE_TYPE_IMPORTANT = "IMPORTANT";
	public static Map<String, String> GUARANTEE_TYPE = new LinkedHashMap<String, String>();
	static {
		GUARANTEE_TYPE.put("NORMAL", "一般");
		GUARANTEE_TYPE.put("IMPORTANT", "重要");
	}

	// 是否已读
	public final static String READ_FLAG_0 = "0";
	public final static String READ_FLAG_1 = "1";
	public static Map<String, String> READ_FLAG = new LinkedHashMap<String, String>();
	static {
		READ_FLAG.put("0", "未读");
		READ_FLAG.put("1", "已读");
	}

	// 请假状态
	public final static String LEAVE_STATUS_AUDITING = "AUDITING";
	public final static String LEAVE_STATUS_REFUSE = "REFUSE";
	public final static String LEAVE_STATUS_PASS = "PASS";
	public final static String LEAVE_STATUS_CANCEL = "CANCEL";
	public static Map<String, String> LEAVE_STATUS = new LinkedHashMap<String, String>();
	static {
		LEAVE_STATUS.put("AUDITING", "待审批");
		LEAVE_STATUS.put("REFUSE", "已驳回");
		LEAVE_STATUS.put("PASS", "已通过");
		LEAVE_STATUS.put("CANCEL", "已撤回");
	}

	public static Map<String, String> AREA_TYPE = new LinkedHashMap<String, String>();
	static {
		AREA_TYPE.put("ARRIVE", "BOARDING");
		AREA_TYPE.put("LEAVE", "GOVERED_BRIDGE");
	}

	// 专项任务每次延迟天数
	public final static int SPECIAL_TASK_DELAY_DAYS_1 = 1;
	public final static int SPECIAL_TASK_DELAY_DAYS_7 = 7;

	// 二维码类型
	public final static String QRCODE_TYPE_EQUIP = "EQUIP";
	public final static String QRCODE_TYPE_POSITION = "POSITION";
	public static Map<String, String> QRCODE_TYPE = new LinkedHashMap<String, String>();
	static {
		QRCODE_TYPE.put("EQUIP", "设备");
		QRCODE_TYPE.put("POSITION", "岗位");
	}

	// 周
	public static Map<String, String> WEEK = new LinkedHashMap<String, String>();
	static {
		WEEK.put("1", "周日");
		WEEK.put("2", "周一");
		WEEK.put("3", "周二");
		WEEK.put("4", "周三");
		WEEK.put("5", "周四");
		WEEK.put("6", "周五");
		WEEK.put("7", "周六");
	}

}
