package com.lite.beans.factory.config;

import com.lite.beans.BeansException;

/**
 * 用于修改实例化后 bean 的修改扩展点
 * @author vince 2024/2/6 10:29
 */
public interface BeanPostProcessor {

    /**
     * 在 bean 执行初始化操作前执行该方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;


    /**
     * 在 bean 执行初始化操作后执行该方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
