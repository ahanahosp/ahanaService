package com.ahana.api.system.listener;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ahana.api.common.Constants;
import com.ahana.api.domain.common.NameValue;
import com.ahana.api.domain.common.AhanaLookupVO;
import com.ahana.api.manager.common.lookup.LookupManager;
import com.ahana.api.system.security.common.SystemProperties;

public class AhanaApplicationInitializer extends ContextLoaderListener {

    public static final String SYSTEM_PRODUCTION = "system.production";

    private static final String IS_SYSTEM_PRODUCTION = "true";

    public static final String CONTEXT_ROOT = "context.root";
    
    private static String applicationPath;
    
    private ServletContext servletContext;
    
    public static final String[] PROPERTIES = new String[] {};

    public static String getApplicationPath() {
        return applicationPath;
    }

    public String[] getAppPropertiesFromServerContext(){
    	return PROPERTIES;
    }

    public final void contextDestroyed(final ServletContextEvent sce) {
        Calendar c = Calendar.getInstance();
        String sdt = c.toString();
        System.out.println("Allocation has been shutdown at local time " + sdt);
    }

    public final void contextInitialized(final ServletContextEvent sce) {
        try {
            String[] properties = getAppPropertiesFromServerContext();
            if (properties == null) {
            	System.out.println("************** TERMINATING APPLICATION ************************* \n"
								+ "      Check and make sure Applications prams are set properly     \n"
								+ "*****************************************************************");
            } else {
                applicationPath = sce.getServletContext().getRealPath("/");
                Context initCtx = new InitialContext();
                Context envCtx = (Context) initCtx.lookup("java:comp/env");
                for (int i = 0; i < properties.length; i++) {
					String property = (String) properties[i];
					try {
						String valueStr = (String) envCtx.lookup(property);
						System.out.println("**************  property :"+ property + "*********** " + valueStr);
						if (valueStr != null && !"".equalsIgnoreCase(valueStr)) {
							SystemProperties.setRuntimeProperty(property, valueStr);
						}
					} catch (NameNotFoundException exp) {
						continue;
					}
				} 
                String isProductionEnv = (String) SystemProperties.getProperty(SYSTEM_PRODUCTION);
                System.out.println("**************  Is for production  :"+ isProductionEnv);
				if (IS_SYSTEM_PRODUCTION.equalsIgnoreCase(isProductionEnv)) {
					for (int i = 0; i < properties.length; i++) {
						String property = (String) properties[i];
						if (!SystemProperties.isValidRuntimeProperty(property)) {
							throw new RuntimeException("No runtime app param specified for param="+ property);
						}
					}
				}
				sce.getServletContext().setAttribute("production", isProductionEnv);
            }
            super.contextInitialized(sce);
            loadApplicationProperties(sce);
        } catch (Throwable e) {
            System.out.println("TERMINATING APPLICATION Check and make sure tomcat prams are set properly ");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public void loadApplicationProperties(ServletContextEvent sce) {
		try {
			servletContext = sce.getServletContext();
			WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
			String environment=System.getProperty(Constants.ENVIRONMENT_TYPE);
			//Setting Profile for environment specific
			if(StringUtils.isBlank(environment) || environment.equalsIgnoreCase(Constants.LOCAL)){
				((ConfigurableWebApplicationContext)ctx).getEnvironment().setActiveProfiles(Constants.LOCAL);
			}else if(environment.equalsIgnoreCase(Constants.STAGING)){
				((ConfigurableWebApplicationContext)ctx).getEnvironment().setActiveProfiles(Constants.STAGING);
			}else if(environment.equalsIgnoreCase(Constants.PRODUCTION)){
				((ConfigurableWebApplicationContext)ctx).getEnvironment().setActiveProfiles(Constants.PRODUCTION);
			}
			LookupManager lookupManager = (LookupManager) ctx.getBean("lookupCacheManager");
			Cache lookupCache = (Cache) ctx.getBean("lookupCache");
			loadAllLookup(lookupManager, lookupCache);
		} catch (Exception exp) {
			exp.printStackTrace();
			System.exit(0);
		} catch (Throwable e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void loadAllLookup(final LookupManager lookupManager,final Cache lookupCache) {
        Map<String,Object> lookupMap =lookupManager.getAllLookups();
        Set<String> keys =  lookupMap.keySet();
		for (String key : keys) {
			if ((!key.equals(Constants.LOOKUP_BY_CODE))) {
				List<NameValue> lookupList = (List<NameValue>) lookupMap.get(key);
				Element element = new Element(key,(Serializable) lookupList);
				lookupCache.put(element);
			}
		}

		Map lookupByCode = (Map) lookupMap.get(Constants.LOOKUP_BY_CODE);
		Set<String> lookup_keys = lookupByCode.keySet();
		for (String lookup_key : lookup_keys) {
			AhanaLookupVO lookupNameValue = (AhanaLookupVO) lookupByCode.get(lookup_key);
			Element lookup_element = new Element(lookup_key,(Serializable) lookupNameValue);
			lookupCache.put(lookup_element);
		}
    }
}