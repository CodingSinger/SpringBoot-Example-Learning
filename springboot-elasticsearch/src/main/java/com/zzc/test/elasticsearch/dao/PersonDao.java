package com.zzc.test.elasticsearch.dao;

import com.zzc.test.elasticsearch.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhengzechao
 * @date 2018/5/1
 * Email ooczzoo@gmail.com
 */
public interface PersonDao extends ElasticsearchRepository<Person,Integer> {


}
