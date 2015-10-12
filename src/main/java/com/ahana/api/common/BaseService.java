package com.ahana.api.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ahana.api.system.security.error.AhanaErrorHandlerUtil;
import com.ahana.api.system.security.error.AhanaResponse;
import com.ahana.api.system.security.error.ErrorContext;
import com.google.gson.Gson;

public abstract class BaseService  {
	
	@Autowired
	private AhanaErrorHandlerUtil ahanaErrorHandlerUtil;
	
	protected final Map<String, Object> handleSuccess(String rootName,final List<?> nameValuePair) {
		Map<String,Object> response=new HashMap<String,Object>();
		response.put(Constants.STATUS, Constants.OK);
		response.put(rootName, nameValuePair);
		return response;
	}
	
	protected final String handleError(Throwable ex) {
		Gson gson=new Gson();
		AhanaResponse ahanaResponse=ahanaErrorHandlerUtil.handleError(populateErrorContext(ex));
		return gson.toJson(ahanaResponse);
	}
	
	protected final Map<String, Object> handleSuccess(String rootName,final Map<String, List<?>> obj) {
		Map<String,Object> response=new HashMap<String,Object>();
		response.put(Constants.STATUS, Constants.OK);
		response.put(rootName, obj);
		return response;
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
	
	protected final Map<String,Object> handleSuccess(String rootName,final AhanaVO ahanaVO) {
		Map<String,Object> response=new HashMap<String,Object>();
		response.put(Constants.STATUS, Constants.OK);
		response.put(rootName, ahanaVO);
		return response;
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