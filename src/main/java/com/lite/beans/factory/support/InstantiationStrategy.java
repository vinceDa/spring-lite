package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.BeanDefinition;

/**
 * @author vince 2024/1/22 15:26
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition) throws BeansException;

}
