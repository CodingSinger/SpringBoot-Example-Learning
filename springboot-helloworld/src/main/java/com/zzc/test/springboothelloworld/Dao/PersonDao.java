package com.zzc.test.springboothelloworld.Dao;

import com.zzc.test.springboothelloworld.domain.Person;
import com.zzc.test.springboothelloworld.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
@Repository
public class PersonDao {
    public static List<Person> persons = new ArrayList<>();

    static{
        persons.add(new Person("张三",1));
        persons.add(new Person("李四",2));
        persons.add(new Person("麻子", 3));

    }

    public Person queryById(Integer id){
        return persons.stream().filter(t -> t.getId() == 1).findFirst().orElse(null);
    }
}
