package com.zhouyu.thread;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class ExerciseTransfer {
    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1000);
        Account b = new Account(1000);
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                a.transfer(b, randomAmount());
                lock.unlock();
            }
        },
                "t1");
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                lock.lock();
                b.transfer(a, randomAmount());
                lock.unlock();
            }

        },
                "t2");
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
        int amount = random.nextInt(100) + 1;
//        System.out.println("amount:" + amount);
        return amount;
    }
}

class Account {
    private volatile int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account target, int amount) {

        if (this.money > amount) {
            this.setMoney(this.getMoney() - amount);
            target.setMoney(target.getMoney() + amount);
        }
    }
}