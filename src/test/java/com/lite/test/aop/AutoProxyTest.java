package com.lite.test.aop;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.service.WorldService;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/27 11:48
 */
public class AutoProxyTest {

    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        // 获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
    }

    @Test
    public void testPopulateProxyBeanWithPropertyValues() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:populate-proxy-bean-with-property-values.xml");
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();

        assert "earth".equals(worldService.getName());
    }

}
