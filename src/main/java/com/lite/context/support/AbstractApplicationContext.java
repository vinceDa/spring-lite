package com.lite.context.support;

import com.lite.beans.BeansException;
import com.lite.context.ConfigurableApplicationContext;
import com.lite.beans.factory.ConfigurableListableBeanFactory;
import com.lite.beans.factory.config.BeanFactoryPostProcessor;
import com.lite.beans.factory.config.BeanPostProcessor;
import com.lite.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author vince 2024/2/6 16:51
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory 并加载 BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 在 bean 实例化前执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        // BeanPostProcessor 需要在其他 bean 实例化前注册
        registerBeanPostProcessors(beanFactory);
        // 提前实例化单例 bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 在 bean 实例化之前执行 BeanFactoryPostProcessor
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorOfTypeMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorOfTypeMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorOfType = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorOfType.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return this.getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.getBeanFactory().getBeanDefinitionNames();
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();
}
