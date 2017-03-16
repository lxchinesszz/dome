package com.example.cache;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 循环检查元素
 * @author Administrator
 *
 */
public class CacheListener extends Thread{

    private Cache cache;

    private volatile boolean stop = false;
    //设置使用s，转换成毫秒
    private volatile long ONE_SECOND = 1000;

    public boolean isStop() {
        return stop;
    }
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    public CacheListener(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        long time = cache.getConfigure().getDiskExpiryThreadIntervalSeconds();
        try {
            while(!stop){
                sleep(time*ONE_SECOND);
                threadCheckElement();
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void destory(){
        ONE_SECOND = 0;
        stop = true;
    }


    public void threadCheckElement(){
        //垃圾回收，满足一下条件的进行回收
        //1.闲置时间超过用户配置时间
        //2.缓存超时，需要更新
        List<Object> keys = new ArrayList<Object>();
        //从缓存中获得所有对象
        Map<Object, Element> map  = cache.getAll();
        if(map != null && map.size() > 0){
            for(Map.Entry<Object, Element> e0: map.entrySet()){
                Element e = e0.getValue();
                if(e != null && e.isExpired()){
                    keys.add(e0.getKey());
                }
            }
        }
        cache.removeAll(keys.toArray());
    }



}