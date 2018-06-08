package com.zzc.test.elasticsearch.service.impl;

import com.zzc.test.elasticsearch.dao.PersonDao;
import com.zzc.test.elasticsearch.domain.Person;
import com.zzc.test.elasticsearch.service.PersonService;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/5/1
 * Email ooczzoo@gmail.com
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao dao;


    /* 分页参数 */
    Integer PAGE_SIZE = 12;          // 每页数量
    Integer DEFAULT_PAGE_NUMBER = 0; // 默认当前页码

    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float MIN_SCORE = 10.0F;      // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    @Override
    public boolean addPerson(Person p) {
        return dao.save(p) != null;
    }

    @Override
    public List<Person> findPerson(Integer pageNum, Integer pageSize, String key) {
        // 校验分页参数
        if (pageSize == null || pageSize <= 0) {
            pageSize = PAGE_SIZE;
        }

        if (pageNum == null || pageNum < DEFAULT_PAGE_NUMBER) {
            pageNum = DEFAULT_PAGE_NUMBER;
        }


        // 构建搜索查询
        FuzzyQueryBuilder searchQuery = QueryBuilders.fuzzyQuery("name",key);
        List<Person> list = new ArrayList<>();
        Iterable iterable = dao.search(searchQuery);
        final Iterator iterator = iterable.iterator();
        while(iterator.hasNext()){
            Person p = (Person) iterator.next();
            list.add(p);
        }
        return list;
    }



}
