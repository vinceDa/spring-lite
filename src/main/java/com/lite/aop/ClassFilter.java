package com.lite.aop;

/**
 * @author vince 2024/2/26 11:35
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
