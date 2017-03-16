package com.cacahe;

import com.example.builder.User;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import java.util.Collection;
import java.util.Date;

/**
 * @Package: com.cacahe
 * @Description: 缓存
 * @author: liuxin
 * @date: 17/3/13 下午2:34
 */
public class CacheStoreTest {

    public static void main(String[] args) {
        CacheManager cacheManager = CacheManager.getInstance();
//        CacheManager.newInstance("配置地址");
        //创建缓存配置对象
        CacheConfiguration conf = new CacheConfiguration();
        conf.name("myCache");
        conf.maxEntriesLocalHeap(1000);//最大缓存数量
        conf.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU);
        //创建缓存
        Cache localCache = new Cache(conf);
        //将缓存添加到缓存管理器中
        cacheManager.addCache(localCache);
        User user = new User("liuxin", "23", "testcache");
        localCache.put(new Element("liuxin", user));
        User user1 = (User) localCache.get("liuxin").getObjectValue();
        System.out.println(user1.getAge());

    }
}
