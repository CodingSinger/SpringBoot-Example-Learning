package com.zzc.test.springboot_mybatis.springbootmybatis.controller;

import com.zzc.test.springboot_mybatis.springbootmybatis.service.Impl.PersonServiceImpl;
import com.zzc.test.springboot_mybatis.springbootmybatis.service.Impl.TestRTwo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/6/6
 * Email ooczzoo@gmail.com
 */
@Component
public class TestCom implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBeanDefinitionNames());
        final TestRTwo bean = applicationContext.getBean(TestRTwo.class);
    }
}
