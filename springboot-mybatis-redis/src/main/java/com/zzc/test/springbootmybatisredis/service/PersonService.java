package com.zzc.test.springbootmybatisredis.service;


import com.zzc.test.springbootmybatisredis.domain.Person;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
public interface PersonService {




    int addPerson(Person p);


    Person findById(Integer id);


    int updatePerson(Person person) throws Exception;

}
