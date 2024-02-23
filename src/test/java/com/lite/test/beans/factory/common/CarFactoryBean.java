package com.lite.test.beans.factory.common;

import com.lite.beans.factory.FactoryBean;
import com.lite.test.beans.factory.bean.Car;

/**
 * @author vince 2024/2/23 16:53
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String brand;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
