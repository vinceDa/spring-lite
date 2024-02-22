package com.lite.test.beans.factory.common;

import com.lite.beans.BeansException;
import com.lite.beans.factory.ConfigurableListableBeanFactory;
import com.lite.beans.factory.config.BeanFactoryPostProcessor;
import com.lite.beans.factory.PropertyValue;
import com.lite.beans.factory.PropertyValues;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/2/6 12:06
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personBeanDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefinition.getPropertyValues();
        // 将 person 的 name 属性改为 changeName
        propertyValues.addPropertyValue(new PropertyValue("name", "ohyoung"));
    }
}
