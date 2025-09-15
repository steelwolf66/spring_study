package com.zhouyu.interceptor.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * jdk动态代理
 */
public class JdkInterceptor implements InvocationHandler {
    private Object target;

    public JdkInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk切面逻辑：方法调用前" + method.getName());
        Object invoked = method.invoke(target, args);
        System.out.println("jdk切面逻辑：方法调用后" + method.getName());
        return invoked;
    }
}
