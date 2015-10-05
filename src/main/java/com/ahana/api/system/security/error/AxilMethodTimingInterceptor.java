package com.ahana.api.system.security.error;

import java.math.BigDecimal;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;

public class AxilMethodTimingInterceptor implements MethodInterceptor {

	private static Logger logger = Logger.getLogger(AxilMethodTimingInterceptor.class);

	public final Object invoke(final MethodInvocation methodInvocation)throws Throwable {
		final StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object methodResult = null;
		try {
			methodResult = methodInvocation.proceed();
		} finally {
			stopWatch.stop();
		}
		final long millis = stopWatch.getTime();
		final BigDecimal seconds = new BigDecimal(millis).divide(new BigDecimal(DateUtils.MILLIS_PER_SECOND),BigDecimal.ROUND_HALF_UP);
		if (logger.isDebugEnabled()) {
			logger.debug("Method Invocation ["+ methodInvocation.getThis().getClass().getName() + "."+ methodInvocation.getMethod().getName() + "] Total Time: "
					+ seconds + "(seconds) " + millis + "(millis)");
		}
		return methodResult;
	}
}