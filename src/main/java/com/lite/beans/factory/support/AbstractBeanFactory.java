package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.FactoryBean;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanPostProcessor;
import com.lite.beans.factory.config.ConfigurableBeanFactory;

import java.util.*;

/**
 * @author vince 2024/1/22 14:20
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = getSingletonBean(beanName);
        if (null != bean) {
            return getObjectForBeanInstance(bean, beanName);
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object = beanInstance;
        try {
            if (beanInstance instanceof FactoryBean) {
                FactoryBean factoryBean = (FactoryBean) beanInstance;
                if (factoryBean.isSingleton()) {
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (Objects.isNull(object)) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    }
                } else {
                    object = factoryBean.getObject();
                }
            }
        } catch (Exception e) {
            throw new BeansException("FactoryBean throw exception on object[" + beanName + "] creation", e);
        }

        return object;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        // 有则覆盖
        beanPostProcessors.remove(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
