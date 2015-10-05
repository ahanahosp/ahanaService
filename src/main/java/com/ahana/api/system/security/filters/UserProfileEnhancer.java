package com.ahana.api.system.security.filters;

import org.springframework.security.core.Authentication;

public interface UserProfileEnhancer {

	public void enhanceUserProfile(Authentication authentication);
}
