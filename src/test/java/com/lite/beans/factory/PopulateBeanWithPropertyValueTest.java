package com.lite.beans.factory;

import com.lite.beans.factory.bean.Car;
import com.lite.beans.factory.bean.Person;
import com.lite.beans.factory.config.BeanDefinition;
import com.lite.beans.factory.config.BeanReference;
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
        PropertyValues carPropertyValues = new PropertyValues();
        carPropertyValues.addPropertyValue(new PropertyValue("brand", "奔驰"));
        carPropertyValues.addPropertyValue(new PropertyValue("color", "黑"));

        BeanDefinition carDefinition = new BeanDefinition(Car.class);
        carDefinition.setPropertyValues(carPropertyValues);
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car", carDefinition);

        PropertyValues personPropertyValues = new PropertyValues();
        personPropertyValues.addPropertyValue(new PropertyValue("name", "vince"));
        personPropertyValues.addPropertyValue(new PropertyValue("age", 18));
        personPropertyValues.addPropertyValue(new PropertyValue("sex", "女"));
        personPropertyValues.addPropertyValue(new PropertyValue("car", new BeanReference("car")));

        BeanDefinition personDefinition = new BeanDefinition(Person.class);
        personDefinition.setPropertyValues(personPropertyValues);
        factory.registerBeanDefinition("person", personDefinition);

        Person person = (Person) factory.getBean("person");
        assert person.getName().equals("vince");
        assert person.getAge() == 18;
        assert person.getSex().equals("女");
        assert person.getCar().getBrand().equals("奔驰");
        assert person.getCar().getColor().equals("黑");
    }
}
