package com.zzc.test.springbootmultidatasource.controller;

import com.zzc.test.springbootmultidatasource.domain.Person;
import com.zzc.test.springbootmultidatasource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengzechao
 * @date 2018/3/14
 */

@RestController
public class PersonController {

    @Autowired
    private PersonService service;



    @RequestMapping(value = "/person/{person}",method = RequestMethod.GET)
    public Person test(@PathVariable(value = "person") int i ){

        System.out.println(service.getFromPrimary(i));
        System.out.println(service.getFromSecondary(i));
        return null;
    }


    @RequestMapping(value = "/primary",method = RequestMethod.GET)
    public Person testTransaction(Person person){

        try {
            System.out.println(service.addPrimaryPerson(person));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/secondary",method = RequestMethod.GET)
    public Person testTransaction2(Person person){

        try {
            System.out.println(service.addSecondaryPerson(person));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
