package com.zhouyu.study.week1.day1;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * 自动关闭资源
 * 自动关闭流
 */
public class TryWithResources {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader("aaa\nbbb"))) {
            bufferedReader.lines().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
