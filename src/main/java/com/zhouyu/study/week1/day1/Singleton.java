package com.zhouyu.study.week1.day1;

/**
 * 单例模式
 * 将构造方法私有
 * 通常正常情况下使用，是单例的
 * 但是，这种方式，可以通过反射被打破，
 * 比如通过反射，访问私有构造函数后，创建对象
 * <p>
 * 还有就是序列化的过程中，如果再反序列化，得到的对象不是原来的对象了，这种可以通过单元素枚举进行处理
 */
public class Singleton {
    public static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }
}

/**
 * 单元素枚举
 * 解决序列化、反序列化问题
 */
enum Elvis {
    INSTANCE;
}