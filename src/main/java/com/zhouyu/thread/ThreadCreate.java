package com.zhouyu.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.FutureTask;

@Slf4j
public class ThreadCreate {
    public static void main(String[] args) {
        //new Thread类
        Thread t1 = new Thread() {
            @Override
            public void run() {
                System.out.println("hello ,t1");
            }
        };
        t1.start();

        //使用runnable接口，可以使用lambda表达式代替， Runnable runnable = () -> System.out.println("hello,runnable");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello ,runnable");
            }
        };
        new Thread(runnable, "t2").start();

        //使用FutureTask ,FutureTask可以接收Callable类型参数，Callable需要有返回值
        FutureTask<Object> objectFutureTask = new FutureTask<>(() -> {
            System.out.println("hello ,FutureTask");
            return null;
        });
        new Thread(objectFutureTask, "t3").start();

        System.out.println("main");
    }

}
