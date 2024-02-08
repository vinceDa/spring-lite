package com.lite.context.support;

import com.lite.beans.factory.support.DefaultListableBeanFactory;
import com.lite.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author vince 2024/2/6 17:42
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
