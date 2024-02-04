package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * 初始化 bean 的简单实现，适用于无参构造方式
 *
 * @author vince 2024/1/26 15:30
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();

        try {
            return beanClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
}
