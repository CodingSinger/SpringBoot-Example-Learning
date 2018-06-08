package com.zzc.test.springboothelloworld;

import com.zzc.test.springboothelloworld.service.Impl.PersonServiceImpl;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @author zhengzechao
 * @date 2018/3/12
 */
public class Test1 {
    @Test
    public void test1() throws Exception {
        Class s = Class.forName("com.zzc.test.springboothelloworld.service.Impl.PersonServiceImpl");


        long l1 = System.currentTimeMillis();
        Annotation[] as = s.getAnnotations();
        for (Annotation s2:as) {
            System.out.println(s2.annotationType().getName());

        }

        System.out.println(System.currentTimeMillis()-l1);
        long l2 = System.currentTimeMillis();

        Arrays.asList(s.getAnnotations()).stream().map(Annotation::annotationType).map(Class::getName).forEach(System.out::println);
        System.out.println(System.currentTimeMillis()-l2);

    }


    @Test
    public void name2() throws Exception {
        Integer i = new Integer(1);
        System.out.println(i^1);
        Integer i1 = new Integer(0);
        System.out.println(i1^1);
    }
}
