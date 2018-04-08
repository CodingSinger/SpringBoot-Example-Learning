package com.zzc.test.springbootmybatisredis.controller;

import com.zzc.test.springbootmybatisredis.domain.Person;
import com.zzc.test.springbootmybatisredis.service.PersonService;
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


    /**
     * PUT方法不能以form-data方式传参
     * @param p
     * @return
     */
    @RequestMapping(value = "/person",method = RequestMethod.PUT)
    public int update(Person p){
        try {
            return service.updatePerson(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @RequestMapping(value = "/person/{id}",method = RequestMethod.GET)
    public Person fperson(@PathVariable(value = "id") Integer id){
        return service.findById(id);
    }

}
