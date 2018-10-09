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
}
