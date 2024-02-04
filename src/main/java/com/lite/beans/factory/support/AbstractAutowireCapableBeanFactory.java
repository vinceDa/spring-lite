package com.lite.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.lite.beans.factory.BeansException;
import com.lite.beans.factory.PropertyValue;
import com.lite.beans.factory.PropertyValues;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanReference;

/**
 * @author vince 2024/1/22 14:30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }

    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = getSingletonBean(beanName);
        if (null == bean) {
            try {
                bean = createBeaInstance(beanDefinition);
                applyPropertyValues(beanName, bean, beanDefinition);
            } catch (Exception e) {
                throw new BeansException("create bean named '" + beanName + "' error");
            }
        }

        addSingletonBean(beanName, bean);
        return bean;
    }

    /**
     * 为bean填充属性
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        try {
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();


                if (value instanceof BeanReference) {
                    // beanA依赖beanB，先实例化beanB
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                //通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("set property value for bean: [" + beanName + "] error", e);
        }
    }

    protected Object createBeaInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    protected InstantiationStrategy getInstantiationStrategy() {
        return simpleInstantiationStrategy;
    }

}
