package com.zzc.test.springbootmultidatasource.service.Impl;

import com.zzc.test.springbootmultidatasource.dao.primary.PrimaryMapper;
import com.zzc.test.springbootmultidatasource.dao.secondary.SecondaryMapper;
import com.zzc.test.springbootmultidatasource.domain.Person;
import com.zzc.test.springbootmultidatasource.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
@Service
public class PersonServiceImpl implements PersonService {


    /**
     *
     */
    @Autowired
    private PrimaryMapper primaryMapper;
    @Autowired
    private SecondaryMapper secondaryMapper;


    @Override
    public Person getFromPrimary(int id) {
        return primaryMapper.queryById(id);
    }

    @Override
    public Person getFromSecondary(int id) {
        return secondaryMapper.queryById(id);
    }
    @Transactional(rollbackFor = Exception.class,transactionManager = "primaryTransactionManager")
    @Override
    public int addPrimaryPerson(Person p) {
        int status = primaryMapper.add(p);
        System.out.println("add primary");
        int i = 1/0;
        return status;
    }

    @Transactional(rollbackFor = Exception.class,transactionManager = "secondaryTransactionManager")
    @Override
    public int addSecondaryPerson(Person p) {
        int status = secondaryMapper.add(p);
        System.out.println("add secondary");
        int i = 1/0;
        return status;
    }
}
