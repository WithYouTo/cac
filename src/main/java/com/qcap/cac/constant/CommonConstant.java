package com.qcap.cac.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommonConstant {

    public final static String EQUIP_CHARGE_STATUS_ALL = "ALL";
    public final static String EQUIP_CHARGE_STATUS_INCHARGE = "INCHARGE";
    public final static String EQUIP_CHARGE_STATUS_CHARGED = "CHARGED";
    public static Map<String,String> EQUIP_CHARGE_STATUS = new LinkedHashMap<String,String>();
    static {
        EQUIP_CHARGE_STATUS.put("ALL","全部");
        EQUIP_CHARGE_STATUS.put("INCHARGE","充电中");
        EQUIP_CHARGE_STATUS.put("CHARGED","充电完成");
    }
    
    public final static String TASK_STATUS_WAIT="WAIT";
    public final static String TASK_STATUS_WORKING="WORKING";
    public final static String TASK_STATUS_FINISH="FINISH";
    public final static String TASK_STATUS_CANCLE="CANCLE";
    public static Map<String,String> TASK_STATUS = new LinkedHashMap<String,String>();
    static {
    	TASK_STATUS.put("WAIT", "未开始");
    	TASK_STATUS.put("WORKING", "进行中");
    	TASK_STATUS.put("FINISH", "已完成");
    	TASK_STATUS.put("CANCLE", "已取消");
    }
    
    public final static String TASK_PREFIX_T="T";
    public final static String TASK_PREFIX_R="R";
    public final static String TASK_PREFIX_E="E";
    public final static String TASK_PREFIX_UQ="DQ";
    public static Map<String,String> TASK_PREFIX = new LinkedHashMap<String,String>();
    static {
    	TASK_PREFIX.put("T", "临时任务前缀");
    	TASK_PREFIX.put("R", "周期性任务前缀");
    	TASK_PREFIX.put("E", "事件性任务前缀");
    	TASK_PREFIX.put("DQ", "不合格任务前缀");
    }
    
    public final static String TASK_TYPE_TEMP="TEMP";
    public final static String TASK_TYPE_REGULAR="REGULAR";
    public final static String TASK_TYPE_EVENT="EVENT";
    public final static String TASK_TYPE_SPECIAL="SPECIAL";
    public static Map<String,String> TASK_TYPE = new LinkedHashMap<String,String>();
    static {
    	TASK_TYPE.put("TEMP", "临时任务");
    	TASK_TYPE.put("REGULAR", "周期性任务");
    	TASK_TYPE.put("EVENT", "事件性任务");
    	TASK_TYPE.put("SPECIAL", "专项任务");
    }
    
    public final static String TASK_CHECK_STATUS_TOCHECK="TOCHECK";
    public final static String TASK_CHECK_STATUS_QUALIFIED="QUALIFIED";
    public final static String TASK_CHECK_STATUS_DISQUALIFIED="DISQUALIFIED";
    public static Map<String,String> TASK_CHECK_STATUS = new LinkedHashMap<String,String>();
    static {
    	TASK_CHECK_STATUS.put("TOCHECK", "待检查");
    	TASK_CHECK_STATUS.put("QUALIFIED", "合格");
    	TASK_CHECK_STATUS.put("DISQUALIFIED", "不合格");
    }
    
    public final static String TASK_CHECK_FLAG_MUST="MUST";
    public final static String TASK_CHECK_FLAG_OPTIONAL="OPTIONAL";
    public static Map<String,String> TASK_CHECK_FLAG = new LinkedHashMap<String,String>();
    static {
    	TASK_CHECK_FLAG.put("MUST", "必须");
    	TASK_CHECK_FLAG.put("OPTIONAL", "非必须");
    }
    
    public final static String SHIFT_DAYTIME="DAYTIME";
    public final static String SHIFT_NIGHT="NIGHT";
    public static Map<String,String> SHIFT = new LinkedHashMap<String,String>();
    static {
    	SHIFT.put("DAYTIME", "白班");
    	SHIFT.put("NIGHT", "夜班");
    }
    public final static String SUCCESS_FLAG="1";
    public final static String FAIL_FLAG="-1";
    
}
