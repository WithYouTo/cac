package com.whxx.core.aop;

import com.whxx.core.annotation.BussinessLog;
import com.whxx.core.dict.base.AbstractDictMap;
import com.whxx.core.entity.TbManager;
import com.whxx.core.log.LogManager;
import com.whxx.core.log.LogObjectHolder;
import com.whxx.core.log.factory.LogTaskFactory;
import com.whxx.core.utils.AppUtils;
import com.whxx.core.utils.Contrast;
import com.whxx.core.utils.RedisUtil;
import com.whxx.core.utils.jwt.JwtProperties;
import com.whxx.core.utils.jwt.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * 日志记录
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午8:48:30
 */
@Aspect
@Component
public class LogAop {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private RedisUtil redisUtil;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value = "@annotation(com.whxx.core.annotation.BussinessLog)")
    public void cutService() {
    }

    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {

        //获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        String methodName = currentMethod.getName();

        //如果当前用户未登录，不做日志
        //获取当前登录用户信息
        TbManager manager = AppUtils.getLoginUser();
        if(manager==null){
        	return ;
        }

        //获取拦截方法的参数
        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String businessName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        //如果涉及到修改,比对变化
        String msg;
        if (AppUtils.getCurrentRequest() != null)
        {
            HashMap<String, String> values = new HashMap<>();
            HttpServletRequest request = AppUtils.getCurrentRequest();
            Enumeration enums = request.getParameterNames();
            while (enums.hasMoreElements())
            {
                String paramName = (String) enums.nextElement();
                String paramValue = request.getParameter(paramName);
                values.put(paramName, paramValue);
            }

            if (StringUtils.containsAny(businessName, "修改", "编辑"))
            {
                Object obj1 = LogObjectHolder.me().get();
                msg = Contrast.contrastObj(dictClass, key, obj1, values);
            } else
            {
                AbstractDictMap dictMap = (AbstractDictMap) dictClass.newInstance();
                msg = Contrast.parseMutiKey(dictMap, key, values);
            }
            LogManager.me().executeLog(LogTaskFactory.businessLog(manager.getAccount(), businessName, className, methodName, msg));
        }
    }
}