package com.zzc.test.elasticsearch.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @author zhengzechao
 * @date 2018/5/1
 * Email ooczzoo@gmail.com
 */
@Document(indexName = "province", type = "person")
public class Person implements Serializable{
    private String name;
    private String profession;
    private Integer id;

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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
