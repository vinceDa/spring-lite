package com.lite.test.bean;

import com.lite.beans.factory.annotation.Value;
import com.lite.stereotype.Component;

import java.time.LocalDate;

/**
 * @author vince 2024/2/4 17:31
 */
@Component
public class Car {

    @Value("${brand}")
    private String brand;

    private String color;

    private int price;

    private LocalDate produceDate;

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

    public int getPrice() {
        return price;
    }

    public LocalDate getProduceDate() {
        return produceDate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
