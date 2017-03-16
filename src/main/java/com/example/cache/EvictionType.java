package com.example.cache;

/**
 * @Package: com.example.cache
 * @Description: 模拟EhCache,缓存收回策略。当前只实现LRU策略
 * @author: liuxin
 * @date: 17/3/13 下午3:57
 */
public enum EvictionType {
    LRU,LFU,FIFO
}
