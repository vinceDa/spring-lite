package com.lite.test.aop;

import com.lite.aop.aspectj.AspectjExpressionPointcut;
import com.lite.test.service.HelloService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * @author vince 2024/2/26 11:47
 */
public class PointcutExpressionTest {

    @Test
    public void testPointcutExpression() throws Exception {
        AspectjExpressionPointcut pointCut = new AspectjExpressionPointcut("execution(* com.lite.test.service.HelloService.*(..))");
        Class<HelloService> clazz = HelloService.class;
        Method method = clazz.getDeclaredMethod("sayHello");

        assert pointCut.matches(clazz);
        assert pointCut.matches(method, clazz);
    }

}
