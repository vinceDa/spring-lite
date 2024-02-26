package com.lite.test.common;

import com.lite.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author vince 2024/2/26 19:00
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice: do something before the earth explodes");
    }
}
