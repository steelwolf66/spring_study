package com.zhouyu.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ExerciseTransfer {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000, 1);
        Account b = new Account(1000, 2);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    TimeUnit.NANOSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                a.transfer(b, randomAmount());
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    TimeUnit.NANOSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                b.transfer(a, randomAmount());
            }

        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
// 查看转账2000次后的总金额
        System.out.println("total:" + (a.getMoney() + b.getMoney()) + ",a:" + a.getMoney() + ",b:" + b.getMoney());
    }

    // Random 为线程安全
    static Random random = new Random();

    // 随机 1~100
    public static int randomAmount() {
        return random.nextInt(100) + 1;
    }
}

class Account {
    private int money;
    private final ReentrantLock lock = new ReentrantLock();

    private final int id;

    public Account(int money, int id) {
        this.money = money;
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account target, int amount) {
        if (target == null || this == target) {
            return;
        }
        ReentrantLock firstLock = this.id > target.id ? this.lock : target.lock;
        ReentrantLock secondLock = this.id > target.id ? target.lock : this.lock;
        firstLock.lock();
        secondLock.lock();
        try {
            if (this.money > amount) {
                this.setMoney(this.getMoney() - amount);
                target.setMoney(target.getMoney() + amount);
            }
        } finally {
            secondLock.unlock();
            firstLock.unlock();
        }
    }
}