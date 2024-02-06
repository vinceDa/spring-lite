package com.lite.beans.factory.config;

import com.lite.beans.BeansException;
import com.lite.beans.factory.BeanFactory;

/**
 * 自动装配功能的 bean 工厂
 *
 * @author vince 2024/2/6 10:22
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 的 postProcessAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
