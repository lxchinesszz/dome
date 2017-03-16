package com.example.mycache;

/**
 * @Package: com.example.mycache
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/14 上午9:48
 */
public interface IStore {
    Integer size();
    Element get(Object key);
    void put(Element e);
}
