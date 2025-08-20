package com.zhouyu.study.week1.day2;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args) {
        new HashMap<Object,Object>(5);//默认的加载因子为0.75，初始化会初始化为16 2的N次幂
        Hashtable<String, String> stringStringHashtable = new Hashtable<>();
        ConcurrentHashMap<Object,Object> concurrentHashMap = new ConcurrentHashMap(5);//默认的加载因子为0.75，初始化会初始化为16 2的N次幂
    }
}
