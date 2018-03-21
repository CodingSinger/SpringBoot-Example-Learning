package com.zzc.test.springboot_mybatis.springbootmybatis;

/**
 * @author zhengzechao
 * @date 2018/3/15s
 */
public class Counter {
    static long counter = 0;
    public static void main(String[] _) {
        for (long i = (long)1e12; i < (long)1e12 + 1e5; i++)
            put(i);
        System.out.println(counter);
    }

    static void put(long v) {
        counter += v;
    }
}
