package com.zzc.test.springbootcanal.listener;

import java.util.Arrays;

/**
 * @author zhengzechao
 * @date 2018/7/6
 * Email ooczzoo@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        C c = new C();
        //检查A是否是c的直接或者间接父类 直接或者间接父接口
        System.out.println(A.class.isAssignableFrom(c.getClass()));
        System.out.println(c.getClass().getSuperclass());
        //不包括父接口
        System.out.println(Arrays.toString(c.getClass().getInterfaces()));

    }

    /**
     *
     * 输出：
     * true
     * class com.zzc.test.springbootcanal.listener.B
     * [interface com.zzc.test.springbootcanal.listener.D, interface com.zzc.test.springbootcanal.listener.G]
     */
}

class A{

}
class B extends A implements F{

}
class C extends B implements D,G{

}


interface D extends E{

}

interface E{

}
interface F{

}
interface G{

}