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
        EQUIP_USE_STATUS.put("USED","使用完成");
    }

}
