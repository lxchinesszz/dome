package com.example.cache;

import com.example.cache.Element;
import java.util.Collection;

/**
 * @Package: com.example.cache
 * @Description: 缓存接口
 * @author: liuxin
 * @date: 17/3/13 下午3:42
 */
public interface Store {
    //获得缓存方法
    public String getName();

    //存取集合
    public Collection<Element> putAll(Collection<Element> elements);

    // 获取元素
    public Element get(Object key);

    // 清除元素
    public void clear();

    // 移除元素
    public void remove(Object key);

    //移除所有元素
    public void removeAll(Object[] keys);

    // 获得的元素长度
    public Integer size();
}
