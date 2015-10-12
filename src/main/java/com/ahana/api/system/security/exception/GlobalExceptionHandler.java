package com.ahana.api.system.security.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahana.api.common.Constants;
import com.ahana.api.system.security.error.AhanaErrorHandlerUtil;
import com.ahana.api.system.security.error.AhanaResponse;
import com.ahana.api.system.security.error.ErrorContext;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private AhanaErrorHandlerUtil ahanaErrorHandlerUtil;
	
	@ExceptionHandler(Throwable.class)
	@ResponseBody
	public AhanaResponse customExceptionHandler(HttpServletRequest httpServletRequest,Exception exception){
		if(exception instanceof MethodArgumentNotValidException){
			AhanaValidationException ahanaValidationException=handleValidationError(((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors());
			return ahanaErrorHandlerUtil.handleError(populateErrorContext(ahanaValidationException));
		}else{
			ErrorContext errorContext = populateErrorContext(exception);
			return ahanaErrorHandlerUtil.handleError(errorContext);
		}
	}
	
	public final ErrorContext populateErrorContext(final Throwable ex) {
		ErrorContext errorContext = new ErrorContext();
		if (ex != null) {
			errorContext.put(Constants.EXCEPTION, ex);
		}
		return errorContext;
	}
	
	protected AhanaValidationException handleValidationError(List<ObjectError> allErrors) {
		AhanaValidationException ahanaValidationException=null;
		List<FieldError> fieldErrors=new ArrayList<FieldError>();
		if(CollectionUtils.isNotEmpty(allErrors)){
			for(ObjectError objectError:allErrors){
				objectError.getDefaultMessage();
				FieldError fieldError=(FieldError)objectError;
				fieldErrors.add(fieldError);
			}
			ahanaValidationException =new AhanaValidationException(fieldErrors,"");
		}
		return ahanaValidationException;
	}
}	