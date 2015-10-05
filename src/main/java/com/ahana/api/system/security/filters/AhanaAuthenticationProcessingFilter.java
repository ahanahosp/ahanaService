package com.ahana.api.system.security.filters;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.ahana.api.common.Constants;
import com.ahana.api.common.ErrorConstants;
import com.ahana.api.domain.user.UserProfile;
import com.ahana.api.system.security.exception.AhanaBadCredentialsException;

@SuppressWarnings({ "rawtypes", "unchecked"})
public class AhanaAuthenticationProcessingFilter extends GenericFilterBean implements ApplicationEventPublisherAware, MessageSourceAware {
	
	private static Logger logger = Logger.getLogger(AhanaAuthenticationProcessingFilter.class);

    protected ApplicationEventPublisher eventPublisher;
    
    protected AuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();
    
    private AuthenticationManager authenticationManager;
    
    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	    
    private RememberMeServices rememberMeServices = new NullRememberMeServices();

    private List<String> filterProcessesUrls;

	private boolean continueChainBeforeSuccessfulAuthentication = false;

	private SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();

	private boolean allowSessionCreation = true;

	private AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
	
	private UserProfileEnhancer userProfileEnhancer;
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)throws IOException, ServletException{
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (!requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Request is to process authentication");
        }
        Authentication authResult;
        try {
        	authResult = attemptAuthentication(request);
            if (authResult == null) {
                return;
            }
            sessionStrategy.onAuthentication(authResult, request, response);
        }catch (AuthenticationException failed) {
            unsuccessfulAuthentication(request, response, failed);
            return;
        }
        // Authentication success
        if (continueChainBeforeSuccessfulAuthentication) {
            chain.doFilter(request, response);
        }
        try {
        	successfulAuthentication(request, response, authResult);
        	userProfileEnhancer.enhanceUserProfile(authResult);
        }catch(AuthenticationException e){
            unsuccessfulAuthentication(request, response, e);
            return;	
        }
    }
	
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if (pathParamIndex > 0) {
            uri = uri.substring(0, pathParamIndex);
        }
        return checkURIEndsWirhFilterURLs(uri,request);
    }
	 
    private boolean checkURIEndsWirhFilterURLs(String uri,HttpServletRequest request) {
    	boolean isAuthRequire = false;
    	if(filterProcessesUrls != null && filterProcessesUrls.size() > 0) {
    		for(String filterProcessesUrl : filterProcessesUrls) {   			
    			isAuthRequire= uri.endsWith(filterProcessesUrl);  		        
                if(isAuthRequire == false) {
                	continue;
                } else {
                	return isAuthRequire;
                }
    		}
    	}
    	return isAuthRequire;
   	}
    
    public final Authentication attemptAuthentication(final HttpServletRequest request) throws AuthenticationException {
        Authentication authentication = null;
        boolean rememberUserName = false;
        String username = null;
        String paramValue = request.getParameter(Constants.REMEMBER_USERNAME);
        if (paramValue != null) {
            if (paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on") || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1")) {
            	rememberUserName = true;
            	if (logger.isDebugEnabled()) {
                    logger.debug("remember-me Enabled for user :" + username + ")");
                }
            }
        }
		Authentication authRequest = populateAuthTokenFromRequest(request);
		if(null!=authRequest){
	        HttpSession session = request.getSession(false);
	        if(session == null && getAllowSessionCreation()){
	            session = request.getSession();
	        }
	        if (session != null && null!=authRequest) {
	            session.setAttribute("SPRING_SECURITY_LAST_USERNAME",TextEscapeUtils.escapeEntities(authRequest.getName().toLowerCase().trim()));
	            session.setAttribute(Constants.REMEMBER_USERNAME_KEY, rememberUserName);
	        }
	        setDetails(request, authRequest);
	        try {
	        	chckLoginFailureCount(session,authRequest);
	            authentication = getAuthenticationManager().authenticate(authRequest);
	      		SecurityContextHolder.getContext().setAuthentication(authentication);
	            if (session != null) {
	            	UserProfile userProfile =null;
	            	if(authentication.getPrincipal() instanceof UserProfile){
	            		userProfile = (UserProfile) authentication.getPrincipal();
	            		session.setAttribute("SPRING_SECURITY_LAST_USERNAME",TextEscapeUtils.escapeEntities(userProfile.getUserId()));
	            	}
	           		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,authentication);
	            }
	        }catch (Throwable ex) {
	        	if(ex instanceof CredentialsExpiredException){
	        		throw new CredentialsExpiredException(ex.getMessage(), ex);
	        	}else if(ex instanceof LockedException){
	        		throw new LockedException(ex.getMessage(), ex);
	        	}else if(ex instanceof DisabledException){
	        		throw new DisabledException(ex.getMessage(), ex);
	        	}else if(ex instanceof AccountExpiredException){
	        		throw new AccountExpiredException(ex.getMessage(), ex);
	        	}else if(ex instanceof AhanaBadCredentialsException){
	        		throw new AhanaBadCredentialsException(ex.getMessage(), ex);
	        	}else{
	        		throw new AuthenticationServiceException(ex.getMessage(), ex);
	        	}
	        }
		}else{
			throw new AccessDeniedException(ErrorConstants.ACCESS_DENIED);
		}
	    return authentication;
    }
    		
    private void chckLoginFailureCount(HttpSession session,Authentication authRequest) {
    	HashMap loginFailureCount = (HashMap)session.getAttribute(Constants.LOGIN_FAILURE_COUNT);
    	if (loginFailureCount == null) {
    		loginFailureCount = new HashMap();
            loginFailureCount.put(authRequest.getName(),0);
    	} else {
    		loginFailureCount.put(authRequest.getName(),(Integer)loginFailureCount.get(authRequest.getName())+1);
    	}
    	//Add the failure count
        session.setAttribute(Constants.LOGIN_FAILURE_COUNT, loginFailureCount);
        if(loginFailureCount!=null && (Integer)loginFailureCount.get(authRequest.getName())>=3){
   			
   		}
        System.out.println("loginFailureCount.get(Constants.LOGIN_FAILURE_COUNT) : "+loginFailureCount.get(authRequest.getName()));
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,Authentication authResult) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: " + authResult);
        }
        SecurityContextHolder.getContext().setAuthentication(authResult);
        rememberMeServices.loginSuccess(request, response, authResult);
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }
    
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication request failed: " + failed.toString());
            logger.debug("Updated SecurityContextHolder to contain null Authentication");
            logger.debug("Delegating to authentication failure handler" + failureHandler);
        }
        rememberMeServices.loginFail(request, response);
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    protected void setDetails(HttpServletRequest request, Authentication authRequest) {
    	if(authRequest instanceof UsernamePasswordAuthenticationToken){
    		((UsernamePasswordAuthenticationToken)authRequest).setDetails(authenticationDetailsSource.buildDetails(request));
    	}
    }
    
    @Override
    public void afterPropertiesSet() {
        if(filterProcessesUrls != null && filterProcessesUrls.size() > 0) {
        	for(String filterProcessesUrl : filterProcessesUrls) {
        		Assert.hasLength(filterProcessesUrl, "filterProcessesUrl must be specified");
    		 	Assert.isTrue(UrlUtils.isValidRedirectUrl(filterProcessesUrl), filterProcessesUrl + " isn't a valid redirect URL");
    		 	Assert.notNull(authenticationManager, "authenticationManager must be specified");
        	}
        } else {
        	Assert.isNull(filterProcessesUrls, "filterProcessesUrl must be specified");
        }
        if (rememberMeServices == null) {
            rememberMeServices = new NullRememberMeServices();
        }
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public List getFilterProcessesUrls() {
        return filterProcessesUrls;
    }

    public void setFilterProcessesUrls(List filterProcessesUrls) {
        this.filterProcessesUrls = filterProcessesUrls;
    }

    public RememberMeServices getRememberMeServices() {
        return rememberMeServices;
    }

    public void setRememberMeServices(RememberMeServices rememberMeServices) {
        this.rememberMeServices = rememberMeServices;
    }

    public void setContinueChainBeforeSuccessfulAuthentication(boolean continueChainBeforeSuccessfulAuthentication) {
        this.continueChainBeforeSuccessfulAuthentication = continueChainBeforeSuccessfulAuthentication;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    public AuthenticationDetailsSource getAuthenticationDetailsSource() {
        return authenticationDetailsSource;
    }

    protected boolean getAllowSessionCreation() {
        return allowSessionCreation;
    }

    public void setAllowSessionCreation(boolean allowSessionCreation) {
        this.allowSessionCreation = allowSessionCreation;
    }

    public void setSessionAuthenticationStrategy(SessionAuthenticationStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        Assert.notNull(successHandler, "successHandler cannot be null");
        this.successHandler = successHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
        Assert.notNull(failureHandler, "failureHandler cannot be null");
        this.failureHandler = failureHandler;
    }
    
    public static Authentication populateAuthTokenFromRequest(HttpServletRequest request) {
		if (StringUtils.isNotBlank(request.getParameter("j_username")) && StringUtils.isNotBlank(request.getParameter("j_password"))) {
			return new UsernamePasswordAuthenticationToken(request.getParameter("j_username"),request.getParameter("j_password"));
		}
		return null;
	}

	public UserProfileEnhancer getUserProfileEnhancer() {
		return userProfileEnhancer;
	}

	public void setUserProfileEnhancer(UserProfileEnhancer userProfileEnhancer) {
		this.userProfileEnhancer = userProfileEnhancer;
	}
}