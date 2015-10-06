package com.ahana.api.system.security.error;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.validation.FieldError;

import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.system.security.exception.AhanaApplicationException;
import com.ahana.api.system.security.exception.AhanaBusinessException;
import com.ahana.api.system.security.exception.AhanaException;
import com.ahana.api.system.security.exception.AhanaSystemException;
import com.ahana.api.system.security.exception.AhanaValidationException;

public class AhanaErrorHandlerUtil {
	
	@Autowired
	private ErrorLookup errorLookup;
	
	@Autowired
	private BaseErrorHandler baseErrorHandler;
	
	@SuppressWarnings("rawtypes")
	public final AhanaResponse handleError(final ErrorContext errorContext) {
		Exception ex = (Exception) errorContext.get(Constants.EXCEPTION);
		ResponseError responseError = null;
		if (ex instanceof AhanaValidationException) {
			AhanaValidationException validationException = (AhanaValidationException)ex;			
			List<FieldError> errorFields = validationException.getErrors();
			List<ResponseError> responseErrors = new ArrayList<ResponseError>();
			if (CollectionUtils.isNotEmpty(errorFields)) {
				for (Iterator iterator = errorFields.iterator(); iterator.hasNext();) {
					FieldError fieldError = (FieldError) iterator.next();
					responseError = getError(fieldError.getDefaultMessage());
					if(responseError!=null){
						responseErrors.add(responseError);
					}
				}
				errorContext.put(Constants.EXCEPTION, ex);
				errorContext.put(Constants.RESPONSE_ERRORS, responseErrors);
				return baseErrorHandler.handleError(errorContext);
			} else {
				responseError = getError(((AhanaValidationException) ex).getShortMessage());
				populateErrorContextWithExceptionMap(((AhanaValidationException) ex),errorContext);
			}
		} else if (ex instanceof AhanaBusinessException) {
			responseError = getError(((AhanaBusinessException) ex).getShortMessage());
			populateErrorContextWithExceptionMap(((AhanaException) ex),errorContext);
		} else if (ex instanceof AhanaApplicationException || ex instanceof AhanaSystemException) {
			responseError = getError(((AhanaException) ex).getShortMessage());
			populateErrorContextWithExceptionMap(((AhanaException) ex),errorContext);
		}else if(ex instanceof AccessDeniedException) {
        	responseError = getError(ErrorConstants.ACCESS_DENIED);
        } else if (ex instanceof AccountStatusException){			
			responseError = getError((ex).getMessage());
        }else if (ex instanceof AhanaException) {
			responseError = getError(((AhanaException) ex).getShortMessage());
			populateErrorContextWithExceptionMap(((AhanaException) ex),errorContext);
		}
		if(responseError==null){
			responseError = getError(ErrorConstants.SERVICE_NOT_AVAILABLE);
			AhanaSystemException exception = new AhanaSystemException(ex.getMessage(), ex);
			errorContext.put(Constants.EXCEPTION, exception);
		}
		errorContext.put(Constants.RESPONSE_ERROR, responseError);
		return baseErrorHandler.handleError(errorContext);
	}
	
	private final ResponseError getError(final String errorConstant) {
		return errorLookup.findErrorByToken(errorConstant);
	}
	
	private void populateErrorContextWithExceptionMap(AhanaException ex,ErrorContext errorContext) {
		if(((AhanaException) ex).getExceptionMap() != null && ((AhanaException) ex).getExceptionMap().size() > 0) {
			Map<String,String> exceptionParamMap = ((AhanaException) ex).getExceptionMap();
			errorContext.putAll(exceptionParamMap);
		}
	}

	public final ErrorContext populateErrorContext(final Throwable ex,Map<String, Object> params) {
		ErrorContext errorContext = new ErrorContext();
		if (ex != null) {
			errorContext.put(Constants.EXCEPTION, ex);
		}
		if (params != null && params.size() > 0) {
			Set<String> keys = params.keySet();
			for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				Object value = params.get(key);
				errorContext.put(key, value);
			}
		}
		return errorContext;
	}
	
    public ErrorContext populateErrorContext(final Throwable ex) {
        ErrorContext errorContext = new ErrorContext();
        if (ex != null) {
            errorContext.put(Constants.EXCEPTION, ex);
        }
        return errorContext;
    }
}