package com.zzc.test.elasticsearch.service;

import com.zzc.test.elasticsearch.domain.Person;

import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/5/1
 * Email ooczzoo@gmail.com
 */
public interface PersonService {
    boolean addPerson(Person p);
    List<Person> findPerson(Integer pageNum, Integer pageSize, String key);
}
