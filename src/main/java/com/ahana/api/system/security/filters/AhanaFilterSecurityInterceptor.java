package com.ahana.api.system.security.filters;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.util.Assert;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.system.security.exception.AhanaApplicationException;
import com.ahana.api.system.security.exception.AhanaRuntimeException;
import com.ahana.api.system.security.exception.AhanaSystemException;


public class AhanaFilterSecurityInterceptor extends FilterSecurityInterceptor {

    private static Logger logger = Logger.getLogger(AhanaFilterSecurityInterceptor.class);

    public void invoke(FilterInvocation fi) throws IOException,ServletException, AhanaRuntimeException {
        try {
        	if(logger.isDebugEnabled()){
        		logger.debug("Invoke method called");
        	}
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(logger.isDebugEnabled()){
            	logger.debug("Get SecurityContextHolder.getContext()"+ ".getAuthentication() :"+ auth);
            }
            if (auth == null) {
                 throw new AuthenticationCredentialsNotFoundException(" Authentication Object not in the security context");
            }
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } catch (AccessDeniedException e) {
            throw e;
        } catch (AuthenticationCredentialsNotFoundException e) {
            throw e;
        } catch (AhanaApplicationException e) {
            throw e;
        }catch(HttpRequestMethodNotSupportedException e){
        	String requestMethod =  ((HttpServletRequest)fi.getRequest()).getMethod().toLowerCase();
        	if(requestMethod.equalsIgnoreCase(String.valueOf(HttpMethod.GET))){
				throw new AhanaApplicationException(ErrorConstants.GET_METHOD_NOT_SUPPORTED);	
			}else if(requestMethod.equalsIgnoreCase(String.valueOf(HttpMethod.POST))){
				throw new AhanaApplicationException(ErrorConstants.POST_METHOD_NOT_SUPPORTED);	
			}
        }
        catch (Throwable e) {
            throw new AhanaSystemException(ErrorConstants.SERVICE_NOT_AVAILABLE, e);
        }
    }

    public void afterPropertiesSet() throws Exception {
        Assert.notNull(getAuthenticationManager(),"An AuthenticationManager is required");
        Assert.notNull(getAccessDecisionManager(),"An AccessDecisionManager is required");
    }
}