package com.lite.beans.factory.support;

import com.lite.beans.factory.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/1/22 14:15
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

}
