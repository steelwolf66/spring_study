package com.zhouyu.thread;

public class ChopstickTest {
    public static void main(String[] args) {
        Chopstick chopstick1 = new Chopstick("1");
        Chopstick chopstick2 = new Chopstick("2");
        Chopstick chopstick3 = new Chopstick("3");
        Chopstick chopstick4 = new Chopstick("4");
        Chopstick chopstick5 = new Chopstick("5");
        new Philosopher("苏格拉底", chopstick1, chopstick2).start();
        new Philosopher("柏拉图", chopstick2, chopstick3).start();
        new Philosopher("阿基米德", chopstick3, chopstick4).start();
        new Philosopher("亚里士多德", chopstick4, chopstick5).start();
        new Philosopher("爱因斯坦", chopstick5, chopstick1).start();
    }
}
