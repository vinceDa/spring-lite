package com.lite.beans.factory.support;

import com.lite.beans.factory.BeanFactory;
import com.lite.beans.factory.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/1/20 20:29
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingletonBean(name);
        if (bean != null) {
            return bean;
        }

        return createBean(name, getBeanDefinition(name));
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
