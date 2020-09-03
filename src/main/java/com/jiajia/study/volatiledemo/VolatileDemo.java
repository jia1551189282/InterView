package com.jiajia.study.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * @author zjiajia
 * @date 2020/9/2 20:54
 *
 * 验证 volatile 保证线程可见性
 *
 *
 */

class MyData{
    /**
     * 初始值大小
     */
    volatile int number = 0;

    /**
     * 给number 加60
     */
    public void add(){
        number = number + 60;
    }

    /**
     *  i++
     */
    public void addPlusPlus(){
        number++;
    }
}

public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        // 开启20个线程,每个线程加1000次  ,如果线程具有原子性的话，结果就会是20000
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();

                }
            },String.valueOf(i)).start();
        }
        // 等待20个线程全部计算完成之后，看结果是否是20000
        while (Thread.activeCount() >  2){
            Thread.yield();
        }

        System.out.println("20个线程计算完成的结果是：" + myData.number);
    }



    // volatile 保证线程安全性
    private static void keepVolatile() {
        MyData myData = new MyData();

        new Thread(() -> {
            System.out.println("AAA 线程 开始执行，初始变量值是：" + myData.number);
            // 休息三秒钟之后  操作加 60
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.add();

            System.out.println("AAA 线程操作完成，操作完成之后变量值是：" + myData.number);
        },"AAA").start();

        while (myData.number == 0){
            // 只要等于0，就一直不会停止，只有等变成了非0之后，才会跳出while
        }

        System.out.println("主线程执行结束,变量值是：" + myData.number );
    }
}
