package com.zzc.test.springboothelloworld.service.Impl;

import com.zzc.test.springboothelloworld.Dao.PersonDao;
import com.zzc.test.springboothelloworld.domain.Person;
import com.zzc.test.springboothelloworld.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
@Service
public class PersonServiceImpl implements PersonService{
    public PersonServiceImpl() {
    }

    @Autowired
    private PersonDao dao;


    @Override
    public Person findById(Integer id) {
        return dao.queryById(id);
    }
}
