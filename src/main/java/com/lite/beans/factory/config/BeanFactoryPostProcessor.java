package com.lite.beans.factory.config;

import com.lite.beans.BeansException;
import com.lite.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改 BeanDefinition 的属性值
 * @author vince 2024/2/6 11:48
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
