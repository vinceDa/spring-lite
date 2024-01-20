package com.lite.beans.factory.support;

import com.lite.beans.factory.config.BeanDefinition;

/**
 *  BeanDefinition 的注册表接口，定义注册 BeanDefinition 的方法
 * @author vince 2024/1/20 18:48
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanClass);

}
