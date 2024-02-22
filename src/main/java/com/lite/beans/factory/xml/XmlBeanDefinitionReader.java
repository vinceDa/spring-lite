package com.lite.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.lite.beans.BeansException;
import com.lite.beans.factory.support.AbstractBeanDefinitionReader;
import com.lite.beans.factory.PropertyValue;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanReference;
import com.lite.beans.factory.support.BeanDefinitionRegistry;
import com.lite.core.io.Resource;
import com.lite.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * 根据 xml 文件读取 bean 的类
 * @author vince 2024/2/5 11:30
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    private static final String BEAN_ELEMENT = "bean";

    private static final String PROPERTY_ELEMENT = "property";

    private static final String ID_ATTRIBUTE = "id";

    private static final String NAME_ATTRIBUTE = "name";

    private static final String VALUE_ATTRIBUTE = "value";

    private static final String REF_ATTRIBUTE = "ref";

    private static final String CLASS_ATTRIBUTE = "class";

    private static final String INIT_METHOD_ATTRIBUTE = "init-method";

    private static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

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
        } catch (IOException ex) {
            throw new BeansException("IOException parsing XML document from" + resource, ex);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        this.loadBeanDefinitions(this.getResourceLoader().getResource(location));
    }

    /**
     * 解析 xml，需要了解 document 相关的用法
     * @param inputStream
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (!(node instanceof Element bean)) {
                continue;
            }

            if (!BEAN_ELEMENT.equals(node.getNodeName())) {
                continue;
            }

            // 解析 bean 标签
            String id = bean.getAttribute(ID_ATTRIBUTE);
            String className = bean.getAttribute(CLASS_ATTRIBUTE);
            String name = bean.getAttribute(NAME_ATTRIBUTE);

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
            beanDefinition.setInitMethodName(bean.getAttribute(INIT_METHOD_ATTRIBUTE));
            beanDefinition.setDestroyMethodName(bean.getAttribute(DESTROY_METHOD_ATTRIBUTE));

            // 解析 bean 的结构体获取 property
            NodeList beansChild = bean.getChildNodes();
            for (int j = 0; j < beansChild.getLength(); j++) {
                Node item = beansChild.item(j);
                if (!(item instanceof Element property)) {
                    continue;
                }

                if (!PROPERTY_ELEMENT.equals(item.getNodeName())) {
                    continue;
                }

                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                String refAttribute = property.getAttribute(REF_ATTRIBUTE);

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

}
