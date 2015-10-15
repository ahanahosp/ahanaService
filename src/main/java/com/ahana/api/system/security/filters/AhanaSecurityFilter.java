package com.ahana.api.system.security.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.ahana.api.common.ErrorConstants;

public class AhanaSecurityFilter extends GenericFilterBean implements InitializingBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws java.io.IOException, ServletException {
		if(SecurityContextHolder.getContext()==null 
				|| SecurityContextHolder.getContext().getAuthentication()==null 
				|| SecurityContextHolder.getContext().getAuthentication()==null 
				|| SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String){
			throw new AccessDeniedException(ErrorConstants.SERVICE_IS_DENIED);
		}
		chain.doFilter(request, response);
	}

}