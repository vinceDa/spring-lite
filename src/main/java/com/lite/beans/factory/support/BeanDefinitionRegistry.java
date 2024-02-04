package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/1/22 14:16
 */
public interface BeanDefinitionRegistry {

    BeanDefinition registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
