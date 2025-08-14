package com.zhouyu.study.week1.day2;

import java.util.Iterator;

/**
 * 手写HashMap
 *
 * @author sun
 * @date 2020/9/16
 */
public class MyHashMap<K, V> {
    //默认容量
    private static final int DEFAULT_CAP = 8; //2的幂
    //负载因子
    private static final float LOAD_FACTOR = 0.75f;
    //数据
    private Entry<K, V>[] table;
    private int size;
    private int threshold;//触发扩展阈值 cop * loadFactor

    //构造方法
    public MyHashMap() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_CAP];
        threshold = (int) (DEFAULT_CAP * LOAD_FACTOR);
    }

    //节点
    public static class Entry<K, V> {//<K,V>
        final K key;
        V value;

        boolean deleted; //开放地址的墓碑标记

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    //核心API
    //put
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        //校验是否需要扩容
        if (size >= threshold) {
            resize();
        }
        int idx = findSlot(key, table);
        Entry<K, V> entry = table[idx];
        //不存在该KEY数据,直接插入
        if (entry == null || entry.deleted) {
            table[idx] = new Entry<>(key, value);
            size++;
            return null;
            //已存在该KEY，则替换数据，返回历史值
        } else {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
    }

    //get
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int idx = findSlot(key, table);
        Entry<K, V> entry = table[idx];
        if (entry == null || entry.deleted) {
            return null;
        } else {
            return entry.value;
        }
    }

    //remove
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int idx = findSlot(key, table);
        Entry<K, V> entry = table[idx];
        if (entry == null || entry.deleted) {
            return null;
        } else {
            V oldValue = entry.value;
            entry.deleted = true;
            size--;
            return oldValue;
        }
    }

    private int hash(Object k) {
        int h = k.hashCode();
        return h ^ (h >>> 16) & (table.length - 1);//位运算 2的幂
    }

    /**
     * 线性探测 目标桶，要么KEY在某个位置 要么第一个空或墓碑
     *
     * @param k   KEY
     * @param tab 桶
     * @return idx
     */
    private int findSlot(K k, Entry<K, V>[] tab) {
        int len = tab.length;
        int idx = hash(k);
        //遍历tab
        while (true) {
            Entry<K, V> e = tab[idx];
            if (e == null) { //找到空位
                return idx;
            }
            if ((e.deleted && !e.key.equals(k)) || //找到墓碑
                    (!e.deleted && e.key.equals(k))) { //命中
                return idx;
            }
            idx = (idx + 1) & (len - 1);//位运算 线性探测
        }
    }

    private void resize() {
        int newCap = table.length << 1;
        Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[newCap];
        for (Entry<K, V> old : table) {
            if (old == null || old.deleted) {
                continue;
            }
            int idx = findSlot(old.key, newTable);
            newTable[idx] = old;
        }
        table = newTable;
        threshold = (int) (newCap * LOAD_FACTOR);
    }

    public Iterator<Entry<K, V>> iterator() { //迭代器
        return new Iterator<Entry<K, V>>() {
            int cursor = 0;

            @Override
            public boolean hasNext() {
                while (cursor < table.length && table[cursor] == null || table[cursor].deleted) {
                    cursor++;
                }
                return cursor < table.length;
            }

            @Override
            public Entry<K, V> next() {
                return table[cursor++];
            }

        };
    }
}
