package com.lite.beans.factory.config;

import com.lite.beans.factory.HierarchicalBeanFactory;

/**
 * @author vince 2024/2/6 10:29
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
