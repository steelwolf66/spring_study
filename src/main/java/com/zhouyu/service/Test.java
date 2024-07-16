package com.zhouyu.service;

import com.zhouyu.spring.ZhouyuApplicationContext;

public class Test {
    public static void main(String[] args) {
        ZhouyuApplicationContext zhouyuApplicationContext = new ZhouyuApplicationContext(AppConfig.class);
        UserInterface userService = (UserInterface) zhouyuApplicationContext.getBean("userService");
        userService.test();
    }
}