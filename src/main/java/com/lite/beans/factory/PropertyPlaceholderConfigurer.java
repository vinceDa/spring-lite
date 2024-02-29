package com.lite.beans.factory;

import com.lite.beans.BeansException;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanFactoryPostProcessor;
import com.lite.core.io.DefaultResourceLoader;
import com.lite.core.io.Resource;
import com.lite.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author vince 2024/2/28 09:48
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    private static final String PLACEHOLDER_PREFIX = "${";

    private static final String PLACEHOLDER_SUFFIX = "}";

    private String location;


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性配置文件
        Properties properties = loadProperties();
        // 属性值替换占位符
        processProperties(beanFactory, properties);
        // 往容器添加字符解析器，供解析 @Value 注解使用
        PlaceholderResolvingStringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
        beanFactory.addEmbeddedValueResolver(valueResolver);
    }

    private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) {
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            resolvePropertyValues(beanDefinition, properties);
        }
    }

    private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof String strVal) {
                // 仅简单支持一个占位符的格式
                StringBuilder sb = new StringBuilder(strVal);
                int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX);
                int endIndex = strVal.indexOf(PLACEHOLDER_SUFFIX);
                if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                    // 获取${xx}中的 xx
                    String propKey = strVal.substring(startIndex + 2, endIndex);
                    String propVal = properties.getProperty(propKey);
                    sb.replace(startIndex, endIndex + 1, propVal);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), sb.toString()));
                }
            }
        }
    }

    private Properties loadProperties() {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return properties;
        } catch (IOException e) {
            throw new BeansException("Could not load Properties, location: " + location, e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String resolvePlaceholder(String value, Properties properties) {
        //todo 仅简单支持一个占位符的格式
        StringBuilder sb = new StringBuilder(value);
        int startIndex = value.indexOf(PLACEHOLDER_PREFIX);
        int endIndex = value.indexOf(PLACEHOLDER_SUFFIX);
        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            String propKey = value.substring(startIndex + 2, endIndex);
            String propVal = properties.getProperty(propKey);
            sb.replace(startIndex, endIndex + 1, propVal);
        }
        return sb.toString();
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
        }
    }
}
