package com.lite.beans.factory;

import com.lite.beans.BeansException;

import java.util.Map;

/**
 * @author vince 2024/2/6 11:50
 */
public interface ListableBeanFactory {

    /**
     * 返回指定类型的所有示例实例
     * @param type
     * @return
     * @param <T>
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回定义的所有 bean 的名称
     * @return
     */
    String[] getBeanDefinitionNames();

}
