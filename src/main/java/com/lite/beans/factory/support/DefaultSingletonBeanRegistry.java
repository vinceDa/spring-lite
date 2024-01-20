package com.lite.beans.factory.support;

import com.lite.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认 bean 注册表实现类
 * @author vince 2024/1/20 20:23
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonBeanMap.get(beanName);
    }

    protected void addBean(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
    }

}
