package com.zzc.test.springbootmybatisredis.service.Impl;

import com.zzc.test.springbootmybatisredis.Dao.PersonDao;
import com.zzc.test.springbootmybatisredis.domain.Person;
import com.zzc.test.springbootmybatisredis.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    @Autowired
    private PersonDao dao;
//    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;


    @Transactional
    @Override
    public int addPerson(Person p) {

        int state = dao.add(p);
        if (p.getName().equals("he")) {
            int i = 1 / 0;
        }
        return state;


    }


    /**
     * 先查缓存 缓存没有再去查数据库 并写入缓存
     * @param id
     * @return
     */
    @Override
    public Person findById(Integer id) {

        Person person = null;
        //先判断是否在内缓存中
        ValueOperations<Integer, Person> valueOperations = redisTemplate.opsForValue();


        //默认的需要
        boolean hasKey = redisTemplate.hasKey(id);

        if (hasKey) {
            person = valueOperations.get(id);
            logger.info("从缓存中获得person" + id);

        } else {
            person = dao.queryById(id);
            valueOperations.set(id,person);
            int i = 1/0;
            logger.info("从数据库中获得person" + id+" 插入缓存");
        }
        return person;

    }



    /**
     * 先删除缓存再进行更新数据库
     * @return
     */
    @Transactional(rollbackFor = Exception.class) //默认只回滚运行时异常 除非用rollbackFor指定  捕捉了的异常不会回滚，事务回滚只会是抛出的异常
    @Override
    public int updatePerson(Person person)throws Exception {

        int state = -1;

        redisTemplate.delete(person.getId());

        state = dao.updateName(person);

//        try {
//            int i = 1/0;
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        } finally {
//        }


        return state;


    }


}
