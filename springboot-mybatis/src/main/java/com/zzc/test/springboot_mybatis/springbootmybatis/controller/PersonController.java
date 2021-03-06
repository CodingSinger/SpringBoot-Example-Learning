package com.zzc.test.springboot_mybatis.springbootmybatis.controller;

import com.zzc.test.springboot_mybatis.springbootmybatis.domain.Person;
import com.zzc.test.springboot_mybatis.springbootmybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhengzechao
 * @date 2018/3/14
 */

@RestController
public class PersonController {

    @Autowired
    private PersonService service;



    @RequestMapping(value = "/person",method = RequestMethod.GET)
    public Person addPerson(Object h,int age, Person p){
        System.out.println(p);

        return p;
    }



    @RequestMapping(value = "/person/{id}",method = RequestMethod.GET)
    public Person fperson(@PathVariable(value = "id") Integer id,String h){
        System.out.println((String)h); //会报错
        return service.findById(id);
    }

}
