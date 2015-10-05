package com.ahana.api.system.security.common;


public abstract class ClassLoaderResolver {

    @SuppressWarnings("rawtypes")
	public static synchronized ClassLoader getClassLoader() {
        final Class caller = getCallerClass(0);
        final ClassLoadContext ctx = new ClassLoadContext(caller);
        return s_strategy.getClassLoader(ctx);
    }

    public static synchronized IClassLoadStrategy getStrategy() {
        return s_strategy;
    }

    public static synchronized IClassLoadStrategy setStrategy(final IClassLoadStrategy strategy) {
        if (strategy == null) {
        	throw new IllegalArgumentException("null input: strategy");
        }
        final IClassLoadStrategy old = s_strategy;
        s_strategy = strategy;
        return old;
    }

    @SuppressWarnings("rawtypes")
	static synchronized ClassLoader getClassLoader(final int callerOffset) {
        final Class caller = getCallerClass(callerOffset);
        final ClassLoadContext ctx = new ClassLoadContext(caller);
        return s_strategy.getClassLoader(ctx);
    }

    private ClassLoaderResolver() {
    }

    @SuppressWarnings("rawtypes")
	private static Class getCallerClass(final int callerOffset) {
        return CALLER_RESOLVER.getClassContext()[CALL_CONTEXT_OFFSET + callerOffset];
    }

    private static IClassLoadStrategy s_strategy; // initialized in <clinit>

    private static final int CALL_CONTEXT_OFFSET = 3; // may need to change if

    private static final CallerResolver CALLER_RESOLVER; // set in <clinit>

    static {
        try {
            CALLER_RESOLVER = new CallerResolver();
        } catch (SecurityException se) {
             throw new RuntimeException(
                 "ClassLoaderResolver: could not create CallerResolver: "
                      + se);
        }

        s_strategy = new DefaultClassLoadStrategy();
    }

}
