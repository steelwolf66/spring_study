package com.zhouyu.interceptor.service.impl;

import com.zhouyu.interceptor.service.OrderService;

public class OrderServiceImpl implements OrderService {
    @Override
    public String create(String sku) {
        System.out.println("原始对象：创建订单");
        return "success:" + sku;
    }
}
