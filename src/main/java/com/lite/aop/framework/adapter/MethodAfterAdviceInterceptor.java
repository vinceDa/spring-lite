package com.lite.aop.framework.adapter;

import com.lite.aop.MethodAfterAdvice;
import com.lite.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 在执行被代理方法之后执行 afterAdvice
 * @author vince 2024/2/26 18:51
 */
public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private MethodAfterAdvice advice;

    public MethodAfterAdviceInterceptor(MethodAfterAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        // 在执行被代理方法之后执行 after advice 操作
        this.advice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return retVal;
    }
}
