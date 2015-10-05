package com.ahana.api.system.security.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.common.Constants;
import com.ahana.api.system.security.error.AhanaErrorHandlerUtil;
import com.ahana.api.system.security.error.ErrorContext;
import com.google.gson.Gson;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private AhanaErrorHandlerUtil ahanaErrorHandlerUtil;
	
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public String customExceptionHandler(HttpServletRequest httpServletRequest,Exception exception){
		ErrorContext errorContext = populateErrorContext(exception);
		return new Gson().toJson(ahanaErrorHandlerUtil.handleError(errorContext));
	}
	
	public final ErrorContext populateErrorContext(final Throwable ex) {
		ErrorContext errorContext = new ErrorContext();
		if (ex != null) {
			errorContext.put(Constants.EXCEPTION, ex);
		}
		return errorContext;
	}
}
