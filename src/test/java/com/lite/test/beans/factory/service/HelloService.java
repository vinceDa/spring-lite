package com.lite.test.beans.factory.service;

import com.lite.beans.BeansException;
import com.lite.beans.factory.BeanFactory;
import com.lite.beans.factory.BeanFactoryAware;
import com.lite.context.ApplicationContext;
import com.lite.context.ApplicationContextAware;

/**
 * @author vince 2024/1/20 21:01
 */
public class HelloService implements ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    public String sayHello() {
        System.out.println("hello world");
        return "hello";
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
