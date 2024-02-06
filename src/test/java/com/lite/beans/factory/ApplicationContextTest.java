package com.lite.beans.factory;

import com.lite.beans.context.ApplicationContext;
import com.lite.beans.context.support.ClassPathXmlApplicationContext;
import com.lite.beans.factory.bean.Car;
import com.lite.beans.factory.bean.Person;
import com.lite.beans.factory.support.DefaultListableBeanFactory;
import com.lite.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/6 18:06
 */
public class ApplicationContextTest {

    @Test
    public void testApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        assert "vince".equals(person.getName());

        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
        assert "奔驰".equals(car.getBrand());
    }

}
