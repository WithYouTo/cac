package com.qcap.cac.tools;

import com.qcap.core.utils.DateUtil;

import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

    public  static String  getUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;
    }


    /**
     * 生成规则
     * P + YYYYMMDDHHMMSS
     * @return
     */
    public  static String  getBatchNo(){
        String batchNo = "P" + DateUtil.formatDate(new Date(),"yyMMddHHmmss");
        return batchNo;
    }
}
