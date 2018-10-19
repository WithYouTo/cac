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
     * 批次号生成规则
     * P + YYYYMMDDHHMMSS
     * @return
     */
    public  static String  getBatchNo(){
        String batchNo = "P" + DateUtil.formatDate(new Date(),"yyMMddHHmmss");
        return batchNo;
    }


    /**
     * 岗位编码生成规则
     * GW + 5位随机数
     * @return
     */
    public  static String  getPositionCode(){
        String positionCode = "GW" + ToolUtil.getRandomInt(5);
        return positionCode;
    }
}
