package com.qcap.cac.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommonConstant {

    //设备充电状态
    public final static String EQUIP_CHARGE_STATUS_INCHARGE = "INCHARGE";
    public final static String EQUIP_CHARGE_STATUS_CHARGED = "CHARGED";
    public static Map<String,String> EQUIP_CHARGE_STATUS = new LinkedHashMap<String,String>();
    static {
        EQUIP_CHARGE_STATUS.put("INCHARGE","充电中");
        EQUIP_CHARGE_STATUS.put("CHARGED","充电完成");
    }
    
    //设备使用状态
    public final static String EQUIP_CHARGE_STATUS_INUSE = "INUSE";
    public final static String EQUIP_CHARGE_STATUS_USED = "USED";
    public static Map<String,String> EQUIP_USE_STATUS = new LinkedHashMap<String,String>();
    static {
        EQUIP_USE_STATUS.put("INUSE","使用中");
        EQUIP_USE_STATUS.put("USED","使用完毕");
    }

    //设备维保类型
    public final static String MAINT_TYPE_EQUIP = "EQUIP";
    public final static String MAINT_TYPE_PARTS = "PARTS";
    public static Map<String,String> MAINT_TYPE = new LinkedHashMap<String,String>();
    static {
        MAINT_TYPE.put("EQUIP","设备");
        MAINT_TYPE.put("PARTS","配件");
    }

    //设备维修状态
    public final static String EQUIP_CHARGE_STATUS_REPAIR = "REPAIR";
    public final static String EQUIP_CHARGE_STATUS_REPAIRED = "REPAIRED";
    public static Map<String,String> EQUIP_REPAIR_STATUS = new LinkedHashMap<String,String>();
    static {
        EQUIP_REPAIR_STATUS.put("REPAIR","维修中");
        EQUIP_REPAIR_STATUS.put("REPAIRED","维修完成");
    }
    
    //任务状态
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
    
    //任务编码前缀
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
    
    //任务类型
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
    
    //任务检查状态
    public final static String TASK_CHECK_STATUS_TOCHECK="TOCHECK";
    public final static String TASK_CHECK_STATUS_QUALIFIED="QUALIFIED";
    public final static String TASK_CHECK_STATUS_DISQUALIFIED="DISQUALIFIED";
    public static Map<String,String> TASK_CHECK_STATUS = new LinkedHashMap<String,String>();
    static {
    	TASK_CHECK_STATUS.put("TOCHECK", "待检查");
    	TASK_CHECK_STATUS.put("QUALIFIED", "合格");
    	TASK_CHECK_STATUS.put("DISQUALIFIED", "不合格");
    }
    
    //任务是否必须检查标识
    public final static String TASK_CHECK_FLAG_MUST="MUST";
    public final static String TASK_CHECK_FLAG_OPTIONAL="OPTIONAL";
    public static Map<String,String> TASK_CHECK_FLAG = new LinkedHashMap<String,String>();
    static {
    	TASK_CHECK_FLAG.put("MUST", "必须");
    	TASK_CHECK_FLAG.put("OPTIONAL", "非必须");
    }
    
    //班次类型
    public final static String SHIFT_DAYTIME="DAYTIME";
    public final static String SHIFT_NIGHT="NIGHT";
    public static Map<String,String> SHIFT = new LinkedHashMap<String,String>();
    static {
    	SHIFT.put("DAYTIME", "白班");
    	SHIFT.put("NIGHT", "夜班");
    }
    
    //任务是否必须上传图片标识
    public final static String UPLOAD_PIC_FLAG_MUST="MUST";
    public final static String UPLOAD_PIC_FLAG_OPTIONAL="OPTIONAL";
    public static Map<String,String> UPLOAD_PIC_FLAG = new LinkedHashMap<String,String>();
    static {
    	UPLOAD_PIC_FLAG.put("MUST", "必须");
    	UPLOAD_PIC_FLAG.put("OPTIONAL", "非必须");
    }
    
    //返回状态
    public final static String BACK_FLAG="flag";
    public final static String BACK_SUCCESS_FLAG="1";
    public final static String BACK_FAIL_FLAG="-1";
    public final static String BACK_MESSAGE="message";

    //领用状态
    public final static String WAREHOUSE_REQ_STATUS_INIT = "INIT";
    public final static String WAREHOUSE_REQ_STATUS_COMMIT = "COMMIT";
    public final static String WAREHOUSE_REQ_STATUS_RECEIVE = "RECEIVE";
    public static Map<String,String> WAREHOUSE_REQ_STATUS = new LinkedHashMap<String,String>();
    static {
        WAREHOUSE_REQ_STATUS.put("INIT","已申请");
        WAREHOUSE_REQ_STATUS.put("COMMIT","已提交");
        WAREHOUSE_REQ_STATUS.put("RECEIVE","已领用");
    }
    
    //周期性计划时间类型
    public final static String PLAN_TIME_TYPE_DAY = "DAY";
    public final static String PLAN_TIME_TYPE_WEEK = "WEEK";
    public final static String PLAN_TIME_TYPE_MONTH = "MONTH";
    public final static String PLAN_TIME_TYPE_YEAR = "YEAR";

}
