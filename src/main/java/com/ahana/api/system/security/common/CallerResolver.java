package com.ahana.api.system.security.common;

final class CallerResolver extends SecurityManager {
    @SuppressWarnings("rawtypes")
	protected Class[] getClassContext() {
       return super.getClassContext();
   }
}
