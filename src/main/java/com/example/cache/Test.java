package com.example.cache;

/**
 * @Package: com.example.cache
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/3/13 下午4:01
 */
public class Test {
    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CacheManager cm = CacheManager.getInstance();//对缓存进行管理
        Cache c1 = new Cache(getConfigure());
        // 最大放5个
        putTestE1(c1);
        cm.putCache(c1);
//        cm.shutDown();
        // 只有2 的hit 值最小，因此超过了就被移除了 null
        System.out.println(c1.get(2));
        // 想存放6了，实际数据只有5个
        System.out.println("总数:"+c1.size());
        System.out.println( cm.getCache("Test1"));
        // 休息3秒，然后使用
        Thread.sleep(1000*3);
        System.out.println("刷新："+c1.get(1));
        // 然后继续休息
        Thread.sleep(1000*3);
        System.out.println("使用着的元素："+c1.size());
        Thread.sleep(1000*15);
        System.out.println("时间太久，全部过期"+c1.size());

        //cm.shutDown();

    }

    public static CacheConfiguration getConfigure(){
        CacheConfiguration c = new CacheConfiguration();
        c.setCacheName("Test1");//设置缓存名字
        // 最多存放5个元素
        c.setMaxElementsInMemory(5);//设置缓存元素大小
        // 假设5秒不用就过期，这两个时间一般默认选小的一个执行，最长时间是 存活的总时间
        c.setTimeToIdleSeconds(5);//设置10s不用就过期
        // 假设最长能存活115秒
        c.setTimeToLiveSeconds(115);
        // 6秒 检查一次过期
        c.setDiskExpiryThreadIntervalSeconds(6);
        return c;
    }

    public static void putTestE1(Cache c1){
        c1.put(new Element(1, 2));
        c1.get(1);
        c1.put(new Element(2, 2));
        //c1.get(2);
        c1.put(new Element(3, 2));
        c1.get(3);
        c1.put(new Element(4, 2));
        c1.get(4);
        c1.put(new Element(5, 2));
        c1.get(5);
        c1.put(new Element(6, 2));
    }
}
