package com.lite.test.ioc;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.core.convert.ConversionService;
import com.lite.test.bean.Car;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * @author vince 2024/3/5 17:55
 */
public class TypeConversionSecondPartTest {

    @Test
    public void testConversionService() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:type-conversion-second-part.xml");

        Car car = applicationContext.getBean("car", Car.class);
        assert car.getPrice() == 1000000;
        assert car.getProduceDate().equals(LocalDate.of(2021, 1, 1));
    }

}
