package com.lite.beans.factory.config;

import com.lite.beans.BeansException;

/**
 * @author vince 2024/1/21 08:39
 */
public interface SingletonBeanRegistry {

    Object getSingletonBean(String beanName) throws BeansException;

}
