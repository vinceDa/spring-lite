package com.lite.beans.factory;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.AutowireCapableBeanFactory;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanPostProcessor;
import com.lite.beans.factory.config.ConfigurableBeanFactory;

/**
 * 可配置的，可罗列的 bean 工厂
 *
 * @author vince 2024/2/6 11:49
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据名称查找 BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 提前实例化所有单例实例
     * @throws BeansException
     */
    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
