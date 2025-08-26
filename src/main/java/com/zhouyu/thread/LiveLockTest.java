package com.zhouyu.thread;

import java.util.concurrent.TimeUnit;

public class LiveLockTest {
    static volatile int count = 10;
    static final Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (count > 0) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
                System.out.println(Thread.currentThread().getName() + " count:" + count);
            }
        }, "t1").start();
        new Thread(() -> {
            while (count < 20) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println(Thread.currentThread().getName() + " count:" + count);
            }
        }, "t2").start();
    }
}
