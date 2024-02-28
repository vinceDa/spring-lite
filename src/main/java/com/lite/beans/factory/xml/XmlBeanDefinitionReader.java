package com.lite.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.lite.beans.BeansException;
import com.lite.beans.factory.PropertyValue;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanReference;
import com.lite.beans.factory.support.AbstractBeanDefinitionReader;
import com.lite.beans.factory.support.BeanDefinitionRegistry;
import com.lite.context.annotation.ClassPathBeanDefinitionScanner;
import com.lite.core.io.Resource;
import com.lite.core.io.ResourceLoader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * 根据 xml 文件读取 bean 的类
 *
 * @author vince 2024/2/5 11:30
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private static final String BEAN_ELEMENT = "bean";

    private static final String PROPERTY_ELEMENT = "property";

    private static final String COMPONENT_SCAN_ELEMENT = "component-scan";

    private static final String ID_ATTRIBUTE = "id";

    private static final String NAME_ATTRIBUTE = "name";

    private static final String VALUE_ATTRIBUTE = "value";

    private static final String SCOPE_ATTRIBUTE = "scope";

    private static final String REF_ATTRIBUTE = "ref";

    private static final String CLASS_ATTRIBUTE = "class";

    private static final String INIT_METHOD_ATTRIBUTE = "init-method";

    private static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try (InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | DocumentException ex) {
            throw new BeansException("IOException parsing XML document from" + resource, ex);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        this.loadBeanDefinitions(this.getResourceLoader().getResource(location));
    }

    /**
     * 解析 xml，需要了解 document 相关的用法
     *
     * @param inputStream
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();

        Element componentScan = root.element(COMPONENT_SCAN_ELEMENT);
        if (Objects.nonNull(componentScan)) {
            String basePackage = componentScan.attributeValue(BASE_PACKAGE_ATTRIBUTE);
            if (StrUtil.isEmpty(basePackage)) {
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(basePackage);
        }

        List<Element> beans = root.elements(BEAN_ELEMENT);
        for (Element bean : beans) {
            // 解析 bean 标签
            String id = bean.attributeValue(ID_ATTRIBUTE);
            String className = bean.attributeValue(CLASS_ATTRIBUTE);
            String name = bean.attributeValue(NAME_ATTRIBUTE);
            String scope = bean.attributeValue(SCOPE_ATTRIBUTE);

            Class<?> clazz;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                throw new BeansException("Cannot find class [" + className + "]");
            }

            // 选择 BeanMap 中的 key，优先级为 id->name->类名的第一个字母小写后作为 bean 的名称
            String beanName = StrUtil.isNotBlank(id) ? id : name;
            if (StrUtil.isBlank(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);

            // 获取初始化方法和销毁方法
            beanDefinition.setInitMethodName(bean.attributeValue(INIT_METHOD_ATTRIBUTE));
            beanDefinition.setDestroyMethodName(bean.attributeValue(DESTROY_METHOD_ATTRIBUTE));
            if (StrUtil.isNotEmpty(scope)) {
                beanDefinition.setScope(scope);
            }

            // 解析 bean 的结构体获取 property
            List<Element> properties = bean.elements(PROPERTY_ELEMENT);
            for (Element property : properties) {
                String nameAttribute = property.attributeValue(NAME_ATTRIBUTE);
                String valueAttribute = property.attributeValue(VALUE_ATTRIBUTE);
                String refAttribute = property.attributeValue(REF_ATTRIBUTE);

                if (StrUtil.isBlank(nameAttribute)) {
                    throw new BeansException("The name attribute cannot be null or empty");
                }

                Object value = valueAttribute;
                if (StrUtil.isNotBlank(refAttribute)) {
                    value = new BeanReference(refAttribute);
                }

                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }

            // bean不能重名
            if (this.getRegistry().containsBeanDefinition(beanName)) {
                throw new BeansException("Duplicate beanName[ + " + beanName + "] is not allowed");
            }

            // 注册 bean
            this.getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }

    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.split(scanPath, ",");
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}
