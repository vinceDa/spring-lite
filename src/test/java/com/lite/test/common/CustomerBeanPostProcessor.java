package com.lite.test.common;

import com.lite.beans.BeansException;
import com.lite.test.bean.Car;
import com.lite.beans.factory.config.BeanPostProcessor;

/**
 * @author vince 2024/2/6 12:07
 */
public class CustomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessorBeforeInitialization beanName: " + beanName);
        if ("car".equals(beanName)) {
            ((Car)bean).setBrand("宝马");
        }
        return bean;
    }

    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessorAfterInitialization beanName: " + beanName);
        return bean;
    }
}
