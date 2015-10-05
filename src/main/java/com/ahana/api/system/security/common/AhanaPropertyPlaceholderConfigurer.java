package com.ahana.api.system.security.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class AhanaPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected Properties mergeProperties() throws IOException {
		Properties superProps = super.mergeProperties();
		Properties runtimeProps = SystemProperties.getInstance().getAllRuntimeProperties();
		Properties securedProps = SystemProperties.getInstance().getAllSecuredProperties();
		Enumeration<Object> securedPropsKeys = securedProps.keys();
		while (securedPropsKeys.hasMoreElements()) {
			String propName = (String) securedPropsKeys.nextElement();
			superProps.put(propName, securedProps.getProperty(propName));
		}
		Enumeration<Object> runtimePropsKeys = runtimeProps.keys();
		while (runtimePropsKeys.hasMoreElements()) {
			String propName = (String) runtimePropsKeys.nextElement();
			superProps.put(propName, runtimeProps.getProperty(propName));
		}
		return superProps;
	}
}