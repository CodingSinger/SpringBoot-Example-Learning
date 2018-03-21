package com.zzc.test.springboot_mybatis.springbootmybatis.service.Impl;

import com.zzc.test.springboot_mybatis.springbootmybatis.Dao.PersonDao;
import com.zzc.test.springboot_mybatis.springbootmybatis.domain.Person;
import com.zzc.test.springboot_mybatis.springbootmybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
@Service
public class PersonServiceImpl implements PersonService {


    @Autowired
    private PersonDao dao;


    @Transactional
    @Override
    public int addPerson(Person p) {

        int state = dao.add(p);
        if (p.getName().equals("he")){
            int i = 1/0;
        }
        return state;


    }


    @Override
    public Person findById(Integer id) {

        return dao.queryById(id);
    }
}
