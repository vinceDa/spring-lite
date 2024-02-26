package com.lite.test.aop;

import com.lite.aop.AdvisedSupport;
import com.lite.aop.MethodMatcher;
import com.lite.aop.TargetSource;
import com.lite.aop.aspectj.AspectjExpressionPointcut;
import com.lite.aop.framework.CglibAopProxy;
import com.lite.aop.framework.JdkDynamicAopProxy;
import com.lite.test.common.WorldServiceInterceptor;
import com.lite.test.service.WorldService;
import com.lite.test.service.WorldServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/26 16:36
 */
public class DynamicProxyTest {

    private AdvisedSupport advisedSupport;

    @BeforeEach
    public void setup() {
        WorldService worldService = new WorldServiceImpl();

        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);

        WorldServiceInterceptor methodInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectjExpressionPointcut("execution(* com.lite.test.service.WorldService.explode(..))")
                .getMethodMatcher();
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

    }

    @Test
    public void testJdkDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

    /**
     * 由于这里我用的是 jdk17，cglib 对 jdk17支持不友好，所以这里是失败的，实际上替换为 jdk8是成功的，只是因为我用了许多 jdk17的语法，改回去太麻烦了就不改了
     * 摘自 cglib github readme：IMPORTANT NOTE: cglib is unmaintained and does not work well (or possibly at all?) in newer JDKs, particularly JDK17+.
     * If you need to support newer JDKs, we will accept well-tested well-thought-out patches...
     * but you'll probably have better luck migrating to something like ByteBuddy.
     */
    @Test
    public void testCglibDynamicProxy() throws Exception {
        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

}
