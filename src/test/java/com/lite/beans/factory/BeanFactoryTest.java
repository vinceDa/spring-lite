package com.lite.beans.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanFactoryTest {

    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        beanFactory = new BeanFactory();
    }

    @Test
    void registerBeanAndRetrieveIt() {
        String beanName = "helloService";
        HelloService bean = new HelloService();

        beanFactory.registerBean(beanName, bean);

        Object retrievedBean = beanFactory.getBean(beanName);
        assertNotNull(retrievedBean);
        assertEquals("hello", ((HelloService) retrievedBean).sayHelloWorld());
    }

    class HelloService {
        public String sayHelloWorld() {
            System.out.println("Hello World!");
            return "hello";
        }
    }
}