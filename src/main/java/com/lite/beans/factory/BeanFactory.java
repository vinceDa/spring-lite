package com.lite.beans.factory;

import com.lite.beans.BeansException;

/**
 * @author vince 2024/1/22 14:15
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    /**
     * 根据名称和类型查找 bean
     * @param beanName
     * @param requiredType
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> T getBean(String beanName, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String name);

}
