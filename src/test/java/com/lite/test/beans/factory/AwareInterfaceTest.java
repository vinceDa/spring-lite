package com.lite.test.beans.factory;

import com.lite.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;
import com.lite.test.beans.factory.service.HelloService;

import java.util.Objects;

/**
 * @author vince 2024/2/23 15:20
 */
public class AwareInterfaceTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        assert Objects.nonNull(helloService.getApplicationContext());
        assert Objects.nonNull(helloService.getBeanFactory());
    }

}
