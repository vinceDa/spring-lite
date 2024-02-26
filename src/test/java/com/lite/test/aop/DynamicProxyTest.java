package com.lite.test.aop;

import com.lite.aop.AdvisedSupport;
import com.lite.aop.MethodMatcher;
import com.lite.aop.TargetSource;
import com.lite.aop.aspectj.AspectjExpressionPointcut;
import com.lite.aop.framework.JdkDynamicAopProxy;
import com.lite.test.common.WorldServiceInterceptor;
import com.lite.test.service.WorldService;
import com.lite.test.service.WorldServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/26 16:36
 */
public class DynamicProxyTest {

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService worldService = new WorldServiceImpl();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);

        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectjExpressionPointcut("execution(* com.lite.test.service.WorldService.explode(..))")
                .getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

}
