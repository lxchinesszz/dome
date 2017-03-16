package com.example.mycache;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @Package: com.example.mycache
 * @Description: 缓存最小储存单元
 * @author: liuxin
 * @date: 17/3/14 上午9:50
 */
public class Element {
    static final Long ONE_SECOND = 1000L;
    private Object key;
    private Object value;
    //热度
    private volatile long hitCount = 0;
    //是否使用单独元素控制
    private Boolean isOpen;
    //声明周期
    private volatile int timeToLive=0;
    //闲置时间
    private volatile int timeToIdle=0;
    //创建时间
    private transient long createTime;
    //最后操作时间
    private transient long lastAccessTime;
    //最后更新时间
    private volatile long lastUpdateTime;

    private void init(Object key,Object value){
        this.key=key;
        this.value=value;
        this.createTime=System.currentTimeMillis();
        this.lastAccessTime=System.currentTimeMillis();
    }

    public Element(final Object key,final Object value){
        init(key,value);
        this.isOpen=isOpen;
    }

    public Element(final Object key,final Object value,Boolean isOpen){
        init(key,value);
        this.isOpen=isOpen;
    }


    //判断是否永久
    public boolean isEternal(){
        return timeToIdle==0&&timeToLive==0;
    }
    //计算过期时间
    public long getExpirationTime(){
        if (isEternal()){//如果永久的就返回最大long值
            return Long.MAX_VALUE;
        }
        //如果会过期就比较时间和最大闲置时间
        long expirationTime=0;
        //过期时间=创建时间+最大生命时间
        long ttlExpiry=createTime+getTimeToLive()*ONE_SECOND;
        //过期时间+最后操作时间+最大空闲时间
        long ttiExpiry=lastAccessTime+getTimeToIdle()*ONE_SECOND;

        if (getTimeToLive()!=0&&(getTimeToIdle()==0||lastAccessTime==0)){
            return ttlExpiry;//如果定义了生命周期，就返回生命周期时间
        }else if (getTimeToLive()==0){//如果不过期就返回最大闲置时间
            return ttiExpiry;
        }else {
            return Math.min(ttlExpiry,ttiExpiry);
        }
    }
    //检查是否过期
    public boolean isExpired(){
        if (isEternal()){//如果是永久的那么，返回false就是没有过期
            return false;
        }
        long expirationTime=getExpirationTime();
        long now=System.currentTimeMillis();
        return now > expirationTime;//当前时间大于过期时间那么就是已经过期了
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getHitCount() {
        return hitCount;
    }

    public void setHitCount(long hitCount) {
        this.hitCount = hitCount;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public int getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    public int getTimeToIdle() {
        return timeToIdle;
    }

    public void setTimeToIdle(int timeToIdle) {
        this.timeToIdle = timeToIdle;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
