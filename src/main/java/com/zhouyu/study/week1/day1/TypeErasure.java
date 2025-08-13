package com.zhouyu.study.week1.day1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 泛型擦除
 * 验证泛型擦除
 */
public class TypeErasure {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("a");
        //泛型擦除 这里实际上接收的是Object类型，因为泛型擦除，无法区分String和Object
        Method add = List.class.getMethod("add", Object.class);
        add.invoke(list, "b");

        System.out.println(list);
    }
}
