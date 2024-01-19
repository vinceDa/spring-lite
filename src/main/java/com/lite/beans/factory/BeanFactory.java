package com.lite.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * bean 容器
 * @author vince 2024/1/18 16:34
 */
public class BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();

    public void registerBean(String name, Object bean) {
        beanMap.put(name, bean);
    }

    public Object getBean(String name) {
        return beanMap.get(name);
    }

}
