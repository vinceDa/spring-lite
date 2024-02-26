package com.lite.test.ioc;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.bean.Car;
import org.junit.jupiter.api.Test;

/**
 * @author vince 2024/2/23 15:54
 */
public class PrototypeBeanTest {

    @Test
    public void testPrototypeBean() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = applicationContext.getBean("car", Car.class);
        Car car2 = applicationContext.getBean("car", Car.class);

        assert car1 != car2;
    }

}
