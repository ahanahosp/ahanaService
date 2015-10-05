package com.ahana.api.system.security.filters;


import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.ahana.api.common.Constants;
import com.ahana.api.system.security.common.SystemProperties;

public class AhanaAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint  {

	private static org.apache.commons.logging.Log logger;

	public AhanaAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	private boolean forceHttps = true;

    @Override
    public final void afterPropertiesSet() {
		try {
			super.afterPropertiesSet();
			String getForceHttps = SystemProperties.getRuntimeProperty(Constants.FORCEHTTPS.toLowerCase());
			if (StringUtils.isEmpty(getForceHttps)) {
				getForceHttps = "true";
			}
			forceHttps = Boolean.parseBoolean(getForceHttps);
		} catch (Throwable e) {
			logger.error("Please check the ForceHttps or Login Form Url");
			System.exit(0);
		}
		this.setForceHttps(forceHttps);
	}
}