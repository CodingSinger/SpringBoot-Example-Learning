package com.zzc.test.springboothelloworld;

import com.zzc.test.springboothelloworld.domain.Person;
import com.zzc.test.springboothelloworld.service.SAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhengzechao
 * @date 2018/5/17
 * Email ooczzoo@gmail.com
 */


@EnableConfigurationProperties(value = {CustomProperties.class,SAd.class})
@Configuration
public class CustomeConfiguration {

    @Autowired
    private CustomProperties customProperties;

    @Autowired
    private SAd sAd;
    @Bean  //默认为方法名 相当于Xml中的<bean>
    public Person person(){
        System.out.println(sAd);
        System.out.println(customProperties);
        Person person = new Person();
        person.setId(customProperties.getAge());
        person.setName(customProperties.getName());
        return person;
    }


}
