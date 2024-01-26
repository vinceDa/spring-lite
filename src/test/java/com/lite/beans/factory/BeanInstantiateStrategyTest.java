package com.lite.beans.factory;

import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/1/20 20:47
 */
public class BeanInstantiateStrategyTest {

    @Test
    public void testBeanFactory() {
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("hello", beanDefinition);

        HelloService helloService = (HelloService) factory.getBean("hello");
        helloService.sayHello();
    }
}
