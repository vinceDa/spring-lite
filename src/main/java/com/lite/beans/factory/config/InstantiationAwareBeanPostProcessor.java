package com.lite.beans.factory.config;

import com.lite.beans.BeansException;
import com.lite.beans.factory.PropertyValue;
import com.lite.beans.factory.PropertyValues;

/**
 * @author vince 2024/2/27 10:37
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 在 bean 实例化之前执行
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;
}
