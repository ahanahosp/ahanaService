package com.ahana.api.system.listener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

public class AhanaCacheInterceptor implements MethodInterceptor, InitializingBean {

	private static Logger logger = Logger.getLogger(AhanaCacheInterceptor.class);

	private Cache cache;

	public final void setCache(final Cache cac) {
		this.cache = cac;
	}

	public final void afterPropertiesSet() throws Exception {
		Assert.notNull(cache, "A cache is required. Use setCache(Cache) to provide one.");
	}

	public final Object invoke(final MethodInvocation invocation) throws Throwable {
		long currentmilli = 0;
		String cacheKey = null;
		Object returnValue = null;
		if (logger.isDebugEnabled()) {
			logger.debug("invoke started:::");
			currentmilli = System.currentTimeMillis();
			logger.debug("AhanaCacheInterceptor---invoke----start--->"	+ currentmilli);
		}
		Object[] arguments = invocation.getArguments();
		String methodName = invocation.getMethod().getName();
		if (logger.isDebugEnabled()) {
			logger.debug("arguments :::" + arguments);
			logger.debug("MethodName Names:::" + methodName);
		}
		if ((arguments != null) && (arguments.length != 0) && arguments.length == 1) {
			if (arguments[0] instanceof String[]) {
				String[] codes = (String[]) arguments[0];
				returnValue = getReturnValue(invocation, codes);
			} else {
				cacheKey = (String) arguments[0];
				returnValue = getReturnValue(invocation, cacheKey);
			}
		} else if (arguments.length == 3 && arguments[1] instanceof String[] && arguments[2] instanceof String[]) {
			String[] categorys = (String[]) arguments[1];
			String[] codes = (String[]) arguments[2];
			returnValue = getReturnValue(invocation, categorys, codes);
		} else {
			String category = (String) arguments[1];
			String code = (String) arguments[2];
			cacheKey = category + "_" + code;
			returnValue = getReturnValue(invocation, cacheKey);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("returnValue:::" + returnValue);
			logger.debug("invoke End:::");
			logger.debug("AhanaCacheInterceptor---invoke----end--->" + (System.currentTimeMillis() - currentmilli));
		}
		return returnValue;
	}

	private Object getReturnValue(final MethodInvocation invocation,String cacheKey) throws Throwable {
		Object result = null;
		if (logger.isDebugEnabled()) {
			logger.debug("getReturnValue started:::");
			logger.debug("cacheKey:::" + cacheKey);
		}
		Element element = cache.get(cacheKey);
		if (element == null) {
			System.out.println("calling intercepted method");
			try {
				result = invocation.proceed();
			} catch (Exception e) {
				return null;
			}
			if (logger.isDebugEnabled()) {
				logger.debug("caching result");
			}
			System.out.println("caching result");
			if (result != null) {
				element = new Element(cacheKey, (Serializable) result);
				cache.put(element);
			} else {
				return null;
			}
		}
		return element.getObjectValue();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getReturnValue(final MethodInvocation invocation,String[] categorys, String[] codes) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("getReturnValue started:::");
			logger.debug("categorys:::" + categorys + "  codes:::" + codes);
		}
		List lookupObjs = new ArrayList();
		for (int i = 0; i < codes.length; i++) {
			String code = codes[i];
			for (int j = 0; j < categorys.length; j++) {
				String category = categorys[j];
				String cacheKey = category + "_" + code;
				Element element = cache.get(cacheKey);
				if (element != null) {
					lookupObjs.add(element.getObjectValue());
					break;
				} else {
					continue;
				}
			}
		}
		if (lookupObjs.size() == 0) {
			return invocation.proceed();
		}
		return lookupObjs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object getReturnValue(final MethodInvocation invocation,String[] codes) throws Throwable {
		if (logger.isDebugEnabled()) {
			logger.debug("getReturnValue started:::");
			logger.debug("codes:::" + codes);
		}
		List lookupObjs = new ArrayList();
		for (int i = 0; i < codes.length; i++) {
			String code = codes[i];
			String cacheKey = code;
			Element element = cache.get(cacheKey);
			if (element != null) {
				lookupObjs.add(element.getObjectValue());
			}
		}
		if (lookupObjs.size() == 0) {
			return invocation.proceed();
		}
		return lookupObjs;
	}
}