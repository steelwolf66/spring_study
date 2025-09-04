package com.zhouyu.stacks;

public class StacksTest {
    public static void main(String[] args) {
        int a = 0;
        int b = a++; //先赋值，再自增
        int d = 0;
        int c = ++d; //先自增，再赋值
        System.out.println("a=" + a + ",b=" + b + ",c=" + c);
    }
}
