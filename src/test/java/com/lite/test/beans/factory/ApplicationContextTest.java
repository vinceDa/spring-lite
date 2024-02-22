package com.lite.test.beans.factory;

import com.lite.test.beans.factory.bean.Car;
import com.lite.test.beans.factory.bean.Person;
import com.lite.context.support.ClassPathXmlApplicationContext;
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
