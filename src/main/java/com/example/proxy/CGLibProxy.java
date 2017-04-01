package com.example.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Package: com.example.proxy
 * @Description: JDK自带动态代理，只能代理，拥有接口的，而Cglib代理，是运行在动态生成字节码的工具中
 * @author: liuxin
 * @date: 17/3/31 上午10:24
 */
public class CGLibProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("===============执行前================");
        Object o = proxy.invokeSuper(obj, args);
        System.out.println("===============执行后================");
        return o;
    }

    public static CGLibProxy instance(){
        return new CGLibProxy();
    }

    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }


    public static void main(String[] args) {
        CGLibProxy.instance().getProxy(Jay2.class).say();
    }
}
