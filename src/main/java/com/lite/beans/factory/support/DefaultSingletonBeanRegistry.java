package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vince 2024/1/21 08:40
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) throws BeansException {
        return singletonBeanMap.get(beanName);
    }

    protected void addSingletonBean(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
    }

}
