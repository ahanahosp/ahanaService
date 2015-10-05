package com.ahana.api.system.security.filters;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.util.Assert;

import com.ahana.api.common.ErrorConstants;
import com.ahana.api.system.security.exception.AhanaBadCredentialsException;

public class AhanaDaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static Logger logger = Logger.getLogger(AhanaDaoAuthenticationProvider.class);

    private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    private SaltSource saltSource;

    private UserDetailsService userDetailsService;

    private boolean includeDetailsObject = true;

    private UserCache userCache = new NullUserCache();

    private boolean forcePrincipalAsString = false;

    public UserDetailsChecker preAuthenticationChecks = new DefaultPreAuthenticationChecks();

    public UserDetailsChecker postAuthenticationChecks = new DefaultPostAuthenticationChecks();
    
	protected void additionalAuthenticationChecks(final UserDetails userDetails,final UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }
        if (authentication.getCredentials() == null) {
        	throw new AhanaBadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",ErrorConstants.BAD_CREDENTIALS));
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(),presentedPassword, salt)) {
        	throw new AhanaBadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",ErrorConstants.BAD_CREDENTIALS));
        }
    }

    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService,"A UserDetailsService must be set");
    }

    protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)throws AuthenticationException {
    	UserDetails loadedUser;
        try {
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        } catch (DataAccessException repositoryProblem) {
            logger.error("Error in retrieveUser() : "+repositoryProblem.getMessage());
             throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        if (loadedUser == null) {
            throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class,authentication, messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports","Only UsernamePasswordAuthenticationToken is supported"));
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED": authentication.getName();
        boolean cacheWasUsed = true;
        UserDetails user = this.userCache.getUserFromCache(username);
        if (user == null) {
            cacheWasUsed = false;
            try {
                user = retrieveUser(username,(UsernamePasswordAuthenticationToken) authentication);
            } catch (UsernameNotFoundException notFound) {
                logger.error("Exception occurred in authenticate() : "+ notFound.getMessage());
                if (hideUserNotFoundExceptions) {
                    throw new AhanaBadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials",ErrorConstants.BAD_CREDENTIALS));
                } else {
                	notFound.printStackTrace();
                    throw notFound;
                }
            }
            Assert.notNull(user,"retrieveUser returned null - a violation of the interface contract");
        }
        try {
            additionalAuthenticationChecks(user,(UsernamePasswordAuthenticationToken) authentication);
        } catch (AuthenticationException exception) {
            logger.error("Error in authenticate() : " + exception.getMessage());
            if (cacheWasUsed) {
                cacheWasUsed = false;
                user = retrieveUser(username,(UsernamePasswordAuthenticationToken) authentication);
                additionalAuthenticationChecks(user,(UsernamePasswordAuthenticationToken) authentication);
            } else {
                throw exception;
            }
        }
        preAuthenticationChecks.check(user);
        postAuthenticationChecks.check(user);
        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user);
        }
        Object principalToReturn = user;
        if (forcePrincipalAsString) {
            principalToReturn = user.getUsername();
        }
        return createSuccessAuthentication(principalToReturn, authentication,user);
    }

    private class DefaultPreAuthenticationChecks implements UserDetailsChecker {
        public void check(UserDetails user) {
            if (!user.isAccountNonLocked()) {
                throw new LockedException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.locked",ErrorConstants.USER_ACCOUNT_LOCKED));
            }
            if (!user.isEnabled()) {
                throw new DisabledException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.disabled",ErrorConstants.USER_DISABLED));
            }
           if (!user.isAccountNonExpired()) {
               throw new AccountExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.expired",ErrorConstants.USER_ACCOUNT_EXPIRED));
            }
        }
    }

    private class DefaultPostAuthenticationChecks implements UserDetailsChecker {
        public void check(UserDetails user) {
            if (!user.isCredentialsNonExpired()) {
                throw new CredentialsExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired",ErrorConstants.USER_CREDENTIALS_EXPIRED));
            }
        }
    }

    public Md5PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }

    protected SaltSource getSaltSource() {
        return saltSource;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    protected boolean isIncludeDetailsObject() {
        return includeDetailsObject;
    }

    public void setIncludeDetailsObject(boolean includeDetailsObject) {
        this.includeDetailsObject = includeDetailsObject;
    }
    
    @SuppressWarnings("rawtypes")
	@Override
    public boolean supports(Class authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}