package com.lite.test.ioc;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.bean.A;
import com.lite.test.bean.B;
import org.junit.jupiter.api.Test;

public class CircularReferenceWithProxyBeanTest {

    @Test
    public void testCircularReferenceWithProxyBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
        assert a.getB() == b;
    }

}
