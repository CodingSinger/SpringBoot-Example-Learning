package com.zzc.test.springboothelloworld.domain;

/**
 * @author zhengzechao
 * @date 2018/7/4
 * Email ooczzoo@gmail.com
 */
public class Car {
    private double price;

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                '}';
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car(double price) {
        this.price = price;
    }
}
