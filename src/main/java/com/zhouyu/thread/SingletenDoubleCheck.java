package com.zhouyu.thread;

/**
 * 双重校验 懒汉式单例模式
 */
public class SingletenDoubleCheck {
    static volatile SingletenDoubleCheck instance = null;

    public static SingletenDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (SingletenDoubleCheck.class) {
                if (instance == null) {
                    instance = new SingletenDoubleCheck();
                }
            }
        }
        return instance;
    }
}
