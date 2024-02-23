package com.lite.beans.factory;

import com.lite.beans.BeansException;

/**
 * 实现该接口能感知 BeanFactory
 * @author vince 2024/2/23 14:49
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
