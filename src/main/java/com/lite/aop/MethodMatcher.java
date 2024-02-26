package com.lite.aop;

import java.lang.reflect.Method;

/**
 * @author vince 2024/2/26 11:34
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}
