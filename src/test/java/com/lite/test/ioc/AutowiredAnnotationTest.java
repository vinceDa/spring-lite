package com.lite.test.ioc;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.bean.Person;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author vince 2024/3/1 10:41
 */
public class AutowiredAnnotationTest {

    @Test
    public void testAutowiredAnnotation() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowired-annotation.xml");

        Person person = applicationContext.getBean("person", Person.class);
        assert Objects.nonNull(person);
        assert Objects.nonNull(person.getCar());
        assert "lamborghini".equals(person.getCar().getBrand());
    }

}
