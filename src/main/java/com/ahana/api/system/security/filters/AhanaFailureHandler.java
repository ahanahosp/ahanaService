package com.ahana.api.system.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

@SuppressWarnings({"rawtypes","unchecked"})
public class AhanaFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
    private Map<String, String> failureUrlMap = new HashMap<String, String>();
    private List<String> appendParams;
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {   
    	String url = failureUrlMap.get(exception.getClass().getName());
        String appendParamVal = "";
        if (url != null) {
        	if(appendParams != null && appendParams.size() > 0) {        		
        		for(String appendParam : appendParams) {
        			if(!StringUtils.isEmpty(request.getParameter(appendParam))){
        				appendParamVal = appendParamVal + "&" +appendParam +"=" + request.getParameter(appendParam);
        			}
        		}        		
        	}        	
        	url = url + appendParamVal;
            getRedirectStrategy().sendRedirect(request, response, url);
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }

    public void setExceptionMappings(Map<?,?> failureUrlMap) {
        this.failureUrlMap.clear();
        for (Map.Entry<?,?> entry : failureUrlMap.entrySet()) {
            Object exception = entry.getKey();
            Object url = entry.getValue();
            Assert.isInstanceOf(String.class, exception, "Exception key must be a String (the exception classname).");
            Assert.isInstanceOf(String.class, url, "URL must be a String");
            Assert.isTrue(UrlUtils.isValidRedirectUrl((String)url), "Not a valid redirect URL: " + url);
            this.failureUrlMap.put((String)exception, (String)url);
        }
    }
    
 	public List getAppendParams() {
        return appendParams;
    }

    public void setAppendParams(List appendParams) {
        this.appendParams = appendParams;
    }
}