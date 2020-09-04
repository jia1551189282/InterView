package com.jiajia.study.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zjiajia
 * @date 2020/9/4 11:38
 *
 * cas 相关代码
 *
 * 是什么？
 *
 * 比较并交换
 */
public class CasDemo {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger();

        System.out.println(atomicInteger.compareAndSet(10, 2020));
        System.out.println(atomicInteger.compareAndSet(10, 1024));

    }
}
