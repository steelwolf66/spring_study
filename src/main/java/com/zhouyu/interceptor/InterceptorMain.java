package com.zhouyu.interceptor;

import com.zhouyu.interceptor.jdk.JdkInterceptor;
import com.zhouyu.interceptor.service.OrderService;
import com.zhouyu.interceptor.service.impl.OrderServiceImpl;

import java.lang.reflect.Proxy;

public class InterceptorMain {
    public static void main(String[] args) {
        OrderService orderService = new OrderServiceImpl();
        JdkInterceptor jdkInterceptor = new JdkInterceptor(orderService);
        OrderService proxyInstance = (OrderService) Proxy.newProxyInstance(OrderService.class.getClassLoader(), new Class[]{OrderService.class}, jdkInterceptor);
        proxyInstance.create("1");

    }
}
