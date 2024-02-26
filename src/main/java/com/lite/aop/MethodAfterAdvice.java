package com.lite.aop;

import java.lang.reflect.Method;

/**
 * @author vince 2024/2/26 18:53
 */
public interface MethodAfterAdvice extends AfterAdvice {

    void after(Method method, Object[] args, Object target) throws Throwable;

}
