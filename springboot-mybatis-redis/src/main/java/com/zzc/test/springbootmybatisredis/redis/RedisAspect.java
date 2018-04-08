package com.zzc.test.springbootmybatisredis.redis;

import com.zzc.test.springbootmybatisredis.domain.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author zhengzechao
 * @date 2018/4/8
 */
@Aspect
//@Component
public class RedisAspect {
    @Autowired
    private RedisTemplate template;
    @Pointcut("execution(* *.find*(..))")
    public void query(){}

    @Around(value = "query()")
    public Object queryOperation(ProceedingJoinPoint joinPoint){

        return null;
    }
}
