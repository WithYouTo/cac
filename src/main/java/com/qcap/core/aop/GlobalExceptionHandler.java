package com.qcap.core.aop;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.google.common.net.HttpHeaders;
import com.qcap.core.entity.TbManager;
import com.qcap.core.exception.BussinessException;
import com.qcap.core.log.LogManager;
import com.qcap.core.log.factory.LogTaskFactory;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.jwt.JwtTokenUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author huangxiang
 * @date 2017/12/25 17:37
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@Resource
	private JwtTokenUtil jwtTokenUtil;

	@Resource
	private RedisUtil redisUtil;

	/**
	 *
	 * 拦截业务异常
	 *
	 * @date 2017/12/25 17:39
	 */
	@ExceptionHandler(BussinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> notFound(HttpServletRequest request, HttpServletResponse response,
			BussinessException e) {
		TbManager manager = new TbManager();
		LogManager.me().executeLog(LogTaskFactory.exceptionLog(manager.getId(), e));
		String currentUserName = manager.getAccount();

		log.error("--------------全局异常日志记录开始----------------------");
		log.error("URL : " + request.getRequestURL().toString());
		log.error("HTTP请求方法 : " + request.getMethod());
		log.error("IP : " + request.getRemoteAddr());
		log.error("当前操作人员 : " + currentUserName);
		log.error("异常类型:" + e.getClass().getName());
		log.error("异常信息:" + e.getMessage());
		log.error("详细的异常堆栈:", e);
		log.error("--------------全局异常日志记录结束----------------------");
		return specialConsumer(request, response,
				() -> new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 *
	 * 拦截未知的运行时异常
	 *
	 * @date 2017/12/26 9:27
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<String> notFound(HttpServletRequest request, HttpServletResponse response,
			RuntimeException e) {
		TbManager user = Optional.ofNullable(AppUtils.getLoginUser()).orElseGet(TbManager::new);
		LogManager.me().executeLog(LogTaskFactory.exceptionLog(user.getId(), e));
		log.error("--------------全局异常日志记录开始----------------------");
		log.error("URL : " + request.getRequestURL().toString());
		log.error("HTTP请求方法 : " + request.getMethod());
		log.error("IP : " + request.getRemoteAddr());
		// log.error("当前操作人员 : " + getLoginUserAccount());
		log.error("异常类型:" + e.getClass().getName());
		log.error("异常信息:" + e.getMessage());
		log.error("详细的异常堆栈:", e);
		log.error("--------------全局异常日志记录结束----------------------");
		return specialConsumer(request, response,
				() -> new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR));
	}

	private void assertAjax(HttpServletRequest request, HttpServletResponse response) {
		if (request.getHeader(HttpHeaders.X_REQUESTED_WITH) != null
				&& "XMLHttpRequest".equalsIgnoreCase(request.getHeader(HttpHeaders.X_REQUESTED_WITH))) {
			// 如果是ajax请求响应头会有，x-requested-with
			// 在响应头设置session状态
			response.setHeader("sessionstatus", "timeout");
		}
	}

	private static ResponseEntity<String> specialConsumer(HttpServletRequest request, HttpServletResponse response,
			Supplier<ResponseEntity> result) {
		Predicate<HttpServletRequest> p = (HttpServletRequest req) -> {
			return req.getHeader(HttpHeaders.ACCEPT).contains("application/json")
					|| (req.getHeader(HttpHeaders.X_REQUESTED_WITH) != null
							&& "XMLHttpRequest".equalsIgnoreCase(req.getHeader(HttpHeaders.X_REQUESTED_WITH)));
		};
		if (p.test(request)) {
			return result.get();
		} else {
			return new ResponseEntity<String>("500:服务器错误", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
