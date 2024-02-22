package com.lite.test.beans.factory;

import com.lite.beans.factory.xml.XmlBeanDefinitionReader;
import com.lite.test.beans.factory.bean.Person;
import com.lite.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/5 12:12
 */
public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assert "vince".equals(person.getName());
        assert "奔驰".equals(person.getCar().getBrand());
    }

}
