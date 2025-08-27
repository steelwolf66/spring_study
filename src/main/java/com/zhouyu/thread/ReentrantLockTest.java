package com.zhouyu.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static ReentrantLock lock = new ReentrantLock();
    static Condition waitCigaretteCondition = lock.newCondition();
    static Condition waitBreakfastCondition = lock.newCondition();
    static volatile boolean hasCigarette = false;
    static volatile boolean hasBreakfast = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                lock.lock();
                while (!hasCigarette) {
                    try {
                        waitCigaretteCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("抽烟");
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                while (!hasBreakfast) {
                    try {
                        waitBreakfastCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("吃饭");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
        TimeUnit.SECONDS.sleep(1);
        sendBreakfast();
        TimeUnit.SECONDS.sleep(1);
        sendCigarette();
    }

    private static void sendCigarette() {
        lock.lock();
        try {
            System.out.println("生产烟");
            hasCigarette = true;
            // 通知t1
            waitCigaretteCondition.signal();
        } finally {
            lock.unlock();
        }
    }


    private static void sendBreakfast() {
        lock.lock();
        try {
            System.out.println("生产饭");
            hasBreakfast = true;
            // 通知t2
            waitBreakfastCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
