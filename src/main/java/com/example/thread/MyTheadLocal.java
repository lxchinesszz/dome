package com.example.thread;

import org.apache.commons.collections.map.HashedMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Package: com.example.thread
 * @Description: 手动实现一个TheadLocal
 * @author: liuxin
 * @date: 17/3/31 下午2:22
 */
public class MyTheadLocal<T> {

    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value){
        container.put(Thread.currentThread(),value);
    }

    public T get(){
       return container.get(Thread.currentThread());
    }

    public void remove(){
        container.remove(Thread.currentThread());
    }

    protected T initialValue(){
        return null;
    }
}
