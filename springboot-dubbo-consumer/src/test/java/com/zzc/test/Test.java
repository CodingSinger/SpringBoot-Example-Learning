package com.zzc.test;

import java.util.HashSet;

/**
 * @author zhengzechao
 * @date 2018/4/18
 * Email ooczzoo@gmail.com
 */
public  class Test {


    static char[] c = {'1','3'};


    public static void main(String[] args) {

        HashSet<Integer> hashSet = new HashSet<Integer>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(3);
        System.out.println(hashSet);

        int[] a = new int[]{1,2,3};

        System.out.println(new Test().maxSubArray(new int[]{1,-2,4,3,-8}));
    }

    public int maxSubArray(int[] A) {

        int max = 0;
        int past = -1;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(max, past + A[i]);
            past = max;
        }

        return max;

    }


}
