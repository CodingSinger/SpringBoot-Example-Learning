package com.zzc.test.springboothelloworld;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/5/17
 * Email ooczzoo@gmail.com
 */

//@Component 不需要这个也可以
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "CustomProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
