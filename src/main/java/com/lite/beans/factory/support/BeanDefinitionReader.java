package com.lite.beans.factory.support;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.core.io.Resource;
import com.lite.core.io.ResourceLoader;

/**
 * bean 的配置信息读取抽象接口
 * @author vince 2024/2/5 11:21
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws BeansException;

}
