package com.zhouyu.object;

public class User {
    public User(String name) {
        this.name = name;
    }
//私有无参构造 会限制外部 创建对象
    private User (){

    }
    private String name;

    /**
     * 修饰静态方法，静态方法归类所有，所以锁住的是类对象
     */
    synchronized static void test() {
        System.out.println("test");
    }


    /**
     * 修饰实例方法
     *
     * @return userName
     */
    public synchronized String getName() {
        return name;
    }

    public void sync() {
        //修饰代码块，锁的是当前对象
        synchronized (this) {
            System.out.println("代码块");
        }
        //修饰代码块，锁的是类对象
        synchronized (User.class) {
            System.out.println("代码块");
        }
    }
}
