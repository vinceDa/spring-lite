package com.lite.test.ioc;

import com.lite.context.support.ClassPathXmlApplicationContext;
import com.lite.test.bean.Car;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * @author vince 2024/2/28 10:50
 */
public class PackageScanTest {

    @Test
    public void testPackageScan() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");

        Car car = applicationContext.getBean("car", Car.class);
        assert Objects.nonNull(car);
    }

}
