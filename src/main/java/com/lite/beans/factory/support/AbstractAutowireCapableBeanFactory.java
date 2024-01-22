package com.lite.beans.factory.support;

import com.lite.beans.factory.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/1/22 14:30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = getSingletonBean(beanName);
        if (null == bean) {
            try {
                Class beanClass = beanDefinition.getBeanClass();
                bean = beanClass.newInstance();
            } catch (Exception e) {
                throw new BeansException("create bean named '" + beanName + "' error");
            }
        }
        addSingletonBean(beanName, bean);
        return bean;
    }

}
