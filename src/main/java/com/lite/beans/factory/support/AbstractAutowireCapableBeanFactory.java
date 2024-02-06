package com.lite.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.lite.beans.BeansException;
import com.lite.beans.factory.PropertyValue;
import com.lite.beans.factory.PropertyValues;
import com.lite.beans.factory.config.AutowireCapableBeanFactory;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanPostProcessor;
import com.lite.beans.factory.config.BeanReference;

/**
 * @author vince 2024/1/22 14:30
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private final InstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

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

                // 执行 bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
                bean = initializeBean(beanName, bean, beanDefinition);
            } catch (Exception e) {
                throw new BeansException("create bean named '" + beanName + "' error");
            }
        }

        addSingletonBean(beanName, bean);
        return bean;
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 执行 BeanPostProcessor 的前置处理
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(bean, beanName);

        //TODO 后面会在此处执行 bean 的初始化方法
        invokeInitMethods(beanName, bean, beanDefinition);

        wrappedBean = applyBeanPostProcessorAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        System.out.println("invokeInitMethods, beanName: " + beanName);
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


                if (value instanceof BeanReference beanReference) {
                    // beanA依赖beanB，先实例化beanB
                    value = getBean(beanReference.getBeanName());
                }

                //通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("set property value for bean: [" + beanName + "] error", e);
        }
    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorBeforeInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }

            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessorAfterInitialization(existingBean, beanName);
            if (current == null) {
                return result;
            }

            result = current;
        }
        return result;
    }

    protected Object createBeaInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    protected InstantiationStrategy getInstantiationStrategy() {
        return simpleInstantiationStrategy;
    }

}
