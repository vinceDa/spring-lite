package com.lite.beans.factory;

import com.lite.beans.factory.bean.Person;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vince 2024/1/20 20:47
 */
public class PopulateBeanWithPropertyValueTest {

    @Test
    public void testPropertyValue() {
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "vince"));
        propertyValues.addPropertyValue(new PropertyValue("age", 18));
        propertyValues.addPropertyValue(new PropertyValue("sex", "女"));

        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        beanDefinition.setPropertyValues(propertyValues);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("person", beanDefinition);

        Person person = (Person) factory.getBean("person");
        assert person.getName().equals("vince");
        assert person.getAge() == 18;
        assert person.getSex().equals("女");
    }
}
