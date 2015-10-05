package com.ahana.api.system.security.error;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ahana.api.common.Constants;
import com.ahana.api.system.security.exception.AhanaException;
import com.ahana.api.system.security.exception.AhanaValidationException;

public class BaseErrorHandler {

	private static Logger logger = Logger.getLogger(BaseErrorHandler.class);

	public AhanaResponse handleError(ErrorContext errorContext,ResponseError responseError, AhanaResponse ahanaResponse) {
		logError(responseError, errorContext);
		enhanceLongMessage(responseError, errorContext);
		ahanaResponse.getErrors().add(responseError);
		return ahanaResponse;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AhanaResponse handleError(ErrorContext errorContext) {
		AhanaResponse ahanaResponse = new AhanaResponse();
		ahanaResponse.setStatus(AhanaResponse.Error);
		ResponseError responseError = (ResponseError) errorContext.get(Constants.RESPONSE_ERROR);
		if (responseError != null) {
			enhanceErrorContext(errorContext);
			return handleError(errorContext, responseError, ahanaResponse);
		} else {
			List<ResponseError> responseErrors = (List) errorContext.get(Constants.RESPONSE_ERRORS);
			if (responseErrors != null) {
				handleErrors(errorContext, ahanaResponse);
			}
		}
		return ahanaResponse;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void handleErrors(ErrorContext errorContext, AhanaResponse ahanaResponse) {
		List<ResponseError> responseErrors = (List) errorContext.get(Constants.RESPONSE_ERRORS);
		for (Iterator iterator = responseErrors.iterator(); iterator.hasNext();) {
			ResponseError responseError = (ResponseError) iterator.next();
			handleError(errorContext, responseError, ahanaResponse);
		}
	}

	private void enhanceLongMessage(ResponseError responseError,ErrorContext errorContext) {
		Exception exception = (Exception) errorContext.get(Constants.EXCEPTION);
		if (exception instanceof AhanaValidationException) {
			String longMessage = responseError.getLongMessage();			
			Map<String, Object> contextValues = errorContext.getErrorContextValues();
			Set<String> keys = contextValues.keySet();
			if (keys != null && keys.size() > 0) {
				longMessage = replaceLongMessage(longMessage,keys,contextValues);
				responseError.setLongMessage(longMessage);
			}
		} else if (exception instanceof AhanaException) { 
			String longMessage = responseError.getLongMessage();			
			Map<String, Object> contextValues = errorContext.getErrorContextValues();
			Set<String> keys = contextValues.keySet();
			longMessage = replaceLongMessage(longMessage,keys,contextValues);
			responseError.setLongMessage(longMessage);
		}
	}

	private String replaceLongMessage(String longMessage,Set<String> keys, Map<String, Object> contextValues) {
		for (String key : keys) {			
			if(longMessage.contains("{"+key+"}")) {
				longMessage = longMessage.replace("{"+key+"}",(String) contextValues.get(key));
			}
		}
		return longMessage;
	}

	private void logError(ResponseError responseError, ErrorContext errorContext) {
		Exception exception = (Exception) errorContext.get(Constants.EXCEPTION);
		if (exception instanceof AhanaValidationException) {
			logger.error(((AhanaValidationException) exception).getShortMessage());
		}
		logger.error(exception.getMessage(), exception);
	}

	public void enhanceErrorContext(ErrorContext errorContext) {
		if (errorContext != null) {
			Exception exception = (Exception) errorContext.get(Constants.EXCEPTION);
			if (errorContext.get(Constants.METHOD_NAME) == null) {
				StackTraceElement stackfirst = exception.getStackTrace()[0];
				errorContext.put(Constants.METHOD_NAME, stackfirst.getMethodName());
			}
			errorContext.put(Constants.EXCEPTION_MESSAGE, exception.getMessage());
		}
	}
	
}