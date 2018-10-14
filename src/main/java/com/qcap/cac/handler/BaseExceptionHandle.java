package com.qcap.cac.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.exception.BaseException;
import com.qcap.core.model.ResParams;

/**
 * 公共异常处理类
 *
 * @version 1.0.0
 * @Author Wuhuakeji_zzc
 * @create 2018-06-11 10:20
 **/
@ControllerAdvice
public class BaseExceptionHandle {

	private final static Logger logger = LoggerFactory.getLogger(BaseExceptionHandle.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResParams handle(Exception e) {
		if (e instanceof BaseException) {
			// 系统自定义业务异常
			BaseException baseException = (BaseException) e;
			logger.info("<自定义业务异常>:{}.", e.getMessage(), e);
			return ResParams.newInstance(baseException.getCode(), baseException.getMessage());
		} else if (e instanceof MethodArgumentNotValidException) {
			BindingResult br = ((MethodArgumentNotValidException) e).getBindingResult();
			if (br.hasErrors()) {
				List<ObjectError> errorList = br.getAllErrors();
				return ResParams.newInstance(CommonCodeConstant.PARAM_EMPTY_CODE, errorList.get(0).getDefaultMessage());
			}
		} else {
			// 服务器跑送的非业务异常
			logger.info("<系统异常>:{}.", e.getMessage(), e);
			return ResParams.newInstance(CommonCodeConstant.SYS_EXCEPTION_CODE, CommonCodeConstant.SYS_EXCEPTION_MSG);
		}
		return null;
	}

}
