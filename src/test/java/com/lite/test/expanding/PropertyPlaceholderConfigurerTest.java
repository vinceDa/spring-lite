package com.lite.test.expanding;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.bean.Car;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/28 10:10
 */
public class PropertyPlaceholderConfigurerTest {

    @Test
    public void testPropertyPlaceholderConfigurer() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");

        Car car = applicationContext.getBean("car", Car.class);
        assert "lamborghini".equals(car.getBrand());
    }

}
