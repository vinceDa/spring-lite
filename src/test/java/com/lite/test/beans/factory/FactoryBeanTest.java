package com.lite.test.beans.factory;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.beans.factory.bean.Car;
import com.lite.test.beans.factory.common.CarFactoryBean;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/23 17:05
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        assert "奔驰".equals(car.getBrand());

    }

}
