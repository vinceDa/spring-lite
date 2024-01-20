package com.lite.beans.factory.config;

/**
 * @author vince 2024/1/20 19:03
 */
public interface SingletonBeanRegistry {

    Object getSingletonBean(String beanName);

}
