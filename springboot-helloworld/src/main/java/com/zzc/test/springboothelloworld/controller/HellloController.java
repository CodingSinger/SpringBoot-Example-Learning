package com.zzc.test.springboothelloworld.controller;

import com.zzc.test.springboothelloworld.domain.Person;
import com.zzc.test.springboothelloworld.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
@RestController
@RequestMapping("/user")
public class HellloController {



    @Autowired
    private PersonService service;


    @Value(value = "${application.name}")
    private String name;


    public HellloController() {
        System.out.println("appplication-"+name+" start "+service);
    }
    @RequestMapping("/person/{id}")
    public Person fperson(@PathVariable(value = "id") Integer id){
        System.out.println(name);
        return service.findById(id);
    }



    @GetMapping(value = "/yml")
    public String getPort(@Value("${port}")  Integer port,@Value("${UT}")  String name,@Value("${DESC}") String desc){
        System.out.println(desc );
        return String.valueOf(port)+"name:"+name;
    }

    @RequestMapping("/hello/{id}")
    public String sayHello(@PathVariable int id){
        return "actor"+id+":Hello!";
    }




}
