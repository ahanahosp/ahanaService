package com.ahana.api.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.ahana.api.system.security.error.AhanaErrorHandlerUtil;
import com.ahana.api.system.security.error.AhanaResponse;
import com.ahana.api.system.security.error.ErrorContext;
import com.ahana.api.system.security.exception.AhanaValidationException;
import com.google.gson.Gson;

public abstract class BaseService  {
	
	@Autowired
	private AhanaErrorHandlerUtil ahanaErrorHandlerUtil;
	
	protected final String handleSuccess(String rootName,final List<?> nameValuePair) {
		Gson gson=new Gson();
		String responseJson=gson.toJson(nameValuePair);
		return "{\"Status\":\"Ok\",\""+rootName+"\":"+responseJson+"}";
	}
	
	protected final String handleError(Throwable ex) {
		Gson gson=new Gson();
		AhanaResponse ahanaResponse=ahanaErrorHandlerUtil.handleError(populateErrorContext(ex));
		return gson.toJson(ahanaResponse);
	}
	
	protected String handleError(List<ObjectError> allErrors) {
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
		Gson gson=new Gson();
		AhanaResponse ahanaResponse=ahanaErrorHandlerUtil.handleError(populateErrorContext(ahanaValidationException));
		return gson.toJson(ahanaResponse);
	}
	
	protected final String handleSuccess(String rootName,final Map<String, List<?>> obj) {
		Gson gson=new Gson();
		String responseJson=gson.toJson(obj);
		return "{\"Status\":\"Ok\",\""+rootName+"\":"+responseJson+"}";
	}
	
	protected final String handleSuccess(final AhanaVO nameValuePair) {
		Gson gson=new Gson();
		return gson.toJson(nameValuePair);
	}
	
	protected final String handleSuccess(final Boolean flag) {
		if(flag){
			return "{\"Status\":\"Ok\"}";
		}else{
			return "{\"Status\":\"Error\"}";
		}
	}
	
	protected final String handleSuccess(String rootName,final AhanaVO ahanaVO) {
		Gson gson=new Gson();
		String responseJson=gson.toJson(ahanaVO);
		return "{\"Status\":\"Ok\",\""+rootName+"\":"+responseJson+"}";
	}
	
	protected String handleSuccess(Map<String, String> permissions) {
		Gson gson=new Gson();
		permissions.put("STATUS", "Ok");
		return gson.toJson(permissions);
	}
	
	protected String handleSuccess(String oids) {
		return "{\"Status\":\"Ok\",\"oid\":"+oids+"}";
	}
	
	public final ErrorContext populateErrorContext(final Throwable ex) {
		ErrorContext errorContext = new ErrorContext();
		if (ex != null) {
			errorContext.put(Constants.EXCEPTION, ex);
		}
		return errorContext;
	}
}