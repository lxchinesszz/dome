package com.example.cache;
import java.io.Serializable;

/**
 * @Package: com.example.cache
 * @Description: 缓存基础存储单元
 * @author: liuxin
 * @date: 17/3/13 下午3:48
 */
@SuppressWarnings("serial")
public class Element implements Serializable {
    static final long ONE_SECOND = 1000L;
    private Object key;
    private Object value;
    // 使用次数
    private volatile long hitCount = 0;
    // 这些属性是单个元素的属性控制
    // 是否使用单独元素控制
    private Boolean isOpen = false;
    // 从创建时间开始后，还能存活时间，0 表示一直存活，超时时间=timeToLive + CreationTime
    private volatile int timeToLive = 0;
    // 从最近即(min(CreationTime，LastAccessTime)) 后还剩余的时间
    private volatile int timeToIdle = 0;
    // 创建时间
    private transient long creationTime;
    // 最后一次使用的时间
    private transient long lastAccessTime;
    // 最后更新时间
    private volatile long lastUpdateTime;
    // 表示是否使用cache 级别的控制，还是元素级别的控制，这里暂时不用
    private volatile boolean cacheDefaultLifespan = true;

    public Element(final Object key, final Object value){
        init(key,value);
    }

    public Element(final Object key, final Object value,Boolean isOpen){
        init(key, value);
        this.isOpen = isOpen;
    }

    private void init(final Object key, final Object value){
        this.key = key;
        this.value = value;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessTime = System.currentTimeMillis();
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
    public long getHitCount() {
        return hitCount;
    }
    public void setHitCount(long hitCount) {
        this.hitCount = hitCount;
    }
    public void addHitCount(){
        hitCount += 1;
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
    public long getCreationTime() {
        return creationTime;
    }
    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
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


    public boolean isCacheDefaultLifespan() {
        return cacheDefaultLifespan;
    }


    public void setCacheDefaultLifespan(boolean cacheDefaultLifespan) {
        this.cacheDefaultLifespan = cacheDefaultLifespan;
    }


    public void setValue(Object value) {
        this.value = value;
    }



    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }

    /**
     * 判断元素 是否过期
     * @return
     */
    public boolean isExpired() {
        if (isEternal()) {//该元素闲置时间和生命周期都是0，也就是刚创建的 不需要进行检查了直接返回
            return false;
        }
        // 获得过期时间
        long expirationTime = getExpirationTime();
        long now = System.currentTimeMillis();
        return now > expirationTime;
    }
    // 是否是不会过期，
    public boolean isEternal() {//该元素闲置时间和生命周期都是0，也就是刚创建的 不需要进行检查了直接返回
        return (0 == timeToIdle) && (0 == timeToLive);
    }

    // 计算过期时间
    public long getExpirationTime() {
        if (isEternal()) { //刚创建的返回最大生命时间
            return Long.MAX_VALUE;
        }
        // 存活时间
        long expirationTime = 0;
        // 到期时间=创建时间+生命周期
        long ttlExpiry = creationTime + getTimeToLive() * ONE_SECOND;
        //比较创建时间和最后使用时间
        long mostRecentTime = Math.max(creationTime, lastAccessTime);
        //检查闲置时间：
        // 如果仅仅设置了timeToLive（生命周期），那么时间以 timeToLive的计算为准
        // 如果仅仅设置了 timeToIdle（最大闲置时间），那么时间以timeToIdle 的计算为准
        long ttiExpiry = mostRecentTime + getTimeToIdle() * ONE_SECOND;
        // 如果仅仅设置了timeToLive，那么时间以 timeToLive的计算为准
        if (getTimeToLive() != 0 && (getTimeToIdle() == 0 || lastAccessTime == 0)) {
            expirationTime = ttlExpiry;
        } else if (getTimeToLive() == 0) {
            // 如果仅仅设置了 timeToIdle，那么时间以timeToIdle 的计算为准
            expirationTime = ttiExpiry;
        } else {
            // 如果两种都设置了，那么取小的一个为准
            expirationTime = Math.min(ttlExpiry, ttiExpiry);
        }
        return expirationTime;
    }

    // 刷新最后一次使用时间
    public void refreshLastAccessTime(){
        lastAccessTime = System.currentTimeMillis();
    }

}
