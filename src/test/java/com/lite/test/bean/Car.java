package com.lite.test.bean;

import com.lite.beans.factory.annotation.Value;
import com.lite.stereotype.Component;

/**
 * @author vince 2024/2/4 17:31
 */
@Component
public class Car {

    @Value("${brand}")
    private String brand;

    private String color;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
