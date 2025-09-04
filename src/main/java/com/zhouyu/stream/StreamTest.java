package com.zhouyu.stream;

import java.util.Arrays;
import java.util.List;

/**
 * stream
 * 中间操作
 * 终止操作
 * <p>
 * 中间操作返回的是流
 * 多个中间操作时，不会等待上一个中间操作，全部遍历完成后，再执行下一个中间操作
 * 比如filter操作，有符合条件的，就会丢到下一个中间操作；map操作处理一个元素，就会丢到下一个中间操作
 * </p>
 * 可以有多个中间操作，只能有一个终止操作
 * 执行终止操作后，流就被关闭
 * <p>
 * 懒加载：只有在调用终止操作的时候，才会执行中间操作
 */
public class StreamTest {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";
        String c = "c";
        List<String> list = Arrays.asList(a, b, c);
        list.stream()
                .filter(s -> s.toLowerCase().startsWith("a"))
                .sorted().forEach(System.out::println);
    }
}
