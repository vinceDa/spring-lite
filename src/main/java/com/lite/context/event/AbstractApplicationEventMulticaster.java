package com.lite.context.event;

import com.lite.beans.factory.BeanFactory;
import com.lite.beans.factory.BeanFactoryAware;
import com.lite.context.ApplicationEvent;
import com.lite.context.ApplicationListener;

import java.util.HashSet;
import java.util.Set;

/**
 * @author vince 2024/2/23 17:33
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new HashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        applicationListeners.remove(applicationListener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
