package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Package: com.example.proxy
 * @Description: 使用JDK动态代理
 * 加载快
 * @author: liuxin
 * @date: 17/3/31 上午9:37
 */
public class JDKProxy implements InvocationHandler {
    Object target;

    public JDKProxy(Object obj) {
        super();
        target = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("===============执行前================");
        Object o = method.invoke(target, args);
        System.out.println("===============执行后================");
        return o;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 优化方案
     * @param args
     */
    public static void main(String[] args) {
        JDKProxy jdkProxy = new JDKProxy(new Jay());
        Person proxy=(Person)jdkProxy.getProxy();
        proxy.say();
    }
}

interface Person {
     void say();
}

class Jay implements Person {
    @Override
    public void say() {
        System.out.println("Jay:\n\tI'am Jay");
    }
}
