package com.zzc.test.springbootmybatisredis.domain;

import java.io.Serializable;

/**
 * @author zhengzechao
 * @date 2018/3/14
 */
public class Person implements Serializable {
    private Integer id;
    private String name;

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
