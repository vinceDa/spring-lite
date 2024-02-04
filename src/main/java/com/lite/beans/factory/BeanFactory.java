package com.lite.beans.factory;

import com.lite.beans.BeansException;

/**
 * @author vince 2024/1/22 14:15
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

}
