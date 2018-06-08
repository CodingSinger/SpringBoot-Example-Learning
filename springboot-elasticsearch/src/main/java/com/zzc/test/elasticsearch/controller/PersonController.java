package com.zzc.test.elasticsearch.controller;

import com.zzc.test.elasticsearch.domain.Person;
import com.zzc.test.elasticsearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/5/1
 * Email ooczzoo@gmail.com
 */
@RestController
public class PersonController {


    @Autowired
    private PersonService personService;

    @PostMapping("/person")
    public boolean addPerson(Person p){
        return personService.addPerson(p);
    }

    @GetMapping("/person")
    public List<Person> getPersons(String name){
        return personService.findPerson(0,0,name);
    }
}
