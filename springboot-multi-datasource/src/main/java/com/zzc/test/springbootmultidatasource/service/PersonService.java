package com.zzc.test.springbootmultidatasource.service;


import com.zzc.test.springbootmultidatasource.domain.Person;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
public interface PersonService {

    Person getFromPrimary(int id);
    Person getFromSecondary(int id);


    int addPrimaryPerson(Person p);
    int addSecondaryPerson(Person p);


}
