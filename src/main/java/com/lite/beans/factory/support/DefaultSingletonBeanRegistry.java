package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.DisposableBean;
import com.lite.beans.factory.ObjectFactory;
import com.lite.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author vince 2024/1/21 08:40
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonBeanMap = new HashMap<>();

    private final Map<String, Object> earlySingletonBeanMap = new HashMap<>();

    private final Map<String, ObjectFactory<?>> singletonFactoryBeanMap = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) throws BeansException {
        Object bean = singletonBeanMap.get(beanName);
        if (Objects.isNull(bean)) {
            bean = earlySingletonBeanMap.get(beanName);
            if (Objects.isNull(bean)) {
                ObjectFactory<?> objectFactory = singletonFactoryBeanMap.get(beanName);
                if (Objects.nonNull(objectFactory)) {
                    bean = objectFactory.getObject();
                    earlySingletonBeanMap.put(beanName, bean);
                    singletonFactoryBeanMap.remove(beanName);
                }

            }
        }
        return bean;
    }

    @Override
    public void addSingleton(String beanName, Object singletonObject) {
        singletonBeanMap.put(beanName, singletonObject);
        earlySingletonBeanMap.remove(beanName);
        singletonFactoryBeanMap.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singleFactory) {
        singletonFactoryBeanMap.put(beanName, singleFactory);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> beanNames = disposableBeanMap.keySet();
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' throw an exception", e);
            }
        }
    }

}
