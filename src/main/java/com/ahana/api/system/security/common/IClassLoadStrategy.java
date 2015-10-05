package com.ahana.api.system.security.common;

public interface IClassLoadStrategy {

    ClassLoader getClassLoader(ClassLoadContext ctx);

}
