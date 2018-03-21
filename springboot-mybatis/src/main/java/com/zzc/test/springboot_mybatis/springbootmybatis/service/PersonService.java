package com.zzc.test.springboot_mybatis.springbootmybatis.service;


import com.zzc.test.springboot_mybatis.springbootmybatis.domain.Person;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
public interface PersonService {



    public int addPerson(Person p);


    public Person findById(Integer id);

}
