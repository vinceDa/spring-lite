package com.lite.beans.factory.support;

import com.lite.beans.factory.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/1/20 20:37
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeansException("bean init failed", e);
        }

        addBean(beanName, bean);
        return bean;
    }
}
