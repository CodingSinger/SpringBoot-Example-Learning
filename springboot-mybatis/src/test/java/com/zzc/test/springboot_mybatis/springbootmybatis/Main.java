package com.zzc.test.springboot_mybatis.springbootmybatis;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


/**
 * @author zhengzechao
 * @date 2018/3/14
 */
public class Main {
    static long l = 10;

    public static void main(String[] args) throws NoSuchMethodException {
        l = 20;
        A a = new A();
        System.out.println(a.a);
        Method m = Main.class.getMethod("hello");
        MyAnnotation b = m.getAnnotation(MyAnnotation.class);
        System.out.println(b.annotationType());
        System.out.println(b.name());

    }

    @MyAnnotation(name = "ds", value = "3")
    public static void hello(){

    }
}


class A{
    A(){
        this(024);
        a = 18;
    }
    int a = 10;
    A(int b){
        this.a = b;
    }
}

class B extends A{

    private B() {
        super();
    }
}

class C implements MyAnnotation{

    @Override
    public String name() {
        return null;
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
