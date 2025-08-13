package com.zhouyu.study.week1.day1;

/**
 * 静态工厂方法
 * 优点：
 * 1、简单
 * 2、高内聚
 * 3.、高复用
 * 缺点：
 * 1.、不符合开闭原则
 * 2.、不符合里氏替换原则
 * 3.、不符合依赖倒转原则
 * 4.、不符合单一职责原则
 */
public class StaticFactory {
    public static void main(String[] args) {
        Boolean b = Boolean.valueOf("true");
        Integer integer = Integer.valueOf("1");
        String a = "a";
        //不应该是下面这个创建方式
        String c = new String("b");

    }
}
