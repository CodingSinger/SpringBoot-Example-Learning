package com.zzc.test.springboot_mybatis.springbootmybatis;

/**
 * @author zhengzechao
 * @date 2018/3/14
 */

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface MyAnnotation {

    String name() ; //没有default的一定要赋值
    String value() default "";
    int id() default 0;



}
