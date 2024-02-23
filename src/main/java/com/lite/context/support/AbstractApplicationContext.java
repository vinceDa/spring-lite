package com.lite.context.support;

import com.lite.beans.BeansException;
import com.lite.context.ApplicationEvent;
import com.lite.context.ApplicationListener;
import com.lite.context.ConfigurableApplicationContext;
import com.lite.beans.factory.ConfigurableListableBeanFactory;
import com.lite.beans.factory.config.BeanFactoryPostProcessor;
import com.lite.beans.factory.config.BeanPostProcessor;
import com.lite.context.event.ApplicationEventMulticaster;
import com.lite.context.event.ContextCloseEvent;
import com.lite.context.event.ContextRefreshEvent;
import com.lite.context.event.SimpleApplicationEventMulticaster;
import com.lite.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author vince 2024/2/6 16:51
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 创建 BeanFactory 并加载 BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 bean 能感知 bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 在 bean 实例化前执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // BeanPostProcessor 需要在其他 bean 实例化前注册
        registerBeanPostProcessors(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        // 注册事件监听器
        registerApplicationListener();

        // 提前实例化单例 bean
        beanFactory.preInstantiateSingletons();

        // 发布容器刷新完成事件
        finishRefresh();
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

    /**
     * 初始化事件发布者
     */
    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册事件监听器
     */
    protected void registerApplicationListener() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    protected void finishRefresh() {
        publishEvent(new ContextRefreshEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return this.getBeanFactory().getBean(beanName, requiredType);
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

    public void close() {
        close();
    }

    @Override
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread(this::doClose);
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    protected void doClose() {
        publishEvent(new ContextCloseEvent(this));

        destroyBeans();
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }
}
