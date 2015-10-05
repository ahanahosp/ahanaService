package com.ahana.api.system.listener;

import java.io.Serializable;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import com.ahana.api.common.Constants;
import com.ahana.api.system.security.error.ParserData;
import com.ahana.api.system.security.error.ResponseError;
import com.ahana.api.system.security.error.XLSDocReader;

public class ErrorLookupCacheInterceptor implements MethodInterceptor,InitializingBean {

	private static String AHANA_XLS_FILE_PATH = "/config/AhanaErrordictionary.xls";

	private static String AHANA_XML_FILE_PATH = "/config/response_error_mapping.xml";

	private Cache cache;

	public final void setCache(final Cache cac) {
		this.cache = cac;
	}

	public final void afterPropertiesSet() throws Exception {
		String xlsFilePath = AHANA_XLS_FILE_PATH;
		String xmlConfigFilePath = AHANA_XML_FILE_PATH;
		loadResponseErrorInCache(xlsFilePath, xmlConfigFilePath);
		Assert.notNull(cache, "A cache is required."+ " Use setCache(Cache) to provide one.");
	}

	public final Object invoke(final MethodInvocation invocation) throws Throwable {
		Object[] arguments = invocation.getArguments();
		String cacheKey = null;
		if ((arguments != null) && (arguments.length != 0)) {
			cacheKey = (String) arguments[0];
		}
		Element element = cache.get(cacheKey);
		if (element == null) {
			return null;
		}
		return ((ResponseError) element.getObjectValue()).clone();
	}

	private void loadResponseErrorInCache(String xlsFilePath, String xmlConfigFilePath) {
		XLSDocReader parser = new XLSDocReader();
		try {
			ParserData parserData = parser.readXLS(xmlConfigFilePath, xlsFilePath);
			if (parserData != null) {
				List<ResponseError> applicationErrors = parserData.getApplicationErrorConstants();
				List<ResponseError> businessErrors = parserData.getBusinessErrorConstants();
				List<ResponseError> systemErrors = parserData.getSystemErrorConstants();

				//Loads the System ResponseError errors in cache.
				loadErrorsInCache(systemErrors, Constants.ERROR_TYPE_SYSTEM);

				//Loads the Application ResponseError errors in cache.
				loadErrorsInCache(applicationErrors, Constants.ERROR_TYPE_APPLICATION);

				//Loads the Business ResponseError errors in cache.
				loadErrorsInCache(businessErrors, Constants.ERROR_TYPE_BUSINESS);
			}
		} catch (Exception e) {
			System.out.println("TERMINATING APPLICATION Error in loading the ResponseError in Cache \n");
			e.printStackTrace();
			System.exit(0);
		}
	}

	private void loadErrorsInCache(List<ResponseError> errors, String type) {
		if (errors != null && errors.size() > 0) {
			for (ResponseError responseError : errors) {
				if (responseError != null && responseError.getErrorCode() != null) {
					responseError.setErrorType(type);
					Element element = null;
					element = new Element(responseError.getShortMessage(),(Serializable) responseError);
					cache.put(element);
				}
			}
		}
	}

}