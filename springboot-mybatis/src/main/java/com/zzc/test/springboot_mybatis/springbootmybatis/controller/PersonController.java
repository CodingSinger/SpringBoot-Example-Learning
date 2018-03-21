package com.zzc.test.springboot_mybatis.springbootmybatis.controller;

import com.zzc.test.springboot_mybatis.springbootmybatis.domain.Person;
import com.zzc.test.springboot_mybatis.springbootmybatis.service.PersonService;
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



    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public int addPerson(Person p){
        int state = service.addPerson(p);
        return state;
    }



    @RequestMapping(value = "/person/{id}",method = RequestMethod.GET)
    public Person fperson(@PathVariable(value = "id") Integer id){
        return service.findById(id);
    }

}
