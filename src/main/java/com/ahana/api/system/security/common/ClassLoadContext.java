package com.ahana.api.system.security.common;

class ClassLoadContext {

    @SuppressWarnings("rawtypes")
	public final Class getCallerClass() {
        return m_caller;
    }

    @SuppressWarnings("rawtypes")
	ClassLoadContext(final Class caller) {
        m_caller = caller;
    }

    @SuppressWarnings("rawtypes")
	private final Class m_caller;

}
