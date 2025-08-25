package com.zhouyu.thread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class ThreadSleep {
    public static void main(String[] args) throws InterruptedException {
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
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("hello ,runnable");
                LockSupport.park();
                System.out.println("unpark,continue");
            }
        };
        Thread t2 = new Thread(runnable, "t2");
        t2.start();
//        t2.join(); 测试unpark方法，这里join会无限等待下去
        TimeUnit.SECONDS.sleep(4);
        LockSupport.unpark(t2);
//        t2.wait(); 报错 wait方法必须在Synchronize代码块中使用，并且，锁的是一个对象，会释放锁

        System.out.println("main");
    }

}
