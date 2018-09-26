package com.qcap.core.warpper;



import com.qcap.core.factory.ConstantFactory;
import com.qcap.core.utils.Contrast;
import com.qcap.core.utils.ToolUtil;

import java.util.Map;

/**
 * 
 * @Description: 日志列表的包装类
 *
 * @ClassName: LogWarpper
 * 
 * @author huangxiang
 * @date 2017/12/26 14:58
 */
public class LogWarpper extends BaseControllerWarpper {

    public LogWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        String message = (String) map.get("message");

        String userid = (String) map.get("userid");
        map.put("userName", ConstantFactory.me().getUserNameById(userid));

        //如果信息过长,则只截取前100位字符串
        if (ToolUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            map.put("message", subMessage);
        }

        //如果信息中包含分割符号;;;   则分割字符串返给前台
        if (ToolUtil.isNotEmpty(message) && message.indexOf(Contrast.separator) != -1) {
            String[] msgs = message.split(Contrast.separator);
            map.put("regularMessage",msgs);
        }else{
            map.put("regularMessage",message);
        }
    }

}
