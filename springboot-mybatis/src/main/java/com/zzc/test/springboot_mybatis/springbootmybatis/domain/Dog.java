package com.zzc.test.springboot_mybatis.springbootmybatis.domain;

/**
 * @author zhengzechao
 * @date 2018/4/24
 * Email ooczzoo@gmail.com
 */
public class Dog {
    private Integer price;

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public Dog() {
        System.out.println(this);
    }
}
