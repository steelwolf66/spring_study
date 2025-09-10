package com.zhouyu.interceptor.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理
 */
public class JdkInterceptor implements InvocationHandler {
    private Object proxy;

    public JdkInterceptor(Object proxy) {
        this.proxy = proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("切面逻辑：方法调用前");
        Object invoked = method.invoke(proxy, args);
        System.out.println("切面逻辑：方法调用后");
        return invoked;
    }
}
