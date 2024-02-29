package com.lite.test.ioc;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.bean.Car;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/29 17:19
 */
public class ValueAnnotationTest {

    @Test
    public void testValueAnnotation() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");

        Car car = applicationContext.getBean("car", Car.class);
        assert "lamborghini".equals(car.getBrand());
    }

}
