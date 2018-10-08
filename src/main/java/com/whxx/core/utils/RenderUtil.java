package com.whxx.core.utils;

import com.alibaba.fastjson.JSON;
import com.whxx.core.exception.GunsException;
import com.whxx.core.exception.GunsExceptionEnum;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 渲染工具类
 *
 * @author fengshuonan
 * @date 2017-08-25 14:13
 */
public class RenderUtil {

    private static Logger logger = LoggerFactory.getLogger(RenderUtil.class);

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new GunsException(GunsExceptionEnum.WRITE_ERROR);
        }
    }

    public static void renderString(HttpServletResponse response,  int status , String result) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/xml; charset=utf-8");
            response.setStatus(status);
            Date currentDate = new Date();
            String createdTime = DateFormatUtils.format(currentDate, "yyyy-MM-dd'T'HH:mm:ss.SSS'+08:00'");

            PrintWriter writer = response.getWriter();
            writer.write(result);
        } catch (IOException e) {
            logger.error("回送信息异常:" + "_" + e.getMessage(), e);
            throw new GunsException(GunsExceptionEnum.SERVER_ERROR);
        }
    }


}
