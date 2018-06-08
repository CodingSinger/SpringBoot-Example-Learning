package com.zzc.test.springboot_mybatis.springbootmybatis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author zhengzechao
 * @date 2018/5/3
 * Email ooczzoo@gmail.com
 */
public class TestReflectionGen {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class c = HelloWorld.class;
        Constructor c1 = c.getDeclaredConstructor();
        Object o = c1.newInstance();
        System.out.println(o);

        HashMap hashMap = new HashMap();
        hashMap.put("1",o);
        hashMap.put("2",o);
        System.out.println(hashMap);
    }
}


class Hello{

    public Hello() {
        System.out.println("hello");
    }
}

class HelloWorld{
    private static Hello hello = new Hello();

    public HelloWorld() {
        System.out.println("helloworld");
    }
}
