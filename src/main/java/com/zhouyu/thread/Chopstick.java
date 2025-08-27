package com.zhouyu.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick extends ReentrantLock {
    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Philosopher extends Thread {
    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    private void eat() throws InterruptedException {
        System.out.println(getName() + " is eating");
        TimeUnit.SECONDS.sleep(1);
    }

    @Override
    public void run() {
        while (true) {
            //获得左手筷子
            if (left.tryLock()) {
                try {
                    //获得右手筷子
                    if (right.tryLock()) {
                        try {
                            eat();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            //放下右手筷子
                        } finally {
                            right.unlock();
                        }
                    }
                    //放下左手筷子
                } finally {
                    left.unlock();
                }
            }
        }
    }
}